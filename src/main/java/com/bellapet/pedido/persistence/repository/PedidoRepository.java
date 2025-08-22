package com.bellapet.pedido.persistence.repository;

import com.bellapet.cliente.persistence.entity.Cliente;
import com.bellapet.pedido.persistence.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findAllByCliente(Cliente cliente);
}
