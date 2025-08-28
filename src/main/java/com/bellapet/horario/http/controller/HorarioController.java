package com.bellapet.horario.http.controller;

import com.bellapet.horario.http.request.HorarioDisponivelRequest;
import com.bellapet.horario.http.request.HorarioRequest;
import com.bellapet.horario.http.response.HorarioResponse;
import com.bellapet.horario.service.HorarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Horário")
@RequestMapping(path = "/horario")
public class HorarioController {
    private final HorarioService horarioService;

    @GetMapping(path = "/listar")
    public ResponseEntity<List<HorarioResponse>> listarHorario() {
        return ResponseEntity.ok(this.horarioService.listarHorario());
    }

    @GetMapping(path = "/listar-inativo")
    public ResponseEntity<List<HorarioResponse>> listarHorarioInativo() {
        return ResponseEntity.ok(this.horarioService.listarHorarioInativo());
    }

    @PostMapping(path = "/listar-disponiveis")
    public ResponseEntity<List<HorarioResponse>> listarHorariosDisponiveis(
            @RequestBody HorarioDisponivelRequest horarioDisponivelRequest) {
        return ResponseEntity.ok(this.horarioService.listarHorariosDisponiveis(horarioDisponivelRequest));
    }

    @GetMapping(path = "/buscar/{id}")
    public ResponseEntity<HorarioResponse> buscarHorario(@PathVariable Long id) {
        return ResponseEntity.ok(this.horarioService.buscarHorario(id));
    }

    @PostMapping(path = "/cadastrar")
    public ResponseEntity<Void> cadastrarHorario(@RequestBody HorarioRequest horarioRequest) {
        this.horarioService.cadastrarHorario(horarioRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(path = "/atualizar/{id}")
    public ResponseEntity<Void> atualizarHorario(@PathVariable Long id,
                                                 @RequestBody HorarioRequest horarioRequest) {
        this.horarioService.atualizarHorario(id, horarioRequest);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(path = "/atualizar-status/{id}")
    public ResponseEntity<Void> atualizarStatusHorario(@PathVariable Long id) {
        this.horarioService.atualizarStatusHorario(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
