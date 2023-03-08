/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.controladores;

import cat.copernic.copernicjobs.administrador.servicios.EmpresaService;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Empresa;
import java.time.LocalDate;
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
public class RegistrarEmpresa {
    
    @Autowired
    EmpresaService empresaService;
    
    @GetMapping("/registrarEmpresa")
    public String inicio(Model model){
        
        //Ruta donde está el archivo html 
        String ruta = "administrador/";
        //nombre del archivo html
        String archivo = "registrarEmpresa";
        
        Empresa empresa = new Empresa();
        model.addAttribute("empresa", new Empresa());
        
        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo);
    }
        
    @PostMapping("/registreEmpresa")
    public String registrarEmpresa(Empresa empresa, String contrasenyaRepetida) {
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

        empresaService.afegirEmpresa(empresa);
        
        return "redirect:/registrarUsuaris";
    }
}

