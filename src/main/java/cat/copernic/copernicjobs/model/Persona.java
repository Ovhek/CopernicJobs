/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.model;

import jakarta.persistence.Column;
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

    @Column(name = "nombre")
    protected String nombre;
    
    @Column(name = "cod_postal")
    protected String codPostal;
    
    @Column(name = "municipio")
    protected String municipio;
    
    @Column(name = "direccion")
    protected String direccion;
    
    @Column(name = "movil")
    protected String movil;
    
    @Column(name = "sexo")
    protected int sexo;
    
    @Column(name = "apellidos")
    protected String apellidos;
    
    @Column(name = "correo_contacto")
    protected String correoContacto;
}
