package com.example.comercio.comercioApp.service.impl;

import com.example.comercio.comercioApp.dto.ArticuloDTO;
import com.example.comercio.comercioApp.dto.VentaDTO;
import com.example.comercio.comercioApp.entity.Articulo;
import com.example.comercio.comercioApp.entity.Venta;
import com.example.comercio.comercioApp.repository.IVentaRepository;
import com.example.comercio.comercioApp.service.VentaServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class VentaServiceImpl implements VentaServiceInterface {
    @Autowired
    private IVentaRepository ventaRepository;

    private ModelMapper modelMapper = new ModelMapper();
    @Override
    public List<ArticuloDTO> masVendidosUltimaSemana(){
        List<Articulo> articulos = new ArrayList<>();

        LocalDate ultimaSemana = LocalDate.now().minusDays(7);

        ventaRepository.findByFechaGreaterThan(ultimaSemana).stream().forEach((final Venta v)->{
            articulos.add(v.getArticulo());
        });
        return pojo2dto(articulos);
    }

    private List<ArticuloDTO> pojo2dto(List<Articulo> articulos){
        List<ArticuloDTO> articulosDTO = new ArrayList<>();
        //List<VentaDTO> ventasDTO = new ArrayList<>();
        articulos.stream().forEach((Articulo articulo)->{
            //ventasDTO.add(modelMapper.map(articulo.getVentas(), VentaDTO.class));
            articulosDTO.add(modelMapper.map(articulo, ArticuloDTO.class));
        });
        return articulosDTO;
    }
}