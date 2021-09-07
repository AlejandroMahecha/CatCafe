
package Modelo.Resultados;

import Modelo.Conexion;
import java.sql.Connection;//importamos la libreria para generar la conexion con la base
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Locale;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
public class Grafica {
    public static void Graficar(String fecha){
        Connection con;
        Conexion cn=new Conexion();
        PreparedStatement ps;
        ResultSet rs;
        try{
            String sql="SELECT total FROM ventas WHERE fecha=?";
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, fecha);
            rs=ps.executeQuery();//EJECUTAMOS LA CONSULTA
            DefaultPieDataset dateset=new DefaultPieDataset();
            while(rs.next()){
                dateset.setValue(rs.getString("total"), rs.getDouble("total")); 
            }
            JFreeChart jf=ChartFactory.createPieChart3D("Reporte grafico de ventas", dateset);
            ChartFrame cf=new ChartFrame("Total de ventas por dia",jf);
            cf.setSize(1000,500);
            cf.setLocationRelativeTo(null);
            cf.setVisible(true);
        }catch(SQLException e){
            System.out.println(e.toString());
            
        }
    }
    
}
