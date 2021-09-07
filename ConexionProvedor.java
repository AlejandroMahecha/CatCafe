
package Modelo;
import java.util.List;
import java.sql.Connection;//importamos la libreria para generar la conexion con la base
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;


public class ConexionProvedor {
    
    Connection con;
    Conexion cn=new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    public boolean RegistrarProveedor(Proveedor pr){
        String sql="INSERT INTO proveedor(cedula,nombre,telefono,direccion) VALUES (?,?,?,?)";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, pr.getCedula());
            ps.setString(2,pr.getNombre());
            ps.setInt(3, pr.getTelefono());
            ps.setString(4, pr.getDireccion());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }finally{
            try{
                con.close();
            }catch(SQLException e){
                System.out.println(e.toString());
            }
        }
    }
    
    public List ListarProveedor(){
        List<Proveedor> Listaprov=new ArrayList();
        String sql="SELECT * FROM proveedor";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Proveedor pr=new Proveedor();
                pr.setId(rs.getInt("id"));
                pr.setCedula(rs.getInt("cedula"));
                pr.setNombre(rs.getString("nombre"));
                pr.setTelefono(rs.getInt("telefono"));
                pr.setDireccion(rs.getString("direccion"));
                Listaprov.add(pr);
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return Listaprov;
    }
    public boolean EliminarProveedor(int id){
        String sql="DELETE FROM proveedor WHERE id=?";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }finally{
            try{
                con.close();
            }catch(SQLException e){
                System.out.println(e.toString());
            }
        }
    }
    
    public boolean ModificarProveedor(Proveedor pr){
        String sql="UPDATE proveedor SET cedula=?,nombre=?,telefono=?,direccion=? WHERE id=?";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1,pr.getCedula());
            ps.setString(2, pr.getNombre());
            ps.setInt(3, pr.getTelefono());
            ps.setString(4, pr.getDireccion());
            ps.setInt(6, pr.getId());
            ps.execute();
            return true;         
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }finally{
            try{
                con.close();
            }catch(SQLException e){
                System.out.println(e.toString());
            }
        }
    }
    
}
