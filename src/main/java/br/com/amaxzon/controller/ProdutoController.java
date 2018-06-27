package br.com.amaxzon.controller;

import br.com.amaxzon.model.Produto;
import br.com.amaxzon.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @GetMapping("/loja")
    public ModelAndView produtosLoja(){
        List<Produto> produtos = produtoService.listarProdutos();
        ModelAndView mv = new ModelAndView("produtos-loja");
        mv.addObject("todosOsProdutos", produtos);
        return mv;
    }

    /*Esse método faz a mesma coisa que o de cima, mas ele vai ser utilizado a fins de CRUD, o contrário do outro*/
    @RequestMapping("/gerenciar")
    public ModelAndView listarProdutos(){
        List<Produto> produtos = produtoService.listarProdutos();
        ModelAndView mv = new ModelAndView("gerenciar-produtos");
        mv.addObject("todosOsProdutos", produtos);
        mv.addObject("novoProduto", new Produto());
        return mv;
    }

    @PostMapping("/salvar")
    public ModelAndView salvarProduto(Produto produto, @RequestParam(value = "imagem")MultipartFile imagem){
        produtoService.adicionarProduto(produto,imagem);
        return new ModelAndView("redirect:/produto/gerenciar");
    }

    @RequestMapping("{id}")
    public ModelAndView atualizarProduto(@PathVariable Long id){
        Produto produto = produtoService.buscarPorId(id);
        ModelAndView mv = new ModelAndView("redirect:/produto/gerenciar");
        mv.addObject("produto",produto);
        return mv;
    }

    @RequestMapping("/excluir/{id}")
    public ModelAndView excluirProduto(@PathVariable Long id) {
        produtoService.removerProduto(id);
        return new ModelAndView("redirect:/produto/gerenciar");
    }

}
