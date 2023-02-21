/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.model;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author joang
 */
@Data
public class Incidencia{
    private String descripcion;
    private Date fechaIncidencia;
    private int estado;
    
}
