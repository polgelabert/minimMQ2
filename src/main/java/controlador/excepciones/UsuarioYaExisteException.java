package controlador.excepciones;

public class UsuarioYaExisteException extends Exception {

    public UsuarioYaExisteException(){
        super(String.valueOf(-6));
    }

}
