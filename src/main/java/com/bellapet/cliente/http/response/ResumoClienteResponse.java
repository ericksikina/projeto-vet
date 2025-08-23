package com.bellapet.cliente.http.response;

import com.bellapet.utils.enums.Status;

public record ResumoClienteResponse(Long id, String nome, String cpf, String telefone, String email, Status status) {
}
