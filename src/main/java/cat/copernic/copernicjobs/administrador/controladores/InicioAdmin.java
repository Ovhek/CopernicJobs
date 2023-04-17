/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.controladores;

import cat.copernic.copernicjobs.dao.AlumnoDAO;
import cat.copernic.copernicjobs.dao.EmpresaDAO;
import cat.copernic.copernicjobs.administrador.servicios.NoticiaService;
import cat.copernic.copernicjobs.alumno.servicios.AlumnoService;
import cat.copernic.copernicjobs.empresa.servicios.EmpresaService;
import cat.copernic.copernicjobs.empresa.servicios.OfertaService;
import cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Alumno;
import cat.copernic.copernicjobs.model.Empresa;
import cat.copernic.copernicjobs.model.Incidencia;
import cat.copernic.copernicjobs.model.Noticia;
import cat.copernic.copernicjobs.model.Oferta;
import cat.copernic.copernicjobs.model.Persona;
import cat.copernic.copernicjobs.model.Rol;
import cat.copernic.copernicjobs.model.Usuario;
import jakarta.validation.Valid;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author joang
 */
@Controller
public class InicioAdmin {

    /*Modifiquem l'atribut noticiaDAO que teniem anteriorment per un atribut de tipus NoticiaService, ja que
     *ara accedirem a la base de dades mitjançant els mètodes d'aquesta classe, afegint una nova capa al 
     *nostre projecte.
     */
    /**
     * Servicio de Noticia utilizado para acceder a la base de datos y realizar
     * operaciones con las noticias.
     */
    @Autowired
    private NoticiaService noticiaService;

    /**
     * Servicio de Alumno utilizado para acceder a la base de datos y realizar
     * operaciones con los alumnos.
     */
    @Autowired
    private AlumnoService alumnoService;

    /**
     * Servicio de Empresa utilizado para acceder a la base de datos y realizar
     * operaciones con las empresas.
     */
    @Autowired
    private EmpresaService empresaService;

    /**
     * Servicio de Oferta utilizado para acceder a la base de datos y realizar
     * operaciones con las ofertas.
     */
    @Autowired
    private OfertaService ofertaService;

    /**
     * Método que maneja las peticiones GET a la URL '/administrador/inici' para
     * mostrar la página de inicio del administrador. Lista las noticias,
     * alumnos, empresas y ofertas guardadas en la base de datos y las añade al
     * modelo para su visualización en la página. Requiere autorización de tipo
     * 'administrador'.
     *
     * @param model Modelo para la vista
     * @param username Detalles del usuario autenticado
     * @return Nombre de la plantilla de la página principal del administrador
     */
    @PreAuthorize("hasAuthority('administrador')")
    @GetMapping("/administrador/inici") //Pàgina inicial d'admin
    public String inicio(Model model, @AuthenticationPrincipal UserDetails username) {

        //Ruta donde está el archivo html 
        String ruta = "administrador/";
        //nombre del archivo html
        String archivo = "inicioAdmin";

        //llistarNoticies() retorna el llistat d'objectes noticia guardats en la taula noticies de la BBDD    
        model.addAttribute("noticias", noticiaService.llistarNoticies());
        model.addAttribute("validaciones", alumnoService.listarAlumnos());
        model.addAttribute("validaciones2", empresaService.llistarEmpreses());
        model.addAttribute("validaciones3", ofertaService.llistarOfertas());

        //Cargamos el archivo y lo añadimos a la plantilla de la página principal
        return CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo, "Inici", username);

    }

    /**
     * Método que maneja las peticiones GET a la URL '/crearNoticia' para
     * mostrar la página de creación de noticias. Crea automáticamente una
     * noticia con atributos vacíos si no existe. Requiere autorización de tipo
     * 'administrador'.
     *
     * @param noticia Noticia pasada como parámetro
     * @param model Modelo para la vista
     * @param username Detalles del usuario autenticado
     * @return Nombre de la plantilla de la página de creación de noticias
     */
    @PreAuthorize("hasAuthority('administrador')")
    @GetMapping("/crearNoticia") //URL a la pàgina amb el formulari de les dades de la noticia
    public String crearNoticia(Noticia noticia, Model model, @AuthenticationPrincipal UserDetails username) {

        String ruta = "administrador/";

        String archivo = "crearNoticia";

        return CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo, "Noticia", username);
    }

    /**
     * Definición del método para asignar los valores introducidos en el
     * formulario a un objeto Noticia pasado como parámetro.
     *
     * La anotación @PostMapping indica que el método se utiliza para enviar los
     * datos a través de una petición HTTP POST. El valor de la acción del
     * formulario se pasa como parámetro en la URL, de esta manera el sistema
     * identifica el método al cual debe enviar los datos introducidos en el
     * formulario.
     *
     * @param noticia Objeto Noticia al cual se asignarán los valores del
     * formulario.
     * @param errors Objeto Errors que contiene los errores de validación del
     * formulario.
     * @param model Objeto Model utilizado para agregar atributos a la vista.
     * @param username Objeto UserDetails que representa los detalles del
     * usuario autenticado.
     * @return La vista a la que se redirecciona después de procesar los datos
     * del formulario.
     */
    @PreAuthorize("hasAuthority('administrador')")
    @PostMapping("/guardarNoticia") //action=guardarNoticia
    public String guardarNoticia(@Valid Noticia noticia, Errors errors, Model model, @AuthenticationPrincipal UserDetails username) {

        if (errors.hasErrors()) { //Si s'han produït errors...
            String ruta = "administrador/";

            String archivo = "crearNoticia";

            return CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo, "Noticia", username);
        }

        noticia.setFechaHora(LocalDate.now());

        noticiaService.afegirNoticia(noticia); //Afegim la noticia passada per paràmetre a la base de dades

        return "redirect:/administrador/inici"; //Retornem a la pàgina inicial dels gossos mitjançant redirect
    }

    /**
     * Definición del método que retorna la vista crearNoticia, en la cual se
     * muestran los datos de la noticia con el idnoticia enviado desde la página
     * inicial.
     *
     * El sistema Spring asocia el idnoticia pasado como parámetro en
     *
     * @GetMapping con el objeto Noticia pasado como parámetro en el método
     * editar, y llama automáticamente al método setIdnoticia de la clase
     * Noticia para realizar esta asociación, es decir, realiza
     * noticia.setIdnoticia(idnoticia).
     *
     * @param noticia Objeto Noticia que se utilizará para obtener los datos de
     * la noticia a editar.
     * @param model Objeto Model utilizado para agregar atributos a la vista.
     * @param username Objeto UserDetails que representa los detalles del
     * usuario autenticado.
     * @return La vista a la que se redirecciona después de obtener los datos de
     * la noticia.
     */
    @PreAuthorize("hasAuthority('administrador')")
    @GetMapping("/editar/{id}")
    public String editar(Noticia noticia, Model model, @AuthenticationPrincipal UserDetails username) {

        /*Cerquem la noticia passada per paràmetre, al qual li correspón l'id de @GetMapping mitjançant 
         *el mètode cercarNoticia de la capa de servei.*/
        String ruta = "administrador/";

        String archivo = "crearNoticia";

        model.addAttribute("noticia", noticiaService.cercarNoticia(noticia));

        return CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo, "Noticia", username);
    }

    /**
     * Definición del método para eliminar la noticia en la base de datos y
     * redireccionar a la página de inicio.
     *
     * La noticia se elimina mediante el método eliminarNoticia de la clase
     * NoticiaService.
     *
     * El sistema asocia el id de la noticia al objeto Noticia pasado como
     * parámetro de la misma forma que en el método editar.
     *
     * @param noticia Objeto Noticia que se utilizará para obtener el id de la
     * noticia a eliminar.
     * @param username Objeto UserDetails que representa los detalles del
     * usuario autenticado.
     * @return La vista a la que se redirecciona después de eliminar la noticia.
     */
    @PreAuthorize("hasAuthority('administrador')")
    @GetMapping("/eliminar/{id}")
    public String eliminar(Noticia noticia, @AuthenticationPrincipal UserDetails username) {

        /*Eliminem la noticia passada per paràmetre, al qual li correspón l'id de @GetMapping mitjançant 
         *el mètode eliminarNoticia de la capa de servei.*/
        noticiaService.eliminarNoticia(noticia);

        return "redirect:/administrador/inici"; //Retornem a la pàgina inicial dels gossos mitjançant redirect
    }

    /**
     * Método para validar un alumno.
     *
     * @param alumno Objeto Alumno a validar.
     * @param model Modelo de datos para la vista.
     * @param username Objeto UserDetails que representa al usuario autenticado.
     * @return String con la vista de carga de pantalla principal.
     */
    @PreAuthorize("hasAuthority('administrador')")
    @GetMapping("/validarRegistreAlumne/{id}")
    public String validarAlumne(Alumno alumno, Model model, @AuthenticationPrincipal UserDetails username) {

        String ruta = "administrador/";

        String archivo = "validarRegistreAlumne";

        model.addAttribute("alumno", alumnoService.buscarAlumno(alumno));

        return CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo, "ValidacioAlumne", username);
    }

    /**
     * Método para validar una empresa.
     *
     * @param empresa Objeto Empresa a validar.
     * @param model Modelo de datos para la vista.
     * @param username Objeto UserDetails que representa al usuario autenticado.
     * @return String con la vista de carga de pantalla principal.
     */
    @PreAuthorize("hasAuthority('administrador')")
    @GetMapping("/validarRegistreEmpresa/{id}")
    public String validarEmpresa(Empresa empresa, Model model, @AuthenticationPrincipal UserDetails username) {

        String ruta = "administrador/";

        String archivo = "validarRegistreEmpresa";

        model.addAttribute("empresa", empresaService.cercarEmpresa(empresa));

        return CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo, "ValidacioEmpresa", username);
    }

    /**
     * Método para validar una oferta.
     *
     * @param oferta Objeto Oferta a validar.
     * @param model Modelo de datos para la vista.
     * @param username Objeto UserDetails que representa al usuario autenticado.
     * @return String con la vista de carga de pantalla principal.
     */
    @PreAuthorize("hasAuthority('administrador')")
    @GetMapping("/validarOferta/{id}")
    public String validarOferta(Oferta oferta, Model model, @AuthenticationPrincipal UserDetails username) {

        String ruta = "administrador/";

        String archivo = "validarOferta";

        model.addAttribute("oferta", ofertaService.cercarOferta(oferta));

        return CargarPantallaPrincipal.cargar(model, NavBarType.ADMINISTRADOR, ruta, archivo, "ValidacioOferta", username);
    }

    /**
     * Método para realizar la validación de una oferta.
     *
     * @param oferta Objeto Oferta a validar.
     * @param model Modelo de datos para la vista.
     * @param username Objeto UserDetails que representa al usuario autenticado.
     * @return String con la vista de carga de pantalla principal.
     */
    @PreAuthorize("hasAuthority('administrador')")
    @PostMapping("/validarOferta")
    public String validacionOferta(Oferta oferta, Model model, @AuthenticationPrincipal UserDetails username) {
        oferta.setFechaValidacion(LocalDate.now());
        ofertaService.afegirOferta(oferta);
        return "redirect:/administrador/inici";
    }

    /**
     * Método para realizar la validación de un alumno.
     *
     * @param alumno Objeto Alumno a validar.
     * @param model Modelo de datos para la vista.
     * @param username Objeto UserDetails que representa al usuario autenticado.
     * @return String con la vista de carga de pantalla principal.
     */
    @PreAuthorize("hasAuthority('administrador')")
    @PostMapping("/validarAlumno")
    public String validacionAlumno(Alumno alumno, Model model, @AuthenticationPrincipal UserDetails username) {
        Alumno alumnoDB = alumnoService.buscarAlumno(alumno);
        alumnoDB.setFechaValidacion(LocalDate.now());
        alumnoService.anadirAlumno(alumnoDB);
        return "redirect:/administrador/inici";
    }

    /**
     * Método para realizar la validación de una empresa.
     *
     * @param empresa Objeto Empresa a validar.
     * @param model Modelo de datos para la vista.
     * @param username Objeto UserDetails que representa al usuario autenticado.
     * @return String con la vista de carga de pantalla principal.
     */
    @PreAuthorize("hasAuthority('administrador')")
    @PostMapping("/validarEmpresa")
    public String validacionEmpresa(Empresa empresa, Model model, @AuthenticationPrincipal UserDetails username) {

        //Buscamos la empresa en la BD.
        Empresa empresaDB = empresaService.cercarEmpresa(empresa);

        //Obtenemos los metodos de la entidad empresa.
        Method[] metodos = empresaDB.getClass().getMethods();

        //Iteramos sobre los metodos
        for (Method metodo : metodos) {

            //Comprobamos que el metodo comienza con "get" --> es un getter.
            if (metodo.getName().startsWith("get") && metodo.getParameterCount() == 0) {
                try {

                    //Nombre del metodo
                    String nombreMetodo = metodo.getName();

                    //Valor del metodo en la entidad Alumno de la BD
                    Object valueEmpresaDB = metodo.invoke(empresaDB);

                    //Valor del metodo en la entidad Alumno del Post
                    Object valueEmpresaPost = metodo.invoke(empresa);

                    //Si el valor del alumno del Post es diferente de nulo lo sobreeescribimso en AlumnoDB.
                    if (valueEmpresaPost != null && !nombreMetodo.contains("Class")) {
                        String nombreMetodoSetter = nombreMetodo.replace("get", "set");
                        Method metodoSetter = Empresa.class.getMethod(nombreMetodoSetter, metodo.getReturnType());

                        metodoSetter.invoke(empresaDB, valueEmpresaPost);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        empresaDB.setFechaValidacion(LocalDate.now());
        empresaService.afegirEmpresa(empresaDB);
        return "redirect:/administrador/inici";
    }
}
