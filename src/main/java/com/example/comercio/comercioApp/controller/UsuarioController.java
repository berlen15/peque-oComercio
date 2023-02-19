package com.example.comercio.comercioApp.controller;

import com.example.comercio.comercioApp.dto.UsuarioDTO;
import com.example.comercio.comercioApp.exception.ErrorResponse;
import com.example.comercio.comercioApp.exception.UsuarioException;
import com.example.comercio.comercioApp.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioService;



    @ExceptionHandler (value = {UsuarioException.class})
    @GetMapping("/usuario/perfil")
    public ResponseEntity verPerfil(@RequestParam String nombreUsuario) throws UsuarioException{
        try{
            UsuarioDTO usuarioDTO = usuarioService.buscarUsuario(nombreUsuario);
            return new ResponseEntity(usuarioDTO, HttpStatus.OK);

        }catch (Exception e){
            ErrorResponse error = new ErrorResponse();
            error.setMensaje("El usuario no existe");
            error.setDate(LocalDate.now());
            error.setStatus(HttpStatus.NOT_FOUND);
            error.setPath("/usuario/perfil");
            return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
        }
    }
}
