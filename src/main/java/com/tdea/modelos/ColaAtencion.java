package com.tdea.modelos;

import com.tdea.estructura.NodoSimple;
import com.tdea.modelos.Huesped;
import com.tdea.compartidos.enums.Generos;
import com.tdea.consola.CLI;

public class ColaAtencion {

    private static final ColaAtencion colaAtencion = new ColaAtencion();
    public NodoSimple<Huesped> cola = null;

    public CLI consola = new CLI();

    private String RED = "\u001B[31m";
    private String GREEN = "\u001B[32m";
    private String YELLOW = "\u001B[33m";
    private String BLUE = "\u001B[34m";
    private String PURPLE = "\u001B[35m";
    private String CYAN = "\u001B[36m";
    private String RESET = "\u001B[0m";

    private ColaAtencion(){
    }

    public static ColaAtencion getInstancia() {
        return colaAtencion;
    }

    public void agregarHuesped(Huesped nuevoHuesped){
        NodoSimple<Huesped> nuevoNodo = new NodoSimple<>(nuevoHuesped);
        
        NodoSimple auxiliar = cola;

        if(auxiliar == null){
            auxiliar = nuevoNodo;
        }else{
            while (auxiliar.getSiguiente() != null){
                auxiliar = auxiliar.getSiguiente();
            }
    
            auxiliar.setSiguiente(nuevoNodo);
        }
        
        cola = auxiliar;
    }

    public void mostrarColaAtencion(){
        consola.mostrarTexto("\n---COLA DE ATENCIÓN---\n", CYAN);
        
        NodoSimple auxiliar = cola;
        
        if(auxiliar == null){
            consola.mostrarTexto("No hay huespedes en la cola de atención");
            return;
        }

        Integer i = 1;
        while (auxiliar != null){
            Huesped huesped = (Huesped) auxiliar.getValor();
            consola.mostrarTexto(i + ". " + huesped.getNombre());
            auxiliar = auxiliar.getSiguiente();
            i = i + 1;
        }
    }
}
