import controlador.ProductManagerImpl;
import controlador.excepciones.*;
import modelo.Pedido;
import modelo.Producto;
import modelo.Usuario;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.*;


public class ProductManagerImplTest {



    final static Logger log = Logger.getLogger(ProductManagerImplTest.class.getName());          // test2.class.getname indica el nombre de la clase.

    private ProductManagerImpl pm;

    String nombreProducto;
    Usuario user;
    private Queue<Pedido> colaPedidos = new PriorityQueue<>();

    String nombreUsuario;
    HashMap<Producto, Integer> mapProductos = new HashMap<>();


    @Before
    public void SetUp() throws UsuarioYaExisteException {

        this.pm = ProductManagerImpl.getInstance();


        try {

            Producto p1 = new Producto("tomate",1);
            Producto p2 = new Producto("jamon",5);
            mapProductos.put(p1,2);
            mapProductos.put(p2,4);

            user = new Usuario("pol");
            pm.crearUsuario(user);


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
    public void realizarPedidoTest() throws UsuarioNoExisteException, ListaProductosVaciaException, ListaCantidadProductoVaciaException, MapProductosVacioException {

        Assertions.assertEquals(0, pm.sizeColaPedidos());
        Pedido pedido = new Pedido(user.getNombreUsuario(), mapProductos);

        Assertions.assertTrue(pm.realizarPedido(pedido));
        Assertions.assertEquals(1, pm.sizeColaPedidos());

        /*
        // Assert BadName y colaPedidos vacía.
        Assertions.assertThrows(UsuarioNoExisteException.class, () -> { pm.realizarPedido(pedido) ;});
        Assertions.assertEquals(0,pm.sizeColaPedidos());


        Assertions.assertTrue(pm.realizarPedido(user.getNombreUsuario(), listaProductos, listaCantidades));

        Assertions.assertEquals(1,pm.sizeColaPedidos());        //Assert colaPedidosSize = 1;

    //Se copian listaProductos para posterior reasignarla.
        //listaProductosReserva = listaProductos;
        listaProductos.clear();
        Assertions.assertThrows(ListaProductosVaciaException.class, () -> { pm.realizarPedido(user.getNombreUsuario(), listaProductos, listaCantidades) ;});
        //listaProductos = listaProductosReserva;

        Producto p1 = new Producto("tomate",1);
        Producto p2 = new Producto("jamon",5);
        listaProductos.add(p1);
        listaProductos.add(p2);

        listaCantidades.clear();
        Assertions.assertThrows(ListaCantidadProductoVaciaException.class, () -> { pm.realizarPedido(user.getNombreUsuario(), listaProductos, listaCantidades) ;});
*/
    }



    @Test
    public void servirPedidoTest() throws ColaPedidosVaciaException, UsuarioNoExisteException, ListaProductosVaciaException, ListaCantidadProductoVaciaException, MapProductosVacioException {


        Pedido pedido = new Pedido(user.getNombreUsuario(), mapProductos);
        Assertions.assertTrue(pm.realizarPedido(pedido));
        Assertions.assertTrue(user.getListaPedidosUsuario().isEmpty());

        Assertions.assertEquals(1, pm.sizeColaPedidos());
        pm.servirPedido();
        Assertions.assertEquals(0, pm.sizeColaPedidos());
        Assertions.assertFalse(user.getListaPedidosUsuario().isEmpty());



/*
       //listaPedidos is Empty.
        Assert.assertTrue(user.getListaPedidosUsuario().isEmpty());

        //Para servir pedido, antes se añade:
        Pedido pedido1 = new Pedido(user.getNombreUsuario(), listaProductos, listaCantidades);
        pm.realizarPedido(user.getNombreUsuario(), listaProductos, listaCantidades);

        //Comprueba size colaPedido = 1;
        Assertions.assertEquals(1, pm.sizeColaPedidos());

        pm.servirPedido();

        //user.getListaPedidosUsuario().pedidoIsInList(pedido1);
        Assertions.assertEquals(1,user.getListaPedidosUsuario().size());
        Assertions.assertEquals("tomate", user.getListaPedidosUsuario().get(0).getListaProductos().get(0).getNombreProducto());

        //Comprueba size colaPedido = 0;
        Assertions.assertEquals(0, pm.sizeColaPedidos());


        //Check Pedidos en mal estado:
        //Pedido BadPedido1 = new Pedido("BadName", listaProductos, listaCantidades);


        listaProductosReserva = listaProductos;
        listaProductos = new ArrayList<>();
        //Pedido BadPedido2 = new Pedido(user.getNombreUsuario(), listaProductos, listaCantidades);
        //Assertions.assertThrows(ListaProductosVaciaException.class, () -> { pm.servirPedido() ;});
        listaProductos = listaProductosReserva;

        listaCantidadesReserva = listaCantidades;
        listaCantidades = new ArrayList<>();
        //Pedido BadPedido3 = new Pedido(user.getNombreUsuario(), listaProductos, listaCantidades);
        //Assertions.assertThrows(ListaCantidadProductoVaciaException.class, () -> { pm.servirPedido() ;});
        listaCantidades = listaCantidadesReserva;
*/
    }


    @Test
    public void listaPedidosUsuario() throws UsuarioNoExisteException, ListaPedidosUsuarioVaciaException, ListaCantidadProductoVaciaException, ListaProductosVaciaException, ColaPedidosVaciaException, MapProductosVacioException {


        Assertions.assertTrue(user.getListaPedidosUsuario().isEmpty());

        Pedido pedido = new Pedido(user.getNombreUsuario(), mapProductos);
        Assertions.assertTrue(pm.realizarPedido(pedido));
        pm.servirPedido();

        List<Pedido> listaPedidosReturned =  pm.listaPedidosUsuario(user.getNombreUsuario());
        //Assertions.assertFalse(user.getListaPedidosUsuario().isEmpty());

        Assertions.assertEquals(mapProductos.get(0), listaPedidosReturned.get(0).getMapProductos().get(0));

        /*
        Assertions.assertTrue(user.getListaPedidosUsuario().isEmpty());

        //Para comprobar que se ha llenado la listaPedidosUsuario, antes se añade un pedido.
        Pedido pedido1 = new Pedido(user.getNombreUsuario(), listaProductos, listaCantidades);
        pm.realizarPedido(user.getNombreUsuario(), listaProductos, listaCantidades);
        pedido1 = new Pedido(user.getNombreUsuario(), listaProductos, listaCantidades);
        pm.servirPedido();

        List<Pedido> listaExpected = pm.listaPedidosUsuario(user.getNombreUsuario());

        Assertions.assertEquals("tomate", listaExpected.get(0).getListaProductos().get(0).getNombreProducto());
*/

    }



}
