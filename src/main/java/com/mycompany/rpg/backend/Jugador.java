package com.mycompany.rpg.backend;

import java.io.Serializable;

/**
 *
 * @author giovanic, Clase que contiene informacion del usuario
 */
public abstract class Jugador implements Serializable{

    private String nombre;

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    abstract public boolean estaVivo();
}
