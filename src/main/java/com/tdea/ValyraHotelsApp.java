package com.tdea;

import com.tdea.consola.CLI;
import com.tdea.modelos.ColaAtencion;
import com.tdea.modelos.ColaMasaje;
import com.tdea.modelos.HabitacionServicio;
import com.tdea.modelos.HuespedServicio;
import com.tdea.modelos.ListaHuespedes;
import com.tdea.modelos.ListaServicios;
import com.tdea.modelos.MetricasServicio;
import com.tdea.modelos.ServicioServicio;

/*
Valyra Hotels es una aplicación de gestión hotelera desarrollada
en Java mediante una interfaz de línea de comandos(CLI), cuyo objetivo
es facilitar la administración de las operaciones básicas de un hotel de manera organizada y eficiente.
*/

public class ValyraHotelsApp {
    public static CLI consola = new CLI();
    private static ColaAtencion colaAtencion = ColaAtencion.getInstancia();
    private static ColaMasaje colaMasaje = ColaMasaje.getInstancia();
    private static ListaHuespedes listaHuespedes = ListaHuespedes.getInstancia();
    private static ListaServicios listaServicios = ListaServicios.getInstancia();
    private static HuespedServicio huespedServicio = new HuespedServicio();
    private static HabitacionServicio habitacionServicio = new HabitacionServicio();
    private static ServicioServicio servicioServicio = new ServicioServicio();
    private static MetricasServicio metricasServicio = new MetricasServicio();

    public static void main(String[] args) {
        consola.limpiarConsola();
        habitacionServicio.crearHabitaciones();
        servicioServicio.crearServicios();
        welcomeValyraHotels();
    }

    public static void welcomeValyraHotels() {
        Integer opcion = null;

        do {
            opcion = consola.mostrarMenu();

            switch (opcion) {
                case 1:
                    huespedServicio.agregarHuespedCola();
                    break;
                case 2:
                    huespedServicio.checkInHuesped(habitacionServicio);
                    break;
                case 3:
                    huespedServicio.checkOutHuesped(habitacionServicio);
                    break;
                case 4:
                    huespedServicio.comprarServicio(servicioServicio);
                    break;
                case 5:
                    colaAtencion.mostrarColaAtencion();
                    break;
                case 6:
                    listaHuespedes.mostrarListaHuespedes();
                    break;
                case 7:
                    metricasServicio.mostarMetricas();
                    break;
                case 8:
                    colaMasaje.mostrarColaMasaje();
                    break;
                case 9:
                    colaMasaje.finalizarServicio();
                    break;
                case 10:
                    listaServicios.mostrarListaServicios();
                    break;
                case 11:
                    habitacionServicio.mostrarHabitaciones();
                    break;
                case 0:
                    consola.mostrarTexto("\nGracias por usar Valyra Hotels\n", "\u001B[32m");
                    break;
                default:
                    break;
            }
        } while (opcion != 0);
    }
}
