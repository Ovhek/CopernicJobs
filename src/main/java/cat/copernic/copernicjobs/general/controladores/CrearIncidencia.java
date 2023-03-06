/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.general.controladores;

import cat.copernic.copernicjobs.general.servicios.IncidenciaService;
import cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Incidencia;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Cole
 */
@Controller
public class CrearIncidencia {
    
    @Autowired
    IncidenciaService incidenciaService;
    
    @GetMapping("/crearIncidencia")
    public String inicio(Incidencia incidencia,Model model){
        
        //Ruta donde está el archivo html 
        String ruta = "";
        //nombre del archivo html
        String archivo = "crearIncidencia";
        
        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return CargarPantallaPrincipal.cargar(model, NavBarType.ALUMNO, ruta, archivo);
    }
    
    @PostMapping("/crearIncidencia")
    public String login(Incidencia incidencia, Model model){
        incidencia.setFecha_incidencia(LocalDate.now());
        
        incidenciaService.anadirIncidencia(incidencia);
        
        model.addAttribute("creado",true);
        return "redirect:/crearIncidencia";
        
    }
}
