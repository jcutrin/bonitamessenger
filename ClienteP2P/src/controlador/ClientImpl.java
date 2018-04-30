/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Vector;
import modelo.*;
import vista.*;

/**
 *
 * @author angel
 */
public class ClientImpl implements ClientInterface{
    
    FachadaVista fv;
    FachadaModelo fm;

    public ClientImpl(FachadaVista fv, FachadaModelo fm) {
        this.fv = fv;
        this.fm = fm;
    }

    @Override
    public Vector<Usuario> getListOfFriendsConnected(String user) throws RemoteException {
        
        Vector<Usuario> amigosConectados = new Vector<Usuario>();
        Usuario testUser = new Usuario("Angel");
        Usuario testUser1 = new Usuario("Angel2");
        amigosConectados.add(testUser1);
        amigosConectados.add(testUser);
        
        return amigosConectados;
    }
    
    /*public Vector<Usuario> getListOfFriendsConnected(ClientInterface friend, String user) throws RemoteException {
        
        Vector<Usuario> amigosConectados = new Vector<Usuario>();
        Usuario testUser = new Usuario("Angel");
        
        amigosConectados.add(testUser);
        
        return amigosConectados;
    }*/

    @Override
    public void friendDisconnected(String user) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateFriends(List<String> amigos) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void friendRequest(String friendName) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void receiveMessage(String message, ClientInterface friend, String friendName) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void startChat(ClientInterface friend, String friendName) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getFriendList() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void receiveFile(String fileName, byte[] content, ClientInterface friend) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addFriend(String friendName) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
}
