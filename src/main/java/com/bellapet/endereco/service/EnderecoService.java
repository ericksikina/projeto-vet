package com.bellapet.endereco.service;

import com.bellapet.cliente.persistence.entity.Cliente;
import com.bellapet.cliente.service.ClienteService;
import com.bellapet.endereco.http.adapter.EnderecoAdapter;
import com.bellapet.endereco.http.request.EnderecoRequest;
import com.bellapet.endereco.persistence.entity.Endereco;
import com.bellapet.endereco.persistence.repository.EnderecoRespository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EnderecoService {
    private final EnderecoRespository enderecoRespository;
    private final ClienteService clienteService;

    public Endereco cadastrarEndereco(EnderecoRequest enderecoRequest) {
        return this.enderecoRespository.save(EnderecoAdapter.toEndereco(new Endereco(), enderecoRequest));
    }

    @Transactional
    public void atualizarEndereco(HttpServletRequest httpServletRequest, EnderecoRequest enderecoRequest) {
        Cliente cLiente = this.clienteService.buscarPorAuth(httpServletRequest);
        this.enderecoRespository.save(EnderecoAdapter.toEndereco(cLiente.getEndereco(), enderecoRequest));
    }
}
