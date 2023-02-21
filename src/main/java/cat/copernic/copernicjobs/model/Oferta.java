/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.model;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author Albert
 */
@Data
public class Oferta {
    private int id_empresa;
    private Date fechaValidacion;
    private String enlacePDF;
    private String tituloOferta;
    private String descripcionOferta;
    private String requisitosAlumno;
    private Date fechaPeticion;
    private boolean baja;
}
