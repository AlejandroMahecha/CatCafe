
package Modelo;

public class Venta {
    private int cedula;
    private String cliente;
    private double total;
    private int id;
    private String fecha;
   
    public Venta(){
        
    }

    public Venta(int cedula, String cliente, double total,String fecha) {
        this.cedula = cedula;
        this.cliente = cliente;
        this.total = total;
        this.fecha=fecha;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }  

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
}