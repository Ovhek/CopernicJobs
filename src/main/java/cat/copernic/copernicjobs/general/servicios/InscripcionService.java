/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.general.servicios;

import cat.copernic.copernicjobs.DAO.EmpresaDAO;
import cat.copernic.copernicjobs.DAO.InscripcionDAO;
import cat.copernic.copernicjobs.model.Inscripcion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Albert
 */
public class InscripcionService implements InscripcionServiceInterface {
    
    @Autowired
    private InscripcionDAO inscripcionDao;

    @Override
    public List<Inscripcion> listarInscripciones() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void añadirInscripcion(Inscripcion inscripcion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarInscripcion(Inscripcion inscripcion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Inscripcion buscarInscripcion(Inscripcion inscripcion) {
        return this.inscripcionDao.findById(inscripcion.getId()).orElse(null);   
    }
    
}
