/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.model;

import java.time.LocalDate;
import java.util.Date;
import lombok.NonNull;

/**
 *
 * @author Albert
 */
public class Inscripcion {
    private Oferta oferta;
    private Alumno alumno;
    private LocalDate fechaInscripcion;
    private int estado;
    private boolean baja;
    private int id;
}
