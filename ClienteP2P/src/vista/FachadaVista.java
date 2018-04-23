/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.FachadaControlador;

/**
 *
 * @author angel
 */
public class FachadaVista {
    
    controlador.FachadaControlador fc;
    VPrincipal vp;

    public FachadaVista(controlador.FachadaControlador fc) {
        this.fc = fc;
        this.vp = new VPrincipal(fc);
    }
    
    public void iniciarVista(){
    
        VLogIn vli;
        
        vli = new VLogIn(vp, true, fc);
        vp.setVisible(true);
        vli.setVisible(true);
        
    }
    
    public void muestraExcepcion(String txtExcepcion){
    
        VAviso va;
        
        va = new VAviso(vp, true, txtExcepcion);
        va.setVisible(true);
    }
    
}
