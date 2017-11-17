package controlador;

import controlador.excepciones.ColaPedidosVaciaException;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.Queue;


public class ColaImpl<T> implements Cola<T>{

    //T[] data;
    Queue<T> data;

    final static Logger log = Logger.getLogger(ColaImpl.class);

    // Constructor



    public ColaImpl() {

        data = new LinkedList<T>();
    }

    public ColaImpl(Queue<T> data) {
        this.data = data;
    }

    public Queue<T> getData() {
        return data;
    }

    public void setData(Queue<T> data) {
        this.data = data;
    }

    public static Logger getLog() {
        return log;
    }

    @Override
    public void push(T d)  {
        data.add(d);
    }

    @Override
    public T pop() throws ColaPedidosVaciaException {

        colaIsEmpty();              // Comprueba si la cola está vacía.
        return data.poll();
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public void colaIsEmpty() throws ColaPedidosVaciaException {
        if(data.size() == 0) throw new ColaPedidosVaciaException();
    }

    @Override
    public boolean contains(T d){
        return data.contains(d);
    }
}
