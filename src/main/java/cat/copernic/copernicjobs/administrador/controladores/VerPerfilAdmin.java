/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.controladores;

import cat.copernic.copernicjobs.DAO.AdministradorDAO;
import cat.copernic.copernicjobs.administrador.servicios.AdministradorService;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Administrador;
import java.time.LocalDate;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author joang
 */
@Controller
public class VerPerfilAdmin {

    @Autowired //Anotació que injecta tots els mètodes i possibles dependències de UsuarioDAO
    private AdministradorService administradorService; //Atribut per poder utilitzar les funcions CRUD de la interfície AdministradorDAO

    @GetMapping("/verPerfilAdmin")
    public String inicio(Model model) {
        int id = 5;
        //Ruta donde está el archivo html 
        String ruta = "administrador/";
        //nombre del archivo html
        String archivo = "verPerfilAdmin";

        Administrador administrador = new Administrador();
        administrador.setId(id);

        model.addAttribute("administrador", administradorService.buscarAdministrador(administrador));
        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo);
    }

    @PostMapping("/guardarAdministrador") //action=guardarAdministrador
    public String guardarAdministrador(Administrador administrador) {

        administradorService.anadirAdministrador(administrador); //Afegim la noticia passada per paràmetre a la base de dades

        return "redirect:/verPerfilAdmin"; //Retornem a la pàgina inicial dels gossos mitjançant redirect
    }

    @GetMapping("/editarAdmin/{id}")
    public String editar(Administrador administrador, Model model) {

        /*Cerquem l'administrador passat per paràmetre, al qual li correspón l'id de @GetMapping mitjançant 
         *el mètode buscarAdministrador de la capa de servei.*/
        
        String ruta="administrador/";
        
        String archivo="editarPerfilAdmin";
        
        model.addAttribute("administrador", administradorService.buscarAdministrador(administrador));

        return cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo);
    }

}
