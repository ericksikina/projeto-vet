package com.bellapet.tipoProduto.persistence.repository;

import com.bellapet.tipoProduto.persistence.entity.TipoProduto;
import com.bellapet.utils.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoProdutoRepository extends JpaRepository<TipoProduto, Long> {
    List<TipoProduto> findAllByStatus(Status status);
}
