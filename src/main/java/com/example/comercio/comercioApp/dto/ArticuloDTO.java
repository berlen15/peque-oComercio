package com.example.comercio.comercioApp.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.io.Serializable;
import java.util.List;

public class ArticuloDTO implements Serializable {
    private int idArticulo;
    private String descripcion;
    private double precio;
    @JsonBackReference
    private List<VentaDTO> ventas;

    private int stock;

    private String referencia;

    @JsonBackReference
    private List<UsuarioDTO> usuariosCompradores;

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public List<VentaDTO> getVentas() {
        return ventas;
    }

    public void setVentas(List<VentaDTO> ventas) {
        this.ventas = ventas;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public List<UsuarioDTO> getUsuariosCompradores() {
        return usuariosCompradores;
    }

    public void setUsuariosCompradores(List<UsuarioDTO> usuariosCompradores) {
        this.usuariosCompradores = usuariosCompradores;
    }
}