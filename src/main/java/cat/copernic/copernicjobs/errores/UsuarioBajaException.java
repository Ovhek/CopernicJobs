/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.errores;

import org.springframework.security.core.AuthenticationException;

/**
 *
 * @author Cole
 */
public class UsuarioBajaException extends AuthenticationException{
    
    public UsuarioBajaException(String msg) {
        super(msg);
    }
    
}
