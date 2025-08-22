package com.bellapet.servico.http.response;

import com.bellapet.utils.enums.Status;

import java.math.BigDecimal;

public record ServicoResponse(Long id, String nome, String descricao, BigDecimal preco, Status status) {
}
