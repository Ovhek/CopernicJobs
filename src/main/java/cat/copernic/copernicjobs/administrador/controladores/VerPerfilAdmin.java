/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.controladores;

import cat.copernic.copernicjobs.administrador.servicios.AdministradorService;
import cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Administrador;
import java.time.LocalDate;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Clase controladora que maneja la visualización del perfil de un
 * administrador. Esta clase es un controlador de Spring MVC que gestiona las
 * peticiones relacionadas con la visualización del perfil de un administrador
 * en la interfaz de administrador.
 *
 * @author joang
 */
@Controller
public class VerPerfilAdmin {

    @Autowired //Anotació que injecta tots els mètodes i possibles dependències de UsuarioDAO
    private AdministradorService administradorService; //Atribut per poder utilitzar les funcions CRUD de la interfície AdministradorDAO

    /**
     * Maneja la petición GET para visualizar el perfil de un administrador.
     *
     * @param model El modelo de datos que se utilizará para enviar datos a la
     * vista.
     * @param username Objeto UserDetails que contiene la información del
     * usuario autenticado.
     * @return El nombre de la vista a la que se redireccionará.
     */
    @PreAuthorize("hasAuthority('administrador')")
    @GetMapping("/administrador/veurePerfil")
    public String inicio(Model model, @AuthenticationPrincipal UserDetails username) {
        int id = administradorService.buscarAdministradorPorUsername(username.getUsername()).getId();
        //Ruta donde está el archivo html 
        String ruta = "administrador/";
        //nombre del archivo html
        String archivo = "verPerfilAdmin";

        Administrador administrador = new Administrador();
        administrador.setId(id);

        model.addAttribute("administrador", administradorService.buscarAdministrador(administrador));
        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo, "Inici", username);
    }
}
