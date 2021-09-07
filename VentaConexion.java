package Modelo;


import java.sql.Connection;//importamos la libreria para generar la conexion con la base
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VentaConexion{
    Connection con;
    Conexion cn=new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    public int IdVenta(){
        int id=0;
        String sql="SELECT MAX(id) FROM ventas";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            if(rs.next()){
                id=rs.getInt(1);
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return id;
    }
    
    public int RegistrarVenta(Venta venta){
        String sql="INSERT INTO ventas(cliente,total,fecha) VALUES (?,?,?)";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, venta.getCliente());
            ps.setDouble(2, venta.getTotal());
            ps.setString(3, venta.getFecha());
            ps.execute();
        }catch(SQLException e){
            System.out.println(e.toString());
        }finally{
            try{
                con.close();
            }catch(SQLException e){
                System.out.println(e.toString());
            }
        }
        return r;
    }
    
    public int RegistrarAdicionales(Adicionales ad){
        String sql="INSERT INTO detalle (codigo_prod,cantidad,precio,id_venta) VALUES (?,?,?,?)";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, ad.getCodigo());
            ps.setInt(2, ad.getCantidad());
            ps.setDouble(3,ad.getPrecio());
            ps.setInt(4, ad.getId());
            ps.execute();
        }catch(SQLException e){
            System.out.println(e.toString());   
        }finally{
            try{
                con.close();
            }catch(SQLException e){
                System.out.println(e.toString());
            }
        }
        return r;
    }
    
    public boolean ActualizarCantidad(int cant,String codigo){
        String sql="UPDATE productos SET cantidad=? WHERE codigo=?";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, cant);
            ps.setString(2,codigo );
            ps.execute();
            return true;
            
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
            
        }
        
    }
    public List ListarVentas(){ //List permite almacenar datos en forma de arreglos
        List<Venta> ListaVenta= new ArrayList();
        String sql="SELECT * FROM ventas";
        try{
            con=cn.getConnection(); //conectamos a la base de datos
            ps=con.prepareStatement(sql);//PreparedStatement funciona para precompilar datos de la BD
            rs=ps.executeQuery();//
            while(rs.next()){
                Venta ven=new Venta();
                ven.setCedula(rs.getInt("id"));
                ven.setCliente(rs.getString("cliente"));
                ven.setTotal(rs.getDouble("total"));
                ListaVenta.add(ven);
            }
            
        }catch(SQLException e){
            System.out.println(e.toString());
            
        }
        return ListaVenta;
    }   
}
