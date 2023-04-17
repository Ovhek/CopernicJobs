/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.errores;

import org.springframework.security.core.AuthenticationException;

/**
 * Excepción que se lanza cuando un usuario no es válido.
 *
 * @author Alex
 */
public class UsuarioNoValidado extends AuthenticationException {

    /**
     * Constructor que envia la excepción
     *
     * @param msg Mensaje de la excepción
     */
    public UsuarioNoValidado(String msg) {
        super(msg);
    }

}
