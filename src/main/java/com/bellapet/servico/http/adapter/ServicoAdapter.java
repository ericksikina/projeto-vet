package com.bellapet.servico.http.adapter;

import com.bellapet.servico.http.request.ServicoRequest;
import com.bellapet.servico.http.response.ServicoResponse;
import com.bellapet.servico.persistence.entity.Servico;
import com.bellapet.utils.enums.Status;

import java.util.List;
import java.util.stream.Collectors;

public class ServicoAdapter {
    public static ServicoResponse toResponse(Servico servico) {
        return new ServicoResponse(servico.getId(), servico.getNome(), servico.getDescricao(), servico.getPreco(),
                servico.getStatus());
    }

    public static List<ServicoResponse> toResponseList(List<Servico> listaServico) {
        return listaServico.stream()
                .map(ServicoAdapter::toResponse)
                .collect(Collectors.toList());
    }

    public static Servico toServico(Servico servico, ServicoRequest servicoRequest) {
        servico.setNome(servicoRequest.nome());
        servico.setDescricao(servicoRequest.descricao());
        servico.setPreco(servicoRequest.preco());
        servico.setStatus(Status.ATIVO);
        return servico;
    }
}
