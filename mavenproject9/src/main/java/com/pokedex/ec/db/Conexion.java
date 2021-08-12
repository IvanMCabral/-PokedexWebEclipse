
package com.pokedex.ec.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author 
 */
public class Conexion 
{
    private static Connection conn = null;
    private static String login = "root";
    private static String pass = "Genios942";
    private static String url = "jdbc:mysql://localhost:3306/pokedex";
    
    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,login,pass);
            conn.setAutoCommit(false);
            
            if (conn != null) {
                              
            }
            else{
                System.out.println("Conexion Erronea");
            }
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Conexion Fallida");
        }
        
        return conn;
        
        }
        
        public void desconexion(){
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("Error al desconectar");
            }
        }

    public int executeQuery(String query) throws ClassNotFoundException, SQLException {
    return conn.createStatement().executeUpdate(query);
  }
    }
    

