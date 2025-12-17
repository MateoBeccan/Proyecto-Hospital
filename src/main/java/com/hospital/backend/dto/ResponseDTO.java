package com.hospital.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ResponseDTO<T> {
    private int status;
    private String mensaje;
    private LocalDateTime timestamp;
    private T data;

    public ResponseDTO() { this.timestamp = LocalDateTime.now(); }

    public ResponseDTO(int status, String mensaje, T data) {
        this();
        this.status = status;
        this.mensaje = mensaje;
        this.data = data;
    }

    // Getters y setters

}
