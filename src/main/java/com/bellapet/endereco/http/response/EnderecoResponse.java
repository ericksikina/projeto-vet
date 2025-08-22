package com.bellapet.endereco.http.response;

public record EnderecoResponse(Long id, String logradouro, String numero, String bairro, String cep,
                               String cidade, String estado, String complemento) {
}
