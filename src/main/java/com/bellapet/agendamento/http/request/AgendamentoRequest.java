package com.bellapet.agendamento.http.request;

import com.bellapet.servico.persistence.entity.Servico;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record AgendamentoRequest(LocalDate data, LocalTime hora, List<Servico> listaDeServico) {
}
