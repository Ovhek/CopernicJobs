/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.controladores;

import cat.copernic.copernicjobs.empresa.servicios.EmpresaService;
import cat.copernic.copernicjobs.empresa.servicios.OfertaService;
import cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Empresa;
import cat.copernic.copernicjobs.model.Oferta;
import jakarta.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Clase controladora que maneja las peticiones relacionadas con la
 * visualización y búsqueda de ofertas en el modo administrador. Requiere que el
 * usuario tenga la autoridad 'administrador' para acceder a estos recursos.
 *
 * @author joang
 */
@Controller
public class VerOfertasAdmin {

    @Autowired
    private OfertaService ofertaService;

    @Autowired
    EmpresaService empresaService;

    /**
     * Método que maneja la petición GET para mostrar la página de inicio de las
     * ofertas en el modo administrador.
     *
     * @param empresa Objeto de tipo Empresa utilizado para filtrar las ofertas
     * por empresa.
     * @param model Objeto de tipo Model que permite agregar atributos para ser
     * enviados a la vista.
     * @param username Objeto de tipo UserDetails que representa los detalles
     * del usuario autenticado.
     *
     * @return La vista a cargar después de procesar la petición.
     */
    @PreAuthorize("hasAuthority('administrador')")
    @GetMapping("/verOfertasAdmin/{id}")
    public String inicio(Empresa empresa, Model model, @AuthenticationPrincipal UserDetails username) {

        Empresa emp = empresaService.cercarEmpresa(empresa);
        //Ruta donde está el archivo html 
        String ruta = "administrador/";
        //nombre del archivo html
        String archivo = "verOfertasAdmin";

        model.addAttribute("ofertas", ofertaService.listarPorNombre(emp.getNombreEmpresa()));
        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo, "Inici", username);
    }

    /**
     * Método que maneja la petición GET para mostrar los detalles de una oferta
     * en el modo administrador.
     *
     * @param oferta Objeto de tipo Oferta a visualizar.
     * @param model Objeto de tipo Model que permite agregar atributos para ser
     * enviados a la vista.
     * @param username Objeto de tipo UserDetails que representa los detalles
     * del usuario autenticado.
     *
     * @return La vista a cargar después de procesar la petición.
     */
    @PreAuthorize("hasAuthority('administrador')")
    @GetMapping("/verOfertaAdmin/{id}")
    public String ver(Oferta oferta, Model model, @AuthenticationPrincipal UserDetails username) {

        String ruta = "administrador/";

        String archivo = "verOfertaAdmin";

        model.addAttribute("oferta", ofertaService.cercarOferta(oferta));

        return CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo, "Inici", username);
    }

    /**
     * Método que maneja la petición POST para realizar la búsqueda de ofertas
     * en el modo administrador.
     *
     * @param btnValue Valor del botón de búsqueda.
     * @param buscar Valor del input de búsqueda.
     * @param ordenar Valor del select de ordenación.
     * @param username Objeto de tipo UserDetails que representa los detalles
     * del usuario autenticado.
     * @param model Objeto de tipo Model que permite agregar atributos para ser
     * enviados a la vista.
     * @param request Objeto de tipo HttpServletRequest utilizado para obtener
     * información de la petición HTTP.
     * @param redirectAttributes Objeto de tipo RedirectAttributes utilizado
     * para agregar atributos en la redirección.
     * @param empresa Objeto de tipo Empresa utilizado para filtrar las ofertas
     * por empresa.
     *
     * @return La vista a cargar después de procesar la petición.
     */
    @PostMapping("/buscarOferta/{id}")
    public String buscarOferta(@RequestParam(name = "buscar") String btnValue, @RequestParam(name = "search-input") String buscar, @RequestParam(name = "sort-select") String ordenar, @AuthenticationPrincipal UserDetails username, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes, Empresa empresa) {
        Empresa emp = empresaService.cercarEmpresa(empresa);

        model.addAttribute("ofertas", ofertaService.filtrarOfertasOrdenacion(buscar, ordenar, emp.getNombreEmpresa()));

        return "redirect:/verOfertasAdmin/{id}";
    }
}
