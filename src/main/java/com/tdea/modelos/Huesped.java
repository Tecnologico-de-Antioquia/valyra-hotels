package com.tdea.modelos;

import com.tdea.compartidos.enums.Generos;

public class Huesped {
    private String documento;
    private String nombre;
    private Integer edad;
    private Generos genero;

    public Huesped(String documento, String nombre, Integer edad, Generos genero){
        this.documento = documento;
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
    }
}
