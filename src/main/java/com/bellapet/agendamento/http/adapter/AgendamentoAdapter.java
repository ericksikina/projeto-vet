package com.bellapet.agendamento.http.adapter;

import com.bellapet.agendamento.http.request.AgendamentoRequest;
import com.bellapet.agendamento.http.request.RemarcarAgendamentRequest;
import com.bellapet.agendamento.http.response.AgendamentoResponse;
import com.bellapet.agendamento.persistence.entity.Agendamento;
import com.bellapet.agendamento.persistence.entity.enums.StatusAgendamento;
import com.bellapet.cliente.http.adapter.ClienteAdapter;
import com.bellapet.cliente.persistence.entity.Cliente;
import com.bellapet.servico.http.adapter.ServicoAdapter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class AgendamentoAdapter {
    public static AgendamentoResponse toResponse(Agendamento agendamento) {
        return  new AgendamentoResponse(agendamento.getId(), LocalDateTime.of(agendamento.getData(),agendamento.getHora()),
                agendamento.getStatus(), agendamento.getTotal(), ClienteAdapter.toResumoResponse(agendamento.getCliente()),
                agendamento.getListaDeServicos().stream()
                        .map(agendamentoServico ->
                                ServicoAdapter.toResponse(agendamentoServico.getServico()))
                        .collect(Collectors.toList()));
    }

    public static List<AgendamentoResponse> toResponseList(List<Agendamento> listaDeAgendamento) {
        return listaDeAgendamento.stream()
                .map(AgendamentoAdapter::toResponse)
                .collect(Collectors.toList());
    }

    public static Agendamento toEntity(Agendamento agendamento, AgendamentoRequest agendamentoRequest, Cliente cliente,
                                       BigDecimal total) {
        agendamento.setData(agendamentoRequest.data());
        agendamento.setHora(agendamentoRequest.hora());
        agendamento.setStatus(StatusAgendamento.AGENDADO);
        agendamento.setCliente(cliente);
        agendamento.setTotal(total);

        return agendamento;
    }

    public static Agendamento toEntity(Agendamento agendamento, RemarcarAgendamentRequest remarcarAgendamentRequest) {
        agendamento.setData(remarcarAgendamentRequest.data());
        agendamento.setHora(remarcarAgendamentRequest.hora());
        agendamento.setStatus(StatusAgendamento.REMARCADO);

        return agendamento;
    }
}
