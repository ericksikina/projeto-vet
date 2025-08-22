package com.bellapet.produto.http.response;

import com.bellapet.tipoProduto.http.response.TipoProdutoResponse;
import com.bellapet.utils.enums.Status;

import java.math.BigDecimal;

public record ProdutoResponse(Long id, String nome, BigDecimal preco, int qtdeEstoque, int qtdeMinima,
                              TipoProdutoResponse tipoProdutoResponse, Status status) {
}
