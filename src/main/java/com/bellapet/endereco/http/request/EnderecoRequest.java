package com.bellapet.endereco.http.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EnderecoRequest(
        @NotBlank(message = "O logradouro não pode ser nulo ou vazio!")
        @Size(max = 100, message = "O logradouro deve ter no máximo {max} caracteres!")
        String logradouro,
        @NotBlank(message = "O número não pode ser nulo ou vazio!")
        @Size(max = 10, message = "O número deve ter no máximo {max} caracteres!")
        String numero,
        @NotBlank(message = "O bairro não pode ser nulo ou vazio!")
        @Size(max = 50, message = "O bairro deve ter no máximo {max} caracteres!")
        String bairro,
        @NotBlank(message = "O cep não pode ser nulo ou vazio!")
        @Size(max = 12, message = "O cep deve ter no máximo {max} caracteres!")
        String cep,
        @NotBlank(message = "A cidade não pode ser nulo ou vazio!")
        @Size(max = 50, message = "A cidade deve ter no máximo {max} caracteres!")
        String cidade,
        @NotBlank(message = "O estado não pode ser nulo ou vazio!")
        @Size(max = 50, message = "O estado deve ter no máximo {max} caracteres!")
        String estado,
        @Size(max = 100, message = "O complemento deve ter no máximo {max} caracteres!")
        String complemento) {
}
