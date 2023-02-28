/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.sql.Date;
import java.time.LocalDate;
import lombok.Data;
import lombok.NonNull;

/**
 *
 * @author Cole
 */

@Data
@Entity
public class Persona extends Usuario{

    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "cod_postal")
    private String codPostal;
    
    @Column(name = "municipio")
    private String municipio;
    
    @Column(name = "direccion")
    private String direccion;
    
    @Column(name = "movil")
    private String movil;
    
    @Column(name = "sexo")
    private int sexo;
    
    @Column(name = "sexo_desc")
    private String sexoDesc;
    
    @Column(name = "apellidos")
    private String apellidos;
    
    @Column(name = "correo_contacto")
    private String correoContacto;
    
}
