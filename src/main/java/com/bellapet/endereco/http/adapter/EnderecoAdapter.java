package com.bellapet.endereco.http.adapter;

import com.bellapet.endereco.http.request.EnderecoRequest;
import com.bellapet.endereco.http.response.EnderecoResponse;
import com.bellapet.endereco.persistence.entity.Endereco;

public class EnderecoAdapter {
    public static EnderecoResponse toResponse(Endereco endereco) {
        return new EnderecoResponse(endereco.getId(), endereco.getRua(), endereco.getNumero(), endereco.getBairro(),
                endereco.getCep(), endereco.getCidade(), endereco.getEstado(), endereco.getComplemento());
    }

    public static Endereco toEndereco(Endereco endereco, EnderecoRequest enderecoRequest) {
        endereco.setRua(enderecoRequest.logradouro());
        endereco.setNumero(enderecoRequest.numero());
        endereco.setBairro(enderecoRequest.bairro());
        endereco.setCep(enderecoRequest.cep());
        endereco.setCidade(enderecoRequest.cidade());
        endereco.setEstado(enderecoRequest.estado());
        endereco.setComplemento(enderecoRequest.complemento());
        return endereco;
    }
}
