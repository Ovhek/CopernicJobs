/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.model;

import lombok.Data;

/**
 *
 * @author joang
 */
@Data
public class Administrador extends Usuario {
    private String nombre;
    private String apellido;
}
