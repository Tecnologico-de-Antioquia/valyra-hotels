package com.tdea.modelos;

public class Servicio {
    private String codigo;
    private String nombre;
    private Integer precio;

    public Servicio(String codigo, String nombre, Integer precio){
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getCodigo(){
        return this.codigo;
    }

    public String getNombre(){
        return this.nombre;
    }

    public Integer getPrecio(){
        return this.precio;
    }
}
