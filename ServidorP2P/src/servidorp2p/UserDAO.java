/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorp2p;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jose
 */
public class UserDAO {
    private Connection conexion;
    
    public UserDAO() {
        this.conexion = DBConnection.getConnection();
    }
    
    public void addUser(String user, String password) {
        try {
            String sqlUpdate = "INSERT INTO Users (userid, password) VALUES (?, ?)";
            PreparedStatement pstmt = conexion.prepareStatement(sqlUpdate);
            pstmt.setString(1,user);
            pstmt.setString(2, password);
            pstmt.execute();
        } catch (SQLException e) {
            System.out.println("Error SQL addUser " + e.getMessage());
        }
    }
    
    public String getUser() {
        try {
            PreparedStatement pstmt = null;
            String query= "SELECT userid FROM Users";
            pstmt = conexion.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            // pstmt.addBatch();
            //pstmt.execute();
            ArrayList<String> users = new ArrayList();
            while (rs.next()) {
              users.add(rs.getString("userid"));
           }
            return users.get(4);
      } catch (SQLException e) {
         System.out.println("SQL Exception: " + e.getMessage());  
           return null;
      } 
    }   
    
    public boolean checkUser(String user,String password) {
        
        try {
            String query = "SELECT 1 from Users where userid = ? and password = ?";
            PreparedStatement pstmt = conexion.prepareStatement(query);
            pstmt.setString(1, user);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();     
        } catch (SQLException e) {
            System.out.println("Excepción SQL: " + e.getMessage());
        }
        return false;
    }
    public boolean existUser(String user) {
        try {
            String query = "SELECT 1 FROM Users where userid = ?";
            PreparedStatement pstmt = conexion.prepareStatement(query);
            pstmt.setString(1, user);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Exceptión SQL comprobando usuario" + e.getMessage());
        }
        return false;
    }
    
    public boolean changePassword(String user, String oldPassword, String newPassword) {
        try {
            String sqlUpdate = ("UPDATE usuarios SET password = ?  WHERE userid = ? AND password = ?;");
            PreparedStatement pstmt = conexion.prepareStatement(sqlUpdate);
            pstmt.setString(1,newPassword);
            pstmt.setString(2,user);
            pstmt.setString(3,oldPassword);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Excepción SQL: " + e.getMessage());
        }
        return false;
    }
    
    
   
    public ArrayList<String> getFriends(String user) {
        ArrayList<String> friends;
        try {
            PreparedStatement pstmt = null;
            String query= "SELECT user2 as friend FROM Friends WHERE user1=? UNION SELECT user1 as friend FROM Friends WHERE user2=? ";
            pstmt = conexion.prepareStatement(query);
            pstmt.setString(1, user);
            pstmt.setString(2, user);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<String> users = new ArrayList();
            while (rs.next()) {
              users.add(rs.getString("friend"));
            }
            return users;
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());  
            return null;
        } 
        
    }
    
      public List<String> getFriendRequests(String user) {
        List<String> friendRequests = new ArrayList();
        try {
            PreparedStatement pstmt = null;
            String query = "SELECT * FROM Friendrequests WHERE requested = ?";
            pstmt = conexion.prepareStatement(query);
            pstmt.setString(1, user);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                friendRequests.add(rs.getString(1)); //primera columna (se empieza a contar en 1)
            }
          //  existeUsuario = rs.next();
        } catch (java.sql.SQLException e) {
            System.out.println("Error ejecutando la sentencia SQL ListaPeticiones");
        }
        return friendRequests;
    }

    public ArrayList<String> searchUser(String user) {
        if (user.length() > 3) {
            ArrayList<String> users = new ArrayList<>();
            try {

                String query = ("SELECT userid FROM Users WHERE userid LIKE '%" + user + "%';");
                PreparedStatement pstmt  = conexion.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery(query);
                while (rs.next()) {
                    users.add(rs.getString(1));
                }
            } catch (java.sql.SQLException e) {
                System.out.println("Error ejecutando la sentencia SQL buscarUsuariosNick");
            }
            return users;
        } else {
            return null;
        }    
    }
    
    public boolean addFriendRequest(String user, String friendName) {
        try {
            String sqlUpdate = "INSERT INTO Friendrequests (petitioner, requested) VALUES (?, ?)";
            PreparedStatement pstmt = conexion.prepareStatement(sqlUpdate);
            pstmt.setString(1,user);
            pstmt.setString(2, friendName);
            pstmt.execute();
        } catch (SQLException e) {
            System.out.println("Error SQL addFriendRequest " + e.getMessage());
        }
        return false;
    }
    
    public void denyFriendRequest(String user, String friendName) {
        try {
            String sqlUpdate = "DELETE FROM Friendrequests WHERE petitioner = ? AND requested = ?";
            PreparedStatement pstmt = conexion.prepareStatement(sqlUpdate);
            pstmt.setString(1,friendName);
            pstmt.setString(2,user);
            pstmt.execute();
        } catch (SQLException e) {
            System.out.println("Error SQL denyFriendRequest: " +  e.getMessage());
        }
    }
   
      public void deleteFriend(String user, String friendName) {
          int deleted;
          try { 
            String query = "DELETE FROM Friends WHERE user1 = ? AND user2 = ?";
            PreparedStatement pstmt = conexion.prepareStatement(query);
            pstmt.setString(1, user);
            pstmt.setString(2, friendName);
            deleted = pstmt.executeUpdate();
            if (deleted == 0) {
                query = "DELETE FROM Friends WHERE user1 = ? AND user2 = ?";
                pstmt = conexion.prepareStatement(query);
                pstmt.setString(1, friendName);
                pstmt.setString(2, user);
                pstmt.executeUpdate();
            }
          
        } catch (SQLException e) {
            System.out.println("Error en SQL deleteFriend: " + e.getMessage());
        }
    }
   
    public void addFriend(String user, String friendName) {
        try {
            String sqlUpdate = "INSERT INTO Friends (user1, user2) VALUES (?, ?)";
            PreparedStatement pstmt = conexion.prepareStatement(sqlUpdate);
            pstmt.setString(1,user);
            pstmt.setString(2, friendName);
            pstmt.execute();
        } catch (SQLException e) {
            System.out.println("Error SQL addFriend " + e.getMessage());
        }
    }

    public boolean checkFriends(String user, String friendName) {
        boolean friendship = false;
        try {
            PreparedStatement pstmt = null;
            String query= "SELECT count(*)  FROM Friends WHERE user1=? and user2=? UNION SELECT count(*) FROM Friends WHERE user2=? and user1=?";
            pstmt = conexion.prepareStatement(query);
            pstmt.setString(1, user);
            pstmt.setString(2, friendName);
            pstmt.setString(3,friendName);
            pstmt.setString(4,user);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
              friendship = true;
            }
            
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());  
        }
        return friendship;
        
    }
        
}
