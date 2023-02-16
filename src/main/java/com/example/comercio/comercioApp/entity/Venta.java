package com.example.comercio.comercioApp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="venta")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idventa")
    private Integer idVenta;
    @Column(name="fecha")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate fecha;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="articulo_id", nullable = false)
    private Articulo articulo;
    @Column(name="cantidad")
    private int cantidad;

    public Venta(Integer idVenta, LocalDate fecha, Articulo articulo, int cantidad) {
        this.idVenta = idVenta;
        this.fecha = fecha;
        this.articulo = articulo;
        this.cantidad = cantidad;
    }

    public Venta(){}

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

    public Articulo getArticuloVendido() {
        return articulo;
    }

    public void setArticuloVendido(Articulo articuloVendido) {
        this.articulo = articuloVendido;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}

