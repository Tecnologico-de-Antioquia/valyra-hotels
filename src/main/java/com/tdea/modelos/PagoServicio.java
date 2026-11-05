package com.tdea.modelos;

import com.tdea.compartidos.enums.MetodoPago;

public class PagoServicio {
    private static final PagoServicio pagoServicio = new PagoServicio();
    private final int[] contadorPagos = new int[MetodoPago.values().length];

    private PagoServicio() {
    }

    public static PagoServicio getInstancia() {
        return pagoServicio;
    }

    public void registrarPago(MetodoPago metodoPago) {
        contadorPagos[metodoPago.ordinal()] += 1;
    }

    public int getCantidad(MetodoPago metodoPago) {
        return contadorPagos[metodoPago.ordinal()];
    }

    public int[] getContadorPagos() {
        return contadorPagos;
    }
}
