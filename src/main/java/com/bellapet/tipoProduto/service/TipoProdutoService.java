package com.bellapet.tipoProduto.service;

import com.bellapet.tipoProduto.http.adapter.TipoProdutoAdapter;
import com.bellapet.tipoProduto.http.request.TipoProdutoRequest;
import com.bellapet.tipoProduto.http.response.TipoProdutoResponse;
import com.bellapet.tipoProduto.persistence.entity.TipoProduto;
import com.bellapet.tipoProduto.persistence.repository.TipoProdutoRepository;
import com.bellapet.utils.enums.Status;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TipoProdutoService {
    private final TipoProdutoRepository tipoProdutoRepository;

    public List<TipoProdutoResponse> listarTipoProduto() {
        return TipoProdutoAdapter.toResponseList(this.tipoProdutoRepository.findAllByStatus(Status.ATIVO));
    }

    public List<TipoProdutoResponse> listarTipoProdutoInativo() {
        return TipoProdutoAdapter.toResponseList(this.tipoProdutoRepository.findAllByStatus(Status.INATIVO));
    }

    @Transactional
    public void cadastrarTipoProduto(TipoProdutoRequest tipoProdutoRequest) {
        this.tipoProdutoRepository.save(TipoProdutoAdapter.toTipoProduto(new TipoProduto(), tipoProdutoRequest));
    }

    @Transactional
    public void atualizarTipoProduto(Long idProduto, TipoProdutoRequest tipoProdutoRequest) {
        TipoProduto tipoProduto = this.buscarTipoProdutoPorId(idProduto);
        this.tipoProdutoRepository.save(TipoProdutoAdapter.toTipoProduto(tipoProduto, tipoProdutoRequest));
    }

    @Transactional
    public void atualizarStatusTipoProduto(Long idProduto) {
        TipoProduto tipoProduto = this.buscarTipoProdutoPorId(idProduto);
        tipoProduto.setStatus(tipoProduto.getStatus().equals(Status.ATIVO) ? Status.INATIVO : Status.ATIVO);
        this.tipoProdutoRepository.save(tipoProduto);
    }

    public TipoProduto buscarTipoProdutoPorId(Long id) {
        return this.tipoProdutoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tipo do produto não encontrado!"));
    }
}
