/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.empresa.servicios;

import cat.copernic.copernicjobs.model.Alumno;
import cat.copernic.copernicjobs.model.Empresa;
import java.util.List;

/**
 *
 * @author Albert
 */
public interface EmpresaServiceInterface {

    public List<Empresa> llistarEmpreses(); //Mètode que implementarem per llistar gossos

    public void afegirEmpresa(Empresa empresa); //Mètode que implementarem per afegir un gos

    public void eliminarEmpresa(Empresa empresa); //Mètode que implementarem per eliminar un gos

    public Empresa cercarEmpresa(Empresa empresa); //Mètode que implementarem per cercar un gos

    public Empresa buscarEmpresaPorUsername(String username);

}
