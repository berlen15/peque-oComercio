package com.example.comercio.comercioApp.service.impl;

import com.example.comercio.comercioApp.dto.ArticuloDTO;
import com.example.comercio.comercioApp.dto.CestaDTO;
import com.example.comercio.comercioApp.dto.UsuarioDTO;
import com.example.comercio.comercioApp.entity.Articulo;
import com.example.comercio.comercioApp.entity.Cesta;
import com.example.comercio.comercioApp.entity.Usuario;
import com.example.comercio.comercioApp.entity.Venta;
import com.example.comercio.comercioApp.repository.IArticuloRepository;
import com.example.comercio.comercioApp.repository.ICestaRepository;
import com.example.comercio.comercioApp.repository.IUsuarioRepository;
import com.example.comercio.comercioApp.repository.IVentaRepository;
import com.example.comercio.comercioApp.service.CestaServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
public class CestaServiceImpl implements CestaServiceInterface {
    @Autowired
    private ICestaRepository cestaRepository;
    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IArticuloRepository articuloRepository;

    @Autowired
    private IVentaRepository ventaRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public boolean añadirArticulo(String nombreUsuario, String referencia) {
        if(referencia == null || referencia.isEmpty()) return false;
        Usuario usuario = usuarioRepository.findByUsername(nombreUsuario);

        Cesta cesta = cestaRepository.findByUsuario(usuario) != null?
                cestaRepository.findByUsuario(usuario) : new Cesta(usuario);

        if(cesta.getListadoArticulos()==null) cesta.setListadoArticulos(new ArrayList<>());

        Articulo articulo = articuloRepository.findByReferencia(referencia);

        if(articulo.getStock()>0){
            cesta.getListadoArticulos().add(articulo);

            int stockActual = articulo.getStock() -1;
            articulo.setStock(stockActual);

            cestaRepository.save(cesta);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public CestaDTO verCesta(String  nombreUsuario) {
        Usuario usuario = usuarioRepository.findByUsername(nombreUsuario);
        return pojo2dto(cestaRepository.findByUsuario(usuario));
    }

    @Override
    public boolean realizarCompra(String nombreUsuario, String numTarjeta) {
        Usuario usuario = usuarioRepository.findByUsername(nombreUsuario);
        Cesta cesta = cestaRepository.findByUsuario(usuario);

        if(cesta.getListadoArticulos().size() <= 0){
            return false;
        } else {
            int stockActual = 0;

            cesta.getListadoArticulos().stream().forEach((Articulo articulo)->{
                usuario.getArticulosComprados().add(articulo);

                Venta venta = new Venta();
                venta.setArticulo(articulo);
                venta.setFecha(LocalDate.now());
                venta.setImporte(articulo.getPrecio());
                venta.setNumTarjeta(numTarjeta);
                venta.setUsuario(usuario);

                articulo.getUsuariosCompradores().add(usuario);

                articuloRepository.save(articulo);
                ventaRepository.save(venta);
                usuarioRepository.save(usuario);
            });

            cestaRepository.delete(cesta);

            return true;
        }

    }

    private CestaDTO pojo2dto(Cesta cesta){
        CestaDTO cestaDTO = new CestaDTO();
        List<ArticuloDTO> articulos = new ArrayList<>();

        if(cesta != null){
            cestaDTO.setIdcesta(cesta.getIdcesta());
            cesta.getListadoArticulos().stream().forEach((Articulo articulo)->{
                articulos.add(modelMapper.map(articulo, ArticuloDTO.class));
            });
            cestaDTO.setListadoArticulos(articulos);
            cestaDTO.setUsuario(modelMapper.map(cesta.getUsuario(), UsuarioDTO.class));

            return cestaDTO;
        }else{
            return null;
        }
    }
}
