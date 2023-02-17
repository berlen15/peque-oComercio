package com.example.comercio.comercioApp.service.impl;

import com.example.comercio.comercioApp.dto.ArticuloDTO;
import com.example.comercio.comercioApp.entity.Articulo;
import com.example.comercio.comercioApp.entity.Venta;
import com.example.comercio.comercioApp.repository.IVentaRepository;
import com.example.comercio.comercioApp.service.VentaServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class VentaServiceImpl implements VentaServiceInterface {
    @Autowired
    private IVentaRepository ventaRepository;

    private ModelMapper modelMapper = new ModelMapper();
    @Override
    public Set<ArticuloDTO> masVendidosUltimaSemana(){
        Set<ArticuloDTO> articulos = new LinkedHashSet<>();

        LocalDate ultimaSemana = LocalDate.now().minusDays(7);
        ventaRepository.findByFechaGreaterThan(ultimaSemana).stream().forEach((Venta v)->{
            articulos.add(modelMapper.map(v.getArticulo(), ArticuloDTO.class));
        });
        return articulos;
    }
}