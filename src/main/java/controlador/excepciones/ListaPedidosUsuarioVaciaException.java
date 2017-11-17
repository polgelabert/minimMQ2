package controlador.excepciones;

public class ListaPedidosUsuarioVaciaException extends Exception {

    public ListaPedidosUsuarioVaciaException(){
        super(String.valueOf(-4));
    }

}
