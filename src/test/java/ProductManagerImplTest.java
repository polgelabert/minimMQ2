import controlador.*;
import controlador.excepciones.*;
import modelo.*;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.*;


public class ProductManagerImplTest {



    final static Logger log = Logger.getLogger(ProductManagerImplTest.class.getName());          // test2.class.getname indica el nombre de la clase.

    private ProductManagerImpl pm;

    String nombreProducto;
    Usuario user;
    //private Queue<Pedido> colaPedidos = new PriorityQueue<>();

    String nombreUsuario;
    Pedido pedido1, pedido2;
    //HashMap<Producto, Integer> mapProductos = new HashMap<>();
    //HashMap<String, Producto> despensa = new HashMap<>();
    //private Cola<Pedido> colaPedidos = new ColaImpl<Pedido>();
    //Pedido pedido = null;



    @Before
    public void SetUp() throws UsuarioYaExisteException, InvalidProductQuantityException, UsuarioNoExisteException {

        this.pm = ProductManagerImpl.getInstance();


        try {

            Producto p1 = new Producto("tomate", 1, 10);
            Producto p2 = new Producto("jamon",5, 10);
            Producto p3 = new Producto("pan",2, 10);
            Producto p4 = new Producto("manzana",8, 10);
            Producto p5 = new Producto("queso",10, 10);
            Producto p6 = new Producto("macarrones",2, 10);

            pm.addDespensa(p1.getNombreProducto(), p1);
            pm.addDespensa(p2.getNombreProducto(), p2);
            pm.addDespensa(p3.getNombreProducto(), p3);
            pm.addDespensa(p4.getNombreProducto(), p4);
            pm.addDespensa(p5.getNombreProducto(), p5);
            pm.addDespensa(p6.getNombreProducto(), p6);

            user = new Usuario("pol");
            pm.crearUsuario(user);

            //Creacion de un pedido con los productos disponibles en la despensa.
            pedido1 = new Pedido(pm.getUserNameFromMapUser("pol"));  // getUserNameFromMapUser comprueba que el nobmreUsuario exista en el MapUser
            pedido1.crearPedido(pm.getProductoDespensa(p1.getNombreProducto()),1);        // Añade a la listaItems p1 y su cantidad asociada.
            pedido1.crearPedido(pm.getProductoDespensa(p2.getNombreProducto()), 4);
            pedido1.crearPedido(pm.getProductoDespensa(p3.getNombreProducto()), 8);
            pedido1.crearPedido(pm.getProductoDespensa(p4.getNombreProducto()), 3);

            pedido2 = new Pedido();
            pedido2.crearPedido(pm.getProductoDespensa(p1.getNombreProducto()),1);
            pedido2.crearPedido(pm.getProductoDespensa(p2.getNombreProducto()), 5);

        }
        catch (Exception e) {
            //log.fatal(e.getMessage() + e.getCause());
            //e.printStackTrace();
            throw e;
        }

    }

    @After
    public void TearDown(){
        pm.getInstance().reset();
    }


    @Test
    public void listaProductosOrdAscPrecio() {

        List<Producto> listaProductosReturned = pm.listaProductosOrdAscPrecio();
    }

    @Test
    public void realizarPedidoTest() throws UsuarioNoExisteException, ListaItemsVacia, NoSuficienteStockException, InvalidProductQuantityException {

        // Pedido realiaz correctamente
        Assertions.assertEquals(0, pm.colaPedidosSize());
        pm.realizarPedido(pedido1);
        Assertions.assertEquals(1, pm.colaPedidosSize());


        // Check pedido incorrecto.
        //Fuera de stock (manzana)
        pedido1.getItems().get(0).quantitat = 20;
        Assertions.assertThrows(NoSuficienteStockException.class, () -> {pm.realizarPedido(pedido1) ;});

        //BadName
        Assertions.assertThrows(UsuarioNoExisteException.class, () -> {pedido2.setNombreUsuario(pm.getUserNameFromMapUser("BadName")) ;});

        //pedido2 sin nombreUsuario
        Assertions.assertThrows(UsuarioNoExisteException.class, () -> {pm.realizarPedido(pedido2) ;});

        //pedido2 sin listaItems
        pedido2 = new Pedido(pm.getUserNameFromMapUser("pol"));      //Reinicio pedido2 con 0 items.
        Assertions.assertThrows(ListaItemsVacia.class, () -> {pm.realizarPedido(pedido2) ;});
    }


    @Test
    public void servirPedidoTest() throws ColaPedidosVaciaException, UsuarioNoExisteException, ListaItemsVacia, NoSuficienteStockException, InvalidProductQuantityException {

        pm.realizarPedido(pedido1);
        pedido2.setNombreUsuario(pm.getUserNameFromMapUser("pol"));
        pm.realizarPedido(pedido2);

        // Servir 2 pedidos correctamente.
        Assertions.assertTrue(user.getListaPedidosUsuario().isEmpty());
        Pedido pedidoServido = pm.servirPedido();
        Assertions.assertEquals(1, user.getListaPedidosUsuario().size());

        Pedido pedidoServido2 = pm.servirPedido();
        Assertions.assertEquals(2, user.getListaPedidosUsuario().size());

        Assertions.assertThrows(ColaPedidosVaciaException.class, () -> {pm.servirPedido() ;});
    }


    @Test
    public void listaPedidosUsuario() throws UsuarioNoExisteException, ListaPedidosUsuarioVaciaException , ColaPedidosVaciaException, ListaItemsVacia, NoSuficienteStockException, InvalidProductQuantityException {

        Assertions.assertThrows(ListaPedidosUsuarioVaciaException.class, () -> {pm.listaPedidosUsuario(pm.getUserNameFromMapUser("pol")) ;});

        pm.realizarPedido(pedido1);
        pedido2.setNombreUsuario(pm.getUserNameFromMapUser("pol"));
        pm.realizarPedido(pedido2);
        Pedido pedidoServido = pm.servirPedido();
        pedidoServido = pm.servirPedido();

        List<Pedido> listaPedidosReturned =  pm.listaPedidosUsuario(pedidoServido.getNombreUsuario());
        Assertions.assertFalse(pm.listaPedidosUsuario(pm.getUserNameFromMapUser("pol")).isEmpty());

    }

    @Test
    public void listaProductosOrdDescVentas() throws NoSuficienteStockException, UsuarioNoExisteException, InvalidProductQuantityException, ListaItemsVacia {

        pm.realizarPedido(pedido1);
        List<Producto> listaProductosOrdDescVentas = pm.listaProductosOrdDescVentas();
    }


}
