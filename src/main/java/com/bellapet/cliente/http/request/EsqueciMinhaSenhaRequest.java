package com.bellapet.cliente.http.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EsqueciMinhaSenhaRequest(
        @Email(message = "Informe um e-mail válido!")
        @NotBlank(message = "O e-mail não pode ser nulo ou vazio!")
        @Size(max = 100, message = "O e-mail deve ter no máximo {max} caracteres!")
        String email,
        @NotBlank(message = "A senha não pode ser nula ou vazia!")
        @Size(max = 100, message = "A senha deve ter no máximo {max} caracteres!")
        String senha) {
}
