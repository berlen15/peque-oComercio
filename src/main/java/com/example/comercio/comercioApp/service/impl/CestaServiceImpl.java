package com.example.comercio.comercioApp.service.impl;

import com.example.comercio.comercioApp.dto.ArticuloDTO;
import com.example.comercio.comercioApp.dto.CestaDTO;
import com.example.comercio.comercioApp.dto.UsuarioDTO;
import com.example.comercio.comercioApp.entity.Articulo;
import com.example.comercio.comercioApp.entity.Cesta;
import com.example.comercio.comercioApp.entity.Usuario;
import com.example.comercio.comercioApp.repository.IArticuloRepository;
import com.example.comercio.comercioApp.repository.ICestaRepository;
import com.example.comercio.comercioApp.repository.IUsuarioRepository;
import com.example.comercio.comercioApp.service.CestaServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void realizarCompra(String nombreUsuario, List<ArticuloDTO> articulosDTO) {

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
