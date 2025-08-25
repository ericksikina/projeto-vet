package com.bellapet.agendamento.http.response;

import com.bellapet.agendamento.persistence.entity.enums.StatusAgendamento;
import com.bellapet.cliente.http.response.ResumoClienteResponse;

import java.time.LocalDateTime;

public record AgendamentoResponse(Long id, LocalDateTime dataHora, StatusAgendamento status, ResumoClienteResponse cliente) {
}
