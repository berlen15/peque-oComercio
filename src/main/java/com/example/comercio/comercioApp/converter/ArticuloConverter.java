package com.example.comercio.comercioApp.converter;

import com.example.comercio.comercioApp.dto.ArticuloDTO;
import com.example.comercio.comercioApp.entity.Articulo;
import org.springframework.stereotype.Component;

@Component
public class ArticuloConverter {
    public Articulo dto2pojo(ArticuloDTO articuloDTO){
        Articulo articulo = new Articulo();

        articulo.setCodigoArticulo(articuloDTO.getCodigoArticulo());
        articulo.setDescripcion(articuloDTO.getDescripcion());
        articulo.setDisponible(articuloDTO.getDisponible());
        articulo.setPrecio(articuloDTO.getPrecio());
        articulo.setVentas(articuloDTO.getVentas());

        return articulo;
    }

    public ArticuloDTO pojo2dto(Articulo articulo){
        ArticuloDTO articuloDTO = new ArticuloDTO();

        articuloDTO.setCodigoArticulo(articulo.getCodigoArticulo());
        articuloDTO.setDescripcion(articulo.getDescripcion());
        articuloDTO.setDisponible(articulo.getDisponible());
        articuloDTO.setPrecio(articulo.getPrecio());
        articuloDTO.setVentas(articulo.getVentas());

        return articuloDTO;
    }
}

