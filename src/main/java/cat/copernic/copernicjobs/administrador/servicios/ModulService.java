/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.servicios;

import cat.copernic.copernicjobs.dao.ModuloDAO;
import cat.copernic.copernicjobs.model.Modulo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author joang
 */
@Service
public class ModulService implements ModulServiceInterface{

    @Autowired
    private ModuloDAO modulo;
    
    @Override
    @Transactional(readOnly=true)
    public List<Modulo> llistarModuls() {
        return (List<Modulo>) modulo.findAll();
    }

    @Override
    @Transactional
    public void afegirModul(Modulo modulo) {
        this.modulo.save(modulo);
    }

    @Override
    @Transactional
    public void eliminarModul(Modulo modulo) {
        this.modulo.delete(modulo);
    }

    @Override
    @Transactional(readOnly=true)
    public Modulo cercarModul(Modulo modulo) {
        return this.modulo.findById(modulo.getID()).orElse(null);
    }
    
}
