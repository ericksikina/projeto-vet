package com.bellapet.agendamento.persistence.repository;

import com.bellapet.agendamento.persistence.entity.Agendamento;
import com.bellapet.cliente.persistence.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    List<Agendamento> findAllByCliente(Cliente cliente);
    Optional<Agendamento> findByIdAndCliente(Long id, Cliente cliente);
    List<Agendamento> findAllByData(LocalDate data);
}
