/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs;

import cat.copernic.copernicjobs.administrador.servicios.NoticiaService;
import cat.copernic.copernicjobs.dao.NoticiaDAO;
import cat.copernic.copernicjobs.model.Alumno;
import cat.copernic.copernicjobs.model.Noticia;
import cat.copernic.copernicjobs.model.Rol;
import java.time.LocalDate;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author joang
 */
//Indicamos que trabajamos con Mockito. Y cargamos extensiones de junit.
@ExtendWith(MockitoExtension.class)
public class TestsNoticiaMockito {

    @Mock
    private NoticiaDAO noticiaDAO; // El DAO de noticia, que será simulado con Mockito

    @InjectMocks
    private NoticiaService noticiaService; // El servicio que se va a probar

    private Noticia noticia;

    //setup --> Se ejecuta antes de cada test.
    @BeforeEach
    void setup() {
        noticia = new Noticia();
        noticia.setTitulo("Título de prueba");
        noticia.setDescripcion("Descripción de prueba");
        LocalDate fechaHora = LocalDate.now();
        noticia.setFechaHora(fechaHora);
        Rol rol = new Rol();
        noticia.setRol(rol);
    }

    /**
     * Test para verificar que se ha creado la noticia usando Mockito.
     */
    @DisplayName("Creacion de una Noticia con Mockito")
    @Test
    public void testAfegirNoticia() {
        Mockito.when(noticiaDAO.save(noticia)).thenReturn(noticia);
        
        // Llamada al método del servicio a probar
        noticiaService.afegirNoticia(noticia);

        // Verificar que la noticia se haya creado correctamente en la base de datos        
        Assertions.assertThat(noticia.getId()).isNotNull();
    }
}
