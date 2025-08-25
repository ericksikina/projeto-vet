package com.bellapet.agendamento.http.request;

import java.time.LocalDateTime;

public record AgendamentoRequest(LocalDateTime dataHora) {
}
