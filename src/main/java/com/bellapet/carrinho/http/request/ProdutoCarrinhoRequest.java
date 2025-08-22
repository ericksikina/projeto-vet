package com.bellapet.carrinho.http.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ProdutoCarrinhoRequest(
        @NotBlank(message = "O id o produto não pode ser nulo ou vazio!")
        @Min(value = 0, message = "O id do produto não pode ser 0!")
        Long idProduto,
        @NotBlank(message = "A quantidade do produto não pode ser nulo ou vazio!")
        @Min(value = 0, message = "A quantidade do produto não pode ser 0!")
        int qtde) {
}
