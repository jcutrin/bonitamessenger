/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Vector;
import vista.Usuario;

/**
 *
 * @author jose
 */
public class FachadaControlador implements ClientInterface, ServerInterface{
    
    vista.FachadaVista fv;
    modelo.FachadaModelo fm;
    ClientInterface clientInterface;

    public FachadaControlador() throws RemoteException {
        
        fm = new modelo.FachadaModelo(this);
        fv = new vista.FachadaVista(this);
        clientInterface = (ClientInterface) new ClientImpl(fv,fm);
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException {
        
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
    
    //Begin Client methods
    //-----------------------------------------------------------------------------------------------------------------------------------------//

    @Override
    public Vector<Usuario> getListOfFriendsConnected(String user) throws RemoteException {
        
        return clientInterface.getListOfFriendsConnected(user);
    }

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

    
    //Begin Server-Model methods
    //-----------------------------------------------------------------------------------------------------------------------------------------//
    
    @Override
    public boolean login(ClientInterface client, String user, String password) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean logout(ClientInterface client, String user, String password) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteFriend(String friendName, String user, String password) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> findFriend(String friendName) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void denyFriendRequest(String user, String password, String friendName) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void acceptFriendRequest(String user, String password, String friendName) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int signUp(String user, String password) throws RemoteException {
        return 1;
    }

    @Override
    public int changePassword(String user, String password, String newPassword) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void friendRequest(String user, String password, String friendName) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean checkUser(String user) throws RemoteException {
        return true;
    }
}
