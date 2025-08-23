package com.bellapet.animal.http.controller;

import com.bellapet.animal.http.request.AnimalRequest;
import com.bellapet.animal.http.response.AnimalResponse;
import com.bellapet.animal.service.AnimalService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Animal")
@RequestMapping(path = "/animal")
public class AnimalController {
    private final AnimalService animalService;

    @GetMapping(path = "/listar")
    public ResponseEntity<List<AnimalResponse>> listarAnimal(HttpServletRequest httpServletRequest){
        return ResponseEntity.ok(this.animalService.listarAnimal(httpServletRequest));
    };

    @PostMapping(path = "/cadsatrar")
    public ResponseEntity<Void> cadastrarAnimal(@RequestBody AnimalRequest animalRequest,
                                                HttpServletRequest httpServletRequest){
        this.animalService.cadastrarAnimal(animalRequest, httpServletRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    };

    @PutMapping(path = "/atualizar/{id}")
    public ResponseEntity<Void> atualizarAnimal(@PathVariable Long id,
                                                @RequestBody AnimalRequest animalRequest,
                                                HttpServletRequest httpServletRequest) {
        this.animalService.atualizarAnimal(id, animalRequest, httpServletRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    };

    @PutMapping(path = "/atualizar-status/{id}")
    public ResponseEntity<Void> atualizarStatusAnimal(@PathVariable Long id,
                                                      HttpServletRequest httpServletRequest) {
        this.animalService.atualizarStatusAnimal(id, httpServletRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    };
}
