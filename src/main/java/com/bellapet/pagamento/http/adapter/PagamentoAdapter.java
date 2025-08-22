package com.bellapet.pagamento.http.adapter;

import com.bellapet.pagamento.http.request.PagamentoRequest;
import com.bellapet.pagamento.http.response.PagamentoResponse;
import com.bellapet.pagamento.persistence.model.Pagamento;

import java.time.LocalDateTime;

public class PagamentoAdapter {
    public static PagamentoResponse toResponse(Pagamento pagamento) {
        return new PagamentoResponse(pagamento.getDataHora(), pagamento.getTipoPagamento());
    }

    public static Pagamento toPagamento(Pagamento pagamento, PagamentoRequest pagamentoRequest) {
        pagamento.setTipoPagamento(pagamentoRequest.tipo());
        pagamento.setDataHora(LocalDateTime.now());

        return pagamento;
    }
}
