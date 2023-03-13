/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.alumno.controladores;

import cat.copernic.copernicjobs.dao.InscripcionDAO;
import cat.copernic.copernicjobs.dao.OfertaDAO;
import cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Empresa;
import cat.copernic.copernicjobs.model.Inscripcion;
import cat.copernic.copernicjobs.model.Oferta;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Cole
 */
@Controller
public class VerOfertaAlumno {
    @Autowired
    private OfertaDAO ofertaDAO;
    
    @Autowired
    private InscripcionDAO inscripcionDAO;
    
    @GetMapping("/veureOferta")
    public String inicio(Model model) {

        int alumnoId = 1;
        int ofertaId = 1;
        //Ruta donde está el archivo html 
        String ruta = "";
        //nombre del archivo html
        String archivo = "verOferta";

        var inscripciones = inscripcionDAO.findAllByOfertaId(ofertaId);
        var alumnosInscritos = inscripciones.size();
        
        var alumnoInscrito =  inscripciones.stream().map(Inscripcion::getAlumno).anyMatch(alumno -> alumno.getId() == alumnoId);
        
        model.addAttribute("oferta", ofertaDAO.findById(ofertaId).get());
        model.addAllAttributes(new HashMap<String,Object>(){{
            put("empresaAdmin", false);
            put("alumnoInscrito", alumnoInscrito);
            put("alumnesInscrits", inscripciones.size());
        }});
        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return CargarPantallaPrincipal.cargar(model, NavBarType.ALUMNO, ruta, archivo);
    }
}
