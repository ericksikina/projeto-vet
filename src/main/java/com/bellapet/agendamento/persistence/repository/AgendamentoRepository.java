package com.bellapet.agendamento.persistence.repository;

import com.bellapet.agendamento.persistence.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {
}
