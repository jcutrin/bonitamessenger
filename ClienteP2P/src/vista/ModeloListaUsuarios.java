/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.util.Vector;
import javax.swing.AbstractListModel;
import javax.swing.JList;

/**
 *
 * @author angel
 */
public class ModeloListaUsuarios extends JList{
    
    private java.util.Vector<Usuario> usuarios;
    
    public ModeloListaUsuarios(Vector<Usuario> usuarios){
        
        this.usuarios = usuarios;
    }

    public String[] getListaUsuarios(){
    
        String[] listaUsuarios = new String[usuarios.size()];
    
        for(int i=0; i<usuarios.size();i++){
        
            listaUsuarios[i]=usuarios.get(i).getUserId();
        }

        return listaUsuarios;
    }
}
