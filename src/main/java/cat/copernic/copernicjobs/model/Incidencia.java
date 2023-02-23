/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.model;

import java.time.LocalDate;
import lombok.Data;
import lombok.NonNull;

/**
 *
 * @author joang
 */
@Data
public class Incidencia{
    private int id;
    private String titulo;
    private String descripcion;
    private LocalDate fechaIncidencia;
    private int estado;
    
}
