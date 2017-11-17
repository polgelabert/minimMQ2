package controlador;

import controlador.excepciones.*;
import modelo.*;

import org.apache.log4j.Logger;
import java.util.*;


public class ProductManagerImpl implements ProductManager {


    final static Logger log = Logger.getLogger(ProductManagerImpl.class.getName());


    private static ProductManagerImpl instance;
    private Map <String, Usuario> mapUser;
    private Map <String, Producto> despensa;


    public ProductManagerImpl(){
        this.despensa = new HashMap();
        this.mapUser = new HashMap();
    }

    String nombreUsuario;
    boolean check;
    Producto producto;
    int cantidad;
    private Cola<Pedido> colaPedidos = new ColaImpl<Pedido>();


    public static ProductManagerImpl getInstance(){
        if(instance == null) {
            instance = new ProductManagerImpl();
            log.info("Se ha instanciado controlador.ProductManagerImpl por primera vez.");
        }
        return  instance;
    }



    public List<Producto> listaProductosOrdAscPrecio(){
        log.info("Inicio listaProductosOrdAscPrecio");
        List<Producto> listaProductos = new ArrayList<>();
        listaProductos.addAll(despensa.values());
        listaProductos.sort(Comparator.comparingInt(Producto::getCosteProducto));

        log.info("Fin listaProductosOrdAscPrecio con éxito.");
        return listaProductos;

    }

    public boolean realizarPedido(Pedido pedido) throws UsuarioNoExisteException, ListaItemsVacia, NoSuficienteStockException, InvalidProductQuantityException {
        log.info("Inicio realizarPedido.");

        //Check que el data esta ok:
        Usuario user = getUser(pedido.getNombreUsuario());              // Usuario existe?
        pedido.checkItemsVacio(pedido);

        enviarPedido(pedido);

        log.info("Fin realizarPedido con éxito.");
        return true;
    }

    public Pedido servirPedido() throws ColaPedidosVaciaException, UsuarioNoExisteException{
        log.info("Inicio servirPedido.");

        Pedido pedido = colaPedidos.pop();

        Usuario user = getUser(pedido.getNombreUsuario());
        user.addPedidoAListaPedidosUsuario(pedido);

        log.info("Fin servirPedido con éxito.");
        return pedido;
    }

    public List<Pedido> listaPedidosUsuario(String nombreUsuario) throws UsuarioNoExisteException, ListaPedidosUsuarioVaciaException {
        log.info("Inicio listaPedidosUsuario.");

        Usuario user = getUser(nombreUsuario);
        user.checkListaPedidosUsuario();            // Comprueba si listaPedidosUsuario está vacia.

        log.info("Fin listaPedidosUsuario con éxito.");
        return user.getListaPedidosUsuario();

    }

    public List<Producto> listaProductosOrdDescVentas(){
        log.info("Inicio listaProductosOrdDescVentas");

        List<Producto> listaProductos = new ArrayList<>();
        listaProductos.addAll(despensa.values());
        listaProductos.sort(Comparator.comparingInt(Producto::getVentas).reversed());       //.reversed() -> orden decreciente.

        log.info("Fin listaProductosOrdDescVentas con éxito.");
        return listaProductos;
    }





    private void enviarPedido(Pedido pedido) throws InvalidProductQuantityException, NoSuficienteStockException {

        for(int i = 0; i< pedido.getItems().size(); i++){

            Producto p = pedido.getItems().get(i).producto;
            int stockInicial = despensa.get(p.getNombreProducto()).getStock();
            int quantitat = pedido.getItems().get(i).quantitat;

            if(stockInicial > quantitat){
                p.setStock(stockInicial - quantitat);           // Actualizamos stock
                p.setVentas(quantitat);

            } else { throw new NoSuficienteStockException(); }
        }

        colaPedidos.push(pedido);                       // Añadimos a la cola

    }

    public int colaPedidosSize() { return colaPedidos.size();}

    public void addDespensa(String nombreProducto, Producto producto){
        despensa.put(nombreProducto, producto);
    }

    public Producto getProductoDespensa (String nombreProducto) { return despensa.get(nombreProducto);}

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

    public void reset() {
        this.instance = null;
    }

    public String getUserNameFromMapUser(String nombreUsuario) throws UsuarioNoExisteException{
        if(!isUser(nombreUsuario)) throw new UsuarioNoExisteException();
        return mapUser.get(nombreUsuario).getNombreUsuario();
    }


    public static Logger getLog() {
        return log;
    }

    public static void setInstance(ProductManagerImpl instance) {
        ProductManagerImpl.instance = instance;
    }

    public Map<String, Usuario> getMapUser() {
        return mapUser;
    }

    public void setMapUser(Map<String, Usuario> mapUser) {
        this.mapUser = mapUser;
    }

    public Map<String, Producto> getDespensa() {
        return despensa;
    }

    public void setDespensa(Map<String, Producto> despensa) {
        this.despensa = despensa;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Cola<Pedido> getColaPedidos() {
        return colaPedidos;
    }

    public void setColaPedidos(Cola<Pedido> colaPedidos) {
        this.colaPedidos = colaPedidos;
    }



}
