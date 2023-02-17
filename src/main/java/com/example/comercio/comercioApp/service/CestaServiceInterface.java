package com.example.comercio.comercioApp.service;

import com.example.comercio.comercioApp.dto.ArticuloDTO;
import com.example.comercio.comercioApp.dto.CestaDTO;

import java.util.List;

public interface CestaServiceInterface {
    public boolean añadirArticulo(String nombreUsuario, String referencia);
    public CestaDTO verCesta (String  nombreUsuario);
    public void realizarCompra(String nombreUsuario, List<ArticuloDTO> articulosDTO);
}
