package controlador;

import controlador.excepciones.*;
import modelo.*;

import java.util.List;



public interface ProductManager  {


    public List<Producto> listaProductosOrdAscPrecio();

     boolean realizarPedido(Pedido pedido) throws UsuarioNoExisteException, ListaItemsVacia,  NoSuficienteStockException, InvalidProductQuantityException;

     Pedido servirPedido() throws ColaPedidosVaciaException, UsuarioNoExisteException;

     List<Pedido> listaPedidosUsuario(String nombreUsuario) throws UsuarioNoExisteException, ListaPedidosUsuarioVaciaException;

     public List<Producto> listaProductosOrdDescVentas();




     boolean crearUsuario(Usuario user) throws UsuarioYaExisteException;

     void reset();


}
