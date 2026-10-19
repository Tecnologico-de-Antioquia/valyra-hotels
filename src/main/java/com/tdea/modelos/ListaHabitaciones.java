package com.tdea.modelos;

import com.tdea.modelos.Habitacion;
import com.tdea.compartidos.enums.EstadoHabitacion;
import com.tdea.consola.CLI;
import com.tdea.estructura.NodoSimple;

public class ListaHabitaciones {
    private static final ListaHabitaciones listaHabitaciones = new ListaHabitaciones();
    public NodoSimple<Habitacion> lista = null;

    public CLI consola = new CLI();

    private String RED = "\u001B[31m";
    private String GREEN = "\u001B[32m";
    private String YELLOW = "\u001B[33m";
    private String BLUE = "\u001B[34m";
    private String PURPLE = "\u001B[35m";
    private String CYAN = "\u001B[36m";
    private String RESET = "\u001B[0m";

    private ListaHabitaciones() {
    }

    public static ListaHabitaciones getInstancia() {
        return listaHabitaciones;
    }

    public void crearHabitacion(Habitacion nuevaHabitacion) {
        NodoSimple<Habitacion> nuevoNodo = new NodoSimple<>(nuevaHabitacion);

        if (lista == null) {
            lista = nuevoNodo;
            return;
        }

        NodoSimple auxiliar = lista;

        nuevoNodo.setSiguiente(auxiliar);

        lista = nuevoNodo;
    }

    public Habitacion buscarHabitacion(Integer numeroHabitacion){
        
        NodoSimple auxiliar = lista;

        if (auxiliar == null) {
            consola.mostrarTexto("No hay habitaciones creadas");
            return null;
        }

        Habitacion habitacion = (Habitacion) auxiliar.getValor();
        
        while (auxiliar != null && habitacion.getNumero().compareTo(numeroHabitacion) != 0) {
            auxiliar = auxiliar.getSiguiente();
            if(auxiliar != null){
                habitacion = (Habitacion) auxiliar.getValor();
            }
        }

        if(auxiliar == null){
            consola.mostrarTexto("\nNo se encontró la habitación\n", RED);
            return null;
        }else{
            return habitacion;
        }
    }

    public void mostrarListaHabitaciones() {
        consola.mostrarTexto("\n---LISTA DE HABITACIONES---\n", CYAN);

        NodoSimple auxiliar = lista;

        if (auxiliar == null) {
            consola.mostrarTexto("No hay habitaciones creadas");
            return;
        }

        while (auxiliar != null) {
            Habitacion habitacion = (Habitacion) auxiliar.getValor();
            consola.mostrarTexto("----------");
            consola.mostrarTexto(habitacion.getNumero() + " - " + habitacion.getTipoHabitacion());
            if(habitacion.getEstadoHabitacion() == EstadoHabitacion.disponible){
                consola.mostrarTexto(habitacion.getEstadoHabitacion() + "", GREEN);
            }else{
                consola.mostrarTexto(habitacion.getEstadoHabitacion() + "", RED);
            }
            consola.mostrarTexto("----------\n");
            auxiliar = auxiliar.getSiguiente();
        }
    }
}
