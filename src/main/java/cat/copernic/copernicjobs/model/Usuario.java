/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.model;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import java.time.LocalDate;
import lombok.Data;

/**
 *
 * @author Cole
 */
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipus_persona", discriminatorType = DiscriminatorType.INTEGER)
public abstract class Usuario {
    //protected porque es superclase
    protected String correo;
    protected String contrasenya;
    protected boolean baja;
    protected Rol rol;
    protected LocalDate fechaRegistro;
    protected LocalDate fechaBaja;
    protected int ID;
}
