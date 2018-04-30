/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorp2p;
import java.sql.*;

/**
 *
 * @author jose
 */
public class DBConnection {
   private static Connection conexion = null;
   private static final String DB = "bonitamessenger";
   private static final String USER = "messenger";
   private static final String PASSWORD = "bonitaPassword";
   private static final String URL = "jdbc:mysql://127.0.0.1:3306/";
   
   public DBConnection() {
      try {
         Class.forName("com.mysql.jdbc.Driver");
         conexion = DriverManager.getConnection(URL + DB, USER, PASSWORD);
      } catch(SQLException | ClassNotFoundException e) {
         System.out.println("Error conectando con la bd: " + e.getMessage());
      }
   }
   
   public static Connection getConnection() {
      try {
         if (conexion == null) {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(URL + DB, USER, PASSWORD);
         }
      } catch (SQLException |ClassNotFoundException e) {
         System.out.println("Error al contectar con la base de datos: " + e.getMessage());
      }
      return conexion;
   }
}

