/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.copernicjobs.general.utils;

import cat.copernic.copernicjobs.model.Modulo;
import cat.copernic.copernicjobs.model.Rol;
import cat.copernic.copernicjobs.model.RolModulo;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Cole
 */
public final class ThymeleafFunctions {
    private static ThymeleafFunctions instance;
    
    private ThymeleafFunctions(){
        
    }
    
    public static ThymeleafFunctions getInstance(){
        if(instance == null) instance = new ThymeleafFunctions();
        return instance;
    }
    
    public boolean hasModulo(Rol rol, Modulo modulo) {
        for (RolModulo rolModulo : rol.getModulos()) {
            if(rolModulo.getModulo().equals(modulo)) return true;
        }
        return false;
    }
    
    public boolean isVisible(Rol rol, Modulo modulo) {
        for (RolModulo rolModulo : rol.getModulos()) {
            if(rolModulo.getModulo().equals(modulo)){
                return rolModulo.isVisibilidad();
            }
        }
        return false;
    }
}
