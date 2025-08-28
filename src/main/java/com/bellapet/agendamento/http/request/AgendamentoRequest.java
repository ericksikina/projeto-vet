package com.bellapet.agendamento.http.request;

import java.time.LocalDate;
import java.time.LocalTime;

public record AgendamentoRequest(LocalDate data, LocalTime hora) {
}
