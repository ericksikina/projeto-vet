package com.bellapet.horario.http.adapter;

import com.bellapet.horario.http.request.HorarioRequest;
import com.bellapet.horario.http.response.HorarioResponse;
import com.bellapet.horario.persistence.entity.Horario;
import com.bellapet.utils.enums.Status;

import java.util.List;
import java.util.stream.Collectors;

public class HorarioAdapter {
    public static HorarioResponse toResponse(Horario horario) {
        return new HorarioResponse(horario.getId(), horario.getHorario(), horario.getStatus());
    }

    public static List<HorarioResponse> toResponseList(List<Horario> listaHorario) {
        return listaHorario.stream()
                .map(HorarioAdapter::toResponse)
                .collect(Collectors.toList());
    }

    public static Horario toHorario(Horario horario, HorarioRequest horarioRequest) {
        horario.setHorario(horarioRequest.horario());
        horario.setStatus(Status.ATIVO);
        return horario;
    }
}
