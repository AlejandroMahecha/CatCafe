
package Modelo;

public class Adicionales {
    private int id;
    private String codigo;
    private int cantidad;
    private double precio;
    private int numeroid;
    
    public Adicionales(){       
    }

    public Adicionales(int id, String codigo, int cantidad, double precio, int numeroid) {
        this.id = id;
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.numeroid = numeroid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getNumeroid() {
        return numeroid;
    }

    public void setNumeroid(int numeroid) {
        this.numeroid = numeroid;
    }
    
    
    
}
