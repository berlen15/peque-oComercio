package com.example.comercio.comercioApp.dto;

import java.io.Serializable;
import java.util.List;

public class ArticuloDTO implements Serializable {
    private int idArticulo;
    private String descripcion;
    private String disponible;
    private double precio;
    private List<VentaDTO> ventas;

    public int getCodigoArticulo() {
        return idArticulo;
    }

    public void setCodigoArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDisponible() {
        return disponible;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public List<VentaDTO> getVentas() {
        return ventas;
    }

    public void setVentas(List<VentaDTO> ventas) {
        this.ventas = ventas;
    }
}