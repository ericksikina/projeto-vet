package com.bellapet.endereco.persistence.repository;

import com.bellapet.cliente.persistence.entity.Cliente;
import com.bellapet.endereco.persistence.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnderecoRespository extends JpaRepository<Endereco, Long> {
    Optional<Endereco> findAllByCliente(Cliente cliente);
}
