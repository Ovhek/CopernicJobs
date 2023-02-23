/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.model;

import java.sql.Date;
import lombok.Data;

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
    protected Date fechaRegistro;
    protected Date fechaBaja;
    protected int ID;
}
