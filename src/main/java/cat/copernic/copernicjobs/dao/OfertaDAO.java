/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.dao;

import cat.copernic.copernicjobs.model.Noticia;
import cat.copernic.copernicjobs.model.Oferta;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Cole
 */
public interface OfertaDAO extends JpaRepository<Oferta, Integer> {

    List<Oferta> findAllByEmpresaId(int id);
    
    List<Oferta> findByFechaValidacionBetween(LocalDate start, LocalDate end);
    
    //List<Oferta> findByTituloOfertaOrderByCriterio(String busqueda, String criterio);
    
}
