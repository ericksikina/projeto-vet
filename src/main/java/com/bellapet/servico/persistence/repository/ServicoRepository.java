package com.bellapet.servico.persistence.repository;

import com.bellapet.servico.persistence.entity.Servico;
import com.bellapet.utils.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {
    List<Servico> findAllByStatus(Status status);
}
