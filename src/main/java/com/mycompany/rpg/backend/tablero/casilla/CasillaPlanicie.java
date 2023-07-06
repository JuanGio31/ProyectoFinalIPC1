package com.mycompany.rpg.backend.tablero.casilla;

import javax.swing.*;
import java.awt.*;

/**
 * @author giovanic Clase que contiene la infromacion de una casilla planicie
 */
public final class CasillaPlanicie extends Casilla {

    /**
     * Metodo constructor
     *
     */
    public CasillaPlanicie() {
        modificarImage();
    }

    @Override
    public void modificarImage() {

        ImageIcon image = new ImageIcon(PATH + "planicie.png");

        ImageIcon img = new ImageIcon(image.getImage().getScaledInstance(ANCHO, ALTO, Image.SCALE_SMOOTH));
        this.setIcon(img);
    }

    @Override
    public void modificarImage(boolean esAtacado) {
        super.modificarImage(esAtacado);
    }
}
