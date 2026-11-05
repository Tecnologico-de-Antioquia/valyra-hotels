package com.tdea.modelos;

public class ConsumoServicio {
    private Huesped huesped;
    private Servicio servicio;
    private Integer cantidad;
    private String detalle;
    private double monto;

    public ConsumoServicio(Huesped huesped, Servicio servicio, Integer cantidad, String detalle, double monto) {
        this.huesped = huesped;
        this.servicio = servicio;
        this.cantidad = cantidad;
        this.detalle = detalle;
        this.monto = monto;
    }

    public Huesped getHuesped() {
        return this.huesped;
    }

    public Servicio getServicio() {
        return this.servicio;
    }

    public Integer getCantidad() {
        return this.cantidad;
    }

    public String getDetalle() {
        return this.detalle;
    }

    public double getMonto() {
        return this.monto;
    }
}
