package com.example.comercio.comercioApp.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ErrorResponse {
    private HttpStatus status;
    private String path;
    private String mensaje;
    private LocalDate date;
}
