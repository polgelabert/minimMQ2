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


/*
    public boolean listaProductosEsIgual(List<Producto> listaProductos){

        for(Producto p : listaProductos){

            for(int i=0; i < this.listaPedidosUsuario.get(0).listaProductos.size(); i++){

                if(p.nombreProducto == this.listaPedidosUsuario.get(0).listaProductos.get(i).nombreProducto){

                }

            }


        }

    }


*/




    /*

    public boolean pedidoIsInList(Pedido pedido1) {

        if(pedido1.nombreUsuario == this.nombreUsuario){

            for(Pedido p : this.listaPedidosUsuario){

            }

        }

        return false;
    }

    public boolean compararListaProductos(Pedido pedido1) {

        boolean requisito1 = false, requisito2 = false, result = false;

        for(int i = 0; i< this.listaPedidosUsuario.size(); i++){            // i recorre pedidos

            for (int j= 0; j< this.listaPedidosUsuario.get(i).listaProductos.size(); j++){

                if(this.listaPedidosUsuario.get(i).listaProductos.get(j).nombreProducto == pedido1.listaProductos.get(k).nombreProducto){
                    requisito1 = true;
                }
            }



            for(Producto p : pedido1.listaProductos){
                if ( pedido1.listaProductos.get(0).nombreProducto == this.listaPedidosUsuario.get(0).listaProductos.get(0).nombreProducto) {
                    requisito1 = true;
                }

                for ( Producto o : pedido1.listaProductos){


                }

            }



            for(Producto p : pedido1.listaProductos) {

                    for(int i = 0; i < this.listaPedidosUsuario.get(j).listaProductos.size(); i++){     // i = contador de listaProductos

                        if(p.nombreProducto == this.listaPedidosUsuario.get(j).getListaProductos().get(i).nombreProducto){
                            requisito1 = true;
                        }
                    }

            }
            */


}
