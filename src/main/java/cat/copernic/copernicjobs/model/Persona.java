/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.model;

import java.sql.Date;
import java.time.LocalDate;
import lombok.Data;
import lombok.NonNull;

/**
 *
 * @author Cole
 */

@Data
public class Persona extends Usuario{

    protected String nombre;
    protected String codPostal;
    protected String municipio;
    protected String direccion;
    protected String movil;
    protected int sexo;
    protected String apellidos;
    protected String correoContacto;
}
