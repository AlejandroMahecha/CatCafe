package Modelo;
import java.sql.*;

public class Conexion{ 
    private static String user = "root";
    private static String password = "";
    private static String url = "jdbc:mysql://localhost:3306/sistemaventa";

    public static Connection getConnection(){
        Connection con = null;

        try {
            //Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);

        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error SQLException : " + ex);
        }
        return con;
    }
}
