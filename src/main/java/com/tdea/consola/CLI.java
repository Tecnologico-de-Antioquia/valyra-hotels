package com.tdea.consola;

import java.util.Scanner;

public class CLI {

    private Scanner escaner = new Scanner(System.in);

    /**
     * Colores para la consola
     **/

    private String RED = "\u001B[31m";
    private String GREEN = "\u001B[32m";
    private String YELLOW = "\u001B[33m";
    private String BLUE = "\u001B[34m";
    private String PURPLE = "\u001B[35m";
    private String CYAN = "\u001B[36m";
    private String RESET = "\u001B[0m";

    public void mostrarTitulo(String title, String color) {
        System.out.println(color + """
                ***************************
                %s
                ***************************
                """.formatted(title.toUpperCase()) + RESET);
    }

    public void mostrarTexto(String texto, String color) {
        System.out.println(color + texto + RESET);
    }

    public void mostrarTexto(String texto) {
        System.out.println(texto);
    }

    public Integer mostrarMenu() {
        mostrarTitulo("Bienvenido a Valyra Hotels", PURPLE);

        mostrarTexto("Seleccione una opción del siguiente menú\n", BLUE);

        mostrarTexto("1. Ingresar huesped a la cola");
        mostrarTexto("2. Atender huesped (Check-in)\n");

        Integer opcion = null;

        while (opcion == null || opcion > 2){
           opcion = leerNatural("");
        }

        limpiarConsola();
        return opcion;
    }

    public String leerTexto(String mensaje){
        System.out.print(CYAN + mensaje + ": " + RESET);
        return escaner.nextLine();
    }

    public Integer leerNatural(String mensaje){
        Integer number = null;

        while (number == null || number < 0) {
            System.out.print(CYAN + mensaje + ": " + RESET);
            number = escaner.nextInt();
        }

        return number;
    }

    public void limpiarConsola(){
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.print(e);
        }
    }
}
