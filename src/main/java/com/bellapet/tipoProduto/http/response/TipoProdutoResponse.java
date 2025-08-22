package com.bellapet.tipoProduto.http.response;

import com.bellapet.utils.enums.Status;

public record TipoProdutoResponse(Long id, String nome, String descricao, Status status) {
}
