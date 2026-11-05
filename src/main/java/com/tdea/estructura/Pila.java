package com.tdea.estructura;

/**
 * Pila genérica construida con nodos simples.
 * No utiliza clases de colecciones de Java.
 * @param <T> Es el tipo de dato almacenado
 **/

public class Pila<T> {
    private NodoSimple<T> cima = null;

    public void apilar(T valor) {
        NodoSimple<T> nuevoNodo = new NodoSimple<>(valor);
        nuevoNodo.setSiguiente(cima);
        cima = nuevoNodo;
    }

    public T desapilar() {
        if (cima == null) {
            return null;
        }

        T valor = cima.getValor();
        cima = cima.getSiguiente();
        return valor;
    }

    public T verCima() {
        if (cima == null) {
            return null;
        }

        return cima.getValor();
    }

    public boolean esVacia() {
        return cima == null;
    }

    public NodoSimple<T> getCima() {
        return cima;
    }
}
