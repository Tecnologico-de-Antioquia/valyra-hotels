package com.tdea.compartidos.enums;

public enum TipoHabitacion {
    sencilla(155000),
    doble(80000),
    familiar(55000);

    private final double precio;

    TipoHabitacion(double precio) {
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }
}