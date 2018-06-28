package br.com.amaxzon.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "pedidos")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    private BigDecimal total;

    @ManyToMany
    @JoinTable(name = "produto_order",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "id_produto")})
    private Set<Produto> produto;

    @OneToOne
    Usuario usuario;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Set<Produto> getProduto() {
        return produto;
    }

    public void setProduto(Set<Produto> produto) {
        this.produto = produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
