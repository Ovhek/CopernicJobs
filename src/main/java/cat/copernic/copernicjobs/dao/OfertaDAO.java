/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.dao;

import cat.copernic.copernicjobs.model.Oferta;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Cole
 */
public interface OfertaDAO extends JpaRepository<Oferta, Integer> {

    List<Oferta> findAllByEmpresaId(int id);
    
    @Query("SELECT o FROM Oferta o JOIN o.empresa e WHERE e.nombreEmpresa LIKE %:nombreEmpresa%")
    List<Oferta> findByNombreEmpresa(String nombre);
    
    List<Oferta> findByFechaValidacionBetween(LocalDate start, LocalDate end);
    
    //List<Oferta> findAllByNombreEmpresa();
    
    //List<Oferta> findByTituloOfertaOrderByTituloOferta(String busqueda, String criterio);
    
    @Query("SELECT o FROM Oferta o LEFT JOIN o.inscripciones i GROUP BY o.id ORDER BY COUNT(i) DESC")
    List<Oferta> findByTituloOfertaOrderByNumeroInscripcionesAsc(String busqueda);
    
    @Query("SELECT o FROM Oferta o WHERE o.tituloOferta LIKE %:busqueda% ORDER BY o.fechaPeticion ASC")
    List<Oferta> findByTituloOfertaOrderByFechaPeticionAsc(String busqueda);
    
    @Query("SELECT o FROM Oferta o WHERE o.tituloOferta LIKE %:busqueda% ORDER BY o.fechaValidacion ASC")
    List<Oferta> findByTituloOfertaOrderByFechaValidacionAsc(String busqueda);
    
}
