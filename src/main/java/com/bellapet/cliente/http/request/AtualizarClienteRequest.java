package com.bellapet.cliente.http.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AtualizarClienteRequest(
        @NotBlank(message = "O nome não pode ser nulo ou vazio!")
        @Size(max = 100, message = "O nome deve ter no máximo {max} caracteres!")
        String nome,
        @NotBlank(message = "O cnpj não pode ser nulo ou vazio!")
        @Size(max = 11, min = 10, message = "O cpf deve ter {min} caracateres ou {max} com a pontuação!")
        String cpf,
        @NotBlank(message = "O telefone não pode ser nulo ou vazio!")
        @Size(max = 11, min = 10, message = "O telefone deve ter {min} caracateres ou {max} com a pontuação!")
        String telefone,
        @Email(message = "Informe um e-mail válido!")
        @NotBlank(message = "O e-mail não pode ser nulo ou vazio!")
        @Size(max = 100, message = "O e-mail deve ter no máximo {max} caracteres!")
        String email) {
}
