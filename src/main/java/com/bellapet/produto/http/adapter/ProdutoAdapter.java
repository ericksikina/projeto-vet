package com.bellapet.produto.http.adapter;

import com.bellapet.produto.http.request.ProdutoRequest;
import com.bellapet.produto.http.response.ProdutoResponse;
import com.bellapet.produto.persistence.entity.Produto;
import com.bellapet.tipoProduto.http.adapter.TipoProdutoAdapter;
import com.bellapet.tipoProduto.persistence.entity.TipoProduto;
import com.bellapet.utils.enums.Status;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoAdapter {
    public static ProdutoResponse toResponse(Produto produto) {
        return new ProdutoResponse(produto.getId(), produto.getNome(), produto.getPreco(), produto.getQtdeEstoque(),
                produto.getQtdeMinima(), TipoProdutoAdapter.toResponse(produto.getTipoProduto()),produto.getStatus(),
                ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/produto/buscar-imagem/")
                        .path(String.valueOf(produto.getId()))
                        .toUriString());
    }

    public static List<ProdutoResponse> toResponseList(List<Produto> listaProduto) {
        return listaProduto.stream()
                .map(ProdutoAdapter::toResponse)
                .collect(Collectors.toList());
    }

    public static Produto toProduto(Produto produto, ProdutoRequest produtoRequest, TipoProduto tipoProduto) {
        produto.setNome(produtoRequest.nome());
        produto.setPreco(produtoRequest.preco());
        produto.setQtdeEstoque(produtoRequest.qtdeEstoque());
        produto.setQtdeMinima(produtoRequest.qtdeMinima());
        produto.setTipoProduto(tipoProduto);
        produto.setStatus(Status.ATIVO);
        return produto;
    }
}
