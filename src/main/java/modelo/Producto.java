package modelo;

public class Producto {


    String nombreProducto;
    int costeProducto;
    private int stock;
    private int ventas;

    public Producto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Producto(){}

    public Producto (String nombreProducto, int costeProducto, int stock) {
        this.nombreProducto = nombreProducto;
        this.costeProducto = costeProducto;
        this.stock = stock;
        this.ventas = 0;
    }

    public Producto (String nombreProducto, int costeProducto, int stock, int ventas) {
        this.nombreProducto = nombreProducto;
        this.costeProducto = costeProducto;
        this.stock = stock;
        this.ventas = ventas;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getCosteProducto() {
        return costeProducto;
    }

    public void setCosteProducto(int costeProducto) {
        this.costeProducto = costeProducto;
    }

    public Integer getStock() {
        return stock;
    }

    public Integer getVentas() {
        return ventas;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setVentas(int ventas) {
        this.ventas = ventas;
    }
}
