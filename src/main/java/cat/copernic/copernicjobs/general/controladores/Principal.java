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
 *
 * @author Albert
 */
@Controller
public class Principal {

    @Autowired
    RolModuloService rolModuloService;

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
        if(todosInvisibles || modulos.isEmpty()) model.addAttribute("noHayModulos",true);
        model.addAttribute("listaModulos", modulos);

        return "principal";
    }

}
