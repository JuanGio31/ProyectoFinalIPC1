package com.mycompany.rpg.backend.tablero.casilla;

import java.awt.Image;
import javax.swing.*;

/**
 * @author giovanic, Clase "casilla" de la que heredan todas las casillas
 * posibles en el juego planicie, lava, arbol, de jugador, etc.
 */
public abstract class Casilla extends JLabel {

    protected final String PATH = "src/main/resources/imagenes/";
    protected static final int ALTO = 50;
    protected static final int ANCHO = 50;

    public Casilla() {

        //  modificarImage();
    }

    /**
     * Metodo para colocar en el label enviado de parametro la imagen que posea
     * la casilla almacenada.
     */
    abstract public void modificarImage();

    public void modificarImage(boolean esAtacado) {
        if (esAtacado) {
            ImageIcon image = new ImageIcon(PATH + "atack.png");
            ImageIcon img = new ImageIcon(image.getImage().getScaledInstance(ANCHO, ALTO, Image.SCALE_SMOOTH));
            this.setIcon(img);
        } else {
            modificarImage();
        }
    }
}
