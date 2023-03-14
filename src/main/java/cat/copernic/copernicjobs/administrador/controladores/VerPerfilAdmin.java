/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.controladores;

import cat.copernic.copernicjobs.dao.AdministradorDAO;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Administrador;
import cat.copernic.copernicjobs.model.Alumno;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author joang
 */
@Controller
public class VerPerfilAdmin {

    @Autowired //Anotació que injecta tots els mètodes i possibles dependències de UsuarioDAO
    private AdministradorDAO administradorDAO; //Atribut per poder utilitzar les funcions CRUD de la interfície AdministradorDAO

    @GetMapping("/verPerfilAdmin")
    public String inicio(Model model) {

        //Ruta donde está el archivo html 
        String ruta = "administrador/";
        //nombre del archivo html
        String archivo = "verPerfilAdmin";
        Administrador administrador = (Administrador) administradorDAO.findById(5).get();

        HashMap<String, Object> datos = new HashMap<>() {
            {
                put("nombrePerfil", administrador.getNombre());
                put("apellidosPerfil", administrador.getApellido());
                put("correoPerfil", administrador.getUsername());
            }
        };
        model.addAllAttributes(datos);
        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo);
    }

}
