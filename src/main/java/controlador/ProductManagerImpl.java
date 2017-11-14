package controlador;

import controlador.excepciones.*;
import controlador.excepciones.MapProductosVacioException;
import modelo.Pedido;
import modelo.Producto;
import modelo.Usuario;
import org.apache.log4j.Logger;

import java.util.*;

public class ProductManagerImpl<colaPedidos> implements ProductManager<colaPedidos> {



    final static Logger log = Logger.getLogger(ProductManagerImpl.class.getName());


    private static ProductManagerImpl instance;
    private Map< String, Usuario> mapUser;
    private Map <String, Producto> mapProd;

    private ProductManagerImpl(){
        this.mapProd = new HashMap();
        this.mapUser = new HashMap();
    }

    String nombreUsuario;
    boolean check;
    private Queue<Pedido> colaPedidos = new PriorityQueue<>();


    public static ProductManagerImpl getInstance(){
        if(instance == null) {
            instance = new ProductManagerImpl();
            log.info("Se ha instanciado controlador.controlador.ProductManagerImpl por primera vez.");
        }
        return  instance;
    }



    //public void listaProductosOrdAscPrecio(){ }

    public boolean realizarPedido(Pedido pedido) throws UsuarioNoExisteException, ListaProductosVaciaException, ListaCantidadProductoVaciaException, MapProductosVacioException{
        log.info("Inicio realizarPedido.");

        //Check que el pedido esta ok:
        Usuario user = getUser(pedido.getNombreUsuario());              // Usuario existe?
        checkMapVacio(pedido);


        addPedidoAColaPedidos(pedido);

        log.info("Fin realizarPedido con éxito.");
        return true;
    }


    public Pedido servirPedido() throws ColaPedidosVaciaException, UsuarioNoExisteException, ListaProductosVaciaException, ListaCantidadProductoVaciaException {
        log.info("Inicio servirPedido.");

        Pedido pedido = servirPedidoDeCola();

        Usuario user = getUser(pedido.getNombreUsuario());
        user.addPedidoAListaPedidosUsuario(pedido);

/*
        //Check de que el pedido está ok.
        check = checkListaProductosVacia(pedido.getListaProductos());
        check = checkListaCantidadProductoVacia(pedido.getListaCantidades());
        Usuario user = getUser(pedido.getNombreUsuario());

        user.addPedidoAListaPedidosUsuario(pedido);                 //Añade pedido a listaPedidosUsuario

        log.info("Fin servirPedido con éxito.");
*/
        return pedido;
    }


    public List<Pedido> listaPedidosUsuario(String nombreUsuario) throws UsuarioNoExisteException, ListaPedidosUsuarioVaciaException {
        log.info("Inicio listaPedidosUsuario.");

        Usuario user = getUser(nombreUsuario);
        //user.checkListaPedidosUsuario();            // Comprueba si listaPedidosUsuario está vacia.

        log.info("Fin listaPedidosUsuario con éxito.");
        return user.getListaPedidosUsuario();

    }

    //public void listaProductosOrdDescVentas(){ }
















    public boolean crearUsuario(Usuario user) throws UsuarioYaExisteException {

        log.info("Inicio crearUsuario: " + user.getNombreUsuario());
        if(isUser(user.getNombreUsuario())) throw new UsuarioYaExisteException();          // lanza excepcion si isUser== true (lo contiene)
        mapUser.put(user.getNombreUsuario(), user);

        log.info("Fin crearUsuario: " + user.getNombreUsuario() + " con éxito.");
        return true;                                                        // return true ya que operacion ok
    }

    private Usuario getUser(String nombre) throws UsuarioNoExisteException {

        if (!mapUser.containsKey(nombre)) throw new UsuarioNoExisteException();
        return mapUser.get(nombre);
    }

    private boolean isUser (String nombre) { return (mapUser.containsKey(nombre)); }

    private boolean checkListaProductosVacia(List<Producto> listaProducto) throws ListaProductosVaciaException {
        if(listaProducto.size() == 0) throw new ListaProductosVaciaException();
        return true;
    }

    //private boolean checkListaCantidadProductoVacia (List<CantidadesProducto> listaCantidadProducto) throws ListaCantidadProductoVaciaException {
   //     if(listaCantidadProducto.size() == 0) throw new ListaCantidadProductoVaciaException();
   //     return true;
   // }

    private void addPedidoAColaPedidos (Pedido pedido){
        colaPedidos.add(pedido);
    }

    private Pedido servirPedidoDeCola() throws ColaPedidosVaciaException{

        if(colaPedidos.isEmpty()) throw new ColaPedidosVaciaException();
        return colaPedidos.poll();
    }

    public int sizeColaPedidos() {
        return this.colaPedidos.size();
    }

    public void reset() {
        this.instance = null;
    }

    private void checkMapVacio(Pedido pedido) throws MapProductosVacioException {
        if(pedido.getMapProductos().isEmpty()) throw new MapProductosVacioException();
    }
}
