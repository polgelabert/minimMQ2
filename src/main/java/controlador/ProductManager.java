package controlador;

import controlador.excepciones.*;
import modelo.Pedido;
import modelo.Producto;
import modelo.Usuario;

import java.util.List;

public interface ProductManager <colaPedidos> {



    //public void listaProductosOrdAscPrecio();

     boolean realizarPedido(Pedido pedido) throws UsuarioNoExisteException, ListaProductosVaciaException, ListaCantidadProductoVaciaException, MapProductosVacioException;

     Pedido servirPedido() throws ColaPedidosVaciaException, UsuarioNoExisteException, ListaProductosVaciaException, ListaCantidadProductoVaciaException;

     List<Pedido> listaPedidosUsuario(String nombreUsuario) throws UsuarioNoExisteException, ListaPedidosUsuarioVaciaException;

     //public void listaProductosOrdDescVentas();

     boolean crearUsuario(Usuario user) throws UsuarioYaExisteException;

     void reset();


}
