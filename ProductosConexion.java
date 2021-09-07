
package Modelo;

import java.util.List;
import java.sql.Connection;//importamos la libreria para generar la conexion con la base
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import javax.swing.JComboBox;


public class ProductosConexion {
    Connection con;
    Conexion cn=new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegistrarProdcutos(Productos pro){
        String sql="INSERT INTO productos (codigo,descripcion,cantidad,precio,proveedor) VALUES (?,?,?,?,?)";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setInt(3, pro.getCantidad());
            ps.setDouble(4, pro.getPrecio());
            ps.setString(5, pro.getProveedor());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }
    }
    
    public void ConsultarProv(JComboBox proveedor){
        String sql="SELECT nombre FROM proveedor";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                proveedor.addItem(rs.getString("nombre"));
            }
            
        }catch(SQLException e){
            System.out.println(e.toString());
            
        }
        
    }
    
    public List ListarProductos(){ //List permite almacenar datos en forma de arreglos
        List<Productos> Listapro = new ArrayList();
        String sql="SELECT * FROM productos";
        try{
            con=cn.getConnection(); //conectamos a la base de datos
            ps=con.prepareStatement(sql);//PreparedStatement funciona para precompilar datos de la BD
            rs=ps.executeQuery();//
            while(rs.next()){
                Productos pro=new Productos();
                pro.setId(rs.getInt("id"));
                pro.setCodigo(rs.getString("codigo"));
                pro.setNombre(rs.getString("descripcion"));
                pro.setCantidad(rs.getInt("cantidad"));
                pro.setPrecio(rs.getDouble("precio"));
                pro.setProveedor(rs.getString("proveedor"));
                Listapro.add(pro);
            }
            
        }catch(SQLException e){
            System.out.println(e.toString());
            
        }
        return Listapro;
    }
    
    public boolean EliminarProductos(int id){
        String sql="DELETE FROM productos WHERE id=?";
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
    
   public boolean ModificarProductos(Productos pro){
        String sql="UPDATE productos SET codigo=?, descripcion=?,cantidad=?,precio=?,proveedor=? WHERE id=?";
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setInt(3, pro.getCantidad());
            ps.setDouble(4, pro.getPrecio());
            ps.setString(5, pro.getProveedor());   
            ps.setInt(6, pro.getId());
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
   
   public Productos BuscarProductos(String cod){
       Productos producto=new Productos();
       String sql="SELECT * FROM productos WHERE codigo=?";
       try{
           con=cn.getConnection();
           ps=con.prepareStatement(sql);
           ps.setString(1, cod);
           rs=ps.executeQuery();
           if(rs.next()){
               producto.setNombre(rs.getString("descripcion"));
               producto.setPrecio(rs.getDouble("precio"));
               producto.setCantidad(rs.getInt("cantidad"));
            }
           
       }catch(SQLException e){
           System.out.println(e.toString());
       }
       return producto;
   }
}
