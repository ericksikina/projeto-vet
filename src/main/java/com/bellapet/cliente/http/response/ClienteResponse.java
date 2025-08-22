package com.bellapet.cliente.http.response;

import com.bellapet.endereco.http.response.EnderecoResponse;
import com.bellapet.utils.enums.Status;

public record ClienteResponse (Long id, String nome, String cpf, String telefone, String email,
                               EnderecoResponse enderecoResponse, Status status) {
}
