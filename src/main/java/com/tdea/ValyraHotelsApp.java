package com.tdea;

import com.tdea.estructura.NodoSimple;
import com.tdea.modelos.ColaAtencion;
import com.tdea.modelos.Huesped;
import com.tdea.consola.CLI;
import com.tdea.compartidos.enums.Generos;
import com.tdea.modelos.HuespedServicio;

/*
Valyra Hotels es una aplicación de gestión hotelera desarrollada
en Java mediante una interfaz de línea de comandos(CLI), cuyo objetivo
es facilitar la administración de las operaciones básicas de un hotel de manera organizada y eficiente.
*/

public class ValyraHotelsApp {
    public static CLI consola = new CLI();
    private static ColaAtencion colaAtencion = ColaAtencion.getInstancia();

    public static void main(String[] args) {
        consola.limpiarConsola();
        welcomeValyraHotels();
    }

    public static void welcomeValyraHotels() {
        Integer opcion = consola.mostrarMenu();

        switch (opcion) {
            case 1:
                HuespedServicio huespedServicio = new HuespedServicio();
                huespedServicio.agregarHuespedCola();
                break;
            case 2:
                //
                break;
            case 3:
                colaAtencion.mostrarColaAtencion();
                break;

            default:
                break;
        }

        welcomeValyraHotels();
    }
}
