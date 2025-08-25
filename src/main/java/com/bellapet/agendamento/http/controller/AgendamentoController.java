package com.bellapet.agendamento.http.controller;

import com.bellapet.agendamento.http.response.AgendamentoResponse;
import com.bellapet.agendamento.service.AgendamentoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Agendamento")
@RequestMapping(path = "/agendamento")
public class AgendamentoController {
    private final AgendamentoService agendamentoService;

    public ResponseEntity<List<AgendamentoResponse>> listarAgendamento(HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<List<AgendamentoResponse>> listarAgendamento() {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<AgendamentoResponse> buscarAgendamento(HttpServletRequest httpServletRequest,
                                                                 @PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<AgendamentoResponse> buscarAgendamento(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> efetuarAgendamento(HttpServletRequest httpServletRequest) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    public ResponseEntity<Void> remarcarAgendamento(HttpServletRequest httpServletRequest,
                                                    @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    public ResponseEntity<Void> cancelarAgendamento(HttpServletRequest httpServletRequest) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
