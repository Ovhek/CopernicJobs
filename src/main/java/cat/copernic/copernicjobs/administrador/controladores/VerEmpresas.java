/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.controladores;

import cat.copernic.copernicjobs.dao.EmpresaDAO;
import cat.copernic.copernicjobs.empresa.servicios.EmpresaService;
import cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Empresa;
import cat.copernic.copernicjobs.model.Oferta;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador que maneja las acciones relacionadas con la visualización de
 * empresas, solo accesibles por usuarios con autoridad de 'administrador'.
 *
 * Este controlador proporciona dos rutas accesibles mediante el método GET: -
 * "/verEmpresas": Muestra una lista de empresas en la página "verEmpresas.html"
 * - "/verEmpresa/{id}": Muestra los detalles de una empresa específica en la
 * página "verEmpresa.html"
 *
 * Este controlador utiliza anotaciones de seguridad para restringir el acceso a
 * las rutas solo a usuarios con la autoridad de 'administrador' mediante la
 * anotación @PreAuthorize.
 *
 * Además, se inyecta una instancia de EmpresaService mediante la anotación
 *
 * @Autowired para realizar operaciones relacionadas con las empresas.
 *
 * @author joang
 */
@Controller
public class VerEmpresas {

    @Autowired
    private EmpresaService empresaService;

    /**
     * Método que maneja la petición GET a la ruta "/verEmpresas" y muestra una
     * lista de empresas en la página "verEmpresas.html".
     *
     * @param model El modelo de la vista.
     * @param username El objeto UserDetails que representa los detalles del
     * usuario autenticado.
     * @return La vista "verEmpresas.html" con la lista de empresas.
     */
    @PreAuthorize("hasAuthority('administrador')")
    @GetMapping("/verEmpresas")
    public String inicio(Model model, @AuthenticationPrincipal UserDetails username) {

        //Ruta donde está el archivo html 
        String ruta = "administrador/";
        //nombre del archivo html
        String archivo = "verEmpresas";

        model.addAttribute("empresas", empresaService.llistarEmpreses());

        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo, "Inici", username);
    }

    /**
     * Método que maneja la petición GET a la ruta "/verEmpresa/{id}" y muestra
     * los detalles de una empresa específica en la página "verEmpresa.html".
     *
     * @param empresa El objeto Empresa que representa la empresa a mostrar.
     * @param model El modelo de la vista.
     * @param username El objeto UserDetails que representa los detalles del
     * usuario autenticado.
     * @return La vista "verEmpresa.html" con los detalles de la empresa.
     */
    @PreAuthorize("hasAuthority('administrador')")
    @GetMapping("/verEmpresa/{id}")
    public String ver(Empresa empresa, Model model, @AuthenticationPrincipal UserDetails username) {

        String ruta = "administrador/";

        String archivo = "verEmpresa";

        model.addAttribute("empresa", empresaService.cercarEmpresa(empresa));

        return CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo, "Inici", username);

    }
}
