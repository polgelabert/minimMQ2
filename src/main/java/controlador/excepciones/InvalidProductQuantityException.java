package controlador.excepciones;

public class InvalidProductQuantityException extends Exception {

    public InvalidProductQuantityException(){
        super(String.valueOf(-2));
    }
}
