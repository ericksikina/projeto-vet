package com.bellapet.produtoCarrinho.http.response;

import com.bellapet.produto.http.response.ProdutoResponse;

public record ProdutoCarrinhoResponse(ProdutoResponse produto, int qtde) {
}
