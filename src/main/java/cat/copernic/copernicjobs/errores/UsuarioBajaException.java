/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.errores;

import org.springframework.security.core.AuthenticationException;

/**
 * Excepción que se lanza cuando un usuario está de baja y se intenta hacer login.
 * @author Alex
 */
public class UsuarioBajaException extends AuthenticationException{
    
    /**
     * Constructor que envia la excepción
     * @param msg Mensaje de la excepción
     */
    public UsuarioBajaException(String msg) {
        super(msg);
    }
    
}
