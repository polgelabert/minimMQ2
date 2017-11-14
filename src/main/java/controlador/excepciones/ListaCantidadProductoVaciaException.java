package controlador.excepciones;

public class ListaCantidadProductoVaciaException extends Exception {
    public ListaCantidadProductoVaciaException(){
        super(String.valueOf(-2));
    }
}
