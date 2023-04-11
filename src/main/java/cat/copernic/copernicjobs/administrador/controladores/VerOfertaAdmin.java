/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.controladores;

import cat.copernic.copernicjobs.empresa.servicios.OfertaService;
import cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Empresa;
import cat.copernic.copernicjobs.model.Oferta;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controlador para la gestión de ofertas en el panel de administrador. Este
 * controlador permite realizar operaciones CRUD (Crear, Leer, Actualizar,
 * Eliminar) sobre las ofertas en el sistema. Se requiere tener permisos de
 * administrador para acceder a las funcionalidades de este controlador.
 *
 * @author joang
 */
@Controller
public class VerOfertaAdmin {

    @Autowired //Anotació que injecta tots els mètodes i possibles dependències de UsuarioDAO
    private OfertaService ofertaService; //Atribut per poder utilitzar les funcions CRUD de la interfície UsuarioDAO

    /**
     * Método para guardar una oferta en el sistema.
     *
     * @param btnOferta Botón que se ha presionado en el formulario
     * @param oferta Objeto Oferta a guardar
     * @param errores Objeto Errors para gestionar errores de validación
     * @param model Objeto Model para pasar datos a la vista
     * @param result Objeto BindingResult para gestionar errores de binding
     * @param redirectAttributes Objeto RedirectAttributes para redireccionar y
     * pasar mensajes
     * @param username Objeto UserDetails con información del usuario
     * autenticado
     * @return Vista de la página principal del panel de administrador
     */
    @PreAuthorize("hasAuthority('administrador')")
    @PostMapping("/guardarOferta")
    public String guardarOferta(@RequestParam(name = "boton") String btnOferta, @Valid Oferta oferta, Errors errores, Model model, BindingResult result, RedirectAttributes redirectAttributes, @AuthenticationPrincipal UserDetails username) {
        if (btnOferta.equals("guardar")) {
            if (errores.hasErrors() || result.hasErrors()) { //Si s'han produït errors...
                List<String> erroresString = new ArrayList<>();
                errores.getAllErrors().forEach(err -> erroresString.add(err.getDefaultMessage()));

                List<String> erroresBindingString = new ArrayList<>();
                result.getAllErrors().forEach(err -> erroresBindingString.add(err.getDefaultMessage()));

                model.addAttribute("errores", erroresString);
                model.addAttribute("errores", erroresBindingString);

                //Ruta donde está el archivo html 
                String ruta = "administrador/";
                //nombre del archivo html
                String archivo = "editarOferta";

                return CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo, "Inici", username);
            }
            guardar(oferta);
        } else {
            borrar(oferta);
        }

        return "redirect:/verEmpresas";
    }

    /**
     * Método auxiliar para guardar una oferta utilizando el servicio de
     * ofertas.
     *
     * @param oferta Objeto Oferta a guardar
     */
    public void guardar(Oferta oferta) {
        ofertaService.afegirOferta(oferta);
    }

    /**
     * Método auxiliar para eliminar una oferta utilizando el servicio de
     * ofertas.
     *
     * @param oferta Objeto Oferta a eliminar
     * @return Vista de la página principal del panel de administrador
     */
    public String borrar(Oferta oferta) {
        ofertaService.eliminarOferta(oferta);
        return "redirect:/verEmpresas";
    }

    /**
     * Método que maneja la petición GET para la edición de una oferta en el
     * modo administrador. Requiere que el usuario tenga la autoridad
     * 'administrador' para acceder a este recurso.
     *
     * @param oferta Objeto de tipo Oferta a editar.
     * @param model Objeto de tipo Model que permite agregar atributos para ser
     * enviados a la vista.
     * @param username Objeto de tipo UserDetails que representa los detalles
     * del usuario autenticado.
     *
     * @return La vista a cargar después de procesar la petición.
     */
    @PreAuthorize("hasAuthority('administrador')")
    @GetMapping("/editarOferta/{id}")
    public String editar(Oferta oferta, Model model, @AuthenticationPrincipal UserDetails username) {

        String ruta = "administrador/";

        String archivo = "editarOferta";

        model.addAttribute("oferta", ofertaService.cercarOferta(oferta));

        return CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo, "Inici", username);
    }

}
