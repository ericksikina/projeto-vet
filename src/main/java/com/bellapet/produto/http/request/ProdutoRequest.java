package com.bellapet.produto.http.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProdutoRequest(
        @NotBlank(message = "O nome não pode ser nulo ou vazio!")
        @Size(max = 100, message = "O nome deve ter no máximo {max} caracteres!")
        String nome,

        @NotNull(message = "O preço não pode ser nulo!")
        @Min(value = 0, message = "O preço não pode ser negativo!")
        BigDecimal preco,

        @NotNull(message = "A quantidade em estoque não pode ser nula!")
        @Min(value = 0, message = "A quantidade em estoque não pode ser negativa!")
        Integer qtdeEstoque,

        @NotNull(message = "A quantidade mínima em estoque não pode ser nula!")
        @Min(value = 0, message = "A quantidade mínima em estoque não pode ser negativa!")
        Integer qtdeMinima,

        @NotNull(message = "O id do tipo do produto não pode ser nulo!")
        @Min(value = 1, message = "O id do tipo do produto deve ser maior que 0!")
        Long idTipoProduto) {
}
