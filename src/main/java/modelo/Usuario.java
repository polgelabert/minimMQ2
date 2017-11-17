package modelo;

import controlador.excepciones.ListaPedidosUsuarioVaciaException;

import java.util.ArrayList;
import java.util.List;

public class Usuario {


    String nombreUsuario;
    List<Pedido> listaPedidosUsuario = new ArrayList<>();



    public Usuario(){}

    public Usuario (String nombreUsuario, List<Pedido> listaPedidosUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.listaPedidosUsuario = listaPedidosUsuario;
    }

    public Usuario (String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.listaPedidosUsuario = new ArrayList<>();
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public List<Pedido> getListaPedidosUsuario() {
        return listaPedidosUsuario;
    }

    public void setListaPedidosUsuario(List<Pedido> listaPedidosUsuario) {
        this.listaPedidosUsuario = listaPedidosUsuario;
    }

    public void addPedidoAListaPedidosUsuario(Pedido pedido) {
        listaPedidosUsuario.add(pedido);
    }

    public void checkListaPedidosUsuario() throws ListaPedidosUsuarioVaciaException {
        if(listaPedidosUsuario.isEmpty()) throw new ListaPedidosUsuarioVaciaException();
    }




}
