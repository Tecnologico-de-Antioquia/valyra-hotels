package com.tdea.modelos;

import com.tdea.compartidos.enums.Generos;
import com.tdea.consola.CLI;
import com.tdea.estructura.NodoSimple;

public class MetricasServicio {
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

    public void mostarMetricas() {
        consola.mostrarTexto("\n---MÉTRICAS DEL HOTEL---\n", CYAN);
        mostrarCantidadHuespedes();
        mostrarCantidadHuespedesPorGenero();
    }

    public void mostrarCantidadHuespedes() {
        NodoSimple auxiliar = listaHuespedes.lista;

        Integer resultado = 0;
        while (auxiliar != null) {
            resultado += 1;
            auxiliar = auxiliar.getSiguiente();
        }

        consola.mostrarTexto("■ Cantidad de huéspedes: " + resultado);
    }

    public void mostrarCantidadHuespedesPorGenero() {
        NodoSimple auxiliar = listaHuespedes.lista;

        Integer resultadoH = 0;
        Integer resultadoM = 0;
        Integer resultadoI = 0;

        while (auxiliar != null) {
            Huesped huesped = (Huesped) auxiliar.getValor();
            if (huesped.getGenero() == Generos.Hombre) {
                resultadoH += 1;
            } else if (huesped.getGenero() == Generos.Mujer) {
                resultadoM += 1;
            } else {
                resultadoI += 1;
            }
            auxiliar = auxiliar.getSiguiente();
        }

        consola.mostrarTexto("■ Cantidad de huéspedes por género: " + "Hombres: " + resultadoH + " - Mujeres: "
                + resultadoM + " - Indeterminado: " + resultadoI);
    }
}
