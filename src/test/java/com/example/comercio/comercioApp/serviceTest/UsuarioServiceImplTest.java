package com.example.comercio.comercioApp.serviceTest;

import com.example.comercio.comercioApp.entity.Usuario;
import com.example.comercio.comercioApp.repository.IUsuarioRepository;
import com.example.comercio.comercioApp.service.impl.UsuarioServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest
public class UsuarioServiceImplTest {

    @Mock
    private IUsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    Usuario usuario;
    @Before
    public void setUp(){
        usuario = new Usuario (1,"belen", "belen@prueba.com", "Belén");
    }

    @Test
    public void buscarUsuarioExistenteTest(){
        Mockito.when(usuarioRepository.findByUsername("belen")).thenReturn(usuario);

        usuarioService.buscarUsuario("Belen");

        Assert.assertEquals(usuarioService.buscarUsuario("Belen"), usuario);
    }

    @Test
    public void buscarUsuarioNoExistenteTest(){
        Mockito.when(usuarioRepository.findByUsername("belen")).thenReturn(null);

        usuarioService.buscarUsuario("belen");

        Assert.assertEquals(usuarioService.buscarUsuario("belen"), null);
    }

}
