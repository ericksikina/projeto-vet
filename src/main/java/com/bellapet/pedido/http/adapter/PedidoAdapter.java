package com.bellapet.pedido.http.adapter;

import com.bellapet.cliente.persistence.entity.Cliente;
import com.bellapet.pagamento.persistence.model.Pagamento;
import com.bellapet.pedido.http.response.PedidoResponse;
import com.bellapet.pedido.http.response.ResumoPedidoResponse;
import com.bellapet.pedido.persistence.entity.Pedido;
import com.bellapet.pedido.persistence.entity.enums.StatusPedido;
import com.bellapet.produtoPedido.http.adapter.ProdutoPedidoAdapter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PedidoAdapter {

    public static PedidoResponse toResponse(Pedido pedido) {
        return new PedidoResponse(pedido.getId(), pedido.getDataHora(), pedido.getStatusPedido(),
                ProdutoPedidoAdapter.toResponseList(pedido.getListaDeProdutos()));
    }

    public static ResumoPedidoResponse toResumoResponse(Pedido pedido) {
        return new ResumoPedidoResponse(pedido.getId(), pedido.getDataHora(), pedido.getStatusPedido(),
                pedido.getCliente().getNome(), pedido.getTotal());
    }

    public static List<ResumoPedidoResponse> toResumoResponseList(List<Pedido> listaDePedidos) {
        return listaDePedidos.stream()
                .map(PedidoAdapter::toResumoResponse)
                .collect(Collectors.toList());
    }

    public static Pedido toEntity(Pedido pedido, Pagamento pagamento, Cliente cliente) {
        pedido.setCliente(cliente);
        pedido.setDataHora(LocalDateTime.now());
        pedido.setStatusPedido(StatusPedido.REALIZADO);
        pedido.setPagamento(pagamento);

        return pedido;
    }
}
