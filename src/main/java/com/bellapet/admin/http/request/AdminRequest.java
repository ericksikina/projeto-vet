package com.bellapet.admin.http.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AdminRequest(
        @NotBlank(message = "O nome não pode ser nulo ou vazio!")
        @Size(max = 100, message = "O nome deve ter no máximo {max} caracteres!")
        String nome,
        @NotBlank(message = "O cpf não pode ser nulo ou vazio!")
        @Size(max = 18, min = 14, message = "O cpf deve ter {min} caracateres ou {max} com a pontuação!")
        String cpf,
        @Email(message = "Informe um e-mail válido!")
        @NotBlank(message = "O e-mail não pode ser nulo ou vazio!")
        @Size(max = 100, message = "O e-mail deve ter no máximo {max} caracteres!")
        String login,
        @NotBlank(message = "A senha não pode ser nula ou vazia!")
        @Size(max = 100, message = "A senha deve ter no máximo {max} caracteres!")
        String senha) {
}
