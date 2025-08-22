package com.bellapet.pedido.persistence.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusPedido {
    REALIZADO("R", "foi realizado."),
    PREPARACAO("P", " está sendo preparado."),
    ENVIADO("E", "foi enviado."),
    CANCELADO("C", "foi cancelado."),
    FINALIZADO("F", "foi entregue.");

    private final String value;
    private final String descricao;
}
