/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.model;

import java.util.ArrayList;
import lombok.Data;

/**
 *
 * @author Cole
 */
@Data
public class Modulo {
    private int ID;
    private String nombre;
    private ArrayList<Rol> roles;
}
