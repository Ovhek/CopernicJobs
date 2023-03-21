/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.general.servicios;

import cat.copernic.copernicjobs.dao.IncidenciaDAO;
import cat.copernic.copernicjobs.dao.RolModuloDAO;
import cat.copernic.copernicjobs.model.Alumno;
import cat.copernic.copernicjobs.model.Incidencia;
import cat.copernic.copernicjobs.model.Modulo;
import cat.copernic.copernicjobs.model.Rol;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Cole
 */
@Service
public class RolModuloService implements RolModuloServiceInterface{

    @Autowired
    RolModuloDAO rolModuloDao;

    @Override
    @Transactional(readOnly = true)
    public List<Modulo> findModulosByRolNom(String nom) {
        return rolModuloDao.findModulosByNombreRol(nom);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Rol> findRoleslByModuloNom(String nom) {
        return rolModuloDao.findRolesByNombreModulo(nom);
    }
    
    
}
