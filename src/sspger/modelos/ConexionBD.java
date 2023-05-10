package sspger.modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionBD {
    
    private static String driver = "com.mysql.jdbc.Driver";
    private static String nombreBase = "controlescolar";
    private static String hostname = "localhost";
    private static String puerto = "3306";
    private static String usuario = "Cesarele23";
    private static String password = "cesarkirbith";
    
    private static String urlConexion = "jdbc:mysql://" + hostname + ":" + puerto + "/" + nombreBase + 
                                        "?allowPublicKeyRetrieval=true&useSSL=false";
    
    public static Connection abrirConexionBD(){
        Connection conexion = null;
        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(urlConexion, usuario, password);
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println("Error de conexión con BD: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        return conexion;
    }
    
}
