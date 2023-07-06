package com.mycompany.rpg.backend.util;

import java.io.Serializable;

/**
 *
 * @author giovanic
 * @param <T> variable a parametrizar
 */
public class Nodo<T> implements Serializable{

    private T contenido;
    private Nodo<T> siguiente;

    public Nodo() {
    }

    public Nodo(T contenido) {
        this.contenido = contenido;
    }

    public Nodo(T contenido, Nodo<T> siguiente) {
        this.contenido = contenido;
        this.siguiente = siguiente;
    }

    public T getContenido() {
        return contenido;
    }

    public void setContenido(T contenido) {
        this.contenido = contenido;
    }

    public Nodo<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }

}
