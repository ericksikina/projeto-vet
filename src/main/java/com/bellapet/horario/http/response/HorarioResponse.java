package com.bellapet.horario.http.response;

import com.bellapet.utils.enums.Status;

import java.time.LocalTime;

public record HorarioResponse (Long id, LocalTime horario, Status status) {
}
