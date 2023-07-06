package com.mycompany.rpg.backend.personaje.jugable;

import com.mycompany.rpg.backend.tablero.Tablero;
import com.mycompany.rpg.backend.tablero.casilla.*;

/**
 * Clase que contiene la informacion de un personaje jugable del tipo gigante
 *
 * @author giovanic
 */
public class Gigante extends Jugable {

    public Gigante(String nombre, double vida, int danio, int movimineto, boolean esVolador) {
        super(nombre, vida, danio, movimineto, esVolador);
    }

    @Override
    public String obtenerTipoAtaque() {
        return """
               golpea con su brazo, lo que le hace danio a todos
                                   los enemigos tu obstaculos en una linea en un
                                   rango de 3 cuadros.""";
    }
    
    @Override
    public void revivir() {
        this.setVida(350);
    }

    @Override
    public void ataque(Tablero tab, int direccion) {
        Casilla[][] cell = tab.getCasillas().clone();
        int x = tab.getPlayerPosicion().getX();
        int y = tab.getPlayerPosicion().getY();

        if (obtenerNumeroRandom() <= ACIERTO) {
            //ataque-izquierda
            if (direccion == 65) {
                for (int i = x - 1; i > x - 4; i--) {
                    if (i > -1) {
                        aplicarAtaqueHaciaCasillas(cell, y, i);
                    }
                }
            }

            //ataque-derecha
            if (direccion == 68) {
                for (int i = x + 1; i < x + 4; i++) {
                    if (i < cell[0].length) {
                        aplicarAtaqueHaciaCasillas(cell, y, i);
                    }
                }
            }

            //ataque->abajo
            if (direccion == 83) {
                for (int i = y + 1; i < y + 4; i++) {
                    if (i < cell.length) {
                        aplicarAtaqueHaciaCasillas(cell, i, x);
                    }
                }
            }

            //ataque->arriba
            if (direccion == 87) {
                for (int i = y - 1; i > y - 4; i--) {
                    if (i > -1) {
                        aplicarAtaqueHaciaCasillas(cell, i, x);
                    }
                }
            }

            tab.setCasillas(cell);
        } else {
            modificarAccion(this.getNombre() + " no acerto el ataque");
        }
    }
}
