/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.DAO;

import cat.copernic.copernicjobs.model.Oferta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Cole
 */


public interface OfertaDAO extends JpaRepository<Oferta,Integer>{
    
    List<Oferta> findAllByEmpresaId(int id);    
    
}