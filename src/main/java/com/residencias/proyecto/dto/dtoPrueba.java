package com.residencias.proyecto.dto;

public class dtoPrueba {

    public String titulo;


    public dtoPrueba(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "{" +
                "\"titulo\": \"" + titulo.toString() + '\"' +
                '}';
    }
}
