package br.com.amaxzon.service;

import br.com.amaxzon.model.Produto;
import br.com.amaxzon.repository.ProdutoRepository;
import br.com.amaxzon.util.AmaxzonFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    public void adicionarProduto(Produto produto, MultipartFile imagem) {
        String caminho = "src/main/resources/static/img/produtos/" + produto.getNome() + ".png";
        AmaxzonFileUtil.salvarImagem(caminho,imagem);
        System.out.println(caminho);

        produtoRepository.save(produto);
    }


    public List<Produto> listarProdutos() {

        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.getOne(id);
    }


    public void removerProduto(Long id) {
        produtoRepository.deleteById(id);

    }
}
