package com.bellapet.horario.http.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record HorarioRequest(
        @JsonFormat(pattern = "HH:mm:ss")
        @Schema(type = "string", example = "12:12:12", description = "Formato HH:mm:ss")
        @NotNull(message = "O horário não pode ser nulo!")
        LocalTime horario) {
}
