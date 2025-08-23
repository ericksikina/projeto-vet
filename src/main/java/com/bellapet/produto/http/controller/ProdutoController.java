package com.bellapet.produto.http.controller;

import com.bellapet.produto.http.request.ProdutoRequest;
import com.bellapet.produto.http.response.ProdutoResponse;
import com.bellapet.produto.service.ProdutoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Produto")
@RequestMapping(path = "/produto")
public class ProdutoController {
    private final ProdutoService produtoService;

    @GetMapping(path = "/listar")
    public ResponseEntity<List<ProdutoResponse>> listarProduto(){
        return ResponseEntity.ok(this.produtoService.listarProduto());
    }

    @GetMapping(path = "/listar-estoque-baixo")
    public ResponseEntity<List<ProdutoResponse>> listarProdutoComEstoqueBaixo(){
        return ResponseEntity.ok(this.produtoService.listarProdutoComEstoqueBaixo());
    }

    @GetMapping(path = "/listar-inativos")
    public ResponseEntity<List<ProdutoResponse>> listarProdutoInativos(){
        return ResponseEntity.ok(this.produtoService.listarProdutoInativos());
    }

    @PostMapping(path = "/cadastrar")
    public ResponseEntity<Void> cadastrarProduto(@RequestBody ProdutoRequest produtoRequest) throws IOException {
        produtoService.cadastrarProduto(produtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

//    @PostMapping(path = "/cadastrar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<Void> cadastrarProduto(@ModelAttribute ProdutoMultipartRequest request) throws IOException {
//        ProdutoRequest produtoRequest = objectMapper.readValue(request.getProduto(), ProdutoRequest.class);
//        produtoService.cadastrarProduto(request.getFile(), produtoRequest);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }

    @PutMapping(path = "/atualizar/{id}")
    public ResponseEntity<Void> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoRequest produtoRequest){
        this.produtoService.atualizarProduto(id, produtoRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(path = "/atualizar-status/{id}")
    public ResponseEntity<Void> atualizarProduto(@PathVariable Long id){
        this.produtoService.atualizarStatusProduto(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
