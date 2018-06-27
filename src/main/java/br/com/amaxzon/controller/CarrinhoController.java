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
        mv.addObject("produtosNoCarrinho", this.carrinho.getProdutosNoCarrinho());
        mv.addObject("totalCompra", this.carrinho.getTotalCompra());
        return mv;
    }

    @GetMapping("/adicionar/{id}")
    public ModelAndView adicionarNoCarrinho(@PathVariable Long id) {
        this.carrinho.getProdutosNoCarrinho().add(prodService.buscarPorId(id));
        this.carrinho.setTotalCompra(this.carrinho.getTotalCompra().add(prodService.buscarPorId(id).getPreco()));
        return new ModelAndView("redirect:/produtos/loja");
    }

    @RequestMapping("/remover/{id}")
    public ModelAndView removerProdutoCarrinho(@PathVariable Long id) {
        Produto p = this.prodService.buscarPorId(id);
        for(Produto pd : this.carrinho.getProdutosNoCarrinho()) {
            if(pd.getId().equals(p.getId())) {
                if(this.carrinho.getProdutosNoCarrinho().remove(this.carrinho.getProdutosNoCarrinho().indexOf(pd)) != null) {
                    this.carrinho.setTotalCompra(this.carrinho.getTotalCompra().subtract(p.getPreco()));
                }
            }
        }
        return new ModelAndView("carrinho");
    }
}
