package com.bellapet.cliente.http.controller;

import com.bellapet.cliente.http.request.AtualizarClienteRequest;
import com.bellapet.cliente.http.request.CadastroClienteRequest;
import com.bellapet.cliente.http.request.EmailRecuperacaoRequest;
import com.bellapet.cliente.http.request.EsqueciMinhaSenhaRequest;
import com.bellapet.cliente.http.response.ClienteResponse;
import com.bellapet.cliente.http.response.EsqueciMinhaSenhaResponse;
import com.bellapet.cliente.http.response.ResumoClienteResponse;
import com.bellapet.cliente.service.ClienteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Cliente")
@RequestMapping(path = "/cliente")
public class ClienteController {
    private final ClienteService clienteService;

    @GetMapping(path = "/listar")
    public ResponseEntity<List<ResumoClienteResponse>> listarCliente(){
        return ResponseEntity.ok(this.clienteService.listarCliente());
    };

    @GetMapping(path = "/listar-inativo")
    public ResponseEntity<List<ResumoClienteResponse>> listarClienteInativo(){
        return ResponseEntity.ok(this.clienteService.listarClienteInativo());
    };

    @GetMapping(path = "/buscar/{id}")
    public ResponseEntity<ClienteResponse> buscarCliente(@PathVariable Long id){
        return ResponseEntity.ok(this.clienteService.buscarCliente(id));
    };

    @PostMapping(path = "/cadastrar")
    public ResponseEntity<Void> cadastrarCliente(@RequestBody CadastroClienteRequest cadastroClienteRequest){
        this.clienteService.cadastrarCliente(cadastroClienteRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    };

    @PutMapping(path = "/atualizar")
    public ResponseEntity<Void> atualizarCliente(HttpServletRequest httpServletRequest,
                                                 @RequestBody AtualizarClienteRequest atualizarClienteRequest) {
        this.clienteService.atualizarCliente(httpServletRequest, atualizarClienteRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    };

    @PutMapping(path = "/atualizar-status")
    public ResponseEntity<Void> atualizarStatus(HttpServletRequest httpServletRequest) {
        this.clienteService.atualizarStatusCliente(httpServletRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    };

    @PostMapping(path = "/enviar-email-confirmacao")
    public ResponseEntity<EsqueciMinhaSenhaResponse> enviarEmailRecuperacao(
            @RequestBody EmailRecuperacaoRequest emailRecuperacaoRequest){
        return ResponseEntity.ok(this.clienteService.enviarEmailConfirmacao(emailRecuperacaoRequest));
    }

    @PutMapping(path = "/atualizar-senha")
    public ResponseEntity<Void> atualizarSenha(@RequestBody EsqueciMinhaSenhaRequest esqueciMinhaSenhaRequest) {
        this.clienteService.atualizarSenha(esqueciMinhaSenhaRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
