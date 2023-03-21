/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.controladores;

import cat.copernic.copernicjobs.empresa.servicios.EmpresaService;
import cat.copernic.copernicjobs.general.utils.EncriptarContrasenya;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Empresa;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author joang
 */
@Controller
public class RegistrarEmpresa {

    @Autowired
    EmpresaService empresaService;

    @Autowired
    private MessageSource messageSource;

    @PreAuthorize("hasAuthority('administrador')")
    @GetMapping("/registrarEmpresa")
    public String inicio(Model model, @AuthenticationPrincipal UserDetails username) {

        //Ruta donde está el archivo html 
        String ruta = "administrador/";
        //nombre del archivo html
        String archivo = "registrarEmpresa";

        Empresa empresa = new Empresa();
        model.addAttribute("empresa", new Empresa());

        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo);
    }

    @PreAuthorize("hasAuthority('administrador')")
    @PostMapping("/registreEmpresa")
    public String registrarEmpresa(@Valid Empresa empresa, Errors errores, BindingResult result, String contrasenyaRepetida, Model model, @AuthenticationPrincipal UserDetails username) {

        if (!empresa.getPassword().equals(contrasenyaRepetida)) {
            ObjectError error = new ObjectError("Contrasenya", messageSource.getMessage("error.contrasenyanocoincide", null, Locale.ENGLISH));
            result.addError(error);
        }

        if (empresaService.buscarEmpresaPorUsername(empresa.getUsername()) != null) {
            ObjectError error = new ObjectError("Username", messageSource.getMessage("error.usuarioyaexite", null, Locale.ENGLISH));
            result.addError(error);
        }
        if (errores.hasErrors() || result.hasErrors()) { //Si s'han produït errors...

            //Ruta donde está el archivo html 
            String ruta = "administrador/";
            //nombre del archivo html
            String archivo = "registrarEmpresa";

            return cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo);
        }

        if (result.hasErrors()) {
            return "redirect:/registrarEmpresa";
        }

        String sexoDesc = "";
        switch (empresa.getSexo()) {
            case 1:
                sexoDesc = "Home";
                break;
            case 2:
                sexoDesc = "Dona";
                break;
            case 3:
                sexoDesc = "Altre";
                break;
            case 4:
                sexoDesc = "Prefereixo no dir'ho";
                break;
            default:
                sexoDesc = "Invalid";
        }

        empresa.setSexoDesc(sexoDesc);
        empresa.setFechaRegistro(LocalDate.now());
        empresa.setPassword(EncriptarContrasenya.encryptar(empresa.getPassword()));
        empresaService.afegirEmpresa(empresa);

        model.addAttribute("registrado", true);

        return "redirect:/registrarUsuaris";
    }
}
