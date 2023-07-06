package com.mycompany.rpg.backend.util;

import com.mycompany.rpg.backend.personaje.jugable.Jugable;
import com.mycompany.rpg.backend.tablero.Tablero;
import com.mycompany.rpg.backend.tablero.casilla.Casilla;
import com.mycompany.rpg.frontend.JuegoFrame;

/**
 *
 * @author giovanic
 */
public class HiloJugador extends Thread {

    private final Tablero tab;
    private final Jugable pr;
    private final int direccion;
    private final JuegoFrame juegoFrame;

    public HiloJugador(Tablero tab, Jugable pr, int direccion, JuegoFrame juegoFrame) {
        this.tab = tab;
        this.pr = pr;
        this.direccion = direccion;
        this.juegoFrame = juegoFrame;
    }

    @Override
    public void run() {
        try {
            pr.ataque(tab, direccion);
            Thread.sleep(310);
        } catch (InterruptedException ex) {
        }
        for (Casilla[] casilla : tab.getCasillas()) {
            for (int j = 0; j < tab.getCasillas()[0].length; j++) {
                casilla[j].modificarImage(false);
            }
        }
        juegoFrame.limpiarTablero();
        juegoFrame.pintarMapa();
    }
}
