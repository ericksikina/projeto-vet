package com.bellapet.agendamento.http.adapter;

import com.bellapet.agendamento.http.request.AgendamentoRequest;
import com.bellapet.agendamento.http.response.AgendamentoResponse;
import com.bellapet.agendamento.persistence.entity.Agendamento;
import com.bellapet.agendamento.persistence.entity.enums.StatusAgendamento;
import com.bellapet.cliente.http.adapter.ClienteAdapter;
import com.bellapet.cliente.persistence.entity.Cliente;

import java.util.List;
import java.util.stream.Collectors;

public class AgendamentoAdapter {
    public static AgendamentoResponse toResponse(Agendamento agendamento) {
        return  new AgendamentoResponse(agendamento.getId(), agendamento.getDataHora(), agendamento.getStatus(),
                ClienteAdapter.toResumoResponse(agendamento.getCliente()));
    }

    public static List<AgendamentoResponse> toResponseList(List<Agendamento> listaDeAgendamento) {
        return listaDeAgendamento.stream()
                .map(AgendamentoAdapter::toResponse)
                .collect(Collectors.toList());
    }

    public static Agendamento toEntity(Agendamento agendamento, AgendamentoRequest agendamentoRequest, Cliente cliente) {
        agendamento.setDataHora(agendamentoRequest.dataHora());
        agendamento.setStatus(StatusAgendamento.AGENDADO);
        agendamento.setCliente(cliente);

        return agendamento;
    }

    public static Agendamento toEntity(Agendamento agendamento, AgendamentoRequest agendamentoRequest) {
        agendamento.setDataHora(agendamentoRequest.dataHora());
        agendamento.setStatus(StatusAgendamento.REMARCADO);

        return agendamento;
    }
}
