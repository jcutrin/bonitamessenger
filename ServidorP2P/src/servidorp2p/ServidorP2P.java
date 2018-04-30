/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorp2p;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author jose
 */
public class ServidorP2P {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic 
/*        UserDAO ud = new UserDAO();
        System.out.println("Usuario: " + ud.getUser() );
        System.out.println("Amigos:");
        ArrayList<String> amigos = new ArrayList<String>();
        amigos = ud.getFriends(ud.getUser());
        for (int i = 0; i < amigos.size(); i++) {
            System.out.println(amigos.get(i));
        }
        ArrayList<String> usuarios = new ArrayList<String>();
        usuarios = ud.searchUser("jacosdfs");
        System.out.println("Usuarios que contienen ja:");
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println(usuarios.get(i));
        }   
        System.out.println("Comprobando el usuario y contraseña de Jose - 1235");
        System.out.println(ud.checkUser("Jose", "1235"));
        System.out.println("Comprobando el usuario y contraseña de Jose - 1234");
        System.out.println(ud.checkUser("Jose", "1234"));
        */
        int RMIPortNumber;
        String registryURL;
        try {
            RMIPortNumber = 1099;
            startRegistry(RMIPortNumber);
            ServerImpl server = new ServerImpl();
            registryURL = "rmi://localhost:" + RMIPortNumber + "/bonitaMessenger";
            
            Naming.rebind(registryURL, server);
      
            System.out.println("BonitaMessenger Server ready.");

        } catch (Exception e) {
            System.out.println("Excepción en ServidorP2P: " + e.getMessage());
        }   
    }
    
      private static void startRegistry(int RMIPortNumber) throws RemoteException{
    try {
        Registry registry = LocateRegistry.getRegistry(RMIPortNumber);
        registry.list( );  
    } catch (RemoteException e) { 
        Registry registry = LocateRegistry.createRegistry(RMIPortNumber);
    }
  } 
    
}
