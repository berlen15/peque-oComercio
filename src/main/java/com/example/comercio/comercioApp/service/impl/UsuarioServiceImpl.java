package com.example.comercio.comercioApp.service.impl;

import com.example.comercio.comercioApp.entity.Usuario;
import com.example.comercio.comercioApp.repository.IUsuarioRepository;
import com.example.comercio.comercioApp.service.UsuarioServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioServiceInterface {
    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Override
    public Usuario buscarUsuario(String username) {
        return usuarioRepository.findByUsername(username);
    }
}
