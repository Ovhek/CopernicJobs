/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Albert
 */
@Data
@Entity
@Table(name="empresa")
@DiscriminatorValue("3")
public class Empresa extends Persona {

    @Column(name="nombre_empresa")
    private String nombreEmpresa;
    @Column(name="descripcion_empresa")
    private String descripcionEmpresa;
    @Column(name="webEmpresa")
    private String webEmpresa;
    @OneToMany(mappedBy = "empresa")
    private List<Oferta> ofertas;
}
