package com.mycompany.rpg.backend.personaje.jugable;

import com.mycompany.rpg.backend.personaje.Personaje;
import com.mycompany.rpg.backend.tablero.Tablero;
import com.mycompany.rpg.backend.tablero.casilla.*;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author giovanic
 */
public class Jugable extends Personaje {

    public static final int COSTO_PERSONAJE = 200;
    protected final int ACIERTO = 75;
    protected String accion = "";

    public Jugable(String nombre, double vida, int danio, int movimineto, boolean esVolador) {
        super(nombre, vida, danio, movimineto, esVolador);
    }

    public Jugable() {
    }

    @Override
    public String toString() {
        return "+ " + getNombre()
                + "\n  - Vida: " + getVida()
                + "\n  - Movimiento: " + getMovimineto()
                + "\n  - Danio: " + getDanio()
                + "\n  - Es Volador: " + (esVolador() ? " Si" : " No")
                + "\n  - Ataque: " + obtenerTipoAtaque();
    }

    public String obtenerTipoAtaque() {
        return "";
    }

    public Jugable crearPersonajeJugable(int selPersonaje) {
        switch (selPersonaje) {
            case 1 -> {
                // Caballero
                return new Caballero("Caballero", 300, 45, 1, false);
            }
            case 2 -> {
                // Arquero
                return new Arquero("Arquero", 150, 100, 3, false);
            }
            case 3 -> {
                // Mago
                return new Mago("Mago", 100, 60, 2, true);
            }
            case 4 -> {
                // Gigante
                return new Gigante("Gigante", 350, 56, 1, false);
            }
            case 5 -> {
                // Dragon
                return new Dragon("Dragon", 250, 75, 3, true);
            }
        }
        return null;
    }

    public String obtenerAccionRealizada() {
        return accion;
    }

    public void revivir() {
    }

    protected void modificarAccion(String str) {
        this.accion = str;
    }

    @Override
    public void ataque(Tablero tab, int direccion) {

    }

    @Override
    public void ataque(Tablero tab, int direccion, int numPersonaje) {

    }

    /**
     * Metodo para cambiar la posición del jugador
     *
     * @param tab El tablero donde se encuentra el personaje
     * @param x Cantidad de espacios en x que se moverá el jugador
     * @param y Cantidad de espacios en y que se moverá el jugador
     * @param personaje tipo de personaje
     */
    public void moverPersonaje(Tablero tab, int x, int y, Personaje personaje) {
        try {
            CasillaJugable playerCell = ((CasillaJugable) tab.getCasillas()[tab.getPlayerPosicion().getY()][tab.getPlayerPosicion().getX()]); //Obtenemos la celda donde esta el jugador
            Casilla aux = playerCell.getSobreCasilla(); //Almacenamos temporalmente la celda sobre la que se encuentra el jugador
            Casilla nuevaCasilla = tab.getCasillas()[tab.getPlayerPosicion().getY() + (y)][tab.getPlayerPosicion().getX() + (x)];
            if (nuevaCasilla instanceof CasillaArbol || nuevaCasilla instanceof CasillaAgua || nuevaCasilla instanceof CasillaEnemigo) {
                if (personaje.esVolador() && !(nuevaCasilla instanceof CasillaEnemigo)) {
                    playerCell.setSobreCasilla(nuevaCasilla); //Cambiamos la celda sobre la que se encuentra el jugador
                    tab.getCasillas()[tab.getPlayerPosicion().getY() + (y)][tab.getPlayerPosicion().getX() + (x)] = playerCell; //Cambiamos la celda a la que se moverá por una celda tipo jugador
                    tab.getCasillas()[tab.getPlayerPosicion().getY()][tab.getPlayerPosicion().getX()] = aux; //Colocamos la celda que estaba debajo del jugador en la posición donde estaba
                    tab.getPlayerPosicion().setY(tab.getPlayerPosicion().getY() + (y)); //modificamos la posición en la que se encuentra el jugador
                    tab.getPlayerPosicion().setX(tab.getPlayerPosicion().getX() + (x)); //modificamos la posición en la que se encuentra el jugador

                    modificarAccion(personaje.getNombre()
                            + " se movio en a la casilla ["
                            + (tab.getPlayerPosicion().getX() + 1) + ","
                            + (tab.getPlayerPosicion().getY() + 1) + "]");
                } else {
                    System.out.println("No te puedes mover sobre un arbol, agua, o sobre un enemigo");
                    mostrarAlerta("No te puedes mover sobre un arbol, agua, o sobre un enemigo");
                }
            } else {
                playerCell.setSobreCasilla(nuevaCasilla); //Cambiamos la celda sobre la que se encuentra el jugador
                tab.getCasillas()[tab.getPlayerPosicion().getY() + (y)][tab.getPlayerPosicion().getX() + (x)] = playerCell; //Cambiamos la celda a la que se moverá por una celda tipo jugador
                tab.getCasillas()[tab.getPlayerPosicion().getY()][tab.getPlayerPosicion().getX()] = aux; //Colocamos la celda que estaba debajo del jugador en la posición donde estaba
                tab.getPlayerPosicion().setY(tab.getPlayerPosicion().getY() + (y)); //modificamos la posición en la que se encuentra el jugador
                tab.getPlayerPosicion().setX(tab.getPlayerPosicion().getX() + (x)); //modificamos la posición en la que se encuentra el jugador
                modificarAccion(personaje.getNombre()
                        + " se movio en a la casilla ["
                        + (tab.getPlayerPosicion().getX() + 1) + ","
                        + (tab.getPlayerPosicion().getY() + 1) + "]");
            }
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("No te puedes mover fuera del mapa");
            modificarAccion("No puedes moverte fuera de los limites del mapa");
            mostrarAlerta("No puedes moverte fuera de los limites del mapa");
        }
    }

    protected void aplicarAtaqueHaciaCasillas(Casilla[][] cell, int y, int x) {
        cell[y][x].modificarImage(true);
        if (cell[y][x] instanceof CasillaArbol) {
            CasillaArbol tmp = (CasillaArbol) cell[y][x];
            tmp.setVida(tmp.getVida() - this.getDanio());
            if (tmp.estaArbolVivo()) {
                cell[y][x] = tmp;
            } else {
                cell[y][x] = new CasillaPlanicie();
                modificarAccion(getNombre() + " ha derivado un arbol");
            }
        } else if (cell[y][x] instanceof CasillaEnemigo) {
            CasillaEnemigo tmp = (CasillaEnemigo) cell[y][x];

            if (tmp.estaSobreArbol()) {
                CasillaArbol arbol = (CasillaArbol) tmp.getSobreCasilla();
                arbol.setVida(arbol.getVida() - this.getDanio());
                if (arbol.estaArbolVivo()) {
                    tmp.setSobreCasilla(arbol);
                } else {
                    tmp.setSobreCasilla(new CasillaPlanicie());
                    modificarAccion(getNombre() + " ha derivado un arbol donde se encontraba un enemigo");
                }
            } else {
                tmp.getEnemigo().setVida(tmp.getEnemigo().getVida() - this.getDanio());
                if (tmp.getEnemigo().estaVivo()) {
                    cell[y][x] = tmp;
                } else {
                    modificarAccion(getNombre() + " ha derivado a un enemigo del tipo " + tmp.getEnemigo().getNombre());
                    cell[y][x] = new CasillaPlanicie();
                }
            }
        }
    }

    /**
     * Metodo para mostrar un mensaje de error durante x-periodo de tiempo
     */
    private void mostrarAlerta(String str) {
        JOptionPane msg = new JOptionPane(str,
                JOptionPane.ERROR_MESSAGE);
        JDialog dlg = msg.createDialog("Advertencia");
        dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                dlg.setVisible(false);
            }
        }).start();
        dlg.setVisible(true);
        dlg.dispose();
    }
}
