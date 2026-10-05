package src.main.java.com.tdea;

import src.main.java.com.tdea.estructura.NodoSimple;

import src.main.java.com.tdea.consola.CLI;

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
    }

    public static void welcomeValyraHotels(){
        consola.printMenu();
    }
}
