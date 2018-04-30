/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientep2p;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.AbstractListModel;
import javax.swing.JList;

/**
 *
 * @author angel
 */
public class ModeloListaUsuarios extends AbstractListModel{
    
    ArrayList<String> listaAmigosConectados;

    public ModeloListaUsuarios(ArrayList<String> listaAmigosConectados) {
        this.listaAmigosConectados = listaAmigosConectados;
    }

    @Override
    public int getSize() {
        
        return listaAmigosConectados.size();
    }

    @Override
    public Object getElementAt(int index) {
        
        return listaAmigosConectados.get(index);
    }
}
