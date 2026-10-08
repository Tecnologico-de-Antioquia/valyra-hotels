package com.tdea.modelos;

import com.tdea.estructura.NodoSimple;
import com.tdea.modelos.Huesped;
import com.tdea.compartidos.enums.Generos;

public class ColaAtencion {

    private ColaAtencion colaAtencion;

    public void ColaAtencion(){
        if(!colaAtencion){
            ColaAtencion colaAtencion = new ColaAtencion();
            return colaAtencion;
        }

        return colaAtencion;
    }

    public void agregarHuesped(Huesped nuevoHuesped){
        NodoSimple<Huesped> nuevoNodo = new NodoSimple<>(nuevoHuesped);

        System.out.println(nuevoNodo.getValor());
    }    
}
