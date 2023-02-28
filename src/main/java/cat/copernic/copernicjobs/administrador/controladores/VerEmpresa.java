/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.controladores;

import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Empresa;
import cat.copernic.copernicjobs.model.Oferta;
import java.time.LocalDate;
import java.util.HashMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author joang
 */
@Controller
public class VerEmpresa {
    
    @GetMapping("/verEmpresa")
    public String inicio(Model model){
        
        //Ruta donde está el archivo html 
        String ruta = "administrador/";
        //nombre del archivo html
        String archivo = "verEmpresa";
        
        Empresa empresa = new Empresa();
        empresa.setNombreEmpresa("Empresa Copernic");
        empresa.setDescripcionEmpresa("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eu dui vitae est dictum posuere eget eu mi. Curabitur ornare urna nibh, elementum vulputate nisl dignissim sit amet. Aenean faucibus tincidunt magna, ut vulputate tortor mollis sit amet. Fusce non maximus enim, in eleifend neque. Quisque tincidunt est sapien, vitae auctor lectus fringilla pharetra. Vestibulum placerat tristique placerat. Nunc tempor leo diam, eget porttitor dui tincidunt in. Praesent nec sem erat. Suspendisse elementum, felis non hendrerit cursus, est velit porta sapien, sed tempor sem ipsum eu magna. In a arcu sodales, ullamcorper velit ac, sodales velit. Curabitur sed eros id ex sagittis facilisis non sit amet enim. In sollicitudin turpis mauris, et commodo leo feugiat tempus.");
        empresa.setWebEmpresa("www.loremipsum/dolorsit/amet");
        empresa.setCodPostal("08225");
        empresa.setMunicipio("Terrassa");
        empresa.setDireccion("Carrer vinyals");
        empresa.setNombre("John");
        empresa.setApellidos("Doe");
        empresa.setMovil("65137893");
        
        
        HashMap dummyData = new HashMap<String,Object>(){{
            put("emNombre", empresa.getNombreEmpresa());
            put("emDesc", empresa.getDescripcionEmpresa());
            put("emDir", empresa.getDireccion());
            put("emPost", empresa.getCodPostal());
            put("emMun", empresa.getMunicipio());
            put("emWeb", empresa.getWebEmpresa());            
            put("resNombre", empresa.getNombre());
            put("resApellidos", empresa.getApellidos());
            put("resMob", empresa.getMovil());

        }};
        model.addAllAttributes(dummyData);
        model.addAttribute(ruta);
        
        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo);
    }
        
}