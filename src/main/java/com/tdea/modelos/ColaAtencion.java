package com.tdea.modelos;

import com.tdea.consola.CLI;
import com.tdea.estructura.NodoSimple;

public class ColaAtencion {

    private static final ColaAtencion colaAtencion = new ColaAtencion();
    public NodoSimple<Huesped> cola = null;

    private int totalAgregados = 0;
    private int sumaTamanoCola = 0;
    private int eventosCola = 0;

    public CLI consola = new CLI();

    private String RED = "\u001B[31m";
    private String GREEN = "\u001B[32m";
    private String YELLOW = "\u001B[33m";
    private String BLUE = "\u001B[34m";
    private String PURPLE = "\u001B[35m";
    private String CYAN = "\u001B[36m";
    private String RESET = "\u001B[0m";

    private ColaAtencion() {
    }

    public static ColaAtencion getInstancia() {
        return colaAtencion;
    }

    public void agregarHuesped(Huesped nuevoHuesped) {
        NodoSimple<Huesped> nuevoNodo = new NodoSimple<>(nuevoHuesped);

        if (cola == null) {
            cola = nuevoNodo;
        } else {
            NodoSimple<Huesped> auxiliar = cola;

            while (auxiliar.getSiguiente() != null) {
                auxiliar = auxiliar.getSiguiente();
            }

            auxiliar.setSiguiente(nuevoNodo);
        }

        totalAgregados += 1;
        registrarEventoCola();
    }

    public Huesped atenderHuesped() {
        if (cola == null) {
            consola.mostrarTexto("\n---COLA DE ATENCIÓN---\n", CYAN);
            consola.mostrarTexto("No hay huéspedes para atender");
            return null;
        }

        NodoSimple<Huesped> auxiliar = cola;

        cola = cola.getSiguiente();
        auxiliar.setSiguiente(null);

        Huesped huesped = auxiliar.getValor();
        registrarEventoCola();
        return huesped;
    }

    public int contarHuespedes() {
        int cantidad = 0;
        NodoSimple<Huesped> auxiliar = cola;

        while (auxiliar != null) {
            cantidad += 1;
            auxiliar = auxiliar.getSiguiente();
        }

        return cantidad;
    }

    public int getTotalAgregados() {
        return totalAgregados;
    }

    public double getPromedioCola() {
        if (eventosCola == 0) {
            return 0;
        }

        return (double) sumaTamanoCola / eventosCola;
    }

    public void mostrarColaAtencion() {
        consola.mostrarTexto("\n---COLA DE ATENCIÓN---\n", CYAN);

        NodoSimple<Huesped> auxiliar = cola;

        if (auxiliar == null) {
            consola.mostrarTexto("No hay huéspedes en la cola de atención");
            return;
        }

        Integer i = 1;
        while (auxiliar != null) {
            Huesped huesped = auxiliar.getValor();
            consola.mostrarTexto(i + ". " + huesped.getNombre() + " - " + huesped.getDocumento() + " - "
                    + huesped.getEdad() + " años." + " - " + huesped.getGenero());
            auxiliar = auxiliar.getSiguiente();
            i = i + 1;
        }
    }

    private void registrarEventoCola() {
        sumaTamanoCola += contarHuespedes();
        eventosCola += 1;
    }
}
