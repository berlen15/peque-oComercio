package com.example.comercio.comercioApp.service.impl;

import com.example.comercio.comercioApp.dto.UsuarioDTO;
import com.example.comercio.comercioApp.entity.Usuario;
import com.example.comercio.comercioApp.repository.IUsuarioRepository;
import com.example.comercio.comercioApp.service.UsuarioServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioServiceInterface {
    @Autowired
    private IUsuarioRepository usuarioRepository;

    ModelMapper modelMapper = new ModelMapper();
    @Transactional
    @Override
    public UsuarioDTO buscarUsuario(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username);

        //Si existe el usuario, lo transformamos a su correspondiente Data Transfer Object para ser retornado.
        if(usuario != null){
            return modelMapper.map(usuario, UsuarioDTO.class);
        }
        return null;
    }
}
