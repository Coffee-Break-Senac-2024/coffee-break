package br.com.coffeebreak.service.relatorio;

import br.com.coffeebreak.model.ItemProduto.ItemProduto;
import br.com.coffeebreak.model.pedido.Pedido;
import br.com.coffeebreak.repositories.PedidoRepository;
import br.com.coffeebreak.repositories.RelatorioRepository;
import br.com.coffeebreak.service.relatorio.strategy.TipoRelatorio;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

@Service
public class RelatorioService {

    @Autowired
    private RelatorioRepository relatorioRepository;
    @Autowired
    private PedidoRepository pedidoRepository;

    public void exportPedidosToExcel(TipoRelatorio tipoRelatorio) throws IOException {
        List<Pedido> listaPedido = pedidoRepository.getPedidosByTime(tipoRelatorio.calcular(),LocalDateTime.now());
        List<ItemProduto> aux = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Relatorio");

        // Criar linha de cabeçalho
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Nome do Produto");
        headerRow.createCell(1).setCellValue("Preco");
        headerRow.createCell(2).setCellValue("Quantidade vendida");
        headerRow.createCell(3).setCellValue("Valor vendido");

        for(Pedido p : listaPedido){


            List<ItemProduto> lista = relatorioRepository.getAllByPeriod(tipoRelatorio.calcular(), LocalDateTime.now());

            for (ItemProduto iP : lista) {
                boolean found = false;
                for (ItemProduto iPAux : aux) {
                    if (iPAux.getProduto().getNome().equalsIgnoreCase(iP.getProduto().getNome())) {
                        double totalVendido = iPAux.getPrecoProduto() + iP.getPrecoProduto();
                        int qtdVendida = iPAux.getQuantidade() + iP.getQuantidade();
                        iPAux.setQuantidade(qtdVendida);
                        iPAux.setPrecoProduto(totalVendido);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    aux.add(new ItemProduto(p, iP.getProduto(), iP.getQuantidade(), iP.getPrecoProduto()));
                }

            }





        }
        int rowIndex = 1;
        for(ItemProduto iP : aux) {
            Row row = sheet.createRow(rowIndex++);
            //Nome
            row.createCell(0).setCellValue(iP.getProduto().getNome());
            //Valor
            row.createCell(1).setCellValue(iP.getProduto().getPreco());
            //Quantidade vendida
            row.createCell(2).setCellValue(iP.getQuantidade());
            //Valor total de vendas
            row.createCell(3).setCellValue(iP.getQuantidade() * iP.getProduto().getPreco());

        }

        Path pastaRelatorios = Paths.get("C:", "relatorios");
        if (!Files.exists(pastaRelatorios)) {
            Files.createDirectory(pastaRelatorios);
        }

        String baseFileName = "pedidos";
        String fileExtension = ".xlsx";
        Path filePath = pastaRelatorios.resolve(baseFileName + fileExtension);
        int fileIndex = 1;

        // Incrementa o nome do arquivo se ele já existir
        while (Files.exists(filePath)) {
            filePath = pastaRelatorios.resolve(baseFileName + "(" + fileIndex + ")" + fileExtension);
            fileIndex++;
        }

        // Grava o arquivo Excel na pasta criada
        try (FileOutputStream outputStream = new FileOutputStream(filePath.toFile())) {
            workbook.write(outputStream);
        } catch (Exception e) {
            throw new IOException(e);
        } finally {
            workbook.close();
        }
         System.out.println("Relatório gerado com sucesso em: " + filePath);
    }

        public List<TipoRelatorio> getTiposRelatorio(){
            return Arrays.asList(TipoRelatorio.values());
        }

    }






