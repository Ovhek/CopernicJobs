/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.alumno.controladores;

import cat.copernic.copernicjobs.administrador.servicios.NoticiaService;
import cat.copernic.copernicjobs.dao.NoticiaDAO;
import cat.copernic.copernicjobs.empresa.servicios.OfertaService;
import cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Cole
 */
@Controller
public class InicioAlumno {

    @Autowired
    private NoticiaService noticiaService;
    @Autowired
    private OfertaService ofertaService;

    @PreAuthorize("hasAuthority('alumne')")
    @GetMapping("/alumne/inici")
    public String inicioAlumno(Model model, @AuthenticationPrincipal UserDetails username) {
        //Ruta donde está el archivo html 
        String ruta = "alumno/";
        //nombre del archivo html
        String archivo = "inicioAlumno";
        model.addAttribute("anuncios", noticiaService.llistarNoticies());
        model.addAttribute("ofertas", ofertaService.llistarOfertasUltimaSemana());
        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return CargarPantallaPrincipal.cargar(model, NavBarType.ALUMNO, ruta, archivo, "Inici", username);
    }


}
