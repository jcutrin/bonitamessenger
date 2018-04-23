/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.util.ArrayList;

/**
 *
 * @author angel
 */
public class Usuario {
    
    private String userId;
    private String password;
    private ArrayList<Usuario> amigos;

    public Usuario(String userId, String password, ArrayList<Usuario> amigos) {
        this.userId = userId;
        this.password = password;
        this.amigos = amigos;
    }

    public Usuario(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
    
    

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Usuario> getAmigos() {
        return amigos;
    }

    public void setAmigos(ArrayList<Usuario> amigos) {
        this.amigos = amigos;
    }
    
    
    
    
}
