package com.mycompany.rpg.backend;

import com.mycompany.rpg.backend.personaje.enemigo.Enemigo;
import java.io.Serializable;

/**
 *
 * @author giovanic
 */
public class IA extends Jugador implements Serializable {

    private Enemigo[] enemigos;

    public IA(String nombre) {
        super(nombre);
    }

    public Enemigo[] getEnemigos() {
        return enemigos;
    }

    public void setEnemigos(Enemigo[] enemigos) {
        this.enemigos = enemigos;
    }

    /**
     * Metodo que indica si la ia aun puede seguir jugando
     *
     * @return verdadero si aun hay personajes enemigos, falso si ya no tiene
     */
    @Override
    public boolean estaVivo() {
        int contador = 0;
        for (Enemigo enemigo : enemigos) {
            if (!enemigo.estaVivo()) {
                contador++;
            }
        }
        return contador != enemigos.length;
    }

    public int darPuntos() {
        int puntos = 0;
        for (Enemigo enemigo : enemigos) {
            if (!enemigo.estaVivo()) {
                puntos = puntos + enemigo.getPuntaje();
            }
        }
        return puntos;
    }
}
