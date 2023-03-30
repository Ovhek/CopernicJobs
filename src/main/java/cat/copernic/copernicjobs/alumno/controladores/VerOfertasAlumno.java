/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.alumno.controladores;

import cat.copernic.copernicjobs.empresa.servicios.OfertaService;
import cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Oferta;
import jakarta.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 *
 * @author Cole
 */
@Controller
public class VerOfertasAlumno {

    @Autowired
    private OfertaService ofertaService;

    /**
     *
     * Método que se encarga de mostrar las ofertas disponibles para un alumno.
     *
     * @param model el modelo utilizado para enviar información a la vista.
     * @param username el nombre del usuario autenticado.
     * @param request la petición HTTP que se realiza para cargar la vista.
     * @return la vista con la lista de ofertas disponibles para el alumno.
     */
    @PreAuthorize("hasAuthority('alumne')")
    @GetMapping("/alumne/veureOfertesAlumne")
    public String inicio(Model model, @AuthenticationPrincipal UserDetails username, HttpServletRequest request) {

        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        //Ruta donde está el archivo html 
        String ruta = "alumno/";
        //nombre del archivo html
        String archivo = "verOfertas";

        List<Oferta> ofertas = new ArrayList<>();

        if (inputFlashMap != null) {
            ofertas = (List<Oferta>) inputFlashMap.get("ofertas");
        } else {
            ofertas = ofertaService.llistarOfertas();
        }
        model.addAttribute("ofertas", ofertas);
        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return CargarPantallaPrincipal.cargar(model, NavBarType.ALUMNO, ruta, archivo, "Veure ofertes", username);
    }

    /**
     *
     * Método que se encarga de buscar ofertas y aplicar ordenación según los
     * parámetros especificados.
     *
     * @param btnValue el valor del botón de búsqueda.
     * @param buscar el texto utilizado para filtrar las ofertas.
     * @param ordenar el criterio de ordenación de las ofertas.
     * @param user el usuario autenticado.
     * @param model el modelo utilizado para enviar información a la vista.
     * @param request la petición HTTP que se realiza para cargar la vista.
     * @param redirectAttributes los atributos de redirección utilizados para
     * enviar información a la vista.
     * @return la redirección a la vista correspondiente según la página desde
     * la que se ha realizado la búsqueda.
     */
    @PostMapping("/alumne/buscaroferta")
    public String buscarOferta(@RequestParam(name = "buscar") String btnValue, @RequestParam(name = "search-input") String buscar, @RequestParam(name = "sort-select") String ordenar, @AuthenticationPrincipal UserDetails user, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String referer = request.getHeader("referer");
        URI refererUri = URI.create(referer);
        String[] pathParts = refererUri.getPath().split("/");
        String firstPart = pathParts[1]; // alumne
        String secondPart = pathParts[2]; // archivo

        redirectAttributes.addFlashAttribute("ofertas", ofertaService.filtrarOfertasOrdenacionAlumno(buscar, ordenar));

        String ruta = "alumno/";
        String archivo = "verOfertas";
        switch (secondPart) {
            case "inscripcions":
                return "redirect:/alumne/inscripcions";
            default:
                return "redirect:/alumne/veureOfertesAlumne";
        }

    }
}
