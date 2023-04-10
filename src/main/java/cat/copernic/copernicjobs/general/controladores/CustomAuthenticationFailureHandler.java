/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.general.controladores;

import cat.copernic.copernicjobs.errores.UsuarioBajaException;
import cat.copernic.copernicjobs.errores.UsuarioNoValidado;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

/**
 *
 * Clase que implementa la interfaz AuthenticationFailureHandler de Spring para
 * manejar las situaciones en las que la autenticación falla.
 *
 * @author Alex
 */
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    MessageSource messageSource;

    /**
     *
     * Método para manejar una excepción de autenticación fallida.
     *
     * @param request objeto HttpServletRequest que representa la solicitud HTTP
     * realizada
     * @param response objeto HttpServletResponse que representa la respuesta
     * HTTP que se enviará
     * @param exception objeto AuthenticationException que representa la
     * excepción de autenticación que se produjo
     * @throws IOException si se produce un error al escribir en la respuesta
     * @throws ServletException si se produce un error en la ejecución de la
     * tarea del servlet
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
            HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {

        String errorMessage = "error.credenciales";

        if (exception.getCause() instanceof UsuarioBajaException) {
            errorMessage = "error.baixa";
        }

        if (exception.getCause() instanceof UsuarioNoValidado) {
            errorMessage = "error.usuarionovalidado";
        }

        response.sendRedirect(request.getContextPath() + "/login?error=" + errorMessage);
    }
}
