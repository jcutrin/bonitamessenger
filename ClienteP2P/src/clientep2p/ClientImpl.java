/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientep2p;
import clientep2p.Usuario;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author angel
 */
public class ClientImpl extends UnicastRemoteObject implements ClientInterface, Serializable{
    
    private VPrincipal vistaPrincipalBonitaMessenger;
    private String userID;
    private String password;
    private ArrayList<String> friendRequests; //Nombres de usuario que enviaron una peticion de amistad a este usuario
    private ArrayList<String> listOfFriends;
    private Map<String, ClientInterface> listOfFriendsConnected;
    private Map<ClientInterface, VChat> activeConversations;

    public ClientImpl(VPrincipal vistaPrincipalBonitaMessenger, String userID, String password, Map<String, ClientInterface> listOfFriendsConnected) 
            throws RemoteException {
        super();
        this.vistaPrincipalBonitaMessenger = vistaPrincipalBonitaMessenger;
        this.userID = userID;
        this.password = password;
        this.friendRequests = new ArrayList();
        this.listOfFriends = new ArrayList();
        this.listOfFriendsConnected = listOfFriendsConnected;
        this.activeConversations = new HashMap();
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
    

    //Actualiza la lista de amigos conectados cuando el usuario se conecta
    @Override
    public void friendConnected(ClientInterface friend, String user) throws RemoteException {
        
        this.listOfFriendsConnected.put(user, friend);
        
        ArrayList<String> namesOfFriendsConnected = new ArrayList<>();
        Collection<String> namesToList = listOfFriendsConnected.keySet();
        
        for (String name : namesToList)
            namesOfFriendsConnected.add(name);
        
        ModeloListaUsuarios modeloListaUsuarios = new ModeloListaUsuarios(namesOfFriendsConnected);
        this.vistaPrincipalBonitaMessenger.jList_AmigosConectados.setModel(modeloListaUsuarios);
        vistaPrincipalBonitaMessenger.jScrollPane_AmigosConectados.setViewportView(vistaPrincipalBonitaMessenger.jList_AmigosConectados);
    }
    
    //Actualiza la lista de amigos conectados cuando uno se desconecta
    @Override
    public void friendDisconnected(String user) throws RemoteException {
        
        this.listOfFriendsConnected.remove(user);
        
        ArrayList<String> namesOfFriendsConnected = new ArrayList<>();
        
        //Actualizamos la lista de amigos conectados para actualizarla en la vista
        for (String name : listOfFriendsConnected.keySet())
            namesOfFriendsConnected.add(name);
        
        //Actualizamos la vista tras haber recibido una desconexion
        ModeloListaUsuarios modeloListaUsuarios = new ModeloListaUsuarios(namesOfFriendsConnected);
        this.vistaPrincipalBonitaMessenger.jList_AmigosConectados.setModel(modeloListaUsuarios);
        vistaPrincipalBonitaMessenger.jScrollPane_AmigosConectados.setViewportView(vistaPrincipalBonitaMessenger.jList_AmigosConectados);
    }
    
    //Añade un amigo a la lista de amigos y procede con la actualizacion    
    @Override
    public void addFriend(String friendName) throws RemoteException {
        
        this.listOfFriends.add(friendName);
        updateFriendsList(listOfFriends);
    }
    
    //Actualiza la lista de amigos cuando arranca el servidor y cada vez que se añade un amigo a la lista del usuario
    @Override
    public void updateFriendsList(ArrayList<String> amigos) throws RemoteException {
        
        listOfFriends = amigos;
        ModeloListaUsuarios modeloListaUsuarios = new ModeloListaUsuarios(listOfFriends);
        this.vistaPrincipalBonitaMessenger.jList_ListaAmigosDelUsuario.setModel(modeloListaUsuarios);
        vistaPrincipalBonitaMessenger.jScrollPane_ListaAmigosDelUsuario.setViewportView(vistaPrincipalBonitaMessenger.jList_ListaAmigosDelUsuario);
    }

    @Override
    public void friendRequest(String friendName) throws RemoteException {
        
        //Comprobamos que esta peticion de amistad no se haya realizado 
        boolean existePeticion = false;
        
        if(this.friendRequests.contains(friendName))
            existePeticion=true;
        
        if(!existePeticion){
        
            this.friendRequests.add(friendName);
            this.vistaPrincipalBonitaMessenger.updateFriendRequests(friendRequests);
        }
    }

    @Override
    public void receiveMessage(String message, ClientInterface friend, String friendName) throws RemoteException {
        
        this.activeConversations.get(friend).setVisible(true);
        String textoChat = this.activeConversations.get(friend).getTextChat();
        this.activeConversations.get(friend).setTextChat(textoChat + friendName + ":   " + message + "\n");
    }

    @Override
    public void startChat(ClientInterface friend, String friendName) throws RemoteException {
        
        VChat chat = new VChat(this, this.userID, friend, friendName);
        this.activeConversations.put(friend, chat);
        chat.setVisible(true);
    }
    
    @Override
    public void receiveFile(String fileName, byte[] content, ClientInterface friend) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public VPrincipal getVistaPrincipalBonitaMessenger() {
        return vistaPrincipalBonitaMessenger;
    }

    public void setVistaPrincipalBonitaMessenger(VPrincipal vistaPrincipalBonitaMessenger) {
        this.vistaPrincipalBonitaMessenger = vistaPrincipalBonitaMessenger;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getFriendRequests() {
        return friendRequests;
    }

    public void setFriendRequests(ArrayList<String> friendRequests) {
        this.friendRequests = friendRequests;
    }

    public ArrayList<String> getListOfFriends() {
        return listOfFriends;
    }

    public void setListOfFriends(ArrayList<String> listOfFriends) {
        this.listOfFriends = listOfFriends;
    }

    public Map<String, ClientInterface> getListOfFriendsConnected() {
        return listOfFriendsConnected;
    }

    public void setListOfFriendsConnected(Map<String, ClientInterface> listOfFriendsConnected) {
        this.listOfFriendsConnected = listOfFriendsConnected;
    }

    public Map<ClientInterface, VChat> getActiveConversations() {
        return activeConversations;
    }

    public void setActiveConversations(Map<ClientInterface, VChat> activeConversations) {
        this.activeConversations = activeConversations;
    }

    
}
