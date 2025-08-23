package com.bellapet.carrinho.http.controller;

import com.bellapet.carrinho.http.request.ProdutoCarrinhoRequest;
import com.bellapet.carrinho.http.response.CarrinhoResponse;
import com.bellapet.carrinho.http.response.DisponibilidadeDoCarrinhoResponse;
import com.bellapet.carrinho.service.CarrinhoService;
import com.bellapet.produto.persistence.entity.Produto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Carrinho")
@RequestMapping(path = "/carrinho")
public class CarrinhoController {
    private final CarrinhoService carrinhoService;

    @GetMapping(path = "/buscar")
    public ResponseEntity<CarrinhoResponse> buscarCarrinho(HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(this.carrinhoService.buscarCarrinho(httpServletRequest));
    };

    @PutMapping(path = "/adicionar")
    public ResponseEntity<CarrinhoResponse> adicionarProduto(HttpServletRequest httpServletRequest,
                                                             @RequestBody ProdutoCarrinhoRequest produtoCarrinhoRequest){
        return ResponseEntity.ok(this.carrinhoService.adicionarProdutoCarrinho(httpServletRequest, produtoCarrinhoRequest));
    };

    @PutMapping(path = "/remover")
    public ResponseEntity<CarrinhoResponse> removerProduto(HttpServletRequest httpServletRequest,
                                                           @RequestBody ProdutoCarrinhoRequest produtoCarrinhoRequest) {
        return ResponseEntity.ok(this.carrinhoService.removerProdutoCarrinho(httpServletRequest, produtoCarrinhoRequest));
    };

    @PutMapping(path = "/limpar")
    public ResponseEntity<Void> esvaziarCarrinho(HttpServletRequest httpServletRequest) {
        this.carrinhoService.esvaziarCarrinho(httpServletRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    };

//    @GetMapping(path = "verificar-disponibilidade-carrinho")
//    public ResponseEntity<List<DisponibilidadeDoCarrinhoResponse>> verificarDisponibilidadeDoCarrinho(
//            HttpServletRequest httpServletRequest) {
//
//    }
}
