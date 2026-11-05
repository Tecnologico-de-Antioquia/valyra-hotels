package com.tdea.modelos;

import com.tdea.compartidos.enums.EstadoHuesped;
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

    public Huesped buscarHuesped(String documentoHuesped) {

        NodoSimple<Huesped> auxiliar = lista;

        if (auxiliar == null) {
            consola.mostrarTexto("No hay huéspedes registrados");
            return null;
        }

        Huesped huesped = auxiliar.getValor();

        while (auxiliar != null && !huesped.getDocumento().equals(documentoHuesped)) {
            auxiliar = auxiliar.getSiguiente();
            if (auxiliar != null) {
                huesped = auxiliar.getValor();
            }
        }

        if (auxiliar == null) {
            consola.mostrarTexto("\nNo se encontró el huésped\n", RED);
            return null;
        } else {
            return huesped;
        }
    }

    public void regitrarHuesped(Huesped nuevoHuesped) {
        NodoSimple<Huesped> nuevoNodo = new NodoSimple<>(nuevoHuesped);

        if (lista == null) {
            lista = nuevoNodo;
            return;
        }

        NodoSimple<Huesped> auxiliar = lista;

        nuevoNodo.setSiguiente(auxiliar);

        lista = nuevoNodo;
    }

    public void retirarHuesped(Huesped huespedRetiro) {
        if (huespedRetiro == null) {
            consola.mostrarTexto("\nNo se pudo retirar el huésped\n", RED);
            return;
        }

        huespedRetiro.setEstado(EstadoHuesped.retirado);
    }

    public int contarHuespedes() {
        int cantidad = 0;
        NodoSimple<Huesped> auxiliar = lista;

        while (auxiliar != null) {
            cantidad += 1;
            auxiliar = auxiliar.getSiguiente();
        }

        return cantidad;
    }

    public void mostrarListaHuespedes() {
        consola.mostrarTexto("\n---LISTA DE HUESPEDES---\n", CYAN);

        NodoSimple<Huesped> auxiliar = lista;

        if (auxiliar == null) {
            consola.mostrarTexto("No hay huéspedes registrados");
            return;
        }

        Integer i = 1;
        while (auxiliar != null) {
            Huesped huesped = auxiliar.getValor();
            consola.mostrarTexto(i + ". " + "-----------------------");
            consola.mostrarTexto(huesped.getNombre() + " - " + huesped.getDocumento() + " - "
                    + huesped.getEdad() + " años");
            consola.mostrarTexto("Habitación: " + huesped.getHabitacion().getNumero() + " - "
                    + huesped.getHabitacion().getTipoHabitacion());
            consola.mostrarTexto("Estado: " + huesped.getEstado());
            consola.mostrarTexto("-----------------------\n");
            auxiliar = auxiliar.getSiguiente();
            i = i + 1;
        }
    }
}
