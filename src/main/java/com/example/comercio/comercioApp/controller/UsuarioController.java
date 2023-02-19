package com.example.comercio.comercioApp.controller;

import com.example.comercio.comercioApp.dto.UsuarioDTO;
import com.example.comercio.comercioApp.service.impl.UsuarioServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioService;



    @GetMapping("/usuario/perfil")
    public ResponseEntity verPerfil(@RequestParam String nombreUsuario){
        UsuarioDTO usuarioDTO = usuarioService.buscarUsuario(nombreUsuario);

        if(usuarioDTO != null){
            return new ResponseEntity(usuarioDTO, HttpStatus.OK);
        }else{
            return new ResponseEntity("El usuario no existe", HttpStatus.BAD_REQUEST);
        }
    }
}
