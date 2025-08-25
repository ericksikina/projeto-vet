package com.bellapet.endereco.http.controller;

import com.bellapet.endereco.http.request.EnderecoRequest;
import com.bellapet.endereco.http.response.EnderecoResponse;
import com.bellapet.endereco.service.EnderecoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
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

    @GetMapping(path = "/buscar")
    public ResponseEntity<EnderecoResponse> buscarEndereco(HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(this.enderecoService.buscarEndereco(httpServletRequest));
    }

    @PutMapping(path = "/atulizar")
    public ResponseEntity<Void>  atualizarEndereco(HttpServletRequest httpServletRequest
            , @RequestBody EnderecoRequest enderecoRequest) {
        this.enderecoService.atualizarEndereco(httpServletRequest, enderecoRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
