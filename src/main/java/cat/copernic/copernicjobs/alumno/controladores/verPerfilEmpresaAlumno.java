package cat.copernic.copernicjobs.alumno.controladores;

import cat.copernic.copernicjobs.empresa.servicios.EmpresaService;
import cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Empresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * Controlador encargado de los endpoints de ver perfil de empresa por parte del alumno.
 * @author Alex
 */
@Controller
public class verPerfilEmpresaAlumno {

    @Autowired
    EmpresaService empresaService;

    /**
     *
     * Método que se encarga de mostrar la página de visualización de la
     * información de una empresa para el alumno.
     *
     * @param empresaGet la empresa a mostrar.
     * @param model el modelo utilizado para enviar información a la vista.
     * @param username los detalles del usuario autenticado.
     * @return la vista que muestra la información de la empresa para el alumno.
     */
    @PreAuthorize("hasAuthority('alumne')")
    @GetMapping("/alumne/veureEmpresaAlumne/{id}")
    public String inicio(Empresa empresaGet, Model model, @AuthenticationPrincipal UserDetails username) {
        Empresa empresa = empresaService.cercarEmpresa(empresaGet);
        model.addAttribute("empresa", empresa);

        String ruta = "alumno/";
        String archivo = "verEmpresaAlumno";

        return CargarPantallaPrincipal.cargar(model, NavBarType.ALUMNO, ruta, archivo, "Empresa - " + empresa.getNombreEmpresa(), username);
    }
}
