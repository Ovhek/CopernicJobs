/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.general.controladores;

import cat.copernic.copernicjobs.empresa.servicios.EmpresaService;
import cat.copernic.copernicjobs.model.Alumno;
import cat.copernic.copernicjobs.model.Empresa;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controlador de los endpoints del registro como empresa,
 * @author Albert
 */
@Controller
public class RegistratComEmpresa {
    
    @Autowired
    EmpresaService empresaService;
    
    @Autowired
    private MessageSource messageSource;
    
    @GetMapping("/registratComEmpresa")
    public String inicio(Model model){
        model.addAttribute("empresa", new Empresa());
        return "registratComEmpresa";
    }

    @PostMapping("/crearempresa")
    public String crearEmpresa(@Valid Empresa empresa, Errors errores, BindingResult result, Model model, String password, String repeteix_contrasenya){
        
        if (!password.equals(repeteix_contrasenya)) {
            ObjectError error = new ObjectError("Contrasenya", messageSource.getMessage("error.contrasenyanocoincide", null, Locale.ENGLISH));
            result.addError(error);
        }
        
        if (errores.hasErrors() || result.hasErrors()) { //Si s'han produït errors...

            return "/registratComEmpresa"; //Mostrem la pàgina del formulari
        }
                
        
        
        empresa.setFechaRegistro(LocalDate.now());
        empresaService.afegirEmpresa(empresa);
        return "redirect:login";
    }

}
