package com.example.comercio.comercioApp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="venta")
public class Venta {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idventa")
    private Integer idVenta;
    @Column(name="fecha")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate fecha;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="articulo_id", nullable = false)
    @JsonBackReference
    private Articulo articulo;

    @Column(name="importe")
    private double importe;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name="tarjeta_bancaria")
    private String numTarjeta;

    public Venta(Integer idVenta, LocalDate fecha, Articulo articulo, double importe, Usuario usuario, String numTarjeta) {
        this.idVenta = idVenta;
        this.fecha = fecha;
        this.articulo = articulo;
        this.importe = importe;
        this.usuario = usuario;
        this.numTarjeta = numTarjeta;
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

