package com.mycompany.rpg.backend.tablero.casilla;

import javax.swing.*;
import java.awt.*;

public final class CasillaArbol extends Casilla {

    private int vida;

    public CasillaArbol(int vida) {
        this.vida = vida;
        modificarImage();
    }

    @Override
    public void modificarImage() {
        ImageIcon image = new ImageIcon(PATH + "arbol.png");
        ImageIcon img = new ImageIcon(image.getImage().getScaledInstance(ANCHO, ALTO, Image.SCALE_SMOOTH));
        this.setIcon(img);
    }

    public boolean estaArbolVivo() {
        return this.vida > 0;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    @Override
    public void modificarImage(boolean esAtacado) {
        super.modificarImage(esAtacado);
    }

}
