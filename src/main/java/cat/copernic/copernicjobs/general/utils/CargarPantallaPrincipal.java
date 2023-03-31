/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.general.utils;

import cat.copernic.copernicjobs.administrador.servicios.AdministradorService;
import cat.copernic.copernicjobs.administrador.servicios.ModulService;
import cat.copernic.copernicjobs.alumno.servicios.AlumnoService;
import cat.copernic.copernicjobs.dao.AdministradorDAO;
import cat.copernic.copernicjobs.dao.AlumnoDAO;
import cat.copernic.copernicjobs.dao.EmpresaDAO;
import cat.copernic.copernicjobs.dao.ModuloDAO;
import cat.copernic.copernicjobs.dao.RolModuloDAO;
import cat.copernic.copernicjobs.empresa.servicios.EmpresaService;
import cat.copernic.copernicjobs.general.servicios.RolModuloService;
import cat.copernic.copernicjobs.model.Modulo;
import cat.copernic.copernicjobs.model.RolModulo;
import cat.copernic.copernicjobs.model.Usuario;
import java.util.List;
import javax.lang.model.util.ElementFilter;
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
    private static RolModuloService staticRolModuloService;
    private static ModulService staticModuloService;

    /**
     * Función que se ejecuta después de realizar la inyección de dependencias
     * de Autowired.Esta función asigna los valores de la inyección de
     * dependencias a los campos estaticos.
     *
     * @param alumnoService
     */
    @Autowired
    public CargarPantallaPrincipal(AlumnoService alumnoService, EmpresaService empresaService, AdministradorService administradorService, RolModuloService rolModuloService, ModulService moduloService, EmpresaDAO empresaDAO, AlumnoDAO alumnoDAO, AdministradorDAO administradorDAO, RolModuloDAO rolmoduloDAO, ModuloDAO moduloDAO) {
        staticAlumnoService = alumnoService;
        staticAlumnoService.setAlumnoDAO(alumnoDAO);
        staticEmpresaService = empresaService;
        staticEmpresaService.setEmpresa(empresaDAO);
        staticAdministradorService = administradorService;
        staticAdministradorService.setAdministradorDAO(administradorDAO);
        staticRolModuloService = rolModuloService;
        staticRolModuloService.setRolModuloDao(rolmoduloDAO);
        staticModuloService = moduloService;
        staticModuloService.setModulo(moduloDAO);
    }

    private static String moduloRequeridoRuta;

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

        //Comprobamos si el rol puede acceder al modulo.
        String rolUsuario = user.getAuthorities().iterator().next().getAuthority();

        String rutaSinCaracteresEspeciales = ruta.replaceAll("[^a-zA-Z0-9]", "");

        switch (rutaSinCaracteresEspeciales) {
            case "alumno":
                moduloRequeridoRuta = "alumne";
                break;
            default:
                moduloRequeridoRuta = ruta;
        }

        moduloRequeridoRuta = moduloRequeridoRuta.replaceAll("[^a-zA-Z0-9]", "");

        List<Modulo> modulosDB = staticModuloService.llistarModuls();
        Modulo accediendoA = modulosDB.stream().filter(modulo -> modulo.getNombre().equals(moduloRequeridoRuta)).findFirst().orElse(null);

        if (accediendoA != null) {
            boolean puedeAcceder = false;

            for (RolModulo rolModulo : accediendoA.getRoles()) {
                puedeAcceder = rolModulo.getRol().getNom().equals(rolUsuario);
                if (puedeAcceder) {
                    break;
                }
            }

            if (!puedeAcceder) {
                return "error";
            }
        }

        //TODO: Usuario que ha iniciado sesión
        Usuario usuario = staticAlumnoService.buscarAlumnoPorUsername(user.getUsername());
        if (usuario == null) {
            usuario = staticAdministradorService.buscarAdministradorPorUsername(user.getUsername());
        }
        if (usuario == null) {
            usuario = staticEmpresaService.buscarPorUsername(user.getUsername());
        }

        //Ruta del archvio de navegación
        String rutaNav = "";
        //Nombre del archvio de navegación sin .html
        String archivoNav = "";
        switch (tipo) {
            case ALUMNO:
                rutaNav = "alumno/";
                archivoNav = "_navAlumno";
                break;
            case EMPRESA:
                rutaNav = "empresa/";
                archivoNav = "_navEmpresa";
                break;
            case ADMINISTRADOR:
                rutaNav = "administrador/";
                archivoNav = "_navAdministrador";
                break;
            default:
                throw new AssertionError();
        }

        if (usuario.getRol().getId() == 1) {
            model.addAttribute("esAlumno", true);
        }

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
}
