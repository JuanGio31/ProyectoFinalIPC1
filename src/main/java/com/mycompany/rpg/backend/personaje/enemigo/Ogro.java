package com.mycompany.rpg.backend.personaje.enemigo;

import com.mycompany.rpg.backend.tablero.Tablero;
import com.mycompany.rpg.backend.tablero.casilla.*;

public class Ogro extends Enemigo {

    public Ogro(String nombre, double vida, int danio, int movimineto, boolean esVolador, int puntaje) {
        super(nombre, vida, danio, movimineto, esVolador, puntaje);
    }

    @Override
    public void ataque(Tablero tab, int direccion, int numPersonaje) {
        Casilla[][] cell = tab.getCasillas().clone();
        int x = tab.getEnemiPos()[numPersonaje].getX();
        int y = tab.getEnemiPos()[numPersonaje].getY();

        if (obtenerNumeroRandom() <= ACIERTO) {
            //ataque->izquierda
            if (direccion == 1) {
                if (x > 0) {
                    aplicarAtaqueHaciaCasillas(cell, y, x - 1);
                }
            }

            //ataque->derecha
            if (direccion == 2) {
                if (x < cell[0].length - 1) {
                    aplicarAtaqueHaciaCasillas(cell, y, x + 1);
                }
            }

            //ataque->abajo
            if (direccion == 3) {
                if (y < cell.length - 1) {
                    aplicarAtaqueHaciaCasillas(cell, y + 1, x);
                }
            }

            //ataque->arriba
            if (direccion == 4) {
                if (y > 0) {
                    aplicarAtaqueHaciaCasillas(cell, y - 1, x);
                }
            }

            tab.setCasillas(cell);
        } else {
            System.out.println("No acertaste");
        }
    }
}