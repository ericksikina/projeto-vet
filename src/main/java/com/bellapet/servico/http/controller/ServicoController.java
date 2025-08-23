package com.bellapet.servico.http.controller;

import com.bellapet.servico.http.request.ServicoRequest;
import com.bellapet.servico.http.response.ServicoResponse;
import com.bellapet.servico.service.ServicoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/servico")
@Tag(name = "Serviço")
@AllArgsConstructor
public class ServicoController {
    private final ServicoService servicoService;

    @GetMapping(path = "/listar")
    public ResponseEntity<List<ServicoResponse>> listarServico(){
        return ResponseEntity.ok(this.servicoService.listarServico());
    }

    @GetMapping(path = "/listar-inativo")
    public ResponseEntity<List<ServicoResponse>> listarServicoInativo(){
        return ResponseEntity.ok(this.servicoService.listarServicoInativo());
    }

    @PostMapping(path = "/cadastrar")
    public ResponseEntity<Void> cadastrarServico(@RequestBody ServicoRequest servicoRequest){
        this.servicoService.cadastrarServico(servicoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(path = "/atualizar/{id}")
    public ResponseEntity<Void> atualizarServico(@PathVariable Long id, @RequestBody ServicoRequest servicoRequest){
        this.servicoService.atualizarServico(id, servicoRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(path = "/atualizar-status/{id}")
    public ResponseEntity<Void> atualizarStatusServico(@PathVariable Long id){
        this.servicoService.atualizarStatusServico(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
