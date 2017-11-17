package modelo;
import controlador.*;
import controlador.excepciones.InvalidProductQuantityException;
import controlador.excepciones.ListaItemsVacia;

import java.util.ArrayList;
import java.util.List;

public class Pedido {


    String nombreUsuario;
    List<ItemPedido> items = new ArrayList<ItemPedido>();


    public Pedido (String nombreUsuario, List<ItemPedido> items) {
        this.nombreUsuario = nombreUsuario;
        this.items = items;
    }

    public Pedido(){}


    public class ItemPedido {
        public Producto producto;
        public int quantitat;



        public ItemPedido(Producto p, int quantitat) {
            this.producto = p;
            this.quantitat = quantitat;
        }

        public  ItemPedido (){}

        public ItemPedido(int quantitat) {
            this.quantitat = quantitat;
        }

        public ItemPedido(Producto producto) {
            this.producto = producto;
        }

        public Producto getProducto() {
            return producto;
        }

        public void setProducto(Producto producto) {
            this.producto = producto;
        }

        public int getQuantitat() {
            return quantitat;
        }

        public void setQuantitat(int quantitat) {
            this.quantitat = quantitat;
        }
    }

    public Pedido (String nombreUsuario) {
        this(nombreUsuario, new ArrayList<ItemPedido>());
    }






    public void crearPedido(Producto producto, int cantidad) throws InvalidProductQuantityException {
        if(cantidad == 0) throw new InvalidProductQuantityException();
        this.items.add(new ItemPedido(producto, cantidad));
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public List<ItemPedido> getItems() {
        return this.items;
    }

    public void setItems(List<ItemPedido> items) {
        this.items = items;
    }

    public void checkItemsVacio(Pedido pedido) throws ListaItemsVacia {
        if(pedido.items.isEmpty()) throw new ListaItemsVacia();
    }

    @Override
    public String toString() {

        return "Pedido [nombreUsuario=" + nombreUsuario + ", listaItemsNombreProducto=" + items.get(0).producto.nombreProducto + ",,"+"]";
    }


}
