/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorp2p;

import java.util.List;
import java.rmi.*;

/**
 *
 * @author jose
 */
public interface ServerInterface extends java.rmi.Remote {
    
    public boolean login(ClientInterface client, String user, String password)
            throws RemoteException;
    
    public boolean logout(ClientInterface client, String user, String password)
            throws RemoteException;
     
    public void deleteFriend(String friendName, String user, String password)
            throws RemoteException;
     
    public List<String> findFriend(String friendName)
              throws RemoteException;
      
    public void denyFriendRequest(String user, String password, String friendName)
              throws RemoteException;
      
    public void acceptFriendRequest(String user, String password, String friendName)
              throws RemoteException;
     
    public boolean signUp (String user, String password)
              throws java.rmi.RemoteException;

    public boolean changePassword(String user, String password, String newPassword)
             throws java.rmi.RemoteException;
     
    public void friendRequest(String user, String password, String friendName)
             throws RemoteException;
    
}