package com.tdea.modelos;

import java.util.concurrent.ThreadLocalRandom;

import com.tdea.compartidos.enums.EstadoHabitacion;
import com.tdea.compartidos.enums.Generos;
import com.tdea.compartidos.enums.TipoHabitacion;
import com.tdea.consola.CLI;
import com.tdea.estructura.NodoSimple;
import com.tdea.modelos.Habitacion;
import com.tdea.modelos.ListaHabitaciones;

public class HabitacionServicio {
    public static CLI consola = new CLI();
    private static ListaHabitaciones listaHabitaciones = ListaHabitaciones.getInstancia();

    private String RED = "\u001B[31m";
    private String GREEN = "\u001B[32m";
    private String YELLOW = "\u001B[33m";
    private String BLUE = "\u001B[34m";
    private String PURPLE = "\u001B[35m";
    private String CYAN = "\u001B[36m";
    private String RESET = "\u001B[0m";

    public void crearHabitaciones() {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 5; j++) {
                Integer numeroHabitacion = (i * 100) + j;
                TipoHabitacion tipoHabitacion = TipoHabitacion.values()[ThreadLocalRandom.current()
                        .nextInt(TipoHabitacion.values().length)];

                Habitacion nuevaHabitacion = new Habitacion(numeroHabitacion, tipoHabitacion,
                        EstadoHabitacion.disponible);
                listaHabitaciones.crearHabitacion(nuevaHabitacion);
            }
        }
    }

    public void asignarHabitacion(Huesped huesped){
        listaHabitaciones.mostrarListaHabitaciones();

        consola.mostrarTexto("Atendiendo a " + huesped.getNombre() + "\n");
        
        Habitacion habitacion = null;
        while(habitacion == null || habitacion.getEstadoHabitacion() == EstadoHabitacion.ocupada){
            Integer numeroHabitacion = consola.leerNatural("Seleccione numero de habitación");
            habitacion = listaHabitaciones.buscarHabitacion(numeroHabitacion);

            if(habitacion != null && habitacion.getEstadoHabitacion() == EstadoHabitacion.ocupada){
                consola.mostrarTexto("\nLa habitación no esta disponible\n", RED);
            }
        }
        huesped.setHabitacion(habitacion);
        habitacion.setEstadoHabitacion(EstadoHabitacion.ocupada);
        consola.mostrarTexto("\nSe ha asignado la habitación\n", GREEN);
    }
}
