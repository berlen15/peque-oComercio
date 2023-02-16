package com.example.comercio.comercioApp.converter;

import com.example.comercio.comercioApp.dto.ArticuloDTO;
import com.example.comercio.comercioApp.dto.UsuarioDTO;
import com.example.comercio.comercioApp.entity.Articulo;
import com.example.comercio.comercioApp.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioConverter {
    private final ArticuloConverter articuloConverter = new ArticuloConverter();
    public Usuario dto2pojo(UsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario();

        if(usuarioDTO == null) return null;

        usuario.setId(usuarioDTO.getId());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setNombreUsuario(usuarioDTO.getNombreUsuario());

        if(usuarioDTO.getArticulosComprados() != null || usuarioDTO.getArticulosComprados().size()>0) {
            usuarioDTO.getArticulosComprados().forEach((final ArticuloDTO articuloDTO) ->
                    usuario.getArticulosComprados().add(articuloConverter.dto2pojo(articuloDTO)));
        }

        return usuario;
    }

    public UsuarioDTO pojo2dto(Usuario usuario){
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        if(usuario == null) return null;

        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setNombreUsuario(usuario.getNombreUsuario());

        if(usuario.getArticulosComprados() != null || usuario.getArticulosComprados().size()>0) {
            usuario.getArticulosComprados().forEach((final Articulo articulo) ->
                    usuarioDTO.getArticulosComprados().add(articuloConverter.pojo2dto(articulo)));
        }

        return usuarioDTO;
    }
}

