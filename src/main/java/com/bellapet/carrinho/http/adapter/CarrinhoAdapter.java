package com.bellapet.carrinho.http.adapter;

import com.bellapet.carrinho.http.response.CarrinhoResponse;
import com.bellapet.carrinho.persistence.entity.Carrinho;
import com.bellapet.produtoCarrinho.http.adapter.ProdutoCarrinhoAdapter;

public class CarrinhoAdapter {
    public static CarrinhoResponse toResponse(Carrinho carrinho) {
        return new CarrinhoResponse(carrinho.getId(),
                ProdutoCarrinhoAdapter.toResponseList(carrinho.getListaDeProdutos()),
                carrinho.getTotal());
    }
}
