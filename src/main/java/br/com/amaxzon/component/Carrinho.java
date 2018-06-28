package br.com.amaxzon.component;

import br.com.amaxzon.model.Produto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;


@Component
public class Carrinho {
    private List<Produto> produtosNoCarrinho;

    public List<Produto> getProdutosNoCarrinho() {
        return produtosNoCarrinho;
    }

    public void setProdutosNoCarrinho(List<Produto> produtosNoCarrinho) {
        this.produtosNoCarrinho = produtosNoCarrinho;
    }

    public String getTotalCompra() {
       /* BigDecimal total = new BigDecimal("0.0");
        BigDecimal valor;

        for (Produto produto:produtosNoCarrinho) {
            valor = new BigDecimal(produto.getPreco());     //Converte para BigDecimal
            total.add(valor);                               //Soma o valor ao total
        }*/

        return "0.0";                            //Retorna como texto
    }

    public int getQtd(){
        return this.produtosNoCarrinho.size();
    }
}
