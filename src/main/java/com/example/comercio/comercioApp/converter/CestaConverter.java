package com.example.comercio.comercioApp.converter;

import com.example.comercio.comercioApp.dto.ArticuloDTO;
import com.example.comercio.comercioApp.dto.CestaDTO;
import com.example.comercio.comercioApp.entity.Articulo;
import com.example.comercio.comercioApp.entity.Cesta;
import org.springframework.stereotype.Component;

@Component
public class CestaConverter {

    private final UsuarioConverter usuarioConverter = new UsuarioConverter();
    private final ArticuloConverter articuloConverter = new ArticuloConverter();
    public Cesta dto2pojo(CestaDTO cestaDTO){
        Cesta cesta = new Cesta();
        if(cesta==null) return null;

        cesta.setIdcesta(cestaDTO.getIdcesta());
        cesta.setUsuario(usuarioConverter.dto2pojo(cestaDTO.getUsuario()));

        if(cestaDTO.getListadoArticulos() != null || cestaDTO.getListadoArticulos().size()>0) {
            cestaDTO.getListadoArticulos().forEach((final ArticuloDTO articuloDTO) ->
                    cesta.getListadoArticulos().add(articuloConverter.dto2pojo(articuloDTO)));
        }

        return cesta;
    }

    public CestaDTO pojo2dto(Cesta cesta){
        CestaDTO cestaDTO = new CestaDTO();
        if(cesta==null) return null;

        cestaDTO.setIdcesta(cestaDTO.getIdcesta());
        cestaDTO.setUsuario(usuarioConverter.pojo2dto(cesta.getUsuario()));

        if(cesta.getListadoArticulos() != null || cesta.getListadoArticulos().size()>0) {
            cesta.getListadoArticulos().forEach((final Articulo articulo) ->
                    cestaDTO.getListadoArticulos().add(articuloConverter.pojo2dto(articulo)));
        }

        return cestaDTO;
    }
}

