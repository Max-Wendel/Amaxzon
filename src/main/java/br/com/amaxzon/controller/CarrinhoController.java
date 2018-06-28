package br.com.amaxzon.controller;

import br.com.amaxzon.component.Carrinho;
import br.com.amaxzon.model.Produto;
import br.com.amaxzon.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {

    @Autowired
    private Carrinho carrinho;
    @Autowired
    private ProdutoService prodService;

    @RequestMapping("/listar")
    public ModelAndView listarProdutosNoCarrinho() {
        ModelAndView mv = new ModelAndView("carrinho");

        List<Produto> produtos = carrinho.listarProdutos();
        String total = carrinho.getTotalCompra();
        int quantidade = carrinho.getQtd();

        mv.addObject("produtos", produtos);
        mv.addObject("total", total);
        mv.addObject("quantidade", quantidade);

        return mv;
    }

    //Operações com o preço são feitas convertendo a string do pruduto para BigDecimal
    @GetMapping("/adicionar/{id}")
    public ModelAndView adicionarNoCarrinho(@PathVariable Long id, Model model) {
        Produto produto = prodService.buscarPorId(id);
        System.out.println("O Produto "+produto.getNome()+" foi adicionado ao carrinho.");
        carrinho.adiciona(produto);
        return new ModelAndView("redirect:/carrinho/listar");
    }

    @RequestMapping("/remover/{id}")
    public ModelAndView removerProdutoCarrinho(@PathVariable Long id) {
        Produto produto = prodService.buscarPorId(id);
        System.out.println("O Produto "+produto.getNome()+" foi removido do carrinho.");
        carrinho.remove(produto);
        return new ModelAndView("redirect:/carrinho/listar");
    }
}
