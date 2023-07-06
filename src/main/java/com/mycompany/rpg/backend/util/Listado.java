package com.mycompany.rpg.backend.util;

import java.io.Serializable;

/**
 *
 * @author giovanic
 * @param <T> variable a parametrizar
 */
public class Listado<T> implements Serializable {

    private Nodo<T> inicio;
    private Nodo<T> ultimo;
    private int size = 0;

    public void agregarElemento(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);
        if (estaVacia()) {
            inicio = nuevo;
            ultimo = inicio;
        } else {
            ultimo.setSiguiente(nuevo);
            ultimo = nuevo;
        }
        size++;
    }

    public int getSize() {
        return size;
    }

    public void eliminarUltimo() throws ListaException {
        if (estaVacia()) {
            throw new ListaException("La lista esta vacia");
        }
        int penultimoIndice = size - 2;
        if (size == 1) {
            inicio = null;
            size = 0;
        } else {
            Nodo<T> penultimoNodo = obtenerNodo(penultimoIndice);
            penultimoNodo.setSiguiente(null);
            ultimo = penultimoNodo;
            size--;
        }
    }

    public T obtenerElemento(int indice) throws ListaException {
        return obtenerNodo(indice).getContenido();
    }

    public void modificarContenido(int indice, T contenido) throws ListaException {
        obtenerNodo(indice).setContenido(contenido);
    }

    public boolean estaVacia() {
        return this.inicio == null;
    }

    private Nodo<T> obtenerNodo(int indice) throws ListaException {
        if (estaVacia()) {
            throw new ListaException("La lista esta vacia");
        }
        if (indice < 0 || size <= indice) {
            throw new ListaException("Indice fuera de rango");
        }

        Nodo<T> nodoActual = inicio;
        for (int i = 0; i < indice; i++) {
            Nodo<T> siguiente = nodoActual.getSiguiente();
            nodoActual = siguiente;
        }
        return nodoActual;
    }

    public void eliminar(int indice) throws ListaException {
        if (indice < 0 || indice >= size) {
            throw new ListaException("Indice fuera de rango");
        }
        if (size == 1 || indice == size - 1) {
            eliminarUltimo();
        } else if (indice == 0) {
            inicio = inicio.getSiguiente();
            size--;
        } else {
            Nodo<T> nodoAnterior = obtenerNodo(indice - 1);
            Nodo<T> nodoAEliminar = nodoAnterior.getSiguiente();
            nodoAnterior.setSiguiente(nodoAEliminar.getSiguiente());
            size--;
        }
    }

}
