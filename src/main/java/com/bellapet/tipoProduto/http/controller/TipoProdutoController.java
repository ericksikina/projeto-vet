package com.bellapet.tipoProduto.http.controller;

import com.bellapet.tipoProduto.http.request.TipoProdutoRequest;
import com.bellapet.tipoProduto.http.response.TipoProdutoResponse;
import com.bellapet.tipoProduto.service.TipoProdutoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Tipo produto")
@RequestMapping(path = "/tipo-produto")
public class TipoProdutoController {
    private final TipoProdutoService tipoProdutoService;

    @GetMapping(path = "/listar")
    public ResponseEntity<List<TipoProdutoResponse>> listarTipoProduto(){
        return ResponseEntity.ok(this.tipoProdutoService.listarTipoProduto());
    }

    @GetMapping(path = "/listar-inativo")
    public ResponseEntity<List<TipoProdutoResponse>> listarTipoProdutoInativo(){
        return ResponseEntity.ok(this.tipoProdutoService.listarTipoProdutoInativo());
    }

    @PostMapping(path = "/cadastrar")
    public ResponseEntity<Void> cadastrarTipoProduto(@RequestBody TipoProdutoRequest tipoProdutoRequest){
        this.tipoProdutoService.cadastrarTipoProduto(tipoProdutoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(path = "/atualizar/{id}")
    public ResponseEntity<Void> atualizarTipoProduto(@PathVariable Long id, @RequestBody TipoProdutoRequest tipoProdutoRequest){
        this.tipoProdutoService.atualizarTipoProduto(id, tipoProdutoRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(path = "/atualizar-status/{id}")
    public ResponseEntity<Void> atualizarStatusTipoProduto(@PathVariable Long id){
        this.tipoProdutoService.atualizarStatusTipoProduto(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
