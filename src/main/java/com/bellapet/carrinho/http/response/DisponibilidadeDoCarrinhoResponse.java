package com.bellapet.carrinho.http.response;

import com.bellapet.produto.http.response.ProdutoResponse;

import java.util.List;

public record DisponibilidadeDoCarrinhoResponse(List<ProdutoResponse> produtosIndisponiveis, List<ProdutoResponse> produtosComEstqueIndisponivel) {
}
