package com.tdea.modelos;

import com.tdea.modelos.Servicio;
import com.tdea.consola.CLI;
import com.tdea.estructura.NodoSimple;

public class ListaServicios {
    private static final ListaServicios listaServicios = new ListaServicios();
    public NodoSimple<Servicio> lista = null;

    public CLI consola = new CLI();

    private String RED = "\u001B[31m";
    private String GREEN = "\u001B[32m";
    private String YELLOW = "\u001B[33m";
    private String BLUE = "\u001B[34m";
    private String PURPLE = "\u001B[35m";
    private String CYAN = "\u001B[36m";
    private String RESET = "\u001B[0m";

    private ListaServicios() {
    }

    public static ListaServicios getInstancia() {
        return listaServicios;
    }

    public void crearServicio(Servicio nuevoServicio) {
        NodoSimple<Servicio> nuevoNodo = new NodoSimple<>(nuevoServicio);

        if (lista == null) {
            lista = nuevoNodo;
            return;
        }

        NodoSimple auxiliar = lista;

        nuevoNodo.setSiguiente(auxiliar);

        lista = nuevoNodo;
    }

    public Servicio buscarServicio(Integer codigoServicio){
        
        NodoSimple auxiliar = lista;

        if (auxiliar == null) {
            consola.mostrarTexto("No hay servicios creados");
            return null;
        }

        Servicio servicio = (Servicio) auxiliar.getValor();
        
        while (auxiliar != null && !servicio.getCodigo().equals(codigoServicio)) {
            auxiliar = auxiliar.getSiguiente();
            if(auxiliar != null){
                servicio = (Servicio) auxiliar.getValor();
            }
        }

        if(auxiliar == null){
            consola.mostrarTexto("\nNo se encontró el servicio\n", RED);
            return null;
        }else{
            return servicio;
        }
    }

    public void mostrarListaServicios() {
        consola.mostrarTexto("\n---LISTA DE SERVICIOS---\n", CYAN);

        NodoSimple auxiliar = lista;

        if (auxiliar == null) {
            consola.mostrarTexto("No hay servicios creados");
            return;
        }

        while (auxiliar != null) {
            Servicio servicio = (Servicio) auxiliar.getValor();
            consola.mostrarTexto("----------");
            consola.mostrarTexto(servicio.getCodigo() + " - " + servicio.getNombre() + " - $" + servicio.getPrecio());

            consola.mostrarTexto("----------\n");
            auxiliar = auxiliar.getSiguiente();
        }
    }
}
