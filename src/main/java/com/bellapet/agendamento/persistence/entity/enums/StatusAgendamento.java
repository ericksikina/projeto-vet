package com.bellapet.agendamento.persistence.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusAgendamento {
    AGENDADO("A", "Agendamento realizado."),
    CANCELADO("C", " Agendamento cancelado."),
    REMARCADO("R", "Agendamento remarcado.");

    private final String value;
    private final String descricao;
}
