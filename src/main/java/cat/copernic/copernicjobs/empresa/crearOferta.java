/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.empresa;

import cat.copernic.copernicjobs.empresa.servicios.EmpresaService;
import cat.copernic.copernicjobs.empresa.servicios.OfertaService;
import cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Empresa;
import cat.copernic.copernicjobs.model.Inscripcion;
import cat.copernic.copernicjobs.model.Oferta;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controlador encargado de los endpoints de crear oferta.
 * @author Albert
 */
@Controller
public class crearOferta {

    @Autowired
    OfertaService ofertaService;

    @Autowired
    EmpresaService empresaService;

    /**
     *
     * Método que retorna la página inicial de la sección de Empresa del
     * proyecto.
     *
     * @param model el modelo que se utilizará para pasar información a la vista
     * @return el nombre de la página a la que se redirige al usuario
     * @throws Ninguna excepción es lanzada por esta función
     * @PreAuthorize Esta anotación indica que el usuario debe tener autoridad
     * de 'Empresa' para acceder a esta función
     * @GetMapping Esta anotación indica que la función maneja solicitudes HTTP
     * GET a la URL '/empresa'
     */
    @PreAuthorize("hasAuthority('Empresa')")
    @GetMapping("/empresa") //Pàgina inicial dels gossos
    public String empresa(Model model) {
        Empresa empresa = new Empresa();
        empresa.setId(3);
        //llistarGossos() retorna el llistat d'objectes gos guardats en la taula gossos de la BBDD    
        model.addAttribute("empresa", empresaService.cercarEmpresa(empresa));

        return "crearOferta"; //Retorna la pàgina iniciEnviarDades
    }

    /**
     *
     * Método que retorna la página de inicio de la sección de Empresa del
     * proyecto para crear una nueva oferta.
     *
     * @param user objeto que representa los detalles del usuario autenticado
     * actualmente
     *
     * @param model el modelo que se utilizará para pasar información a la vista
     *
     * @return el nombre de la página a la que se redirige al usuario
     *
     * @throws Ninguna excepción es lanzada por esta función
     *
     * @PreAuthorize Esta anotación indica que el usuario debe tener autoridad
     * de 'Empresa' para acceder a esta función
     *
     * @GetMapping Esta anotación indica que la función maneja solicitudes HTTP
     * GET a la URL '/empresa/crearoferta'
     */
    @PreAuthorize("hasAuthority('Empresa')")
    @GetMapping("/empresa/crearoferta")
    public String inicio(@AuthenticationPrincipal UserDetails user, Model model) {
        Oferta oferta = new Oferta();
        //Ruta donde está el archivo html 
        String ruta = "empresa/";
        //nombre del archivo html
        String archivo = "crearoferta";

        model.addAttribute("oferta", oferta);
        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return CargarPantallaPrincipal.cargar(model, NavBarType.EMPRESA, ruta, archivo, "Crear Oferta", user);
    }

    /**
     *
     * Método que registra una oferta nueva creada por una empresa.
     *
     * @param btnOferta el botón de la oferta pulsado por el usuario
     *
     * @param oferta el objeto Oferta creado por la empresa
     *
     * @param errors la lista de errores que se producen en la validación del
     * objeto Oferta
     *
     * @param user objeto que representa los detalles del usuario autenticado
     * actualmente
     *
     * @param model el modelo que se utilizará para pasar información a la vista
     *
     * @return el nombre de la página a la que se redirige al usuario
     *
     * @throws Ninguna excepción es lanzada por esta función
     *
     * @PreAuthorize Esta anotación indica que el usuario debe tener autoridad
     * de 'Empresa' para acceder a esta función
     *
     * @PostMapping Esta anotación indica que la función maneja solicitudes HTTP
     * POST a la URL '/empresa/registraroferta'
     */
    @PreAuthorize("hasAuthority('Empresa')")
    @PostMapping("/empresa/registraroferta")
    public String registrarOferta(@RequestParam(name = "boton") String btnOferta, @Valid Oferta oferta, Errors errors, @AuthenticationPrincipal UserDetails user, Model model) {

        if (btnOferta.equals("registrar")) {
            return guardarOferta(oferta, errors, model, user);
        }

        return "redirect:/empresa/crearoferta";
        //else borrarOferta(oferta);

    }

    /**
     *
     * Método que guarda una oferta nueva creada por una empresa en la base de
     * datos.
     *
     * @param oferta el objeto Oferta creado por la empresa
     * @param errors la lista de errores que se producen en la validación del
     * objeto Oferta
     * @param model el modelo que se utilizará para pasar información a la vista
     * @param user objeto que representa los detalles del usuario autenticado
     * actualmente
     * @return el nombre de la página a la que se redirige al usuario
     * @throws Ninguna excepción es lanzada por esta función
     */
    public String guardarOferta(Oferta oferta, Errors errors, Model model, UserDetails user) {
        if (errors.hasErrors()) {
            List<String> erroresString = new ArrayList<>();
            errors.getAllErrors().forEach(err -> erroresString.add(err.getDefaultMessage()));
            model.addAttribute("errores", erroresString);
            return inicio(user, model);
        } else {
            int id = empresaService.buscarPorUsername(user.getUsername()).getId();
            Empresa e = new Empresa();
            e.setId(id);
            Empresa empresa = empresaService.cercarEmpresa(e);
            oferta.setEmpresa(empresa);
            oferta.setFechaPeticion(LocalDate.now());
            oferta.setFechaValidacion(LocalDate.now());
            oferta.setInscripciones(new ArrayList<Inscripcion>());
            ofertaService.afegirOferta(oferta); //Afegim el gos passat per paràmetre a la base de dades
            return "redirect:/empresa/inici";
        }
    }
}
