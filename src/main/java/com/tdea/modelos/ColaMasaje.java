package com.tdea.modelos;

import com.tdea.consola.CLI;
import com.tdea.estructura.NodoSimple;

public class ColaMasaje {
    private static final ColaMasaje colaMasaje = new ColaMasaje();
    private static final int MAXIMO_ESPERA = 5;

    private NodoSimple<Huesped> cola = null;
    private Huesped enServicio = null;
    private int totalAtendidos = 0;

    public CLI consola = new CLI();

    private String RED = "\u001B[31m";
    private String GREEN = "\u001B[32m";
    private String YELLOW = "\u001B[33m";
    private String BLUE = "\u001B[34m";
    private String PURPLE = "\u001B[35m";
    private String CYAN = "\u001B[36m";
    private String RESET = "\u001B[0m";

    private ColaMasaje() {
    }

    public static ColaMasaje getInstancia() {
        return colaMasaje;
    }

    public boolean agregarHuesped(Huesped nuevoHuesped) {
        if (enServicio == nuevoHuesped || contieneEnEspera(nuevoHuesped)) {
            consola.mostrarTexto("\nEl huésped ya se encuentra en el servicio de masaje\n", RED);
            return false;
        }

        if (enServicio == null) {
            enServicio = nuevoHuesped;
            consola.mostrarTexto("\nEl huésped pasa directamente al servicio de masaje\n", GREEN);
            return true;
        }

        if (contarEnEspera() >= MAXIMO_ESPERA) {
            consola.mostrarTexto("\nLa cola del servicio de masaje está llena (máximo 5 personas en espera)\n", RED);
            return false;
        }

        NodoSimple<Huesped> nuevoNodo = new NodoSimple<>(nuevoHuesped);

        if (cola == null) {
            cola = nuevoNodo;
        } else {
            NodoSimple<Huesped> auxiliar = cola;

            while (auxiliar.getSiguiente() != null) {
                auxiliar = auxiliar.getSiguiente();
            }

            auxiliar.setSiguiente(nuevoNodo);
        }

        consola.mostrarTexto("\nEl huésped fue agregado a la cola de masaje\n", GREEN);
        return true;
    }

    public Huesped finalizarServicio() {
        consola.mostrarTitulo("Finalizar servicio de masaje", PURPLE);

        if (enServicio == null) {
            consola.mostrarTexto("\nNo hay ningún huésped en el servicio de masaje\n", RED);
            return null;
        }

        Huesped atendido = enServicio;
        totalAtendidos += 1;
        enServicio = desencolar();

        consola.mostrarTexto("\nSe finalizó el masaje de " + atendido.getNombre() + "\n", GREEN);

        if (enServicio != null) {
            consola.mostrarTexto("Ahora pasa al servicio: " + enServicio.getNombre() + "\n", GREEN);
        }

        return atendido;
    }

    public boolean retirarHuesped(Huesped huespedRetiro) {
        if (huespedRetiro == null) {
            return false;
        }

        if (enServicio == huespedRetiro) {
            enServicio = desencolar();
            return true;
        }

        NodoSimple<Huesped> actual = cola;
        NodoSimple<Huesped> anterior = null;

        while (actual != null && actual.getValor() != huespedRetiro) {
            anterior = actual;
            actual = actual.getSiguiente();
        }

        if (actual == null) {
            return false;
        }

        if (anterior == null) {
            cola = actual.getSiguiente();
        } else {
            anterior.setSiguiente(actual.getSiguiente());
        }

        actual.setSiguiente(null);
        return true;
    }

    public int contarEnEspera() {
        int cantidad = 0;
        NodoSimple<Huesped> auxiliar = cola;

        while (auxiliar != null) {
            cantidad += 1;
            auxiliar = auxiliar.getSiguiente();
        }

        return cantidad;
    }

    public int getCantidadEnEspera() {
        return contarEnEspera();
    }

    public int getTotalAtendidos() {
        return totalAtendidos;
    }

    public boolean hayHuespedEnServicio() {
        return enServicio != null;
    }

    public void mostrarColaMasaje() {
        consola.mostrarTexto("\n---COLA DE MASAJES---\n", CYAN);

        if (enServicio == null && cola == null) {
            consola.mostrarTexto("No hay huéspedes en el servicio de masaje");
            return;
        }

        if (enServicio != null) {
            consola.mostrarTexto("En servicio: " + enServicio.getNombre() + " - " + enServicio.getDocumento(),
                    GREEN);
        } else {
            consola.mostrarTexto("En servicio: nadie");
        }

        consola.mostrarTexto("\nEn espera (" + contarEnEspera() + "/" + MAXIMO_ESPERA + "):");

        NodoSimple<Huesped> auxiliar = cola;

        if (auxiliar == null) {
            consola.mostrarTexto("No hay huéspedes en espera");
            return;
        }

        int i = 1;
        while (auxiliar != null) {
            Huesped huesped = auxiliar.getValor();
            consola.mostrarTexto(i + ". " + huesped.getNombre() + " - " + huesped.getDocumento());
            auxiliar = auxiliar.getSiguiente();
            i += 1;
        }
    }

    private Huesped desencolar() {
        if (cola == null) {
            return null;
        }

        Huesped huesped = cola.getValor();
        cola = cola.getSiguiente();
        return huesped;
    }

    private boolean contieneEnEspera(Huesped huespedBusqueda) {
        NodoSimple<Huesped> auxiliar = cola;

        while (auxiliar != null) {
            if (auxiliar.getValor() == huespedBusqueda) {
                return true;
            }

            auxiliar = auxiliar.getSiguiente();
        }

        return false;
    }
}
