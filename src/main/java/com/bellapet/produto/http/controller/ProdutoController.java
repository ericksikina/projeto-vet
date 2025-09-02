package com.bellapet.produto.http.controller;

import com.bellapet.produto.http.request.ProdutoRequest;
import com.bellapet.produto.http.response.ProdutoResponse;
import com.bellapet.produto.persistence.entity.Produto;
import com.bellapet.produto.service.ProdutoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping(path = "/buscar/{id}")
    public ResponseEntity<ProdutoResponse> buscarProduto(@PathVariable Long id){
        return ResponseEntity.ok(this.produtoService.buscarProduto(id));
    }

    @GetMapping("buscar-imagem/{id}")
    public ResponseEntity<byte[]> buscarImagemProduto(@PathVariable Long id) {
        Produto produto = this.produtoService.buscarProdutoPorId(id);

        return new ResponseEntity<>(produto.getFoto(), this.produtoService.buscarHttpHeaders(produto.getFoto()), HttpStatus.OK);
    }

    @PostMapping(path = "/cadastrar")
    public ResponseEntity<ProdutoResponse> cadastrarProduto(@RequestBody ProdutoRequest produtoRequest) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.cadastrarProduto(produtoRequest));
    }

    @PutMapping(value = "/atualizar-foto/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> atualizarFoto(
            @PathVariable Long id,
            @RequestParam("foto") MultipartFile foto) throws IOException {
        this.produtoService.atualizarFoto(id, foto);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

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
