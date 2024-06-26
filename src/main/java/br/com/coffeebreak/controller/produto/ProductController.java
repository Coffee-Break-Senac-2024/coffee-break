package br.com.coffeebreak.controller.produto;

import br.com.coffeebreak.model.produto.Produto;
import br.com.coffeebreak.service.produto.ProdutoService;
import br.com.coffeebreak.service.stock.EstoqueService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/administrator/products")
public class ProductController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private EstoqueService estoqueService;

    private static String caminhoImagens = "src/main/resources/static/images/produtos/";

    @GetMapping
    @PreAuthorize("!hasAuthority('ATENDENTE')")
    public ModelAndView index( @RequestParam(required = false) String nome,
                               @PageableDefault(size = 6) Pageable pageable){
        ModelAndView mv = new ModelAndView("administrator/product/index");

        Page<Produto> produtos;
        if (nome != null && !nome.isEmpty()) {
            produtos = produtoService.getAllProducts(nome, pageable);
        }else{
            produtos = produtoService.getAllProducts(pageable);
        }

        mv.addObject("produtos", produtos);
        return mv;
    }

    @GetMapping("/create")
    @PreAuthorize("!hasAuthority('ATENDENTE')")
    public ModelAndView create(){
        ModelAndView mv = new ModelAndView("administrator/product/create");
        mv.addObject("ingredientes", this.estoqueService.getAllIngredientes());
        mv.addObject("produto", new Produto());
        return mv;
    }

    @PostMapping("/create")
    @PreAuthorize("!hasAuthority('ATENDENTE')")
    public String createProduct(
                                @Valid @ModelAttribute("produto") Produto produto,
                                BindingResult bindingResult,
                                @RequestParam(name = "ingredientes") List<String> ingredientes,
                                RedirectAttributes redirectAttributes,
                                @RequestParam("file") MultipartFile arquivo,
                                Model model) {

        if (bindingResult.hasErrors()){
            model.addAttribute("ingredientes", this.estoqueService.getAllIngredientes());
            redirectAttributes.addFlashAttribute("error", bindingResult.getAllErrors());
            return "administrator/product/create";
        }


       produto.setEstoqueList(this.produtoService.getIngredientsToBeUsed(ingredientes));

       try {
           if (!arquivo.isEmpty()) {
               byte[] bytes = arquivo.getBytes();
               Path caminho = Paths.get(caminhoImagens+arquivo.getOriginalFilename());
               Files.write(caminho, bytes);

               produto.setNomeImagem(arquivo.getOriginalFilename());
           }

       } catch (Exception e) {
           System.out.println("Erro no upload do arquivo");
       }

        if (this.produtoService.cadastrarProduto(produto)) {
            redirectAttributes.addFlashAttribute("success", "Produto cadastrado com sucesso!");
            return "redirect:/administrator/products";
        }

        redirectAttributes.addFlashAttribute("error", "Não foi possível cadastrar este produto.");
        return "redirect:/administrator/products/create";
    }


    @PostMapping("/delete/{id}")
    @PreAuthorize("!hasAuthority('ATENDENTE')")
    public ModelAndView deleteProduct(@PathVariable String id, RedirectAttributes redirectAttributes) {
        boolean isDeleted = this.produtoService.excluirProduto(id);

        if (isDeleted) {
            redirectAttributes.addFlashAttribute("success", "Produto apagado com sucesso");
            System.out.println("hello");
            return new ModelAndView("redirect:/administrator/products");
        }

        redirectAttributes.addFlashAttribute("error", "Não foi possível deletar este produto.");
        return new ModelAndView("redirect:/administrator/products");
    }

    @GetMapping("/update/{id}")
    @PreAuthorize("!hasAuthority('ATENDENTE')")
    public ModelAndView updateProduct(@PathVariable("id") String id, Produto produto, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView("administrator/product/create");
        try {
            produto = this.produtoService.getProductById(id);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Atualizacao de produto falhou!");
            produto = null;
            return mv;
        }
        mv.addObject("produto", produto);
        mv.addObject("ingredientes", this.estoqueService.getAllIngredientes());
        return mv;

    }

    @PostMapping("/update")
    @PreAuthorize("!hasAuthority('ATENDENTE')")
    public ModelAndView updateProduct(@ModelAttribute("produto") Produto produto, RedirectAttributes redirectAttributes, @RequestParam(name = "ingredientes") List<String> ingredientes, @RequestParam(name = "file") MultipartFile arquivo) {
        ModelAndView mv = new ModelAndView("/administrator/product/create");
        produto.setEstoqueList(this.produtoService.getIngredientsToBeUsed(ingredientes));

        try {
            if (!arquivo.isEmpty()) {
                byte[] bytes = arquivo.getBytes();
                Path caminho = Paths.get(caminhoImagens+arquivo.getOriginalFilename());
                Files.write(caminho, bytes);

                produto.setNomeImagem(arquivo.getOriginalFilename());
            }

        } catch (Exception e) {
            System.out.println("Erro no upload do arquivo");
        }

        try {

            this.produtoService.update(produto);

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Não foi possivel realizar a atualização");
            System.out.println("Esta dando erro");
            return mv;
        }

        redirectAttributes.addFlashAttribute("success", "Atualizado com sucesso.");
        mv.setViewName("redirect:/administrator/products");
        return mv;

    }
}
