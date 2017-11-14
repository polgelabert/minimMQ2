package controlador.excepciones;

public class ColaPedidosVaciaException extends Exception {

    public ColaPedidosVaciaException(){
        super(String.valueOf(-1));
    }
}
