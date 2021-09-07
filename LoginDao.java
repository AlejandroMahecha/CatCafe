
package Modelo;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
public class LoginDao {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn=new Conexion();
    
    public Login log(String correo, String password){
        Login l=new Login();
        String sql="SELECT * FROM usuarios WHERE correo=? AND password=?";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1,correo);
            ps.setString(2,password);
            rs=ps.executeQuery();
            if(rs.next()){
                l.setId(rs.getInt("id"));
                l.setNombre(rs.getString("nombre"));
                l.setCorreo(rs.getString("correo"));
                l.setPassword(rs.getString("password"));
            }
            
            
        }catch(SQLException e){
            System.out.println(e.toString());       
        }
        return l;
    }
    
    public boolean RegistrarUsuario(Login log){
        String sql="INSERT INTO usuarios (nombre,correo,password) VALUES(?,?,?)";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, log.getNombre());
            ps.setString(2, log.getCorreo());
            ps.setString(3, log.getPassword());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }
    }
    
    
}
