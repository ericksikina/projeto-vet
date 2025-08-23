package com.bellapet.cliente.persistence.repository;

import com.bellapet.auth.persistence.entity.Auth;
import com.bellapet.cliente.persistence.entity.Cliente;
import com.bellapet.utils.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findByAuth(Auth auth);
    Optional<Cliente> findByCpf(String cpf);
    List<Cliente> findALlByStatus(Status status);
}
