package controlador.excepciones;

public class UsuarioNoExisteException extends Exception {

    public UsuarioNoExisteException(){
        super(String.valueOf(-5));
    }

}
