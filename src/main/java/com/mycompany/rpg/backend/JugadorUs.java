package com.mycompany.rpg.backend;

import com.mycompany.rpg.backend.personaje.jugable.Jugable;
import com.mycompany.rpg.backend.tienda.Inventario;

/**
 * Clase que contiene la infromacion del jugador
 *
 * @author giovanic
 */
public class JugadorUs extends Jugador {

    private int oro;
    private int puntuacion;
    private Inventario inventario;
    private Jugable[] jugablesEnJuego;
    private String nombrePartidaGuardada;

    /**
     * Metodo constructor.
     *
     * @param nombre identificador del personaje
     */
    public JugadorUs(String nombre) {
        super(nombre);
        this.jugablesEnJuego = new Jugable[2];
        this.oro = 500;
        inventario = new Inventario();
    }

    public void aplicarCambioPersonaje() {
        if (comprobarCambioPersonaje()) {
            cambiar();
        }
    }

    public void cambiar() {
        Jugable aux = jugablesEnJuego[0];

        jugablesEnJuego[0] = jugablesEnJuego[1];
        jugablesEnJuego[1] = aux;
    }

    /**
     * Metodo que indica si el usuario aun puede seguir jugando
     *
     * @return verdadero si aun hay personajes jugables, falso si ya no tiene
     */
    @Override
    public boolean estaVivo() {
        int contador = 0;
        for (Jugable jugablesEnJuego1 : jugablesEnJuego) {
            if (!jugablesEnJuego1.estaVivo()) {
                contador++;
            }
        }
        return contador != jugablesEnJuego.length;
    }

    public boolean comprobarCambioPersonaje() {
        int contador = 0;
        boolean val;
        for (Jugable jugablesEnJuego1 : jugablesEnJuego) {
            if (!jugablesEnJuego1.estaVivo()) {
                contador++;
            }
        }
        val = contador != 1;
        return val;
    }

    public int getOro() {
        return oro;
    }

    public void setOro(int oro) {
        this.oro = oro;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public Jugable[] getJugablesEnJuego() {
        return jugablesEnJuego;
    }

    public void setJugablesEnJuego(Jugable[] jugablesEnJuego) {
        this.jugablesEnJuego = jugablesEnJuego;
    }

    public String getNombrePartidaGuardada() {
        return nombrePartidaGuardada;
    }

    public void setNombrePartidaGuardada(String nombrePartidaGuardada) {
        this.nombrePartidaGuardada = nombrePartidaGuardada;
    }
}
