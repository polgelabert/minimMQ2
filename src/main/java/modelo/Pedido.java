package modelo;

import java.util.HashMap;

public class Pedido {


    String nombreUsuario;
    HashMap<Producto, Integer> mapProductos = new HashMap<>();



    public Pedido(){}

    public Pedido (String nombreUsuario, HashMap<Producto,Integer> mapProductos) {
        this.nombreUsuario = nombreUsuario;
        this.mapProductos = mapProductos;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public HashMap<Producto, Integer> getMapProductos() {
        return mapProductos;
    }

    public void setMapProductos(HashMap<Producto, Integer> mapProductos) {
        this.mapProductos = mapProductos;
    }
}
