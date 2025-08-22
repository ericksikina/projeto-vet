package com.bellapet.produtoCarrinho.http.adapter;

import com.bellapet.carrinho.http.request.ProdutoCarrinhoRequest;
import com.bellapet.produto.http.adapter.ProdutoAdapter;
import com.bellapet.produto.persistence.entity.Produto;
import com.bellapet.produtoCarrinho.http.response.ProdutoCarrinhoResponse;
import com.bellapet.produtoCarrinho.persistence.entity.ProdutoCarrinho;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoCarrinhoAdapter {
    public static ProdutoCarrinhoResponse toResponse(ProdutoCarrinho produtoCarrinho) {
        return new ProdutoCarrinhoResponse(ProdutoAdapter.toResponse(produtoCarrinho.getProduto()), produtoCarrinho.getQtde());
    }

    public static List<ProdutoCarrinhoResponse> toResponseList(List<ProdutoCarrinho> listaDeProdutoCarrinho) {
        return listaDeProdutoCarrinho.stream()
                .map(ProdutoCarrinhoAdapter::toResponse)
                .collect(Collectors.toList());
    }

    public static ProdutoCarrinho toEntity(ProdutoCarrinho produtoCarrinho,
                                           ProdutoCarrinhoRequest produtoCarrinhoRequest, Produto produto) {
        produtoCarrinho.setProduto(produto);
        produtoCarrinho.setQtde(produtoCarrinhoRequest.qtde());
        return produtoCarrinho;
    }
}
