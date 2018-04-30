/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.rmi.*;
import java.util.List;
import java.util.Vector;
import vista.Usuario;

/**
 *
 * @author jose
 */
public interface ClientInterface extends Remote {

    public Vector<Usuario> getListOfFriendsConnected(String user) throws java.rmi.RemoteException;

    public void friendDisconnected(String user) throws java.rmi.RemoteException;

    public void updateFriends(List<String> amigos)
            throws java.rmi.RemoteException;

    public void friendRequest(String friendName)  throws java.rmi.RemoteException;

    public void receiveMessage(String message, ClientInterface friend, String friendName)
            throws java.rmi.RemoteException;

    public void startChat(ClientInterface friend, String friendName)
            throws java.rmi.RemoteException;

    public List<String> getFriendList() throws java.rmi.RemoteException;

    public void receiveFile(String fileName, byte[] content, ClientInterface friend)
            throws java.rmi.RemoteException;

    public void addFriend(String friendName) throws java.rmi.RemoteException;

}