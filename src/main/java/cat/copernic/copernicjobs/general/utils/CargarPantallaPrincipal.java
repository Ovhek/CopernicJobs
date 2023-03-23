/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.general.utils;

import cat.copernic.copernicjobs.administrador.servicios.AdministradorService;
import cat.copernic.copernicjobs.alumno.servicios.AlumnoService;
import cat.copernic.copernicjobs.dao.AdministradorDAO;
import cat.copernic.copernicjobs.dao.AlumnoDAO;
import cat.copernic.copernicjobs.dao.EmpresaDAO;
import cat.copernic.copernicjobs.empresa.servicios.EmpresaService;
import cat.copernic.copernicjobs.model.Administrador;
import cat.copernic.copernicjobs.model.Alumno;
import cat.copernic.copernicjobs.model.Empresa;
import cat.copernic.copernicjobs.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

/**
 *
 * Función especializada en cargar la pantalla principal como plantilla para las
 * demás pantallas.
 */
@Component
public class CargarPantallaPrincipal {

    //@Autowired
    //private AlumnoService alumnoService;

    //@Autowired
    //AdministradorService alumnoService;
    //@Autowired
    //private EmpresaService empresaService;

    private static AlumnoService staticAlumnoService;
    private static EmpresaService staticEmpresaService;
    private static AdministradorService staticAdministradorService;

    
    /**
     * Función que se ejecuta después de realizar la inyección de dependencias de Autowired.Esta función asigna los valores de la inyección de dependencias a los campos estaticos.
     * @param alumnoService
     */    
    @Autowired
    public CargarPantallaPrincipal(AlumnoService alumnoService, EmpresaService empresaService, AdministradorService administradorService, EmpresaDAO empresaDAO, AlumnoDAO alumnoDAO, AdministradorDAO administradorDAO){
        staticAlumnoService = alumnoService;
        staticAlumnoService.setAlumnoDAO(alumnoDAO);
        staticEmpresaService = empresaService;
        staticEmpresaService.setEmpresa(empresaDAO);
        staticAdministradorService = administradorService;
        staticAdministradorService.setAdministradorDAO(administradorDAO);
    }

    /**
     * Función para cargar la plantilla de la pantalla Principal.
     *
     * @param model Modelo de springboot UI
     * @param tipo Tipo de Menu
     * @param ruta Ruta de la página a cargar sobre la plantilla
     * @param archivo Archivo html (sin .html) de la página la cual cargar en la
     * plantilla.
     * @param titulo Título que tendrá la página.
     * @return devuelve la plantilla
     */
    public static String cargar(Model model, NavBarType tipo, String ruta, String archivo, String titulo, UserDetails user) {

        //TODO: Usuario que ha iniciado sesión
        Usuario usuario = null;
        //Ruta del archvio de navegación
        String rutaNav = "";
        //Nombre del archvio de navegación sin .html
        String archivoNav = "";
        switch (tipo) {
            case ALUMNO:
                rutaNav = "alumno/";
                archivoNav = "_navAlumno";
                usuario = staticAlumnoService.buscarAlumnoPorUsername(user.getUsername());
                break;
            case EMPRESA:
                rutaNav = "empresa/";
                archivoNav = "_navEmpresa";
                usuario = staticEmpresaService.buscarPorUsername(user.getUsername());
                break;
            case ADMINISTRADOR:
                rutaNav = "administrador/";
                archivoNav = "_navAdministrador";
                usuario = staticAdministradorService.buscarAdministradorPorUsername(user.getUsername());
                break;
            default:
                throw new AssertionError();
        }

        if(tipo == NavBarType.ALUMNO) model.addAttribute("esAlumno",true);
        
        model.addAttribute("usuario", usuario);
        model.addAttribute("titulo", titulo);
        //Añadimos los atributos rutaNav y archivoNav dependiendo del tipo de navegación
        model.addAttribute("rutaNav", rutaNav);
        model.addAttribute("archivoNav", archivoNav);

        //Añadimos los atributos rutaContenido y archivoContenido al HTML dependiendo del valor
        model.addAttribute("rutaContenido", ruta);
        model.addAttribute("archivoContenido", archivo);

        //Devuelve la plantilla de la página Principal con la navegación añadida y el contenido dinamico añadido.
        return "layout/_plantillaPaginaPrincipal";
    }
    
    
    //TODO: ELIMINAR DESPUÉS.
    public static String cargar(Model model, NavBarType tipo, String ruta, String archivo) {

        //TODO: Usuario que ha iniciado sesión
        int id = 1;
        Usuario usuario = null;

        //Ruta del archvio de navegación
        String rutaNav = "";
        //Nombre del archvio de navegación sin .html
        String archivoNav = "";
        switch (tipo) {
            case ALUMNO:
                rutaNav = "alumno/";
                archivoNav = "_navAlumno";

                Alumno alumno = new Alumno();
                alumno.setId(id);
                usuario = staticAlumnoService.buscarAlumno(alumno);
                break;
            case EMPRESA:
                rutaNav = "empresa/";
                archivoNav = "_navEmpresa";

                Empresa empresa = new Empresa();
                empresa.setId(id);
                usuario = staticEmpresaService.cercarEmpresa(empresa);
                break;
            case ADMINISTRADOR:
                rutaNav = "administrador/";
                archivoNav = "_navAdministrador";

                //TODO: Usar el AdministradorService para obtener el admin.
                Administrador administrador = new Administrador();
                administrador.setId(id);
                usuario = staticAdministradorService.buscarAdministrador(administrador);
                break;
            default:
                throw new AssertionError();
        }

        model.addAttribute("usuario", usuario);

        //Añadimos los atributos rutaNav y archivoNav dependiendo del tipo de navegación
        model.addAttribute("rutaNav", rutaNav);
        model.addAttribute("archivoNav", archivoNav);

        //Añadimos los atributos rutaContenido y archivoContenido al HTML dependiendo del valor
        model.addAttribute("rutaContenido", ruta);
        model.addAttribute("archivoContenido", archivo);

        //Devuelve la plantilla de la página Principal con la navegación añadida y el contenido dinamico añadido.
        return "layout/_plantillaPaginaPrincipal";
    }
}
