package com.tdea.modelos;

import com.tdea.compartidos.enums.EstadoHabitacion;
import com.tdea.compartidos.enums.Generos;
import com.tdea.compartidos.enums.TipoHabitacion;
import com.tdea.consola.CLI;
import com.tdea.estructura.NodoSimple;
import com.tdea.modelos.ColaAtencion;

import src.main.java.com.tdea.modelos.Habitacion;

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

        Huesped nuevoHuesped = new Huesped(documento, nombre, edad, Generos.Hombre);

        colaAtencion.agregarHuesped(nuevoHuesped);
    }

    public void checkInHuesped(){
        Huesped huesped = colaAtencion.atenderHuesped();

        if(huesped == null){
            return;
        }

        Integer numeroHabitacion = consola.leerNatural("Ingrese el numero de habitación");
        Integer tipoHabitacionAux = 0;
        TipoHabitacion tipoHabitacion = TipoHabitacion.sencilla;

        while(tipoHabitacionAux >= 1 && tipoHabitacionAux <= 3 ){
            Integer tipoHabitacionAux = consola.leerNatural("Ingrese el tipo de habitación (1: sencilla / 2: doble / 3: familiar)");
        }

        if(tipoHabitacionAux == 1){
            tipoHabitacion = TipoHabitacion.sencilla;
        } else if(tipoHabitacionAux == 2){
            tipoHabitacion = TipoHabitacion.doble;
        }else{
            tipoHabitacion = TipoHabitacion.familiar;
        }

        Habitacion nuevaHabitacion = new Habitacion(numeroHabitacion, tipoHabitacion, EstadoHabitacion.ocupada);
        huesped.setHabitacion(nuevaHabitacion);

        listaHuespedes.checkInHuesped(huesped);
    }
}
