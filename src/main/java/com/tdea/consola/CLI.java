package com.tdea.consola;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class CLI {

    private static final Scanner escaner = new Scanner(System.in);

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
        System.out.println(color + "\n***************************\n%s\n***************************\n".formatted(title.toUpperCase()) + RESET);
    }

    public void mostrarTexto(String texto, String color) {
        System.out.println(color + texto + RESET);
    }

    public void mostrarTexto(String texto) {
        System.out.println(texto);
    }

    public String formatearMoneda(double valor) {
        return String.format(Locale.US, "$%,.0f", valor);
    }

    public Integer mostrarMenu() {

        mostrarTitulo("Bienvenido a Valyra Hotels", PURPLE);

        mostrarTexto("Seleccione una opción del siguiente menú\n", BLUE);

        mostrarTexto("1. Ingresar huesped a la cola");
        mostrarTexto("2. Atender huesped (Check-in)");
        mostrarTexto("3. Retirar huesped (Check-out)");
        mostrarTexto("4. Adquirir un servicio");
        mostrarTexto("5. Mostrar cola de atención");
        mostrarTexto("6. Mostrar huéspedes registrados");
        mostrarTexto("7. Mostrar métricas");
        mostrarTexto("8. Mostrar cola de masajes");
        mostrarTexto("9. Finalizar servicio de masaje");
        mostrarTexto("10. Mostrar servicios");
        mostrarTexto("11. Mostrar habitaciones");
        mostrarTexto("0. Salir");
        mostrarTexto("\n");

        Integer opcion = null;

        while (opcion == null || opcion < 0 || opcion > 11) {
            opcion = leerNatural("");
        }

        limpiarConsola();
        return opcion;
    }

    public String leerTexto(String mensaje) {
        System.out.print(CYAN + mensaje + ": " + RESET);
        String texto = escaner.nextLine();
        return texto;
    }

    public Integer leerNatural(String mensaje) {
        Integer number = null;

        while (number == null || number < 0) {
            System.out.print(CYAN + mensaje + ": " + RESET);

            try {
                number = escaner.nextInt();
                escaner.nextLine();

                if (number < 0) {
                    System.out.print(RED + "\nEl dato debe ser positivo\n" + RESET);
                }
            } catch (InputMismatchException e) {
                escaner.nextLine();
                System.out.print(RED + "\nIngrese un número válido\n" + RESET);
                number = null;
            }
        }

        return number;
    }

    public Integer leerEnteroPositivo(String mensaje) {
        Integer number = null;

        while (number == null || number <= 0) {
            number = leerNatural(mensaje);

            if (number <= 0) {
                System.out.print(RED + "\nEl dato debe ser mayor que cero\n" + RESET);
            }
        }

        return number;
    }

    public void limpiarConsola() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.print(e);
        }
    }
}
