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
