/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.copernicjobs.administrador.servicios;

import cat.copernic.copernicjobs.model.Empresa;
import java.util.List;

/**
 *
 * @author joang
 */
//Interface on definirem els mètodes CRUD personalitzats per la nostra aplicació
public interface EmpresaServiceInterface {
    
    public List<Empresa> llistarEmpreses(); //Mètode que implementarem per llistar empreses
    
    public void afegirEmpresa(Empresa empresa); //Mètode que implementarem per afegir una empresa
    
    public void eliminarEmpresa(Empresa empresa); //Mètode que implementarem per eliminar una empresa
    
    public Empresa cercarEmpresa(Empresa empresa); //Mètode que implementarem per cercar una empresa
    
}
