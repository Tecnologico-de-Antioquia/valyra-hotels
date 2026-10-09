package com.tdea.modelos;

import com.tdea.compartidos.enums.Generos;
import com.tdea.consola.CLI;
import com.tdea.modelos.ColaAtencion;

public class HuespedServicio {
    public static CLI consola = new CLI();
    private static ColaAtencion colaAtencion = ColaAtencion.getInstancia();

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
}
