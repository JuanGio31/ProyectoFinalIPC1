package com.mycompany.rpg.backend.tablero.casilla;

import com.mycompany.rpg.backend.personaje.jugable.*;

import javax.swing.*;
import java.awt.*;

/**
 * Clase que contiene la informacion de una casilla que contiene a un personaje
 * tipo jugador
 *
 * @author giovanic
 */
public final class CasillaJugable extends Casilla {

    private Jugable jugable;
    private Casilla sobreCasilla;

    /**
     * *
     *
     * @param jugable personaje del tipo jugable
     * @param sobreCasilla casilla donde se encuentra el jugador
     */
    public CasillaJugable(Jugable jugable, Casilla sobreCasilla) {
        this.jugable = jugable;
        this.sobreCasilla = sobreCasilla;

        modificarImage();
    }

    public boolean estaSobreArbol() {
        return this.sobreCasilla instanceof CasillaArbol;
    }

    public Jugable getJugable() {
        return jugable;
    }

    public void setJugable(Jugable jugable) {
        this.jugable = jugable;
    }

    public Casilla getSobreCasilla() {
        return sobreCasilla;
    }

    public void setSobreCasilla(Casilla sobreCasilla) {
        this.sobreCasilla = sobreCasilla;
    }

    @Override
    public void modificarImage() {
        ImageIcon image;
        if (jugable instanceof Caballero) {
            image = new ImageIcon(PATH + "caballero.png");
        } else if (jugable instanceof Arquero) {
            image = new ImageIcon(PATH + "arquero.png");
        } else if (jugable instanceof Mago) {
            image = new ImageIcon(PATH + "mago.png");
        } else if (jugable instanceof Gigante) {
            image = new ImageIcon(PATH + "gigante.png");
        } else {
            image = new ImageIcon(PATH + "dragon.png");
        }

        ImageIcon img = new ImageIcon(image.getImage().getScaledInstance(ANCHO, ALTO, Image.SCALE_SMOOTH));
        this.setIcon(img);
    }

    @Override
    public void modificarImage(boolean esAtacado) {
        super.modificarImage(esAtacado);
    }

    public void aplicarEfectoLava() {
        double res = jugable.getVida() - (jugable.getVida() * 0.05);
        if (sobreCasilla instanceof CasillaLava) {
            jugable.setVida(res);
        }
    }

}
