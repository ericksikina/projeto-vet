package com.bellapet.agendamento.http.response;

import com.bellapet.agendamento.persistence.entity.enums.StatusAgendamento;
import com.bellapet.cliente.http.response.ResumoClienteResponse;
import com.bellapet.servico.http.response.ServicoResponse;

import java.time.LocalDateTime;
import java.util.List;

public record AgendamentoResponse(Long id, LocalDateTime dataHora, StatusAgendamento status,
                                  ResumoClienteResponse cliente, List<ServicoResponse> listaDeServicos) {
}
