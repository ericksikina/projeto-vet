package com.bellapet.pagamento.http.request;

import com.bellapet.pagamento.persistence.model.enums.TipoPagamento;
import jakarta.validation.constraints.NotBlank;

public record PagamentoRequest (
        @NotBlank(message = "O tipo do produto não pode ser nulo ou vazio!")
        TipoPagamento tipo) {
}
