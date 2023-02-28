/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Entity
@Table(name="empresa")
public class Empresa extends Persona {

    @Column(name="nombre_empresa")
    private String nombreEmpresa;
    @Column(name="descripcion_empresa")
    private String descripcionEmpresa;
    @Column(name="webEmpresa")
    private String webEmpresa;
    @OneToMany
    private ArrayList<Oferta> ofertas;
}
