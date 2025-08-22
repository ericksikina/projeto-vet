package com.bellapet.endereco.service;

import com.bellapet.endereco.http.adapter.EnderecoAdapter;
import com.bellapet.endereco.http.request.EnderecoRequest;
import com.bellapet.endereco.persistence.entity.Endereco;
import com.bellapet.endereco.persistence.repository.EnderecoRespository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EnderecoService {
    private final EnderecoRespository enderecoRespository;

    public Endereco cadastrarEndereco(EnderecoRequest enderecoRequest) {
        return this.enderecoRespository.save(EnderecoAdapter.toEndereco(new Endereco(), enderecoRequest));
    }

    @Transactional
    public void atualizarEndereco(Long idEndereco, EnderecoRequest enderecoRequest) {
        Endereco endereco = this.buscarEnderecoPorId(idEndereco);
        this.enderecoRespository.save(EnderecoAdapter.toEndereco(endereco, enderecoRequest));
    }

    private Endereco buscarEnderecoPorId(Long id) {
        return this.enderecoRespository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado!"));
    }
}
