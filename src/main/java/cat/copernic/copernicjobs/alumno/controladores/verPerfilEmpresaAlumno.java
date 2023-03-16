package cat.copernic.copernicjobs.alumno.controladores;


import cat.copernic.copernicjobs.empresa.servicios.EmpresaService;
import cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Empresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Cole
 */
@Controller
public class verPerfilEmpresaAlumno {

    @Autowired
    EmpresaService empresaService;
    @PreAuthorize("hasAuthority('alumne')")
    @GetMapping("/alumne/veureEmpresaAlumne/{id}")
    public String inicio(Empresa empresaGet, Model model) {
        Empresa empresa = empresaService.cercarEmpresa(empresaGet);
        model.addAttribute("empresa", empresa);

        String ruta = "alumno/";
        String archivo = "verEmpresaAlumno";

        return CargarPantallaPrincipal.cargar(model, NavBarType.ALUMNO, ruta, archivo, "Empresa - " + empresa.getNombreEmpresa());
    }
}
