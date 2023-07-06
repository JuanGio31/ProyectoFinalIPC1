package com.mycompany.rpg.backend.personaje.jugable;

import com.mycompany.rpg.backend.tablero.Tablero;
import com.mycompany.rpg.backend.tablero.casilla.Casilla;

public class Caballero extends Jugable {

    public Caballero(String nombre, double vida, int danio, int movimineto, boolean esVolador) {
        super(nombre, vida, danio, movimineto, esVolador);
    }

    @Override
    public String obtenerTipoAtaque() {
        return "golpea con su espada a todos a su alrededor";
    }

    @Override
    public void revivir() {
        this.setVida(300);
    }

    @Override
    public void ataque(Tablero tab, int direccion) {
        Casilla[][] cell = tab.getCasillas().clone();
        int x = tab.getPlayerPosicion().getX();
        int y = tab.getPlayerPosicion().getY();

        if (obtenerNumeroRandom() <= ACIERTO) {
            //if ((x > 0 && y > 0) && (x < cell[0].length && y < cell.length)) {
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
            modificarAccion(this.getNombre() + " no acerto el ataque");
        }
    }
}
