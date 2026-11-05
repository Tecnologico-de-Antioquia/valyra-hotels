package com.tdea.modelos;

import com.tdea.estructura.NodoSimple;
import com.tdea.estructura.Pila;

public class PilaServicios {
    private static final PilaServicios pilaServicios = new PilaServicios();
    private final Pila<ConsumoServicio> pila = new Pila<>();

    private PilaServicios() {
    }

    public static PilaServicios getInstancia() {
        return pilaServicios;
    }

    public void registrarConsumo(ConsumoServicio nuevoConsumo) {
        pila.apilar(nuevoConsumo);
    }

    public int contarPorServicio(String codigoServicio) {
        int cantidad = 0;
        NodoSimple<ConsumoServicio> auxiliar = pila.getCima();

        while (auxiliar != null) {
            ConsumoServicio consumo = auxiliar.getValor();

            if (consumo.getServicio().getCodigo().equals(codigoServicio)
                    && !yaContado(pila.getCima(), auxiliar, codigoServicio, consumo.getHuesped().getDocumento())) {
                cantidad += 1;
            }

            auxiliar = auxiliar.getSiguiente();
        }

        return cantidad;
    }

    private boolean yaContado(NodoSimple<ConsumoServicio> inicio, NodoSimple<ConsumoServicio> actual,
            String codigoServicio, String documentoHuesped) {
        NodoSimple<ConsumoServicio> auxiliar = inicio;

        while (auxiliar != null && auxiliar != actual) {
            ConsumoServicio consumo = auxiliar.getValor();

            if (consumo.getServicio().getCodigo().equals(codigoServicio)
                    && consumo.getHuesped().getDocumento().equals(documentoHuesped)) {
                return true;
            }

            auxiliar = auxiliar.getSiguiente();
        }

        return false;
    }

    public double totalPorServicio(String codigoServicio) {
        double total = 0;
        NodoSimple<ConsumoServicio> auxiliar = pila.getCima();

        while (auxiliar != null) {
            if (auxiliar.getValor().getServicio().getCodigo().equals(codigoServicio)) {
                total += auxiliar.getValor().getMonto();
            }
            auxiliar = auxiliar.getSiguiente();
        }

        return total;
    }

    public NodoSimple<ConsumoServicio> getCima() {
        return pila.getCima();
    }
}
