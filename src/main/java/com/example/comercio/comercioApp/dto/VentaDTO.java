package com.example.comercio.comercioApp.dto;


import com.example.comercio.comercioApp.entity.Articulo;
import com.example.comercio.comercioApp.entity.Usuario;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDate;

public class VentaDTO implements Serializable {
    @ApiModelProperty(hidden = true)
    private Integer idVenta;
    @ApiModelProperty(position = 0)
    private LocalDate fecha;
    @ApiModelProperty(position = 1)
    private Articulo articulo;
    @ApiModelProperty(position = 2)
    private double importe;
    @ApiModelProperty(position = 3)
    private Usuario usuario;
    @ApiModelProperty(position = 4)
    private String numTarjeta;

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(String numTarjeta) {
        this.numTarjeta = numTarjeta;
    }
}

