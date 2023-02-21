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
public class Noticia{
    private Rol rol;
    private String titulo;
    private String descripcion;
    private Date fechaHora;
}
