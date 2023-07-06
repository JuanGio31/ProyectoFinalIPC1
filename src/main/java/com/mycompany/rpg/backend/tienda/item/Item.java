package com.mycompany.rpg.backend.tienda.item;

import java.io.Serializable;

public class Item implements Serializable {

    private String tipo;
    private int precio;
    private String uso;

    /**
     * *Metodo contrusctor, para inicializar el tipo, precio y el uso
     *
     * @param tipo   tipo de objeto : String
     * @param precio valor de cada item
     * @param uso    descripcion de su funcionalidad
     */
    public Item(String tipo, int precio, String uso) {
        this.tipo = tipo;
        this.precio = precio;
        this.uso = uso;
    }

    /**
     * Contructor vacio
     */
    public Item() {
    }

    @Override
    public String toString() {
        return tipo + "\tUso: " + uso + "\n\t\t\t\tPrecio: " + precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPrecio() {
        return precio;
    }

    public String getUso() {
        return uso;
    }

    public Item crearItem(int selObjeto) {

        switch (selObjeto) {
            case 1 -> {
                return new SemillaDeLaVida("Semilla de la vida", 50, "Resucita a uno de los personajes.");
            }
            case 2 -> {
                return new ElixirVerde("Elixir verde", 25, "Cura 50 de vida.");
            }
            case 3 -> {
                return new CapaDeMovilidad("Capa de Movilidad", 75,
                        "Mueve al personaje una casilla a su alrededor.");
            }
        }
        return null;
    }

}
