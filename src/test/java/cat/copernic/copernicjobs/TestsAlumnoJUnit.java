/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs;

import cat.copernic.copernicjobs.alumno.servicios.AlumnoService;
import cat.copernic.copernicjobs.model.Alumno;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Tests usando Junit u Spring boot
 *
 * @author Alex
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestsAlumnoJUnit {

    
    //Inyectamos el servicio del alumno.
    @Autowired
    private AlumnoService alumnoServiceJunit;

    //Antes de cada test hacemos el setup y creamos un usuario.
    @BeforeEach
    void setup() {
        alumno = new Alumno();
        alumno.setUsername("usuario1@gmail.com");
        alumno.setNombre("NoJuan");
    }
    
    private Alumno alumno;
    
    //Realizamos el test
    @DisplayName("Buscar Alumno con JUnit")
    @Test
    void testBuscarAlumnoJUnit() {
        Alumno alumnoEncontrado = alumnoServiceJunit.buscarAlumnoPorUsername(alumno.getUsername());
        Assertions.assertTrue(alumnoEncontrado.getUsername().equals(alumno.getUsername()));
    }
}
