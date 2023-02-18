package com.example.comercio.comercioApp.controller;

import com.example.comercio.comercioApp.dto.UsuarioDTO;
import com.example.comercio.comercioApp.service.impl.UsuarioServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/usuario/perfil")
    public UsuarioDTO verPerfil(@RequestParam String nombreUsuario){
        return modelMapper.map(usuarioService.buscarUsuario(nombreUsuario), UsuarioDTO.class);
    }
}
