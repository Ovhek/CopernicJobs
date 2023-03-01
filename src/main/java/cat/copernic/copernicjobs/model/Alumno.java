/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author Cole
 */
@Data
@Entity
@DiscriminatorValue("1")
public class Alumno extends Persona implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Column(name = "tarjeta_sanitaria")
    private String tarjetaSanitaria;
    @Column(name = "seg_social")
    private String segSocial;
    @Column(name = "dni")
    private String dni;
    @Column(name = "linkedin")
    private String linkedin;
    @Column(name = "portafoli_personal")
    private String portafoliPersonal;
    @Column(name = "curriculum_online")
    private String curriculumOnline;
    @Column(name = "pdf_link")
    private String pdfLink;
    @Column(name = "avatar_link")
    private String avatarLink;
}
