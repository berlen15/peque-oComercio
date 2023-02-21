package com.example.comercio.comercioApp.service;

import com.example.comercio.comercioApp.dto.CestaDTO;

public interface CestaServiceInterface {
    public boolean añadirArticulo(String nombreUsuario, String referencia);
    public CestaDTO verCesta (String  nombreUsuario);
    public boolean realizarCompra(String nombreUsuario, String numTarjeta);
}
