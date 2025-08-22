package com.bellapet.carrinho.persistence.repository;

import com.bellapet.carrinho.persistence.entity.Carrinho;
import com.bellapet.cliente.persistence.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
    Optional<Carrinho> findByCliente(Cliente cliente);
}
