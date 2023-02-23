/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.alumno.controladores;

import cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Empresa;
import cat.copernic.copernicjobs.model.Oferta;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Cole
 */
@Controller
public class VerOferta {

    @GetMapping("/veureOferta")
    public String inicio(Model model) {

        //Ruta donde está el archivo html 
        String ruta = "";
        //nombre del archivo html
        String archivo = "verOferta";

        Oferta oferta = new Oferta();
        oferta.setEmpresa(new Empresa());
        oferta.setTituloOferta("TituloOferta");
        oferta.setDescripcionOferta("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eu dui vitae est dictum posuere eget eu mi. Curabitur ornare urna nibh, elementum vulputate nisl dignissim sit amet. Aenean faucibus tincidunt magna, ut vulputate tortor mollis sit amet. Fusce non maximus enim, in eleifend neque. Quisque tincidunt est sapien, vitae auctor lectus fringilla pharetra. Vestibulum placerat tristique placerat. Nunc tempor leo diam, eget porttitor dui tincidunt in. Praesent nec sem erat. Suspendisse elementum, felis non hendrerit cursus, est velit porta sapien, sed tempor sem ipsum eu magna. In a arcu sodales, ullamcorper velit ac, sodales velit. Curabitur sed eros id ex sagittis facilisis non sit amet enim. In sollicitudin turpis mauris, et commodo leo feugiat tempus.");
        oferta.setRequisitosAlumno("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eu dui vitae est dictum posuere eget eu mi. Curabitur ornare urna nibh, elementum vulputate nisl dignissim sit amet. Aenean faucibus tincidunt magna, ut vulputate tortor mollis sit amet. Fusce non maximus enim, in eleifend neque. Quisque tincidunt est sapien, vitae auctor lectus fringilla pharetra. Vestibulum placerat tristique placerat. Nunc tempor leo diam, eget porttitor dui tincidunt in. Praesent nec sem erat. Suspendisse elementum, felis non hendrerit cursus, est velit porta sapien, sed tempor sem ipsum eu magna. In a arcu sodales, ullamcorper velit ac, sodales velit. Curabitur sed eros id ex sagittis facilisis non sit amet enim. In sollicitudin turpis mauris, et commodo leo feugiat tempus.");
        oferta.setSeOfrece("private String seOfrece;");
        oferta.getEmpresa().setMunicipio("Terrassa");
        oferta.getEmpresa().setNombreEmpresa("Google");
        oferta.setFechaValidacion(LocalDate.now());
        
        HashMap dummyData = new HashMap<String,Object>(){{
            put("ofTit", oferta.getTituloOferta());
            put("nomEmpresa", oferta.getEmpresa().getNombreEmpresa());
            put("ofDesc", oferta.getDescripcionOferta());
            put("ofReq", oferta.getRequisitosAlumno());
            put("ofOfer", oferta.getSeOfrece());
            put("alumnesInscrits", 10);
            put("alumnoNoInscrito", true);
            put("ofFecha", oferta.getFechaValidacion());
        }};
        model.addAllAttributes(dummyData);
        model.addAttribute(ruta);
        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return CargarPantallaPrincipal.cargar(model, NavBarType.ALUMNO, ruta, archivo);
    }
}
