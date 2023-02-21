package com.example.comercio.comercioApp.service.impl;

import com.example.comercio.comercioApp.dto.ArticuloDTO;
import com.example.comercio.comercioApp.dto.CestaDTO;
import com.example.comercio.comercioApp.dto.UsuarioDTO;
import com.example.comercio.comercioApp.entity.Articulo;
import com.example.comercio.comercioApp.entity.Cesta;
import com.example.comercio.comercioApp.entity.Usuario;
import com.example.comercio.comercioApp.entity.Venta;
import com.example.comercio.comercioApp.exception.ArticuloException;
import com.example.comercio.comercioApp.exception.CestaException;
import com.example.comercio.comercioApp.exception.UsuarioException;
import com.example.comercio.comercioApp.repository.IArticuloRepository;
import com.example.comercio.comercioApp.repository.ICestaRepository;
import com.example.comercio.comercioApp.repository.IUsuarioRepository;
import com.example.comercio.comercioApp.repository.IVentaRepository;
import com.example.comercio.comercioApp.service.CestaServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Transactional
    @Override
    public boolean añadirArticulo(String nombreUsuario, String referencia) {
        if(referencia == null || referencia.isEmpty()) return false;

        Usuario usuario = usuarioRepository.findByUsername(nombreUsuario);

        //Dado que las cestas se eliminan cuando se efectúa el pago de los artículos, comprobamos si la cesta se
        //encuentra creada. En ese caso la obtenemos. En caso contrario, se crea la cesta para el usuario.
        Cesta cesta = cestaRepository.findByUsuario(usuario) != null?
                cestaRepository.findByUsuario(usuario) : new Cesta(usuario);

        if(cesta.getListadoArticulos()==null) cesta.setListadoArticulos(new ArrayList<>());

        Articulo articulo = articuloRepository.findByReferencia(referencia);

        if(articuloRepository.findByReferencia(referencia)==null) throw new ArticuloException("El artículo no existe");

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

        if (usuario == null)
            throw new UsuarioException("El usuario no existe");

        return pojo2dto(cestaRepository.findByUsuario(usuario));
    }

    @Override
    public boolean realizarCompra(String nombreUsuario, String numTarjeta) {
        Usuario usuario = usuarioRepository.findByUsername(nombreUsuario);
        Cesta cesta = cestaRepository.findByUsuario(usuario);

        if(cesta == null ){
            throw new CestaException("Ha habido un problema con la cesta.");
        } else {
            if(cesta.getListadoArticulos().isEmpty())
                return false;

            cesta.getListadoArticulos().stream().forEach((Articulo articulo)->{
                Venta venta = new Venta();
                venta.setArticulo(articulo);
                venta.setFecha(LocalDate.now());
                venta.setImporte(articulo.getPrecio());
                venta.setNumTarjeta(numTarjeta);
                venta.setUsuario(usuario);

                articulo.getUsuariosCompradores().add(usuario);

                usuario.getArticulosComprados().add(articulo);

                articuloRepository.save(articulo);
                ventaRepository.save(venta);
            });
            cestaRepository.delete(cesta);
            return true;
        }

    }

    //Método pojo2dto: Permite convertir el objeto pojo a un data transfer object para operar con el.
    private CestaDTO pojo2dto(Cesta cesta){
        CestaDTO cestaDTO = new CestaDTO();
        List<ArticuloDTO> articulos = new ArrayList<>();

        if(cesta == null) return null;

        cestaDTO.setIdcesta(cesta.getIdcesta());
        cesta.getListadoArticulos().stream().forEach((Articulo articulo)->{
            articulos.add(modelMapper.map(articulo, ArticuloDTO.class));
        });
        cestaDTO.setListadoArticulos(articulos);
        cestaDTO.setUsuario(modelMapper.map(cesta.getUsuario(), UsuarioDTO.class));

        return cestaDTO;

    }
}
