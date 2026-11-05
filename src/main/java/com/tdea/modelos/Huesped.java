package com.tdea.modelos;

import com.tdea.compartidos.enums.EstadoHuesped;
import com.tdea.compartidos.enums.Generos;
import com.tdea.compartidos.enums.MetodoPago;

public class Huesped {
    private String documento;
    private String nombre;
    private Integer edad;
    private Generos genero;
    private Habitacion habitacion;
    private Integer noches;
    private ListaConsumos consumos;
    private double subtotal;
    private double descuento;
    private double totalPagado;
    private MetodoPago metodoPago;
    private EstadoHuesped estado;

    public Huesped(String documento, String nombre, Integer edad, Generos genero) {
        this.documento = documento;
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.consumos = new ListaConsumos();
    }

    public String getDocumento() {
        return this.documento;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Integer getEdad() {
        return this.edad;
    }

    public Generos getGenero() {
        return this.genero;
    }

    public Habitacion getHabitacion() {
        return this.habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public Integer getNoches() {
        return this.noches;
    }

    public void setNoches(Integer noches) {
        this.noches = noches;
    }

    public ListaConsumos getConsumos() {
        return this.consumos;
    }

    public double getSubtotal() {
        return this.subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getDescuento() {
        return this.descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getTotalPagado() {
        return this.totalPagado;
    }

    public void setTotalPagado(double totalPagado) {
        this.totalPagado = totalPagado;
    }

    public MetodoPago getMetodoPago() {
        return this.metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public EstadoHuesped getEstado() {
        return this.estado;
    }

    public void setEstado(EstadoHuesped estado) {
        this.estado = estado;
    }
}
