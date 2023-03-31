/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.general.servicios;

import cat.copernic.copernicjobs.dao.RolModuloDAO;
import cat.copernic.copernicjobs.model.Modulo;
import cat.copernic.copernicjobs.model.Rol;
import cat.copernic.copernicjobs.model.RolModulo;
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

    public void setRolModuloDao(RolModuloDAO rolModuloDao) {
        this.rolModuloDao = rolModuloDao;
    }

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
    
    @Transactional(readOnly = true)
    public List<RolModulo> findAll(){
        return rolModuloDao.findAll();
    }
    
    @Transactional
    public void guardar(RolModulo rolModulo){
        rolModuloDao.save(rolModulo);
    }
    
    @Transactional
    public void eliminar(RolModulo rolModulo){
        rolModuloDao.delete(rolModulo);
    }
    
    public void guardarTodos(List<RolModulo> rolesModulos){
        rolesModulos.forEach(rolModulo -> guardar(rolModulo));
    }

    public void eliminarTodos(List<RolModulo> rolModulosAEliminar) {
        rolModulosAEliminar.forEach(rolModulo -> eliminar(rolModulo));
    }
}
