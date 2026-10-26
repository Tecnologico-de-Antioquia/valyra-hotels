package com.tdea.modelos;

import java.util.concurrent.ThreadLocalRandom;

import com.tdea.compartidos.enums.EstadoHabitacion;
import com.tdea.compartidos.enums.Generos;
import com.tdea.compartidos.enums.TipoHabitacion;
import com.tdea.consola.CLI;
import com.tdea.estructura.NodoSimple;
import com.tdea.modelos.Habitacion;
import com.tdea.modelos.ListaHabitaciones;

public class ServicioServicio {
    public static CLI consola = new CLI();
    private static ListaServicios listaServicios = ListaServicios.getInstancia();

    private String RED = "\u001B[31m";
    private String GREEN = "\u001B[32m";
    private String YELLOW = "\u001B[33m";
    private String BLUE = "\u001B[34m";
    private String PURPLE = "\u001B[35m";
    private String CYAN = "\u001B[36m";
    private String RESET = "\u001B[0m";

    public void crearServicios() {
        Servicio restauranteServicio = new Servicio("RES", "Restaurante", 1);
        Servicio lavanderiaServicio = new Servicio("LAV", "Lavandería", 2000);
        Servicio masajeServicio = new Servicio("MAS", "Masaje", 25000);
        Servicio cajaFuerteServicio = new Servicio("CAJ", "Caja fuerte", 15000);
        Servicio vehiculoServicio = new Servicio("VEH", "Alquiler de vehículo", 30000);

        listaServicios.crearServicio(restauranteServicio);
        listaServicios.crearServicio(lavanderiaServicio);
        listaServicios.crearServicio(masajeServicio);
        listaServicios.crearServicio(cajaFuerteServicio);
        listaServicios.crearServicio(vehiculoServicio);
    }

    /*public void asignarHabitacion(Huesped huesped) {
        listaHabitaciones.mostrarListaHabitaciones();

        consola.mostrarTexto("Atendiendo a " + huesped.getNombre() + "\n");

        Habitacion habitacion = null;
        while (habitacion == null || habitacion.getEstadoHabitacion() == EstadoHabitacion.ocupada) {
            Integer numeroHabitacion = consola.leerNatural("Seleccione numero de habitación");
            habitacion = listaHabitaciones.buscarHabitacion(numeroHabitacion);

            if (habitacion != null && habitacion.getEstadoHabitacion() == EstadoHabitacion.ocupada) {
                consola.mostrarTexto("\nLa habitación no esta disponible\n", RED);
            }
        }
        huesped.setHabitacion(habitacion);
        habitacion.setEstadoHabitacion(EstadoHabitacion.ocupada);
        consola.mostrarTexto("\nSe ha asignado la habitación\n", GREEN);
    }*/
}
