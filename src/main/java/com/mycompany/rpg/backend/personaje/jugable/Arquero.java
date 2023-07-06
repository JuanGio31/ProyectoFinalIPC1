package com.mycompany.rpg.backend.personaje.jugable;

import com.mycompany.rpg.backend.tablero.Tablero;
import com.mycompany.rpg.backend.tablero.casilla.*;

public class Arquero extends Jugable {

    public Arquero(String nombre, double vida, int danio, int movimineto, boolean esVolador) {
        super(nombre, vida, danio, movimineto, esVolador);
    }

    @Override
    public String obtenerTipoAtaque() {
        return """
               dispara una flecha a cualquier punto en un rango
                                   de tres casillas, ten cualquiera de las lineas.
                                   Este personaje solo ataca un cuadro.""";
    }

    @Override
    public void revivir() {
        this.setVida(150);
    }

    @Override
    public void ataque(Tablero tab, int direccion) {
        Casilla[][] cell = tab.getCasillas().clone();
        int x = tab.getPlayerPosicion().getX();
        int y = tab.getPlayerPosicion().getY();

        if (obtenerNumeroRandom() <= ACIERTO) {
            //ataque->izquierda
            switch (direccion) {
                case 65 -> {
                    if (x > 2) {
                        aplicarAtaqueHaciaCasillas(cell, y, x - 3);
                    }
                }
                case 68 -> {
                    //ataque->derecha
                    if (x < cell[0].length - 3) {
                        aplicarAtaqueHaciaCasillas(cell, y, x + 3);
                    }
                }
                case 83 -> {
                    //ataque->abajo
                    if (y < cell.length - 3) {
                        aplicarAtaqueHaciaCasillas(cell, y + 3, x);
                    }
                }
                case 87 -> {
                    //ataque->arriba
                    if (y > 2) {
                        aplicarAtaqueHaciaCasillas(cell, y - 3, x);
                    }
                }
            }

            tab.setCasillas(cell);
        } else {
            modificarAccion(this.getNombre() + " no acerto el ataque");
        }
    }
}
