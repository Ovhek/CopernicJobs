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
public class Alumno extends Persona{
    
    private String tarjetaSanitaria;
    private String segSocial;
    private String dni;
}
