package com.bellapet.produtoPedido.http.adapter;

import com.bellapet.produto.http.adapter.ProdutoAdapter;
import com.bellapet.produtoPedido.http.response.ProdutoPedidoResponse;
import com.bellapet.produtoPedido.persistence.entity.ProdutoPedido;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoPedidoAdapter {
    public static ProdutoPedidoResponse toResponse(ProdutoPedido produtoPedido) {
        return new ProdutoPedidoResponse(ProdutoAdapter.toResponse(produtoPedido.getProduto()), produtoPedido.getQtde());
    }

    public static List<ProdutoPedidoResponse> toResponseList(List<ProdutoPedido> listaDeProdutos) {
        return listaDeProdutos.stream()
                .map(ProdutoPedidoAdapter::toResponse)
                .collect(Collectors.toList());
    }
}
