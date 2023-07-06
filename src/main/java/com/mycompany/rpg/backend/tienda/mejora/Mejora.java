package com.mycompany.rpg.backend.tienda.mejora;

import java.io.Serializable;

/**
 * *
 *
 * @author giovanic Clase que almacena la informacion de las mejoras
 */
public class Mejora implements Serializable {

    private String tipo;
    private int precio;
    private String beneficio;

    /**
     * *
     *
     * @param tipo      tipo de mejora
     * @param precio    precio de la mejora
     * @param beneficio funcionalidad de la mejora
     */
    public Mejora(String tipo, int precio, String beneficio) {
        this.tipo = tipo;
        this.precio = precio;
        this.beneficio = beneficio;
    }

    public Mejora() {
    }

    @Override
    public String toString() {
        return tipo + "\tBeneficio: " + beneficio + "\n\t\t\t\tPrecio: " + precio;
    }

    public Mejora crearMejora(int selMejora) {

        switch (selMejora) {
            case 1 -> {
                // +Vida
                return new MejoraVida("Mejorar Vida", 80, "Le otorga al personaje 50 de vida maxima");
            }
            case 2 -> {
                // +Danio
                return new MejoraDanio("Mejorar Danio", 125, "Le otorga al personaje 10 de danio de ataque");
            }
            case 3 -> {
                // +Movilidad
                return new MejoraMovilidad("Mejorar Movilidad", 500,
                        "Le otorga al personaje 1 casilla adicional de movimiento");
            }
        }

        return null;
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

    public String getBeneficio() {
        return beneficio;
    }

}
