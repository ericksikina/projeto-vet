package com.bellapet.pagamento.persistence.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoPagamento {
    PIX("P"),
    CREDITO("C"),
    DEBITO("D");

    private final String value;
}
