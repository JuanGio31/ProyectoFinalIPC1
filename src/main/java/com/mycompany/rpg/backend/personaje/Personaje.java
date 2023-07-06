//switch (direccion) {
//            case 38 -> this.activeGame.moverArriba();
//            case 37 ->this.activeGame.moverIzquierda();
//            case 40 ->this.activeGame.moverAbajo();
//            case 39 ->this.activeGame.moverDerecha();
//        }
package com.mycompany.rpg.backend.personaje;

import com.mycompany.rpg.backend.tablero.Tablero;
import java.io.Serializable;

public abstract class Personaje implements Serializable {

    private String nombre;
    private double vida;
    private int danio;
    private int movimineto;
    private boolean esVolador;

    public Personaje(String nombre, double vida, int danio, int movimineto, boolean esVolador) {
        this.nombre = nombre;
        this.vida = vida;
        this.danio = danio;
        this.movimineto = movimineto;
        this.esVolador = esVolador;
    }

    public Personaje() {
    }

    abstract public void ataque(Tablero tab, int direccion);

    abstract public void ataque(Tablero tab, int direccion, int numPersonaje);

    public boolean estaVivo() {
        return vida > 0;
    }

    protected int obtenerNumeroRandom() {
        return (int) (Math.random() * 100 + 1);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getVida() {
        return vida;
    }

    public void setVida(double vida) {
        if (vida >= 0) {
            this.vida = vida;
        } else {
            this.vida = 0;
        }
    }

    public int getDanio() {
        return danio;
    }

    public void setDanio(int danio) {
        this.danio = danio;
    }

    public int getMovimineto() {
        return movimineto;
    }

    public void setMovimineto(int movimineto) {
        this.movimineto = movimineto;
    }

    public boolean esVolador() {
        return esVolador;
    }

    public void setEsVolador(boolean esVolador) {
        this.esVolador = esVolador;
    }
}
