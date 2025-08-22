package com.bellapet.produtoCarrinho.persistence.repository;

import com.bellapet.carrinho.persistence.entity.Carrinho;
import com.bellapet.produto.persistence.entity.Produto;
import com.bellapet.produtoCarrinho.persistence.entity.ProdutoCarrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoCarrinhoRepository extends JpaRepository<ProdutoCarrinho, Long> {
    Optional<ProdutoCarrinho> findByProdutoAndCarrinho(Produto produto, Carrinho carrinho);
    void deleteAllByCarrinho(Carrinho carrinho);
    void deleteByProduto(Produto produto);
}
