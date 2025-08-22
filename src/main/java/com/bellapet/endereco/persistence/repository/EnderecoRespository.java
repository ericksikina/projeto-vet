package com.bellapet.endereco.persistence.repository;

import com.bellapet.endereco.persistence.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRespository extends JpaRepository<Endereco, Long> {
}
