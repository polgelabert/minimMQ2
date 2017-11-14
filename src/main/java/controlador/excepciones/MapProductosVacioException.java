package controlador.excepciones;

public class MapProductosVacioException extends Exception {
    public MapProductosVacioException(){
        super(String.valueOf(-4));
    }
}
