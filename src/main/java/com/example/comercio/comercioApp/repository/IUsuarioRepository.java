package com.example.comercio.comercioApp.repository;

import com.example.comercio.comercioApp.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
    //@Query(value = "select * from Usuario where username=?1", nativeQuery = true)
    Usuario findByUsername(String username);
}
