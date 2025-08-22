package com.bellapet.cliente.http.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmailRecuperacaoRequest(
        @Email(message = "Informe um e-mail válido!")
        @NotBlank(message = "O e-mail não pode ser nulo ou vazio!")
        @Size(max = 100, message = "O e-mail deve ter no máximo {max} caracteres!")
        String email) {
}
