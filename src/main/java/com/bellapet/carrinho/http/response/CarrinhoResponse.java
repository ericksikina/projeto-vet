package com.bellapet.carrinho.http.response;

import com.bellapet.produtoCarrinho.http.response.ProdutoCarrinhoResponse;

import java.math.BigDecimal;
import java.util.List;

public record CarrinhoResponse(Long id, List<ProdutoCarrinhoResponse> listaDeProduto, BigDecimal total) {
}
