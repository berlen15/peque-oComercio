package com.example.comercio.comercioApp.repository;

import com.example.comercio.comercioApp.entity.Cesta;
import com.example.comercio.comercioApp.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICestaRepository extends JpaRepository<Cesta, Integer> {
    Cesta findByUsuario(Usuario usuario);
}
