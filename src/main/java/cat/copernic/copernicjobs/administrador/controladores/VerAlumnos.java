/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.controladores;

import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Alumno;
import cat.copernic.copernicjobs.model.Empresa;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author joang
 */
@Controller
public class VerAlumnos {
    
    @GetMapping("/verAlumnos")
    public String inicio(Model model){
        
        //Ruta donde está el archivo html 
        String ruta = "administrador/";
        //nombre del archivo html
        String archivo = "verAlumnos";
        
        ArrayList<Alumno> alumnos = new ArrayList<>(Arrays.asList(
                new Alumno(),
                new Alumno(),
                new Alumno(),
                new Alumno()
        ));
        alumnos.forEach(e -> {
            e.setNombre("John");
            e.setApellidos("Doe");
            e.setCorreo("JohnDoe@gmail.com");
        });
        
        model.addAttribute("alumnos", alumnos);
        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo);
    }
        
}