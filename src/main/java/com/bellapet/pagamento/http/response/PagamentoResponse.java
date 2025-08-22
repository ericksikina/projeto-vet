package com.bellapet.pagamento.http.response;

import com.bellapet.pagamento.persistence.model.enums.TipoPagamento;

import java.time.LocalDateTime;

public record PagamentoResponse (LocalDateTime data, TipoPagamento tipo) {
}
