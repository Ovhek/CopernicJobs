/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.alumno.controladores;

import cat.copernic.copernicjobs.alumno.servicios.AlumnoService;
import cat.copernic.copernicjobs.general.utils.CargarPantallaPrincipal;
import cat.copernic.copernicjobs.general.utils.EncriptarContrasenya;
import cat.copernic.copernicjobs.general.utils.NavBarType;
import cat.copernic.copernicjobs.model.Alumno;
import cat.copernic.copernicjobs.model.Usuario;
import io.micrometer.common.lang.Nullable;
import jakarta.validation.Valid;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Alex
 */
@Controller
public class EditarPerfilAlumno {

    @Autowired //Anotació que injecta tots els mètodes i possibles dependències de UsuarioDAO
    private AlumnoService alumnoService; //Atribut per poder utilitzar les funcions CRUD de la interfície UsuarioDAO

    @Autowired
    private MessageSource messageSource;

    /**
     * Función encargada de cargar la pantalla correspondiente a al ruta "alumne/editarPerfil".
     * @param model Modelo Thymeleaf del HTML.
     * @param username Objeto correspondiente a los detalles del usuario.
     * @return HTML a mostrar.
     */
    @PreAuthorize("hasAuthority('alumne')")
    @GetMapping("/alumne/editarPerfil")
    public String inicio(Model model, @AuthenticationPrincipal UserDetails username) {
        //AuthenticationPrincipal hace que cargue los datos del usuario que ha cargado la sesion.
        Alumno alumno = alumnoService.buscarAlumnoPorUsername(username.getUsername());

        if (alumno == null) {
            return "/error";
        }

        //Ruta donde está el archivo html 
        String ruta = "alumno/";
        //nombre del archivo html
        String archivo = "editarPerfilAlumno";

        model.addAttribute("alumno", alumno);
        return CargarPantallaPrincipal.cargar(model, NavBarType.ALUMNO, ruta, archivo, "Editar Perfil", username);
    }

    /**
     * Función encargada de manjear la edición del perfil de usuario.
     * @param btnValue Valor del botón presionado con el atributo name = button
     * @param img Archivo de imagen
     * @param curriculum Archivo PDF
     * @param alumno Objeto Alumno que contiene los campos editados.
     * @param errores Objeto para manejar los errores (validaciones).
     * @param model Modelo del Thymeleaf del HTML.
     * @param username Detalles de usuario del usuario actual.
     * @param result Objeto que representa errores personalizados.
     * @param redirectAttributes Objeto encargado de manejar atributos del redirect.
     * @param passwordNueva String que representa la contraseña nueva que haya puesto el usuario.
     * @param confirmaPasswordNueva String que representa la confirmación de la contraseá que haya puesto el usuario.
     * @return 
     */
    @PreAuthorize("hasAuthority('alumne')")
    @PostMapping("/alumne/editarPerfil")
    public String editarPerfilAlumno(@RequestParam(name = "button", required = false) String btnValue, @Nullable @RequestParam(name = "profileImg") MultipartFile img, @Nullable @RequestParam(name = "profileCurriculum") MultipartFile curriculum, @Valid Alumno alumno, Errors errores, Model model, @AuthenticationPrincipal UserDetails username, BindingResult result, RedirectAttributes redirectAttributes, String passwordNueva, String confirmaPasswordNueva) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Alumno alumnoDB = alumnoService.buscarAlumno(alumno);

        if (btnValue.equals("baixa")) {
            alumnoDB.setBaja(true);
            alumnoDB.setFechaBaja(LocalDate.now());
            alumnoService.anadirAlumno(alumnoDB);
            return "redirect:/logout";
        }
        
        if (img != null && !img.getOriginalFilename().isEmpty()) {
            alumno.setAvatarLink(subirFichero(img, alumno, "img"));
        }
        if (curriculum != null && !curriculum.getOriginalFilename().isEmpty()) {
            alumno.setPdfLink(subirFichero(curriculum, alumno, "curriculum"));
        }
        ObjectError error = null;

        if (alumnoDB == null) {
            error = new ObjectError("UsuarioNoExiste", messageSource.getMessage("error.usuarionoexiste", null, Locale.ENGLISH));
            result.addError(error);
        }

        if (alumno.getUsername() != null && !alumno.getUsername().equals(alumnoDB.getUsername())) {
            Alumno alumnoTemp = alumnoService.buscarAlumnoPorUsername(alumno.getUsername());
            if (alumnoTemp != null) {
                error = new ObjectError("UsuarioYaExiste", messageSource.getMessage("error.usuarioyaexite", null, Locale.ENGLISH));
                result.addError(error);
            }
        }

        //No funciona
        if (alumno.getPassword() != null && alumnoDB.getPassword() != null && !passwordEncoder.matches(alumno.getPassword(), alumnoDB.getPassword())) {
            error = new ObjectError("ContraseñaActualNoCoincide", messageSource.getMessage("error.editarusuariocontrasenyanocoincide", null, Locale.ENGLISH));
            result.addError(error);
        }

        if (passwordNueva != null && !passwordNueva.equals(confirmaPasswordNueva)) {
            error = new ObjectError("ContraseñaNoCoincide", messageSource.getMessage("error.contrasenyanocoincide", null, Locale.ENGLISH));
            result.addError(error);
        }

        if (errores.hasErrors() || result.hasErrors()) { //Si s'han produït errors...
            List<String> erroresString = new ArrayList<>();
            errores.getAllErrors().forEach(err -> erroresString.add(err.getDefaultMessage()));

            List<String> erroresBindingString = new ArrayList<>();
            result.getAllErrors().forEach(err -> erroresBindingString.add(err.getDefaultMessage()));

            model.addAttribute("errores", erroresString);
            model.addAttribute("errores", erroresBindingString);
            return inicio(model, username);
        }

        //Comprobamos que el valor del botón de acción del post no sea nulo
        if (btnValue != null) {

            //Comprobamos que el botón pulsado es "Guardar Canvis"
            if (btnValue.equals("guardar")) {

                //Obtenemos los metodos de la entidad alumno.
                Method[] metodos = alumnoDB.getClass().getMethods();

                //Iteramos sobre los metodos
                for (Method metodo : metodos) {

                    //Comprobamos que el metodo comienza con "get" --> es un getter.
                    if (metodo.getName().startsWith("get") && metodo.getParameterCount() == 0) {
                        try {

                            //Nombre del metodo
                            String nombreMetodo = metodo.getName();

                            //Valor del metodo en la entidad Alumno de la BD
                            Object valueAlumnoDB = metodo.invoke(alumnoDB);

                            //Valor del metodo en la entidad Alumno del Post
                            Object valueAlumnoPost = null;
                            if (nombreMetodo.equals("getPassword") && !passwordNueva.isBlank()) {
                                valueAlumnoPost = EncriptarContrasenya.encryptar(passwordNueva);
                            } else if (nombreMetodo.equals("getPassword") && passwordNueva.isBlank()) {
                                continue;
                            } else {
                                valueAlumnoPost = metodo.invoke(alumno);
                            }

                            //Si el valor del alumno del Post es diferente de nulo lo sobreeescribimso en AlumnoDB.
                            if (valueAlumnoPost != null && !nombreMetodo.contains("Class")) {
                                String nombreMetodoSetter = nombreMetodo.replace("get", "set");
                                Method metodoSetter = Alumno.class.getMethod(nombreMetodoSetter, metodo.getReturnType());

                                metodoSetter.invoke(alumnoDB, valueAlumnoPost);
                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            } else {
                return "redirect:/alumne/veurePerfil";
            }
            String sexoDesc = "";
            switch (alumno.getSexo()) {
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
            alumnoDB.setSexoDesc(sexoDesc);
            alumnoService.anadirAlumno(alumnoDB);
        }
        redirectAttributes.addFlashAttribute("CanvisGuardats", messageSource.getMessage("info.alumneguardat", null, Locale.ENGLISH));
        return "redirect:/alumne/veurePerfil";
    }

    /**
     * Permite subir un fichero al servidor y obtener el enlace de su ubicación
     *
     * @param file Archvio a subir
     * @param alumno Usuario de tipo alumno
     * @param tipo tipo de archivo.
     * @return enlace del archivo en el servidor.
     */
    private String subirFichero(MultipartFile file, Usuario usuario, String tipo) {
        String link = "";
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            Path path = null;
            switch (tipo.toLowerCase()) {
                case "img":
                    path = Paths.get(messageSource.getMessage("ruta.images", null, Locale.ENGLISH) + usuario.getId());
                    link = "/files/img/" + usuario.getId() + "/profile." + FilenameUtils.getExtension(file.getOriginalFilename());
                    break;
                case "curriculum":
                    path = Paths.get(messageSource.getMessage("ruta.curriculum", null, Locale.ENGLISH) + usuario.getId());
                    link = "/files/curriculum/" + usuario.getId() + "/profile." + FilenameUtils.getExtension(file.getOriginalFilename());
                    break;
                default:
                    throw new AssertionError();
            }
            Files.createDirectories(path);
            path = Paths.get(path.toString(), "/" + "profile." + FilenameUtils.getExtension(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            // manejo de errores
        }
        return link;
    }
}
