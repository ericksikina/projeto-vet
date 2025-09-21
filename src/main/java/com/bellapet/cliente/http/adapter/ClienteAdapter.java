package com.bellapet.cliente.http.adapter;

import com.bellapet.auth.persistence.entity.Auth;
import com.bellapet.carrinho.persistence.entity.Carrinho;
import com.bellapet.cliente.http.request.AtualizarClienteRequest;
import com.bellapet.cliente.http.request.CadastroClienteRequest;
import com.bellapet.cliente.http.response.ClienteResponse;
import com.bellapet.cliente.http.response.ResumoClienteResponse;
import com.bellapet.cliente.persistence.entity.Cliente;
import com.bellapet.endereco.http.adapter.EnderecoAdapter;
import com.bellapet.endereco.persistence.entity.Endereco;
import com.bellapet.utils.enums.Status;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteAdapter {
    public static ClienteResponse toResponse(Cliente cliente) {
        return new ClienteResponse(cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getTelefone(),
                cliente.getEmail(), EnderecoAdapter.toResponse(cliente.getEndereco()), cliente.getStatus());
    }

    public static ResumoClienteResponse toResumoResponse(Cliente cliente) {
        return new ResumoClienteResponse(cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getTelefone(),
                cliente.getEmail(), cliente.getStatus());
    }

    public static List<ClienteResponse> toResponseList(List<Cliente> listaCliente) {
        return listaCliente.stream()
                .map(ClienteAdapter::toResponse)
                .collect(Collectors.toList());
    }

    public static List<ResumoClienteResponse> toResumoResponseList(List<Cliente> listaCliente) {
        return listaCliente.stream()
                .map(ClienteAdapter::toResumoResponse)
                .collect(Collectors.toList());
    }

    public static Cliente toCliente(Cliente cliente, AtualizarClienteRequest atualizarClienteRequest) {
        cliente.setNome(atualizarClienteRequest.nome());
        cliente.setCpf(atualizarClienteRequest.cpf());
        cliente.setTelefone(atualizarClienteRequest.telefone());
        cliente.setEmail(atualizarClienteRequest.email());
        cliente.setStatus(Status.ATIVO);
        return cliente;
    }

    public static Cliente toCliente(Cliente cliente, CadastroClienteRequest cadastroClienteRequest,
                                    Endereco endereco, Auth auth) {
        cliente.setNome(cadastroClienteRequest.nome());
        cliente.setCpf(cadastroClienteRequest.cpf());
        cliente.setTelefone(cadastroClienteRequest.telefone());
        cliente.setEmail(cadastroClienteRequest.email());
        cliente.setEndereco(endereco);
        cliente.setAuth(auth);
        cliente.setStatus(Status.ATIVO);
        cliente.setCarrinho(new Carrinho((cliente)));
        return cliente;
    }
}
