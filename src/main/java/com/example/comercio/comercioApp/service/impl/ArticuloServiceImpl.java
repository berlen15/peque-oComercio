package com.example.comercio.comercioApp.service.impl;

import com.example.comercio.comercioApp.entity.Articulo;
import com.example.comercio.comercioApp.repository.IArticuloRepository;
import com.example.comercio.comercioApp.service.ArticuloServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticuloServiceImpl implements ArticuloServiceInterface {
    @Autowired
    private IArticuloRepository articuloRepository;
    @Override
    public List<Articulo> obtenerDisponibles() {
        List<Articulo> articulos = new ArrayList<>();
        this.articuloRepository.findAll().stream().forEach((final Articulo articulo) -> {
            if(articulo.getStock()>0) articulos.add(articulo);
        });
        return articulos;
    }
}
