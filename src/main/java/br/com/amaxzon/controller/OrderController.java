package br.com.amaxzon.controller;

import br.com.amaxzon.model.Order;
import br.com.amaxzon.model.Produto;
import br.com.amaxzon.model.Usuario;
import br.com.amaxzon.repository.OrderRepository;
import br.com.amaxzon.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Set;

@RequestMapping("/pedidos")
@Controller
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/listar")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("pedidos");
        mv.addObject("produtos", produtoRepository.findAll());
        mv.addObject("pedidos", orderRepository.findAll());
        return mv;
    }

    @PostMapping("/salvar")
    public ModelAndView salvarPedido(@RequestParam Usuario usuario, @RequestParam Set<Produto> produtos) {
        Order pedido = new Order();

        pedido.setUsuario(usuario);
        pedido.setProduto(produtos);

        BigDecimal total = new BigDecimal("0.0");
        BigDecimal valor;
        for (Produto produto : produtos) {
            valor = new BigDecimal(produto.getPreco());
            total.add(valor);
        }

        pedido.setTotal(total);

        orderRepository.save(pedido);

        return new ModelAndView("redirect:/pedidos/listar");
    }


    @RequestMapping("/excluir/{id}")
    public ModelAndView removePedido(@RequestParam Long Id) {
        orderRepository.deleteById(Id);
        return new ModelAndView("redirect:/pedidos/listar");

    }
}