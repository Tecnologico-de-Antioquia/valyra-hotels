package com.tdea.modelos;

import com.tdea.compartidos.enums.EstadoHabitacion;
import com.tdea.compartidos.enums.Generos;
import com.tdea.compartidos.enums.TipoHabitacion;
import com.tdea.consola.CLI;
import com.tdea.estructura.NodoSimple;
import com.tdea.modelos.ColaAtencion;
import com.tdea.modelos.Habitacion;

public class HuespedServicio {
    public static CLI consola = new CLI();
    private static ColaAtencion colaAtencion = ColaAtencion.getInstancia();
    private static ListaHuespedes listaHuespedes = ListaHuespedes.getInstancia();

    private String RED = "\u001B[31m";
    private String GREEN = "\u001B[32m";
    private String YELLOW = "\u001B[33m";
    private String BLUE = "\u001B[34m";
    private String PURPLE = "\u001B[35m";
    private String CYAN = "\u001B[36m";
    private String RESET = "\u001B[0m";

    public void agregarHuespedCola() {
        consola.mostrarTitulo("Agregar Huesped a la Cola", PURPLE);

        String documento = consola.leerTexto("Ingrese el documento del huesped");
        String nombre = consola.leerTexto("Ingrese el nombre del huesped");
        Integer edad = consola.leerNatural("Ingrese la edad del huesped");

        Generos genero = null;
        while (genero == null) {
            Integer generoAux = consola
                    .leerNatural("Ingrese el genero del huesped (1. Hombre, 2. Mujer, 3. Indeterminado)");
            if (generoAux == 1) {
                genero = Generos.Hombre;
            } else if (generoAux == 2) {
                genero = Generos.Mujer;
            } else if (generoAux == 3) {
                genero = Generos.Mujer;
            }
        }

        Huesped nuevoHuesped = new Huesped(documento, nombre, edad, genero);

        colaAtencion.agregarHuesped(nuevoHuesped);
    }

    public void checkInHuesped(HabitacionServicio habitacionServicio) {
        Huesped huesped = colaAtencion.atenderHuesped();

        if (huesped == null) {
            return;
        }

        consola.mostrarTitulo("Registrar Huesped (check-in)", PURPLE);

        habitacionServicio.asignarHabitacion(huesped);
        listaHuespedes.regitrarHuesped(huesped);
    }

    public void checkOutHuesped(HabitacionServicio habitacionServicio) {
        String documentoHuesped = consola.leerTexto("Ingrese el documento del huésped");
        Huesped huesped = listaHuespedes.buscarHuesped(documentoHuesped);

        if (huesped == null) {
            return;
        }

        consola.mostrarTitulo("Retirar Huesped (check-out)", PURPLE);

        listaHuespedes.regitrarHuesped(huesped);
    }
}
