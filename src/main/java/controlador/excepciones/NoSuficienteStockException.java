package controlador.excepciones;

public class NoSuficienteStockException extends Exception {
    public NoSuficienteStockException(){
        super(String.valueOf(-5));
    }
}
