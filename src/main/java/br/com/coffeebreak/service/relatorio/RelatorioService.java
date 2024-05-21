package br.com.coffeebreak.service.relatorio;

import br.com.coffeebreak.model.ItemProduto.ItemProduto;
import br.com.coffeebreak.model.produto.Produto;
import br.com.coffeebreak.model.relatorio.Relatorio;
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
import java.nio.file.Paths;
import java.util.List;

@Service
public class RelatorioService {

    @Autowired
    private RelatorioRepository relatorioRepository;


    public void exportPedidosToExcel(TipoRelatorio tipoRelatorio) throws IOException {
        List<String> nomes = relatorioRepository.getAllProdutosNames();


        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Relatorio");

        // Criar linha de cabeçalho
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Nome do Produto");
        headerRow.createCell(1).setCellValue("Preco");
        headerRow.createCell(1).setCellValue("Quantidade vendida");
        headerRow.createCell(1).setCellValue("Valor vendido");

        for(String nome : nomes){
            double totalVendido = 0;
            int qtdVendida = 0;
            Produto p = relatorioRepository.getByName(nome);
            String pID = p.getId();
            List<ItemProduto> lista = relatorioRepository.getAllByPeriod(pID,tipoRelatorio.calcular());

            for(ItemProduto iP: lista){
                totalVendido += iP.getQuantidade() * iP.getPrecoProduto();
                qtdVendida += iP.getQuantidade();
            }

            int rowIndex = 1;

            Row row = sheet.createRow(rowIndex++);
            //Nome
            row.createCell(0).setCellValue((String) p.getNome());
            //Valor
            row.createCell(1).setCellValue((double) p.getPreco());
            //Quantidade vendida
            row.createCell(2).setCellValue((int) qtdVendida);
            //Valor total de vendas
            row.createCell(3).setCellValue((double) totalVendido);

            Relatorio relatorio = new Relatorio();
            relatorio.setProduto(p.getNome());
            relatorio.setTipoRelatorio(br.com.coffeebreak.enums.TipoRelatorio.SEMANAL);
            relatorio.setPreco(totalVendido);
            relatorio.setQtdPedidosRealizados(qtdVendida);

            relatorioRepository.save(relatorio);
        }

        String userHome = System.getProperty("user.home");
        String filePath = Paths.get(userHome, "Documentos", "pedidos.xlsx").toString();

        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            workbook.write(outputStream);
        } catch (Exception e){
            System.out.println(e);
        } finally {

            workbook.close();
        }

    }




}
