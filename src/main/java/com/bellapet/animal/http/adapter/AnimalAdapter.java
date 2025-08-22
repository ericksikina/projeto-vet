package com.bellapet.animal.http.adapter;

import com.bellapet.animal.http.request.AnimalRequest;
import com.bellapet.animal.http.response.AnimalResponse;
import com.bellapet.animal.persistence.entity.Animal;
import com.bellapet.cliente.persistence.entity.Cliente;
import com.bellapet.utils.enums.Status;

import java.util.List;
import java.util.stream.Collectors;

public class AnimalAdapter {
    public static AnimalResponse toResponse(Animal animal) {
        return new AnimalResponse(animal.getId(), animal.getNome(), animal.getRaca(), animal.getTipo(),
                animal.getStatus());
    }

    public static List<AnimalResponse> toResponseList(List<Animal> listaAnimal) {
        return listaAnimal.stream()
                .map(AnimalAdapter::toResponse)
                .collect(Collectors.toList());
    }

    public static Animal toAnimal(Animal animal, AnimalRequest animalRequest, Cliente cliente) {
        animal.setNome(animalRequest.nome());
        animal.setRaca(animalRequest.raca());
        animal.setTipo(animalRequest.tipo());
        animal.setCliente(cliente);
        animal.setStatus(Status.ATIVO);
        return animal;
    }

    public static Animal toAnimal(Animal animal, AnimalRequest animalRequest) {
        animal.setNome(animalRequest.nome());
        animal.setRaca(animalRequest.raca());
        animal.setTipo(animalRequest.tipo());
        return animal;
    }
}
