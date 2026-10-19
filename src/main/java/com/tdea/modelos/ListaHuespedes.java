package com.tdea.modelos;

import com.tdea.modelos.Huesped;
import com.tdea.consola.CLI;
import com.tdea.estructura.NodoSimple;

public class ListaHuespedes {
    private static final ListaHuespedes listaHuespedes = new ListaHuespedes();
    public NodoSimple<Huesped> lista = null;

    public CLI consola = new CLI();

    private String RED = "\u001B[31m";
    private String GREEN = "\u001B[32m";
    private String YELLOW = "\u001B[33m";
    private String BLUE = "\u001B[34m";
    private String PURPLE = "\u001B[35m";
    private String CYAN = "\u001B[36m";
    private String RESET = "\u001B[0m";

    private ListaHuespedes() {
    }

    public static ListaHuespedes getInstancia() {
        return listaHuespedes;
    }

    public void regitrarHuesped(Huesped nuevoHuesped) {
        NodoSimple<Huesped> nuevoNodo = new NodoSimple<>(nuevoHuesped);

        if (lista == null) {
            lista = nuevoNodo;
            return;
        }

        NodoSimple auxiliar = lista;

        nuevoNodo.setSiguiente(auxiliar);

        lista = nuevoNodo;
    }

    public void mostrarListaHuespedes() {
        consola.mostrarTexto("\n---LISTA DE HUESPEDES---\n", CYAN);

        NodoSimple auxiliar = lista;

        if (auxiliar == null) {
            consola.mostrarTexto("No hay huespedes registrados");
            return;
        }

        Integer i = 1;
        while (auxiliar != null) {
            Huesped huesped = (Huesped) auxiliar.getValor();
            consola.mostrarTexto(i + ". " + "-----------------------");
            consola.mostrarTexto(huesped.getNombre() + " - " + huesped.getDocumento() + " - "
                    + huesped.getEdad() + " años");
            consola.mostrarTexto("Habitación: " + huesped.getHabitacion().getNumero() + " - "
                    + huesped.getHabitacion().getTipoHabitacion());
            consola.mostrarTexto("-----------------------\n");
            auxiliar = auxiliar.getSiguiente();
            i = i + 1;
        }
    }
}
