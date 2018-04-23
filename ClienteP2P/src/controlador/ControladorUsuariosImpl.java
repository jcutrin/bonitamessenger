/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import modelo.*;
import vista.*;

/**
 *
 * @author angel
 */
public class ControladorUsuariosImpl implements IControladorUsuarios{
    
    FachadaVista fv;
    FachadaModelo fm;

    public ControladorUsuariosImpl(FachadaVista fv, FachadaModelo fm) {
        this.fv = fv;
        this.fm = fm;
    }

    @Override
    public Boolean checkUser(String userId) {
        
        return true;
    }

    @Override
    public void registrarUsuario(Usuario user) {
        
        System.out.println("Registrado!");
    }
    
    
    
}
