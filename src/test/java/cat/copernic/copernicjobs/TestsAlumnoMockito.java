/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs;

import cat.copernic.copernicjobs.alumno.servicios.AlumnoService;
import cat.copernic.copernicjobs.dao.AlumnoDAO;
import cat.copernic.copernicjobs.model.Alumno;
import java.time.LocalDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Tests relacionados con Alumnos.
 *
 * @author Alex
 */
//Indicamos que trabajamos con Mockito. Y cargamos extensiones de junit.
@ExtendWith(MockitoExtension.class)
public class TestsAlumnoMockito {

    //Indicamos que creamos un simulacro del alumnoDAO.
    @Mock
    private AlumnoDAO alumnoDAO;

    //Inyectamos el DAO en el servicio.
    @InjectMocks
    private AlumnoService alumnoService;

    private Alumno alumno;

    //setup --> Se ejecuta antes de cada test.
    @BeforeEach
    void setup() {
        alumno = new Alumno();
        alumno.setUsername("usuario1@gmail.com");
        alumno.setNombre("NoJuan");
    }

    /**
     * Test para buscar un alumno usando Mockito.
     */
    @DisplayName("Buscar un Alumno con Mockito")
    @Test
    void testBuscarAlumnoMockito() {
        BDDMockito.given(alumnoDAO.findByUsername(alumno.getUsername())).willReturn(alumno);

        Alumno alumnoBuscado = alumnoService.buscarAlumnoPorUsername(alumno.getUsername());

        Assertions.assertThat(alumnoBuscado).isNotNull();

    }
}
