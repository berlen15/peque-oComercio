package com.example.comercio.comercioApp.converter;

import com.example.comercio.comercioApp.dto.ArticuloDTO;
import com.example.comercio.comercioApp.dto.VentaDTO;
import com.example.comercio.comercioApp.entity.Articulo;
import com.example.comercio.comercioApp.entity.Venta;
import org.springframework.stereotype.Component;

@Component
public class ArticuloConverter {

    private final VentaConverter ventaConverter = new VentaConverter();
    public Articulo dto2pojo(ArticuloDTO articuloDTO){
        Articulo articulo = new Articulo();

        articulo.setCodigoArticulo(articuloDTO.getCodigoArticulo());
        articulo.setDescripcion(articuloDTO.getDescripcion());
        articulo.setDisponible(articuloDTO.getDisponible());
        articulo.setPrecio(articuloDTO.getPrecio());

        if(articuloDTO.getVentas() != null || articuloDTO.getVentas().size()>0) {
            articuloDTO.getVentas().forEach((final VentaDTO ventaDTO) ->
                    articulo.getVentas().add(ventaConverter.dto2pojo(ventaDTO)));
        }

        return articulo;
    }

    public ArticuloDTO pojo2dto(Articulo articulo){
        ArticuloDTO articuloDTO = new ArticuloDTO();

        articuloDTO.setCodigoArticulo(articulo.getCodigoArticulo());
        articuloDTO.setDescripcion(articulo.getDescripcion());
        articuloDTO.setDisponible(articulo.getDisponible());
        articuloDTO.setPrecio(articulo.getPrecio());

        if(articulo.getVentas() != null || articulo.getVentas().size()>0) {
            articulo.getVentas().forEach((final Venta venta) ->
                    articuloDTO.getVentas().add(ventaConverter.pojo2dto(venta)));
        }

        return articuloDTO;
    }
}

