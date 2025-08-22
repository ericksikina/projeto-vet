package com.bellapet.produtoCarrinho.service;

import com.bellapet.carrinho.persistence.entity.Carrinho;
import com.bellapet.produto.persistence.entity.Produto;
import com.bellapet.produtoCarrinho.persistence.entity.ProdutoCarrinho;
import com.bellapet.produtoCarrinho.persistence.repository.ProdutoCarrinhoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProdutoCarrinhoService {
    ProdutoCarrinhoRepository produtoCarrinhoRepository;

    public void removerProduto(Long id){
        this.produtoCarrinhoRepository.deleteById(id);
    }

    public Optional<ProdutoCarrinho> buscarPorProdutoECarrinho(Produto produto, Carrinho carrinho){
        return this.produtoCarrinhoRepository.findByProdutoAndCarrinho(produto, carrinho);
    }
}
