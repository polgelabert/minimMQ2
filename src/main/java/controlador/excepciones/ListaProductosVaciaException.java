package controlador.excepciones;

public class ListaProductosVaciaException extends Exception {

    public ListaProductosVaciaException(){
        super(String.valueOf(-4));
    }
}
