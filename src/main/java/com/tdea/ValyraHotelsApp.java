package com.tdea;

import com.tdea.estructura.NodoSimple;
import com.tdea.modelos.ColaAtencion;
import com.tdea.modelos.HabitacionServicio;
import com.tdea.modelos.Huesped;
import com.tdea.consola.CLI;
import com.tdea.compartidos.enums.Generos;
import com.tdea.modelos.HuespedServicio;
import com.tdea.modelos.ListaHuespedes;

/*
Valyra Hotels es una aplicación de gestión hotelera desarrollada
en Java mediante una interfaz de línea de comandos(CLI), cuyo objetivo
es facilitar la administración de las operaciones básicas de un hotel de manera organizada y eficiente.
*/

public class ValyraHotelsApp {
    public static CLI consola = new CLI();
    private static ColaAtencion colaAtencion = ColaAtencion.getInstancia();
    private static ListaHuespedes listaHuespedes = ListaHuespedes.getInstancia();
    private static HuespedServicio huespedServicio = new HuespedServicio();
    private static HabitacionServicio habitacionServicio = new HabitacionServicio();

    public static void main(String[] args) {
        consola.limpiarConsola();
        habitacionServicio.crearHabitaciones();
        welcomeValyraHotels();
    }

    public static void welcomeValyraHotels() {
        Integer opcion = consola.mostrarMenu();

        switch (opcion) {
            case 1:
                huespedServicio.agregarHuespedCola();
                break;
            case 2:
                huespedServicio.checkInHuesped(habitacionServicio);
                break;
            case 3:
                colaAtencion.mostrarColaAtencion();
                break;
            case 4:
                listaHuespedes.mostrarListaHuespedes();
                break;

            default:
                break;
        }

        welcomeValyraHotels();
    }
}
