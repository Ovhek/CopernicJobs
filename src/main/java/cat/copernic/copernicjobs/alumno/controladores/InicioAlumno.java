/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.alumno.controladores;

import cat.copernic.copernicjobs.administrador.servicios.NoticiaService;
import cat.copernic.copernicjobs.empresa.servicios.OfertaService;
import cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/alumne/")
    public String inicioAlumno_2(Model model) {

        //Ruta donde está el archivo html 
        String ruta = "alumno/";
        //nombre del archivo html
        String archivo = "inicioAlumno";
        model.addAttribute("anuncios", noticiaService.llistarNoticies());
        model.addAttribute("ofertas", ofertaService.llistarOfertasUltimaSemana());
        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return CargarPantallaPrincipal.cargar(model, NavBarType.ALUMNO, ruta, archivo, "Inici");
    }

    @GetMapping("/iniciAlumne")
    public String inicioAlumno(Model model) {

        //Ruta donde está el archivo html 
        String ruta = "alumno/";
        //nombre del archivo html
        String archivo = "inicioAlumno";
        model.addAttribute("anuncios", noticiaService.llistarNoticies());
        model.addAttribute("ofertas", ofertaService.llistarOfertasUltimaSemana());
        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return CargarPantallaPrincipal.cargar(model, NavBarType.ALUMNO, ruta, archivo, "Inici");
    }

    @RequestMapping("/alumne/inici")
    public String inicioAlumno_(Model model) {
        //Ruta donde está el archivo html 
        String ruta = "alumno/";
        //nombre del archivo html
        String archivo = "inicioAlumno";
        model.addAttribute("anuncios", noticiaService.llistarNoticies());
        model.addAttribute("ofertas", ofertaService.llistarOfertasUltimaSemana());
        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return CargarPantallaPrincipal.cargar(model, NavBarType.ALUMNO, ruta, archivo, "Inici");
    }


}
