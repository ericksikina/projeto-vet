package com.bellapet.endereco.http.controller;

import com.bellapet.endereco.http.request.EnderecoRequest;
import com.bellapet.endereco.service.EnderecoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Tag(name = "Endereço")
@RequestMapping(path = "/endereco")
public class EnderecoController {
    private final EnderecoService enderecoService;

    @PutMapping(path = "/atulizar/{id}")
    public ResponseEntity<Void>  atualizarEndereco(@PathVariable Long id, @RequestBody EnderecoRequest enderecoRequest) {
        this.enderecoService.atualizarEndereco(id, enderecoRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
