package com.bellapet.animal.http.response;

import com.bellapet.utils.enums.Status;

public record AnimalResponse(Long id, String nome, String raca, String tipo, Status status) {
}
