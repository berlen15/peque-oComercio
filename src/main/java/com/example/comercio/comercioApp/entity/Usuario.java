package com.example.comercio.comercioApp.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="nombreusuario", unique = true, nullable = false)
    private String nombreUsuario;

    @Column(name="email")
    private String email;

    @Column(name="nombre")
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "articulo_usuario",
            joinColumns = @JoinColumn(name="articulo_id", nullable = false),
            inverseJoinColumns =  @JoinColumn(name="usuario_id", nullable = false)
    )
    private List<Articulo> articulosComprados;

    public Usuario(Integer id, String nombreUsuario, String email, String nombre) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.nombre = nombre;
        this.articulosComprados = new ArrayList<>();
    }

    public Usuario(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Usuario(String nombreUsuario){
        this.nombreUsuario=nombreUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public List<Articulo> getArticulosComprados() {
        return articulosComprados;
    }

    public void setArticulosComprados(List<Articulo> articulosComprados) {
        this.articulosComprados = articulosComprados;
    }
}
