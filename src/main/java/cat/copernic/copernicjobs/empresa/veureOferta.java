/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.empresa;

import cat.copernic.copernicjobs.alumno.servicios.InscripcionService;
import cat.copernic.copernicjobs.dao.OfertaDAO;
import cat.copernic.copernicjobs.empresa.servicios.OfertaService;
import cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Oferta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Albert
 */
@Controller
public class veureOferta {

    @Autowired
    OfertaService ofertaService;

    @Autowired
    InscripcionService inscripcionService;

    /**
     *
     * Método que procesa la petición GET de visualizar una oferta en el
     * sistema.
     *
     * @param user El objeto UserDetails que representa al usuario que está
     * realizando la búsqueda.
     * @param oferta El objeto Oferta que se está visualizando.
     * @param model El objeto Model utilizado para almacenar y pasar datos a la
     * vista.
     * @return La plantilla HTML de la página principal de la empresa con los
     * detalles de la oferta.
     */
    @GetMapping("/empresa/veureoferta/{id}")
    public String inicio(@AuthenticationPrincipal UserDetails user, Oferta oferta, Model model) {
        //Ruta donde está el archivo html 
        String ruta = "empresa/";
        oferta = ofertaService.cercarOferta(oferta);
        //nombre del archivo html
        String archivo = "veureoferta";

        model.addAttribute("ofertas", ofertaService.cercarOferta(oferta));

        model.addAttribute("numinscritos", inscripcionService.buscarInscripcionPorOfertaId(oferta.getId()).size());

        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return CargarPantallaPrincipal.cargar(model, NavBarType.EMPRESA, ruta, archivo, "Veure Oferta", user);
    }
}
