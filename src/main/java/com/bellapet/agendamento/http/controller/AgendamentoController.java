package com.bellapet.agendamento.http.controller;

import com.bellapet.agendamento.http.request.AgendamentoRequest;
import com.bellapet.agendamento.http.response.AgendamentoResponse;
import com.bellapet.agendamento.service.AgendamentoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Agendamento")
@RequestMapping(path = "/agendamento")
public class AgendamentoController {
    private final AgendamentoService agendamentoService;

    @GetMapping(path = "/listar-e-commerce")
    public ResponseEntity<List<AgendamentoResponse>> listarAgendamento(HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(this.agendamentoService.listarAgendamento(httpServletRequest));
    }

    @GetMapping(path = "/listar-dashboard")
    public ResponseEntity<List<AgendamentoResponse>> listarAgendamento() {
        return ResponseEntity.ok(this.agendamentoService.listarAgendamento());
    }

    @GetMapping(path = "/buscar-e-commerce/{id}")
    public ResponseEntity<AgendamentoResponse> buscarAgendamento(HttpServletRequest httpServletRequest,
                                                                 @PathVariable Long id) {
        return ResponseEntity.ok(agendamentoService.buscarAgendamento(httpServletRequest, id));
    }

    @GetMapping(path = "/buscar-dashboard/{id}")
    public ResponseEntity<AgendamentoResponse> buscarAgendamento(@PathVariable Long id) {
        return ResponseEntity.ok(this.agendamentoService.buscarAgendamento(id));
    }

    @PostMapping(path = "/efetuar")
    public ResponseEntity<Void> efetuarAgendamento(HttpServletRequest httpServletRequest,
                                                   @RequestBody AgendamentoRequest agendamentoRequest) {
        this.agendamentoService.efetuarAgendamento(httpServletRequest, agendamentoRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(path = "/remarcar/{id}")
    public ResponseEntity<Void> remarcarAgendamento(HttpServletRequest httpServletRequest,
                                                    @RequestBody AgendamentoRequest agendamentoRequest,
                                                    @PathVariable Long id) {
        this.agendamentoService.remarcarAgendamento(httpServletRequest, agendamentoRequest, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(path = "/cancelar/{id}")
    public ResponseEntity<Void> cancelarAgendamento(HttpServletRequest httpServletRequest,
                                                    @PathVariable Long id) {
        this.agendamentoService.cancelarAgendamento(httpServletRequest, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
