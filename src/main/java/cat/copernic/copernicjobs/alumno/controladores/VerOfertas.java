/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.alumno.controladores;

import cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Empresa;
import cat.copernic.copernicjobs.model.Oferta;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Cole
 */
@Controller
public class VerOfertas {

    @GetMapping("/veureOfertes")
    public String inicio(Model model) {

        //Ruta donde está el archivo html 
        String ruta = "alumno/";
        //nombre del archivo html
        String archivo = "verOfertas";
        ArrayList<Oferta> ofertas = new ArrayList<>(Arrays.asList(
                new Oferta(),
                new Oferta()
        ));
        ofertas.forEach(e -> {
            e.setEmpresa(new Empresa());
            e.setTituloOferta("TituloOferta");
            e.setDescripcionOferta("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eu dui vitae est dictum posuere eget eu mi. Curabitur ornare urna nibh, elementum vulputate nisl dignissim sit amet. Aenean faucibus tincidunt magna, ut vulputate tortor mollis sit amet. Fusce non maximus enim, in eleifend neque. Quisque tincidunt est sapien, vitae auctor lectus fringilla pharetra. Vestibulum placerat tristique placerat. Nunc tempor leo diam, eget porttitor dui tincidunt in. Praesent nec sem erat. Suspendisse elementum, felis non hendrerit cursus, est velit porta sapien, sed tempor sem ipsum eu magna. In a arcu sodales, ullamcorper velit ac, sodales velit. Curabitur sed eros id ex sagittis facilisis non sit amet enim. In sollicitudin turpis mauris, et commodo leo feugiat tempus.");
        });
        
        ofertas.forEach(e -> {
            e.getEmpresa().setMunicipio("Terrassa");
            e.getEmpresa().setNombreEmpresa("Google");
        });

        model.addAttribute("ofertas", ofertas);
        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return CargarPantallaPrincipal.cargar(model, NavBarType.ALUMNO, ruta, archivo);
    }
}
