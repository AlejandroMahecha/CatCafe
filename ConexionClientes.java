
package Modelo;
import java.util.List;
import java.sql.Connection;//importamos la libreria para generar la conexion con la base
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

public class ConexionClientes {
    Conexion cn=new Conexion();
    Connection con; //Creamos la conexion con la base de datos, con la libreria de mysql
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegistrarCliente(Clientes cl){
        String sql="INSERT INTO clientes (cedula,nombre,telefono,direccion) VALUES (?,?,?,?)";//Comando para almacenar los datos en mysql
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, cl.getCedula());//Colocamos que se almacenen los valores obtenidos de la cedula en la fila 1
            ps.setString(2, cl.getNombre());
            ps.setInt(3, cl.getTelefono());
            ps.setString(4, cl.getDireccion());
            ps.execute();
            return true;
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }finally{
            try{
                con.close();
            }catch(SQLException e){
                System.out.println(e.toString());
                        
            }
        }
    }
    
    public List ListarCliente(){ //List permite almacenar datos en forma de arreglos
        List<Clientes> ListaCl = new ArrayList();
        String sql="SELECT * FROM clientes";
        try{
            con=cn.getConnection(); //conectamos a la base de datos
            ps=con.prepareStatement(sql);//PreparedStatement funciona para precompilar datos de la BD
            rs=ps.executeQuery();//
            while(rs.next()){
                Clientes cl=new Clientes();
                cl.setId(rs.getInt("id"));
                cl.setCedula(rs.getInt("cedula"));
                cl.setNombre(rs.getString("nombre"));
                cl.setTelefono(rs.getInt("telefono"));
                cl.setDireccion(rs.getString("direccion"));
                ListaCl.add(cl);
            }
            
        }catch(SQLException e){
            System.out.println(e.toString());
            
        }
        return ListaCl;
    }
    
    public boolean EliminarCliente(int id){
        String sql="DELETE FROM clientes WHERE id=?";
        try{
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
    
    public boolean ModificarCliente(Clientes cl){
        String sql="UPDATE clientes SET cedula=?, nombre=?,telefono=?,direccion=? WHERE id=?";
        try{
            ps=con.prepareStatement(sql);
            ps.setInt(1, cl.getCedula());
            ps.setString(2, cl.getNombre());
            ps.setInt(3, cl.getTelefono());
            ps.setString(4, cl.getDireccion());
            ps.setInt(5, cl.getId());   
            ps.execute();
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }finally{
            try{
                con.close();
            }catch (SQLException e){
                System.out.println(e.toString());
            }
        }
        
    }
    
    public Clientes Buscar(int num){
        Clientes cl=new Clientes();
        String sql="SELECT * FROM clientes WHERE cedula=?";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, num);
            rs=ps.executeQuery();
            if(rs.next()){
                cl.setNombre(rs.getString("nombre"));
                cl.setCedula(rs.getInt("cedula"));
                cl.setTelefono(rs.getInt("telefono"));
                cl.setDireccion(rs.getString("direccion"));
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return cl;
        
        
    }
}
