package vista;

import controlador.*;
import controlador.excepciones.*;
import modelo.*;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

/**
 * POSTMAN LINK: https://www.getpostman.com/collections/406d65d19b392659255b
 */

@Path("/json")
public class JSONService {



    protected ProductManagerImpl pm  = ProductManagerImpl.getInstance();
    final static Logger log = Logger.getLogger(ProductManagerImpl.class.getName());
    String nombreProducto;
    Usuario user;

    String nombreUsuario;
    Pedido pedido1, pedido2;





    public JSONService() throws UsuarioYaExisteException, UsuarioNoExisteException, InvalidProductQuantityException {

        try {
            Producto p1 = new Producto("tomate", 1, 10);
            Producto p2 = new Producto("jamon", 5, 10);
            Producto p3 = new Producto("pan", 2, 10);
            Producto p4 = new Producto("manzana", 8, 10);
            Producto p5 = new Producto("queso", 10, 10);
            Producto p6 = new Producto("macarrones", 2, 10);

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
            pedido1.crearPedido(pm.getProductoDespensa(p1.getNombreProducto()), 1);        // Añade a la listaItems p1 y su cantidad asociada.
            pedido1.crearPedido(pm.getProductoDespensa(p2.getNombreProducto()), 4);
            pedido1.crearPedido(pm.getProductoDespensa(p3.getNombreProducto()), 8);
            pedido1.crearPedido(pm.getProductoDespensa(p4.getNombreProducto()), 3);

            pedido2 = new Pedido();
            pedido2.crearPedido(pm.getProductoDespensa(p1.getNombreProducto()), 1);
            pedido2.crearPedido(pm.getProductoDespensa(p2.getNombreProducto()), 5);

        }
        catch (Exception e) {
            log.fatal(e.getMessage() + e.getCause());
            e.printStackTrace();
            throw e;
        }


        pm.reset();
    }


    @GET
    @Path("/producto/{nombreProducto}")
    @Produces(MediaType.APPLICATION_JSON)
    public Producto realizarPedidoInJSON(@PathParam("nombreProducto") String nombreProducto) throws UsuarioNoExisteException, InvalidProductQuantityException, UsuarioYaExisteException, ListaItemsVacia, NoSuficienteStockException {

        try {
            boolean res = pm.realizarPedido(pedido1);

            return pm.getProductoDespensa(nombreProducto);
            //return pedido1;

        } catch (Exception e) {
            log.fatal(e.getMessage() + e.getCause());
            e.printStackTrace();
            log.fatal(e.getLocalizedMessage());

            throw e;
        }



    }


    @POST
    @Path("/realizarPedido")
    @Produces(MediaType.TEXT_PLAIN)
    public Response realizarPedido() throws UsuarioNoExisteException, InvalidProductQuantityException, UsuarioYaExisteException, ListaItemsVacia, NoSuficienteStockException {

        try {
            boolean res = pm.realizarPedido(pedido1);

            //return pedido1;
            return Response.status(200).entity(1).type("text/plain").build();

        } catch (Exception e) {
            throw e;
        }
    }


    @GET
    @Path("/servirPedido")
    @Produces(MediaType.APPLICATION_JSON)
    public Pedido servirPedidoInJSON() throws UsuarioNoExisteException, InvalidProductQuantityException, UsuarioYaExisteException, ListaItemsVacia, NoSuficienteStockException, ColaPedidosVaciaException {

        try {
            //boolean res = pm.realizarPedido(pedido1);
            //Pedido pedido = pm.getColaPedidos().pop();
            //return pedido;
            return pm.servirPedido();

        } catch (Exception e) {
            throw e;
        }
    }

    @GET
    @Path("/listaProdOrdAscPrecio")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Producto> listaProdOrdAscPrecioInJSON() throws UsuarioNoExisteException, InvalidProductQuantityException, UsuarioYaExisteException, ListaItemsVacia, NoSuficienteStockException, ColaPedidosVaciaException {

        try {

            return pm.listaProductosOrdAscPrecio();

        } catch (Exception e) {
            throw e;
        }
    }

    @GET
    @Path("/listaProdOrdDescVentas")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Producto> listaProdOrdDescVentasInJSON() throws UsuarioNoExisteException, InvalidProductQuantityException, UsuarioYaExisteException, ListaItemsVacia, NoSuficienteStockException, ColaPedidosVaciaException {

        try {
            boolean res = pm.realizarPedido(pedido1);
            return pm.listaProductosOrdDescVentas();

        } catch (Exception e) {
            throw e;
        }
    }

    @GET
    @Path("/listaPedidosUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pedido> listaPedidosUsuarioInJSON() throws UsuarioNoExisteException, InvalidProductQuantityException, UsuarioYaExisteException, ListaItemsVacia, NoSuficienteStockException, ColaPedidosVaciaException {

        try {
            boolean res = pm.realizarPedido(pedido1);
            Pedido pedido = pm.servirPedido();
            pedido2.setNombreUsuario("pol");
            res = pm.realizarPedido(pedido2);
            pedido = pm.servirPedido();

            String userName = pedido.getNombreUsuario();
            return pm.getMapUser().get(userName).getListaPedidosUsuario();


        } catch (Exception e) {
            throw e;
        }
    }




}






