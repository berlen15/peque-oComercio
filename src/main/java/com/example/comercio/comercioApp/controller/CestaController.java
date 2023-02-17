package com.example.comercio.comercioApp.controller;

import com.example.comercio.comercioApp.dto.CestaDTO;
import com.example.comercio.comercioApp.service.impl.ArticuloServiceImpl;
import com.example.comercio.comercioApp.service.impl.CestaServiceImpl;
import com.example.comercio.comercioApp.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CestaController {
    @Autowired
    private CestaServiceImpl cestaService;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private ArticuloServiceImpl articuloService;

    @GetMapping("/cesta")
    public ResponseEntity verCesta(@RequestParam String nombreUsuario){
        if(usuarioService.buscarUsuario(nombreUsuario)==null)
            return new ResponseEntity("El usuario no está dado de alta", HttpStatus.OK);

        CestaDTO cestaDTO = cestaService.verCesta(nombreUsuario);

        if(cestaDTO!= null){
            return new ResponseEntity(cestaDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity("La cesta está vacía", HttpStatus.OK);
        }
    }
    @PostMapping("/cesta/añadirArticulo/{referencia}")
    public ResponseEntity añadirArticulo(@RequestParam String nombreUsuario, @PathVariable String referencia){
        if(articuloService.obtenerArticulo(referencia)==null)
            return new ResponseEntity("El articulo no existe",HttpStatus.BAD_REQUEST);
        if(cestaService.añadirArticulo(nombreUsuario, referencia)){
            return new ResponseEntity("Artículo creado con éxito",HttpStatus.CREATED);
        }else{
            return new ResponseEntity("El articulo no se ha añadido correctamente. Por favor, inténtelo de nuevo más tarde", HttpStatus.BAD_REQUEST);
        }

    }
}
