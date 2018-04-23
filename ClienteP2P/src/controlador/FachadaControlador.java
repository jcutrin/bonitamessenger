/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.util.Vector;
import vista.Usuario;

/**
 *
 * @author jose
 */
public class FachadaControlador implements IControladorUsuarios{
    
    vista.FachadaVista fv;
    modelo.FachadaModelo fm;
    IControladorUsuarios cu;

    public FachadaControlador() {
        
        fm = new modelo.FachadaModelo(this);
        fv = new vista.FachadaVista(this);
        cu = (IControladorUsuarios) new ControladorUsuariosImpl(fv,fm);
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        FachadaControlador fc;
        
        fc = new FachadaControlador();
        fc.iniciarInterfazUsuario();
    }
    
    public void iniciarInterfazUsuario(){
        fv.iniciarVista();
    }
    
    public void muestraExcepcion(String e){
        fv.muestraExcepcion(e);
    }

    @Override
    public Boolean checkUser(String userId) {
        
        return cu.checkUser(userId);
    }

    @Override
    public void registrarUsuario(Usuario user) {
        cu.registrarUsuario(user);
    }
    
    
}
