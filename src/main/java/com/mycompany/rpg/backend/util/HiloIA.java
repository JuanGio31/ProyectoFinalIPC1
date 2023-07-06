package com.mycompany.rpg.backend.util;

import com.mycompany.rpg.backend.personaje.enemigo.Enemigo;
import com.mycompany.rpg.backend.tablero.Tablero;
import com.mycompany.rpg.backend.tablero.casilla.Casilla;

/**
 *
 * @author giovanic
 */
public class HiloIA extends Thread {

    private final Tablero tab;
    private final Enemigo pr;
    private final int numEnemigo;
    private final int direccion;

    public HiloIA(Tablero tab, Enemigo pr, int direccion, int numEnemigo) {
        this.tab = tab;
        this.pr = pr;
        this.numEnemigo = numEnemigo;
        this.direccion = direccion;
    }

    @Override
    public void run() {
        try {
            pr.ataque(tab, direccion, numEnemigo);
            Thread.sleep(310);
        } catch (InterruptedException ex) {
        }
        for (Casilla[] casilla : tab.getCasillas()) {
            for (int j = 0; j < tab.getCasillas()[0].length; j++) {
                casilla[j].modificarImage(false);
            }
        }
    }
}
