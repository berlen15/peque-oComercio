package com.example.comercio.comercioApp.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

@Data
public class ErrorResponse {
    private HttpStatus status;
    private String path;
    private String mensaje;
    private LocalDate date;

    public ErrorResponse(HttpStatus status, String path, String mensaje, LocalDate date) {
        this.status = status;
        this.path = path;
        this.mensaje = mensaje;
        this.date = date;
    }
}
