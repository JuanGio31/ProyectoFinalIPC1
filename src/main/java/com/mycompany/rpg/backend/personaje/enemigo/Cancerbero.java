package com.mycompany.rpg.backend.personaje.enemigo;

import com.mycompany.rpg.backend.tablero.Tablero;
import com.mycompany.rpg.backend.tablero.casilla.*;

public class Cancerbero extends Enemigo {

    public Cancerbero(String nombre, double vida, int danio, int movimineto, boolean esVolador, int puntaje) {
        super(nombre, vida, danio, movimineto, esVolador, puntaje);
    }

    @Override
    public void ataque(Tablero tab, int direccion, int numPersonaje) {
        Casilla[][] cell = tab.getCasillas().clone();
        int x = tab.getEnemiPos()[numPersonaje].getX();
        int y = tab.getEnemiPos()[numPersonaje].getY();

        if (obtenerNumeroRandom() <= ACIERTO) {
            for (int i = y - 1; i <= y + 1; i++) {
                if (i >= 0 && i < tab.getCasillas().length) {
                    for (int j = x - 1; j <= x + 1; j++) {
                        if (j >= 0 && j < tab.getCasillas()[0].length) {
                            if (i != y || j != x) {
                                aplicarAtaqueHaciaCasillas(cell, i, j);
                            }
                        }
                    }
                }
            }
            tab.setCasillas(cell);
        } else {
            System.out.println("No acertaste");
        }
    }
}
