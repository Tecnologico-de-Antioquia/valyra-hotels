package com.tdea.estructura;

/**
 * Almacena un valor y un nodo siguiente
 * @param <T> Es el tipo de dato del valor
**/

public class NodoSimple<T> {
    private T valor;
    private NodoSimple<T> siguiente;

    public NodoSimple(T valor) {
        this.valor = valor;
    }

    public T getValor() {
        return valor;
    }

    public void setSiguiente(NodoSimple<T> siguienteNodo) {
        this.siguiente = siguienteNodo;
    }

    public NodoSimple<T> getSiguiente() {
        return this.siguiente;
    }
}
