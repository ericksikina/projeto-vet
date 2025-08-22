package com.bellapet.servico.http.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ServicoRequest(
        @NotBlank(message = "O serviço não pode ser nulo ou vazio!")
        @Size(max = 100, message = "O serviço deve ter no máximo {max} caracteres!")
        String nome,
        @NotBlank(message = "A descrição do serviço não pode ser nula ou vazia!")
        @Size(max = 200, message = "A descrição do serviço deve ter no máximo {max} caracteres!")
        String descricao,
        @NotBlank(message = "O preço não pode ser nulo ou vazio!")
        BigDecimal preco) {
}
