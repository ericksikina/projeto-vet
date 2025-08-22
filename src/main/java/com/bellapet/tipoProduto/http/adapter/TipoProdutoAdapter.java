package com.bellapet.tipoProduto.http.adapter;

import com.bellapet.tipoProduto.http.request.TipoProdutoRequest;
import com.bellapet.tipoProduto.http.response.TipoProdutoResponse;
import com.bellapet.tipoProduto.persistence.entity.TipoProduto;
import com.bellapet.utils.enums.Status;

import java.util.List;
import java.util.stream.Collectors;

public class TipoProdutoAdapter {
    public static TipoProdutoResponse toResponse(TipoProduto tipoProduto) {
        return new TipoProdutoResponse(tipoProduto.getId(), tipoProduto.getNome(), tipoProduto.getDescricao(),
                tipoProduto.getStatus());
    }

    public static List<TipoProdutoResponse> toResponseList(List<TipoProduto> listaTipoProduto) {
        return listaTipoProduto.stream()
                .map(TipoProdutoAdapter::toResponse)
                .collect(Collectors.toList());
    }

    public static TipoProduto toTipoProduto(TipoProduto tipoProduto, TipoProdutoRequest tipoProdutoRequest) {
        tipoProduto.setNome(tipoProdutoRequest.nome());
        tipoProduto.setDescricao(tipoProdutoRequest.descricao());
        tipoProduto.setStatus(Status.ATIVO);
        return tipoProduto;
    }
}
