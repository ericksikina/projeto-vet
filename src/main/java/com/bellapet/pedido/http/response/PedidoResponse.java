package com.bellapet.pedido.http.response;

import com.bellapet.cliente.http.response.ClienteResponse;
import com.bellapet.pagamento.http.response.PagamentoResponse;
import com.bellapet.produtoPedido.http.response.ProdutoPedidoResponse;
import com.bellapet.pedido.persistence.entity.enums.StatusPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponse(Long id, LocalDateTime dataHora, StatusPedido status, BigDecimal total,
                             PagamentoResponse pagamento, ClienteResponse cliente, List<ProdutoPedidoResponse> listaDeProdutos) {
}
