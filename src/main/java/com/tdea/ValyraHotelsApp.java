package com.tdea;

import com.tdea.estructura.NodoSimple;
import com.tdea.modelos.Huesped;
import com.tdea.consola.CLI;
import com.tdea.compartidos.enums.Generos;

/*
Valyra Hotels es una aplicación de gestión hotelera desarrollada
en Java mediante una interfaz de línea de comandos(CLI), cuyo objetivo
es facilitar la administración de las operaciones básicas de un hotel de manera organizada y eficiente.
*/

public class ValyraHotelsApp {
    public static CLI consola = new CLI();

    public static void main(String[] args) {
        // NodoSimple<Integer> primerNodo = new NodoSimple<>(1);
        // NodoSimple<Integer> nuevoNodo = new NodoSimple<>(2);
        // primerNodo.setSiguiente(nuevoNodo);
        // System.out.println(primerNodo.getValor());

        welcomeValyraHotels();

        // Huesped pablo = new Huesped("123456789", "Juan Pablo", 32, Generos.Hombre);
    }

    public static void welcomeValyraHotels(){
        consola.printMenu();
    }
}
