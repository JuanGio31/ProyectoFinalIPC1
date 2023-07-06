package com.mycompany.rpg.backend.tablero.casilla;

import javax.swing.*;
import java.awt.*;

/**
 * @author giovanic Clase que almacena la informacion de una casilla tipo agua
 */
public final class CasillaAgua extends Casilla {

    public CasillaAgua() {
        modificarImage();
    }

    @Override
    public void modificarImage() {
        ImageIcon image = new ImageIcon(PATH + "agua.png");
        ImageIcon img = new ImageIcon(image.getImage().getScaledInstance(ANCHO, ALTO, Image.SCALE_SMOOTH));
        this.setIcon(img);
    }

    @Override
    public void modificarImage(boolean esAtacado) {
    super.modificarImage(esAtacado);
    }
    
    
}
