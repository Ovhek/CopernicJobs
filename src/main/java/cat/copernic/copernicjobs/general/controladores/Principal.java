/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.general.controladores;

import cat.copernic.copernicjobs.general.servicios.RolModuloService;
import cat.copernic.copernicjobs.model.Modulo;
import cat.copernic.copernicjobs.model.RolModulo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador de la pagina de inicio del ERP.
 *
 * @author Alex
 */
@Controller
public class Principal {

    @Autowired
    RolModuloService rolModuloService;

    /**
     *
     * Método que se encarga de gestionar la vista principal de la aplicación.
     * Obtiene la lista de módulos que están asociados al rol del usuario que ha
     * iniciado sesión y comprueba su visibilidad. Si todos los módulos son
     * invisibles, muestra un mensaje indicando que no hay módulos disponibles.
     * Añade los módulos visibles al modelo y redirige a la vista principal.
     *
     * @param model el modelo utilizado por Spring MVC para el intercambio de
     * datos con la vista
     * @param username el usuario que ha iniciado sesión
     * @return una cadena con el nombre de la vista principal
     */
    @GetMapping("/inici")
    public String inicio(Model model, @AuthenticationPrincipal UserDetails username) {

        List<GrantedAuthority> roles = new ArrayList<>(username.getAuthorities());

        List<Modulo> modulos = rolModuloService.findModulosByRolNom(roles.get(0).getAuthority());
        List<RolModulo> rolesModulos = rolModuloService.findAll();
        for (Modulo modulo : modulos) {
            boolean visibilidad = false;
            for (RolModulo rolModulo : rolesModulos) {
                if (rolModulo.getRol().getNom().equals(roles.get(0).getAuthority()) && rolModulo.getModulo().getID() == modulo.getID()) {
                    visibilidad = rolModulo.isVisibilidad();
                    break;
                }
            }
            modulo.setVisibilidad(visibilidad);
        }
        boolean todosInvisibles = modulos.stream().allMatch(modulo -> !modulo.isVisibilidad());
        if (todosInvisibles || modulos.isEmpty()) {
            model.addAttribute("noHayModulos", true);
        }
        model.addAttribute("listaModulos", modulos);

        return "principal";
    }

}
