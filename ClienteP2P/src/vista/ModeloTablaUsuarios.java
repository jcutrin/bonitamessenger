/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author angel
 */
public class ModeloTablaUsuarios extends AbstractTableModel{

    private java.util.Vector<Usuario> usuarios;
    
    public ModeloTablaUsuarios(){
        
        this.usuarios = new java.util.Vector<Usuario>();
    }
    
    public int getColumnCount (){
        return 2;
    }
    
    
    public int getRowCount(){
        return usuarios.size();
    }
    
    public String getColumnName(int col){
        String nombre="";

        switch (col){
            case 0: nombre= "userId"; break;
            case 1: nombre="password"; break;
        }
        return nombre;
    }
    
    public Class getColumnClass(int col){
        Class clase=null;

        switch (col){
            case 0: clase= java.lang.String.class; break;
            case 1: clase=java.lang.String.class; break;
        }
        return clase;
    }
    
    public boolean isCellEditable(int row, int col){
        return false;
    }
    
    public Object getValueAt(int row, int col){
        Object resultado=null;

        switch (col){
            case 0: resultado= usuarios.elementAt(row).getUserId(); break;
            case 1: resultado=usuarios.elementAt(row).getPassword();break;
        }
        return resultado;
    }
    
    public void setFilas(java.util.Vector<Usuario> usuarios){
        this.usuarios = usuarios;
        fireTableDataChanged();
    }
    
    public void nuevoUsuario(Usuario u){
        this.usuarios.addElement(u);
        fireTableRowsInserted(this.usuarios.size()-1, this.usuarios.size()-1);
    }
    
    public Usuario obtenerUsuario(int i){
        return this.usuarios.elementAt(i);
    }
}
