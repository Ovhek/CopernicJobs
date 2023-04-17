/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.dao;

import cat.copernic.copernicjobs.model.Oferta;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * DAO de la entidad Oferta.
 * @author Alex
 */
public interface OfertaDAO extends JpaRepository<Oferta, Integer> {

    List<Oferta> findAllByEmpresaId(int id);
    
    @Query("SELECT o FROM Oferta o JOIN o.empresa e WHERE e.nombreEmpresa = :nombre")
    List<Oferta> findByNombreEmpresa(String nombre);
    
    @Query("SELECT o FROM Oferta o WHERE o.tituloOferta = :titulo")
    Oferta findByTituloOferta(String titulo);
    
    List<Oferta> findByFechaValidacionBetween(LocalDate start, LocalDate end);
    
    //@Query("SELECT o FROM Oferta o JOIN o.empresa e WHERE e.nombreEmpresa = :nombre")
    //List<Oferta> findByNombreEmpresa(String nombre);
    
    //aaaa
    @Query("SELECT o FROM Oferta o JOIN o.empresa e WHERE e.nombreEmpresa = :busqueda ORDER BY e.nombreEmpresa ASC")
    List<Oferta> findByNombreEmpresaOrderByTituloOfertaAsc(String busqueda);
    
    //Select Numero Candidatos
    @Query("SELECT o FROM Oferta o JOIN o.inscripciones i JOIN o.empresa e ON e.id = o.empresa.id WHERE e.nombreEmpresa = :username AND o.tituloOferta LIKE %:busqueda% GROUP BY o.id ORDER BY COUNT(i) DESC")
    List<Oferta> findByTituloOfertaOrderByNumeroInscripcionesAsc(String busqueda, String username);

    //Select Numero Candidatos
    @Query("SELECT o FROM Oferta o JOIN o.inscripciones i JOIN o.empresa e WHERE e.nombreEmpresa = :username GROUP BY o.id ORDER BY COUNT(i) DESC")
    List<Oferta> findByNombreEmpresaOrderByNumeroInscripcionesAsc(String username);

    //Select Ofertas por Fecha Peticion
    @Query("SELECT o FROM Oferta o LEFT JOIN o.empresa e WHERE o.tituloOferta LIKE %:busqueda% AND e.nombreEmpresa = :username ORDER BY o.fechaPeticion ASC")
    List<Oferta> findByTituloOfertaOrderByFechaPeticionAsc(String busqueda, String username);
    
    //Select Ofertas por Fecha Peticion
    @Query("SELECT o FROM Oferta o LEFT JOIN o.empresa e WHERE  e.nombreEmpresa = :username ORDER BY o.fechaPeticion ASC")
    List<Oferta> findByNombreEmpresaOrderByFechaPeticionAsc(String username);    
    
    
    //Select Ofertas Publicadas y Data Publicacio
    @Query("SELECT o FROM Oferta o JOIN o.empresa e WHERE o.tituloOferta LIKE %:busqueda% AND e.nombreEmpresa = :username ORDER BY o.fechaValidacion ASC")
    List<Oferta> findByTituloOfertaOrderByFechaValidacionAsc(String busqueda, String username);


    @Query("SELECT o FROM Oferta o JOIN o.empresa e WHERE e.nombreEmpresa = :username ORDER BY o.fechaValidacion ASC")
    List<Oferta> findByNombreEmpresaOrderByFechaValidacionAsc(String username);

    
    
    @Query("SELECT o FROM Oferta o JOIN o.empresa e WHERE o.tituloOferta LIKE %:busqueda% AND e.nombreEmpresa = :username ORDER BY e.nombreEmpresa ASC")
    List<Oferta> findByTituloOfertaOrderByTituloOfertaAsc(String busqueda, String username);
    
    //Select Numero Candidatos
    @Query("SELECT o FROM Oferta o WHERE o.tituloOferta LIKE %:busqueda% OR o.descripcionOferta LIKE %:busqueda%")
    List<Oferta> findByTituloDescripcionOfertaOrderByAnythingAscOrDesc(String busqueda, Sort sort);
    
}
