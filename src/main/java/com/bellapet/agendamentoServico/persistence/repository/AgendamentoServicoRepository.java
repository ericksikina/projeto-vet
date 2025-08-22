package com.bellapet.agendamentoServico.persistence.repository;

import com.bellapet.agendamentoServico.persistence.entity.AgendamentoServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoServicoRepository extends JpaRepository<AgendamentoServico, Integer> {
}
