package com.bellapet.animal.persistence.repository;

import com.bellapet.animal.persistence.entity.Animal;
import com.bellapet.cliente.persistence.entity.Cliente;
import com.bellapet.utils.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findAllByClienteAndStatus(Cliente cliente, Status status);
}
