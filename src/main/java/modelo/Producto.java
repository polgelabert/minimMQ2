package modelo;

public class Producto {


    String nombreProducto;
    int costeProducto;

    public Producto (String nombreProducto, int costeProducto) {
        this.nombreProducto = nombreProducto;
        this.costeProducto = costeProducto;
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





}
