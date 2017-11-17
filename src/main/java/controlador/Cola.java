package controlador;


import controlador.excepciones.ColaPedidosVaciaException;

public interface Cola<T> {


    public void push(T d) ;

    public T pop() throws ColaPedidosVaciaException;

    public int size();

    public void colaIsEmpty() throws ColaPedidosVaciaException;


    public boolean contains(T d);

}
