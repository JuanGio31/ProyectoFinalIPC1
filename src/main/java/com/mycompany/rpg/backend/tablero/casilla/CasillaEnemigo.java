package com.mycompany.rpg.backend.tablero.casilla;

import com.mycompany.rpg.backend.personaje.enemigo.*;

import javax.swing.*;
import java.awt.*;

/**
 * *
 * @author giovanic Clase que contiene la informacion de un personaje del tipo
 * enemigo
 */
public final class CasillaEnemigo extends Casilla {

    private Enemigo enemigo;
    private Casilla sobreCasilla;

    /**
     * Metodo constructor
     *
     * @param enemigo personaje del tipo enemigo
     * @param casillaEnemigo casilla en la que se encuentra un personaje enemigo
     */
    public CasillaEnemigo(Enemigo enemigo, Casilla casillaEnemigo) {
        this.enemigo = enemigo;
        this.sobreCasilla = casillaEnemigo;

        modificarImage();
    }

    public boolean estaSobreArbol() {
        return this.sobreCasilla instanceof CasillaArbol;
    }

    public Enemigo getEnemigo() {
        return enemigo;
    }

    public void setEnemigo(Enemigo enemigo) {
        this.enemigo = enemigo;
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
        if (enemigo instanceof Ogro) {
            image = new ImageIcon(PATH + "ogro.png");
        } else if (enemigo instanceof Gargola) {
            image = new ImageIcon(PATH + "gargola.png");
        } else if (enemigo instanceof Bruja) {
            image = new ImageIcon(PATH + "bruja.png");
        } else if (enemigo instanceof Cancerbero) {
            image = new ImageIcon(PATH + "cerbero.png");
        } else {
            image = new ImageIcon(PATH + "carnivora.png");
        }
        ImageIcon img = new ImageIcon(image.getImage().getScaledInstance(ANCHO, ALTO, Image.SCALE_SMOOTH));
        this.setIcon(img);
    }

    @Override
    public void modificarImage(boolean esAtacado) {
        super.modificarImage(esAtacado);
    }

    public void aplicarEfectoLava() {
        double res = enemigo.getVida() - (enemigo.getVida() * 0.05);
        if (sobreCasilla instanceof CasillaLava) {
            enemigo.setVida(res);
        }
    }
}
