package com.mycompany.rpg.backend.personaje.enemigo;

import com.mycompany.rpg.backend.tablero.Tablero;
import com.mycompany.rpg.backend.tablero.casilla.*;

public class Gargola extends Enemigo {

    public Gargola(String nombre, double vida, int danio, int movimineto, boolean esVolador, int puntaje) {
        super(nombre, vida, danio, movimineto, esVolador, puntaje);
    }

    @Override
    public void ataque(Tablero tab, int direccion, int numPersonaje) {
        Casilla[][] cell = tab.getCasillas().clone();
        int x = tab.getEnemiPos()[numPersonaje].getX();
        int y = tab.getEnemiPos()[numPersonaje].getY();

        if (obtenerNumeroRandom() <= ACIERTO) {

            if (direccion == 1) {    //ataque->izquierda
                for (int i = x - 1; i > x - 4; i--) {
                    if (i > -1) {
                        aplicarAtaqueHaciaCasillas(cell, y, i);
                    }
                }
            }

            //ataque->derecha
            if (direccion == 2) {
                for (int i = x + 1; i < x + 4; i++) {
                    if (i < cell[0].length) {
                        aplicarAtaqueHaciaCasillas(cell, y, i);
                    }
                }
            }

            //ataque->abajo
            if (direccion == 3) {
                for (int i = y + 1; i < y + 4; i++) {
                    if (i < cell.length) {
                        aplicarAtaqueHaciaCasillas(cell, i, x);
                    }
                }
            }

            //ataque->arriba
            if (direccion == 4) {
                for (int i = y - 1; i > y - 4; i--) {
                    if (i > -1) {
                        aplicarAtaqueHaciaCasillas(cell, i, x);
                    }
                }
            }
            tab.setCasillas(cell);
        } else {
            System.out.println("No acertaste");
        }
    }
}
