package br.com.amaxzon.model;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class Carrinho {
    private List<Produto> produtosNoCarrinho;
    private BigDecimal totalCompra;

    public List<Produto> getProdutosNoCarrinho() {
        return produtosNoCarrinho;
    }

    public BigDecimal getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(BigDecimal totalCompra) {
        this.totalCompra = totalCompra;
    }
}
