package com.example.comercio.comercioApp.controller;

import com.example.comercio.comercioApp.dto.UsuarioDTO;
import com.example.comercio.comercioApp.exception.ErrorResponse;
import com.example.comercio.comercioApp.exception.UsuarioException;
import com.example.comercio.comercioApp.service.impl.UsuarioServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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


    @ApiOperation(value = "verPerfil", notes = "Endpoint que nos permite visualizar el perfil del usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Se ha podido recuperar la info del usuario"),
            @ApiResponse(code = 404, message = "Not found. El usuario no existe")})
    @ExceptionHandler (value = {UsuarioException.class})
    @GetMapping("/usuario/perfil")
    public ResponseEntity verPerfil(@RequestParam String nombreUsuario) throws UsuarioException{
        try{
            UsuarioDTO usuarioDTO = usuarioService.buscarUsuario(nombreUsuario);
            return new ResponseEntity(usuarioDTO, HttpStatus.OK);

        }catch (UsuarioException e){
            ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND, "/usuario/perfil",
                    "Usuario no existente", LocalDate.now());
            return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
        }
    }
}
