package com.tdea.modelos;

import com.tdea.estructura.NodoSimple;

public class ListaConsumos {
    private NodoSimple<ConsumoServicio> lista = null;

    public void agregarConsumo(ConsumoServicio nuevoConsumo) {
        NodoSimple<ConsumoServicio> nuevoNodo = new NodoSimple<>(nuevoConsumo);

        if (lista == null) {
            lista = nuevoNodo;
            return;
        }

        NodoSimple<ConsumoServicio> auxiliar = lista;

        while (auxiliar.getSiguiente() != null) {
            auxiliar = auxiliar.getSiguiente();
        }

        auxiliar.setSiguiente(nuevoNodo);
    }

    public double getTotal() {
        double total = 0;
        NodoSimple<ConsumoServicio> auxiliar = lista;

        while (auxiliar != null) {
            total += auxiliar.getValor().getMonto();
            auxiliar = auxiliar.getSiguiente();
        }

        return total;
    }

    public NodoSimple<ConsumoServicio> getLista() {
        return lista;
    }
}
