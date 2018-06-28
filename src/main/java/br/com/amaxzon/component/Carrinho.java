package br.com.amaxzon.component;

import br.com.amaxzon.model.Produto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Component
@SessionScope
public class Carrinho {

    private List<Produto> produtosNoCarrinho;

    public List<Produto> listarProdutos() {
        return produtosNoCarrinho;
    }

    public void setProdutosNoCarrinho(List<Produto> produtosNoCarrinho) {
        this.produtosNoCarrinho = produtosNoCarrinho;
    }

    public void adiciona(Produto produto){
        Produto produto1 = new Produto();
        produto1 = produto;
        List<Produto> produtos_aux = new ArrayList<>();

        if(listarProdutos() != null){
            for (Produto p:produtosNoCarrinho) {
                produtos_aux.add(p);
            }
        }

        produtos_aux.add(produto);
        setProdutosNoCarrinho(produtos_aux);
    }

    public void remove(Produto produto){
        Produto produto1 = new Produto();
        produto1 = produto;

        List<Produto> nova = new ArrayList<>();

        for (Produto p: produtosNoCarrinho) {
            if(!(p.getId() == produto1.getId()))
                nova.add(p);
        }

        setProdutosNoCarrinho(nova);
    }

    public String getTotalCompra() {
        Float total = new Float(0);
        Float valor;

        if(listarProdutos() != null){
            for (Produto produto:produtosNoCarrinho) {
                valor = Float.parseFloat(produto.getPreco());      //Converte para BigDecimal
                total += valor;                               //Soma o valor ao total
            }
        }

        return total.toString();                            //Retorna como texto
    }



    public int getQtd(){
        int qtd = 0;

        if(listarProdutos() != null){
            qtd = produtosNoCarrinho.size();
        }

        return qtd;
    }
}
