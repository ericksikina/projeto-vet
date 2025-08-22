package com.bellapet.tipoProduto.http.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TipoProdutoRequest(
        @NotBlank(message = "O nome não pode ser nulo ou vazio!")
        @Size(max = 100, message = "O nome deve ter no máximo {max} caracteres!")
        String nome,
        @NotBlank(message = "A descrição do tipo do produto não pode ser nula ou vazia!")
        @Size(max = 200, message = "A descrição do tipo do produto deve ter no máximo {max} caracteres!")
        String descricao) {
}
