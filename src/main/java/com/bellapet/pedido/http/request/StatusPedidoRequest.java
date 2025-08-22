package com.bellapet.pedido.http.request;

import com.bellapet.pedido.persistence.entity.enums.StatusPedido;
import jakarta.validation.constraints.NotBlank;

public record StatusPedidoRequest(
        @NotBlank(message = "O status do pedido não pode ser nulo ou vazio!")
        StatusPedido status) {
}
