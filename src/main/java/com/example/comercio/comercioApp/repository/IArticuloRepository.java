package com.example.comercio.comercioApp.repository;

import com.example.comercio.comercioApp.entity.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IArticuloRepository extends JpaRepository<Articulo, Integer> {
    Articulo findByReferencia (String referencia);
}
