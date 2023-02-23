/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import lombok.Data;
import lombok.NonNull;

/**
 *
 * @author Albert
 */
@Data
public class Empresa extends Persona {

    private int id;
    private String nombreEmpresa;
    private String descripcionEmpresa;
    private String webEmpresa;
    private String telefonoEmpresa; 
    private ArrayList<Oferta> ofertas;
}
