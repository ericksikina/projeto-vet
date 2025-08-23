package com.bellapet.produto.persistence.repository;

import com.bellapet.produto.persistence.entity.Produto;
import com.bellapet.utils.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findAllByStatus(Status status);
    @Query("SELECT p FROM Produto p WHERE p.qtdeEstoque < p.qtdeMinima")
    List<Produto> findProdutosComEstoqueAbaixoDoMinimo();

}
