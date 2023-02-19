package com.example.comercio.comercioApp.service.impl;

import com.example.comercio.comercioApp.dto.ArticuloDTO;
import com.example.comercio.comercioApp.entity.Articulo;
import com.example.comercio.comercioApp.repository.IArticuloRepository;
import com.example.comercio.comercioApp.service.ArticuloServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticuloServiceImpl implements ArticuloServiceInterface {
    @Autowired
    private IArticuloRepository articuloRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Transactional
    @Override
    public List<ArticuloDTO> obtenerDisponibles() {
        List<ArticuloDTO> articulos = new ArrayList<>();

        List<Articulo> articulosEntity = articuloRepository.findByStockGreaterThan(0);
        //Buscamos aquellos artículos que cumplan con la condición. Si la lista devuelve artículos, lo transformamos
        //a su correspondiente Data Transfer Object con el fin de retornarlo.
        if(articulosEntity != null){
            articuloRepository.findByStockGreaterThan(0).stream().forEach((final Articulo articulo) -> {
                articulos.add(modelMapper.map(articulo, ArticuloDTO.class));
            });
        }
        return articulos;
    }

    @Transactional
    @Override
    public ArticuloDTO obtenerArticulo(String referencia) {
        //En caso de que el artículo buscado exista, lo transformamos a su correspondiente Data Transfer Object.
        //En caso contrario, retornaremos null
        return articuloRepository.findByReferencia(referencia) != null ?
                modelMapper.map(articuloRepository.findByReferencia(referencia), ArticuloDTO.class) : null;
    }
}
