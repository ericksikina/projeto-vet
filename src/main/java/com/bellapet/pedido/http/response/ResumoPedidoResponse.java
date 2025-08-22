package com.bellapet.pedido.http.response;

import com.bellapet.pedido.persistence.entity.enums.StatusPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ResumoPedidoResponse(Long id, LocalDateTime dataHora, StatusPedido statusPedido, String cliente, BigDecimal valorDaVenda) {
}
