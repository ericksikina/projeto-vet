package com.bellapet.animal.persistence.repository;

import com.bellapet.animal.persistence.entity.Animal;
import com.bellapet.cliente.persistence.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findAllByCliente(Cliente cliente);
}
