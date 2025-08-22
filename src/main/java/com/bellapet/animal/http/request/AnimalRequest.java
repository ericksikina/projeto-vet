package com.bellapet.animal.http.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AnimalRequest(
        @NotBlank(message = "O nome não pode ser nulo ou vazio!")
        @Size(max = 100, message = "O nome deve ter no máximo {max} caracteres!")
        String nome,
        @NotBlank(message = "A raça não pode ser nulo ou vazio!")
        @Size(max = 100, message = "A raça deve ter no máximo {max} caracteres!")
        String raca,
        @NotBlank(message = "O tipo não pode ser nulo ou vazio!")
        @Size(max = 100, message = "O tipo deve ter no máximo {max} caracteres!")
        String tipo) {
}
