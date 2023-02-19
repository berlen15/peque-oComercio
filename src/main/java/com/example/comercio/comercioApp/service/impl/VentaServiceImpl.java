package com.example.comercio.comercioApp.service.impl;

import com.example.comercio.comercioApp.dto.ArticuloDTO;
import com.example.comercio.comercioApp.entity.Articulo;
import com.example.comercio.comercioApp.entity.Venta;
import com.example.comercio.comercioApp.repository.IVentaRepository;
import com.example.comercio.comercioApp.service.VentaServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

@Service
public class VentaServiceImpl implements VentaServiceInterface {
    @Autowired
    private IVentaRepository ventaRepository;

    private ModelMapper modelMapper = new ModelMapper();
    @Transactional
    @Override
    public List<ArticuloDTO> masVendidosUltimaSemana(){
        List<Articulo> articulos = new ArrayList<>();

        LocalDate ultimaSemana = LocalDate.now().minusDays(7);

        List<Venta> ventas = ventaRepository.findByFechaGreaterThan(ultimaSemana);

        if(ventas != null){
            ventaRepository.findByFechaGreaterThan(ultimaSemana).stream().forEach((final Venta v)->{
                articulos.add(v.getArticulo());
            });
            return pojo2dto(articulos);
        }

        return null;
    }

    private List<ArticuloDTO> pojo2dto(List<Articulo> articulos){
        List<ArticuloDTO> articulosDTO = new ArrayList<>();
        articulos.stream().forEach((Articulo articulo)->{
            articulosDTO.add(modelMapper.map(articulo, ArticuloDTO.class));
        });
        return articulosDTO;
    }
}