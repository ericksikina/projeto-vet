package com.bellapet.animal.service;

import com.bellapet.animal.http.adapter.AnimalAdapter;
import com.bellapet.animal.http.request.AnimalRequest;
import com.bellapet.animal.http.response.AnimalResponse;
import com.bellapet.animal.persistence.entity.Animal;
import com.bellapet.animal.persistence.repository.AnimalRepository;
import com.bellapet.cliente.persistence.entity.Cliente;
import com.bellapet.cliente.service.ClienteService;
import com.bellapet.utils.enums.Status;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AnimalService {
    private final AnimalRepository animalRepository;
    private final ClienteService clienteService;

    public List<AnimalResponse> listarAnimal(HttpServletRequest httpServletRequest){
        Cliente cliente = this.clienteService.buscarPorAuth(httpServletRequest);
        return AnimalAdapter.toResponseList(this.animalRepository.findAllByClienteAndStatus(cliente,Status.ATIVO));
    };

    public List<AnimalResponse> listarAnimalInativo(HttpServletRequest httpServletRequest){
        Cliente cliente = this.clienteService.buscarPorAuth(httpServletRequest);
        return AnimalAdapter.toResponseList(this.animalRepository.findAllByClienteAndStatus(cliente,Status.INATIVO));
    };

    @Transactional
    public void cadastrarAnimal(AnimalRequest animalRequest, HttpServletRequest httpServletRequest){
        Cliente cliente = this.clienteService.buscarPorAuth(httpServletRequest);
        this.animalRepository.save(AnimalAdapter.toAnimal(new Animal(), animalRequest, cliente));
    };

    @Transactional
    public void atualizarAnimal(Long idAnimal, AnimalRequest animalRequest, HttpServletRequest httpServletRequest) {
        Cliente cliente = this.clienteService.buscarPorAuth(httpServletRequest);
        Animal animal = this.buscarAnimalPorId(idAnimal);
        this.animalPertenceAoCliente(animal, cliente);
        this.animalRepository.save(AnimalAdapter.toAnimal(animal, animalRequest));
    };

    @Transactional
    public void atualizarStatusAnimal(Long idAnimal, HttpServletRequest httpServletRequest) {
        Cliente cliente = this.clienteService.buscarPorAuth(httpServletRequest);
        Animal animal = this.buscarAnimalPorId(idAnimal);
        this.animalPertenceAoCliente(animal, cliente);
        animal.setStatus(animal.getStatus().equals(Status.ATIVO) ? Status.INATIVO : Status.ATIVO);

        this.animalRepository.save(animal);
    };

    private Animal buscarAnimalPorId(Long id) {
        return this.animalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Animal não encontrado!"));
    }

    private void animalPertenceAoCliente(Animal animal, Cliente cliente) {
        if(!Objects.equals(animal.getCliente(), cliente)){
            throw new IllegalArgumentException("O animal + " + animal.getNome() + " não pertence a você!");
        }
    }
}
