/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs;

import cat.copernic.copernicjobs.alumno.servicios.AlumnoService;
import cat.copernic.copernicjobs.empresa.servicios.EmpresaService;
import cat.copernic.copernicjobs.model.Alumno;
import cat.copernic.copernicjobs.model.Empresa;
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
public class TestsEmpresaJUnit {

    //Inyectamos el servicio del alumno.
    @Autowired
    private AlumnoService alumnoServiceJunit;

    @Autowired
    private EmpresaService empresaServiceJunit;

    //Creamos una empresa.
    @BeforeEach
    void setup() {
        empresa = new Empresa();
        empresa.setUsername("j.garcia@enttia.com");
        empresa.setPassword("12345678");
    }
    private Empresa empresa;


    @DisplayName("Buscar Empresa con JUnit")
    @Test
    void testBuscarEmpresaJUnit() {
        
        Empresa empresaEncontrada = empresaServiceJunit.buscarPorUsername(empresa.getUsername());
        Assertions.assertTrue(empresaEncontrada.getUsername().equals(empresa.getUsername()));

    }
}
