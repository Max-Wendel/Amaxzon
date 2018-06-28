package br.com.amaxzon.controller;

import br.com.amaxzon.component.Carrinho;
import br.com.amaxzon.model.Produto;
import br.com.amaxzon.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

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

        BigDecimal total = new BigDecimal("0.0");
        BigDecimal valor;

        for (Produto produto: this.carrinho.getProdutosNoCarrinho()) {
            valor = new BigDecimal(produto.getPreco());     //Converte para BigDecimal
            total.add(valor);                               //Soma o valor ao total
        }

        mv.addObject("produtos", this.carrinho.getProdutosNoCarrinho());
        mv.addObject("total", total);
        return mv;
    }

    //Operações com o preço são feitas convertendo a string do pruduto para BigDecimal
    @GetMapping("/adicionar/{id}")
    public ModelAndView adicionarNoCarrinho(@PathVariable Long id) {
        //Atualizar Carrinho
        Produto produto = prodService.buscarPorId(id);
        this.carrinho.getProdutosNoCarrinho().add(produto);

        return new ModelAndView("redirect:/produtos/loja");
    }

    @RequestMapping("/remover/{id}")
    public ModelAndView removerProdutoCarrinho(@PathVariable Long id) {
        Produto produto = this.prodService.buscarPorId(id);
        this.carrinho.getProdutosNoCarrinho().remove(produto);
        return new ModelAndView("redirect:/carrinho/listar");
    }
}
