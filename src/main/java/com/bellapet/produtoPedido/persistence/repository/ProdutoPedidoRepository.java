package com.bellapet.produtoPedido.persistence.repository;

import com.bellapet.produtoPedido.persistence.entity.ProdutoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoPedidoRepository extends JpaRepository<ProdutoPedido, Long> {
}
