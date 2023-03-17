/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.general.controladores;

import cat.copernic.copernicjobs.empresa.servicios.EmpresaService;
import cat.copernic.copernicjobs.model.Empresa;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Cole
 */
@Controller
public class RegistratComEmpresa {
    
    @Autowired
    EmpresaService empresaService;
    
    @GetMapping("/registratComEmpresa")
    public String inicio(){
        return "registratComEmpresa";
    }

    @PostMapping("/crearempresa")
    public String crearEmpresa(Empresa empresa){
        empresa.setFechaRegistro(LocalDate.now());
        empresaService.afegirEmpresa(empresa);
        return null;
    }

}
