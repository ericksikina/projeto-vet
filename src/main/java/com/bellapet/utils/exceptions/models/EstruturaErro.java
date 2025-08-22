package com.bellapet.utils.exceptions.models;

import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
public class EstruturaErro implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Long timestamp;
    private final Integer status;
    private final String error;
    private final String message;
    private final String path;

    public EstruturaErro(Long timestamp, Integer status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}
