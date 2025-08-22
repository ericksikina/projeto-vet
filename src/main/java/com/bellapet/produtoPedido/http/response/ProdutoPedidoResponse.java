package com.bellapet.produtoPedido.http.response;

import com.bellapet.produto.http.response.ProdutoResponse;

public record ProdutoPedidoResponse(ProdutoResponse produto, int qtde) {
}
