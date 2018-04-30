/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorp2p;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jose
 */
public class ServerImpl extends UnicastRemoteObject implements ServerInterface {
    
    private Map<String, ClientInterface> onlineUsers;
    private UserDAO users;

    public ServerImpl() throws RemoteException {
        super();
        onlineUsers = new HashMap<>();
        users = new UserDAO();
    }

    @Override
    public synchronized boolean login(ClientInterface client, String user, String password)
            throws java.rmi.RemoteException {

        boolean aux = false;
        if (users.checkUser(user, password)) {
            if (!onlineUsers.containsValue(client)) {

                onlineUsers.put(user, client);

                //Se inicializan los friendNamea
                List<String> friends = users.getFriends(user);
                client.updateFriends(friends);

                //Se avisa a todos los usuarios conectados de que se ha conectado
                //un nuevo usuario, si son friendNames
                for (String friend : friends) {
                    if (this.onlineUsers.containsKey(friend)) {
                        ClientInterface friendInterface = this.onlineUsers.get(friend);
                        // Comprobamos que el friendName y el usuario no son la misma persona
                        if (friendInterface != client) {
                            client.friendConnected(friendInterface, friend);
                            friendInterface.friendConnected(client, user);
                        }
                    }
                }
                // Se comprueba si hay peticiones de amistad
                
                for (String friend : users.getFriendRequests(user)) {
                    client.friendRequest(friend);
                 
                }

                System.out.println("Sesión iniciada");
                aux = true;
            }

        } else {
            System.out.println("No se pudo iniciar sesión");
        }

        return aux;
    }

    //Cerramos la sesión del cliente. Posteriormente actualizamos el vector
    //de clientes conectados de los dems usuarios.
    @Override
    public synchronized boolean logout(
            ClientInterface client, String user, String password)
            throws java.rmi.RemoteException {

        //Se elimina de la lista de usuarios conectados
        if (onlineUsers.remove(user) != null) {
            List<String> friends = users.getFriends(user);
            for (String friendName : friends) {
                //Lo siguiente devuelve null si el friendName no esta conectado
                ClientInterface friendInterface = this.onlineUsers.get(friendName);
                // Si el exfriendName está conectado se elimina de la lista de friendNames conectados
                if (friendInterface != null) {
                    friendInterface.friendDisconnected(user);
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void deleteFriend(String friendName, String user, String password) throws RemoteException{
        //Se elimina la amistad entre ambos
        if (users.checkUser(user, password)) {
            
            // Se elimina al amigo
            users.deleteFriend(user, friendName);
            try {
                //Lista de friendNames actuales (luego de realizar el borrado)
                List<String> friends = users.getFriends(user);
                ClientInterface myInterface = onlineUsers.get(user);
                // se actualiza la lista de los amigos del usuario que lo borra
                myInterface.updateFriends(friends);

                friends = users.getFriends(friendName); //todos los amigos del amigo
                ClientInterface friendInterface = onlineUsers.get(friendName);
                // se actualiza la lista de friendNames del otro usuario
                friendInterface.updateFriends(friends);

                // se actualiza la lista de conectados para ambos usuarios
                myInterface.friendDisconnected(friendName);
                friendInterface.friendDisconnected(user);
            } catch (Exception ex) {
                System.out.println("Error al eliminar friendName:" + ex.getMessage());
            }
        }
    }

    @Override
    public List<String> findFriend(String friendName) throws RemoteException{
        List<String> usersList = users.searchUser(friendName);
        return usersList;
    }

    @Override
    public void denyFriendRequest(String user, String password, String friendName) throws RemoteException {
        if (users.checkUser(user, password)) {
            users.denyFriendRequest(user, friendName);
        }
    }

    @Override
    public void acceptFriendRequest(String user, String password, String friendName) throws RemoteException{
        if (users.checkUser(user, password)) {
            users.addFriend(user, friendName); //Se acepta la peticion de amistad
            //Se busca la interfaz del amigo que envio la peticion
            //Es posible que no se encuentre (en caso de que no este conectado)
            ClientInterface friendInterface = onlineUsers.get(friendName); //es posible que sea null
            ClientInterface myInterface = this.onlineUsers.get(user); //este sí esta conectado

            try {
                myInterface.addFriend(friendName);
                
                if (friendInterface != null) { //y en caso de que este conectado (quien envio la peticion)
                    friendInterface.addFriend(user);
                    myInterface.friendConnected(friendInterface, friendName);
                    friendInterface.friendConnected(myInterface, user);
                }
                // Si no esta conectado, cuando inicie sesión en la base de datos aparecerá como friendName, ya que la amistad es reciproca
            } catch (Exception e) {
                System.out.println("Error al aceptar la amistad:" + e.getMessage());
            }
        }
    }

    @Override
    public boolean signUp(String user, String password) throws java.rmi.RemoteException{
        if (users.checkUser(user, password)) {
            // Si el usuario ya existe devolvemos false
            return false;
        } else {
            // Si el usuario no existe lo añadimos y devolvemos true
            users.addUser(user,password);
            return true;
        }
    }

    @Override
    public boolean changePassword(String user, String oldPass, String newPass) throws java.rmi.RemoteException{
        boolean cp = false;
        if (users.checkUser(user, oldPass)) {
            cp = users.changePassword(user, oldPass, newPass);
        }
        return cp;
    }

    @Override
    public void friendRequest(String user, String password, String friendName) throws java.rmi.RemoteException{
        //Si existe el usuario, temas de seguridad
        if (users.checkUser(user, password)) {
            //Si existe el amigo, y no son amigos y el usuario y el amigo no son la misma persona  
            if (users.existUser(friendName) && !users.checkFriends(user, friendName) && !friendName.equals(user)) {
                try {
                    users.addFriendRequest(user, friendName); //se añade la peticion a la base de datos

                    ClientInterface friendInterface = this.onlineUsers.get(friendName);

                    if (friendInterface != null) //Si el usuario que recibe la peticion esta conectado
                    {
                        // Se actualiza la lista de peticiones en el friendName
                        friendInterface.friendRequest(user);
                    }
                } catch (Exception ex) {
                    System.out.println("Excepcion en SolicitarAmigo: " + ex.getMessage());
                }
            }
        }
    }

}
    
