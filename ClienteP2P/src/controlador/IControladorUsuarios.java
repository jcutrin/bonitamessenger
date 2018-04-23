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
public interface IControladorUsuarios {
    
    public Boolean checkUser(String userId);        //Comprobar validez de un usuario
    
    public void registrarUsuario(Usuario user); //Registrar usuario en BD
    
}
