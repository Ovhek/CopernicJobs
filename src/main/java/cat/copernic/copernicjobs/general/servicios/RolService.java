/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.general.servicios;

import cat.copernic.copernicjobs.dao.IncidenciaDAO;
import cat.copernic.copernicjobs.dao.RolDAO;
import cat.copernic.copernicjobs.model.Alumno;
import cat.copernic.copernicjobs.model.Incidencia;
import cat.copernic.copernicjobs.model.Rol;
import java.util.List;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Cole
 */
@Service
public class RolService implements RolServiceInterface{

    @Autowired
    RolDAO rolDAO;

    @Transactional(readOnly = true)
    @Override
    public List<Rol> listarRoles() {
        var a = rolDAO.findAll();
        a.forEach(rol -> Hibernate.initialize(rol.getModulos()));
        return a;
    }

    @Transactional
    @Override
    public void anadirRol(Rol rol) {
        rolDAO.save(rol);
    }

    @Transactional
    @Override
    public void eliminarRol(Rol rol) {
       rolDAO.delete(rol);
    }

    @Transactional(readOnly = true)
    @Override
    public Rol buscarRol(Rol rol) {
        Rol rol_ = rolDAO.findById(rol.getId()).orElse(null);
        Hibernate.initialize(rol_.getModulos());
        return rol_;
    }
    

    
}
