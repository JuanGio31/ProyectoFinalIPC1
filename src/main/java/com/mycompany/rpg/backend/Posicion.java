package com.mycompany.rpg.backend;

import java.io.Serializable;

/**
 *
 * @author giovanic Clase que almacena la posicion de algo
 */
public class Posicion implements Serializable{

    private int x;
    private int y;

    public Posicion(int y, int x) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}