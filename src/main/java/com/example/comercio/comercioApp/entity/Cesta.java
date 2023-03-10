package com.example.comercio.comercioApp.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="cesta")
public class Cesta {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idcesta")
    private Integer idcesta;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(
            name = "cesta_articulo",
            joinColumns = @JoinColumn(name="cesta_id", nullable = false),
            inverseJoinColumns =  @JoinColumn(name="articulo_id")
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Articulo> listadoArticulos;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Cesta(){}

    public Cesta(Usuario usuario){
        this.usuario=usuario;
        this.listadoArticulos = new ArrayList<>();
    }


    public Integer getIdcesta() {
        return idcesta;
    }

    public void setIdcesta(Integer idcesta) {
        this.idcesta = idcesta;
    }

    public List<Articulo> getListadoArticulos() {
        return listadoArticulos;
    }

    public void setListadoArticulos(List<Articulo> listadoArticulos) {
        this.listadoArticulos = listadoArticulos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

