/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.model;

import java.time.LocalDate;
import java.util.Date;
import lombok.Data;
import lombok.NonNull;

/**
 *
 * @author Albert
 */
@Data
public class Oferta {

    private int id_empresa;
    private LocalDate fechaValidacion;
    private String enlacePDF;
    private String tituloOferta;
    private String descripcionOferta;
    private String requisitosAlumno;
    private String seOfrece;
    private LocalDate fechaPeticion;
    private boolean baja;
    private Empresa empresa;
}
