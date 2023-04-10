/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.controladores;

import cat.copernic.copernicjobs.administrador.servicios.ModulService;
import cat.copernic.copernicjobs.general.servicios.RolModuloService;
import cat.copernic.copernicjobs.general.servicios.RolService;
import cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.general.utils.ThymeleafFunctions;
import cat.copernic.copernicjobs.model.Modulo;
import cat.copernic.copernicjobs.model.Rol;
import cat.copernic.copernicjobs.model.RolModulo;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author joang
 */
@Controller
public class VerModulos {

    @Autowired
    private ModulService moduloService;

    @Autowired
    private RolService rolService;

    @Autowired
    private RolModuloService rolModuloService;
    
    @Autowired
    private MessageSource messageSource;

    @PreAuthorize("hasAuthority('administrador')")
    @GetMapping("/administrador/veureModuls")
    public String inicio(Model model, @AuthenticationPrincipal UserDetails username) {

        var roles = rolService.listarRoles();
        //Ruta donde está el archivo html 
        String ruta = "administrador/";
        //nombre del archivo html
        String archivo = "verModulos";

        model.addAttribute("ThymeleafFunctions", ThymeleafFunctions.getInstance());
        model.addAttribute("modulos", moduloService.llistarModuls());
        model.addAttribute("roles", roles);
        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo, "Inici", username);
    }

    @PreAuthorize("hasAuthority('administrador')")
    @PostMapping("/administrador/guardar-modulos")
    public String guardar(@RequestParam(name = "modulos", required = false) List<String> checkModulos, @RequestParam(name = "visible", required = false) List<String> checkVisibles,RedirectAttributes redirectAttributes) {
        List<RolModulo> rolesModulosDB = rolModuloService.findAll();
        List<Rol> rolesDB = rolService.listarRoles();
        List<Modulo> modulosDB = moduloService.llistarModuls();

        List<RolModulo> rolesModulosHabilitados = new ArrayList<>();

        //Checkboxes de modulo que están seleccionados
        for (String checkModulo : checkModulos) {
            String[] rolModuloHabilitado = checkModulo.split(":");
            String rolHabilitado = rolModuloHabilitado[0];
            String moduloHabilitado = rolModuloHabilitado[1];

            RolModulo rolModuloFiltered = rolesModulosDB.stream().filter(rolModulo -> rolModulo.getRol().getNom().equals(rolHabilitado) && rolModulo.getModulo().getNombre().equals(moduloHabilitado)).findFirst().orElse(null);

            //Si es nulo creamos un RolModulo nuevo y lo guardamos
            if (rolModuloFiltered == null) {
                RolModulo nuevoRolModulo = new RolModulo();
                nuevoRolModulo.setModulo(modulosDB.stream().filter(modulo -> modulo.getNombre().equalsIgnoreCase(moduloHabilitado)).findFirst().orElse(null));
                nuevoRolModulo.setRol(rolesDB.stream().filter(rol -> rol.getNom().equalsIgnoreCase(rolHabilitado)).findFirst().orElse(null));

                String checkVisibleExists = checkVisibles
                        .stream()
                        .filter(checkVisible -> checkVisible
                        .split(":")[0]
                        .equalsIgnoreCase(rolHabilitado)
                        && checkVisible.split(":")[1]
                                .equalsIgnoreCase(moduloHabilitado))
                        .findFirst()
                        .orElse(null);

                nuevoRolModulo.setVisibilidad(checkVisibleExists != null);
                rolesModulosDB.add(nuevoRolModulo);
            } //Si no actualizamos la visibilidad de los roles.
            else {
                String checkVisibleExists = checkVisibles
                        .stream()
                        .filter(checkVisible -> checkVisible
                        .split(":")[0]
                        .equalsIgnoreCase(rolHabilitado)
                        && checkVisible.split(":")[1]
                                .equalsIgnoreCase(moduloHabilitado))
                        .findFirst()
                        .orElse(null);
                rolesModulosDB.stream().filter(rolModulo -> rolModulo.getId() == rolModuloFiltered.getId()).findFirst().orElse(null).setVisibilidad(checkVisibleExists != null);
            }

            rolesModulosHabilitados.add(rolesModulosDB.stream().filter(rolModulo -> rolModulo.getRol().getNom().equalsIgnoreCase(rolHabilitado) && rolModulo.getModulo().getNombre().equalsIgnoreCase(moduloHabilitado)).findFirst().orElse(null));
        }

        List<RolModulo> rolModulosAEliminar = Stream.concat(rolesModulosDB.stream().filter(t -> !rolesModulosHabilitados.contains(t)),
                rolesModulosHabilitados.stream().filter(t -> !rolesModulosDB.contains(t)))
                .distinct()
                .collect(Collectors.toList());

        rolModuloService.eliminarTodos(rolModulosAEliminar);
        rolModuloService.guardarTodos(rolesModulosHabilitados);
        redirectAttributes.addFlashAttribute("CanvisGuardats", messageSource.getMessage("info.alumneguardat", null, Locale.ENGLISH));

        return "redirect:/verModulos";
    }
}
