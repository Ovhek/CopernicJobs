/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs;

import cat.copernic.copernicjobs.administrador.servicios.NoticiaService;
import cat.copernic.copernicjobs.model.Noticia;
import cat.copernic.copernicjobs.model.Rol;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author joang
 */
@SpringBootTest
@Transactional
public class TestsNoticiaJUnit {
    
    @Autowired
    private NoticiaService noticiaService;
    
    @Test
    public void testAfegirNoticia() {
        // Datos de prueba
        Noticia noticia = new Noticia();
        noticia.setTitulo("Título de prueba");
        noticia.setDescripcion("Descripción de prueba");
        LocalDate fechaHora = LocalDate.now();
        noticia.setFechaHora(fechaHora);
        Rol rol = new Rol();
        noticia.setRol(rol);

        // Llamar al método afegirNoticia() del servicio de noticia
        noticiaService.afegirNoticia(noticia);

        // Verificar que la noticia se haya creado correctamente en la base de datos
        assertNotNull(noticia.getId(), "La noticia no se creó correctamente en la base de datos");
    }
}
