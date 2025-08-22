package com.bellapet.pedido.http.response;

import com.bellapet.produtoPedido.http.response.ProdutoPedidoResponse;
import com.bellapet.pedido.persistence.entity.enums.StatusPedido;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponse(Long id, LocalDateTime dataHora, StatusPedido statusPedido,
                             List<ProdutoPedidoResponse> listaDeProdutos) {
}
