package com.mycompany.rpg.backend.personaje.enemigo;

import com.mycompany.rpg.backend.personaje.Personaje;
import com.mycompany.rpg.backend.tablero.Tablero;
import com.mycompany.rpg.backend.tablero.casilla.*;

public class Enemigo extends Personaje {

    protected final int ACIERTO = 60;
    private int puntaje;
    protected String accion = "";

    public Enemigo(String nombre, double vida, int danio, int movimineto, boolean esVolador, int puntaje) {
        super(nombre, vida, danio, movimineto, esVolador);
        this.puntaje = puntaje;
    }

    public Enemigo() {
    }

    public Enemigo crearEnemigo(int selPersonaje) {

        switch (selPersonaje) {
            case 1 -> {
                // Ogro
                return new Ogro("Ogro", 300, 50, 1, false, 25);
            }
            case 2 -> {
                // Gargola
                return new Gargola("Gargola", 150, 100, 3, true, 45);
            }
            case 3 -> {
                // Bruja
                return new Bruja("Bruja", 150, 45, 1, true, 100);
            }
            case 4 -> {
                // Cerbero
                return new Cancerbero("Cerbero", 400, 45, 1, false, 30);
            }
            case 5 -> {
                // Flor
                return new FlorCarnivora("Flor", 250, 30, 0, false, 75);
            }
        }

        return null;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public String obtenerAccionRealizada() {
        return accion;
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
     * @param x Cantidad de espacios en x que se moverá el enemigo
     * @param y Cantidad de espacios en y que se moverá el enemigo
     * @param personaje tipo de personaje
     */
    public void moverPersonaje(Tablero tab, int x, int y, Personaje[] personaje, int numEnemigo) {
        try {
            CasillaEnemigo enemigoCell = ((CasillaEnemigo) tab.getCasillas()[tab.getEnemiPos()[numEnemigo].getY()][tab.getEnemiPos()[numEnemigo].getX()]); //Obtenemos la celda donde esta el jugador
            Casilla aux = enemigoCell.getSobreCasilla(); //Almacenamos temporalmente la celda sobre la que se encuentra el jugador
            Casilla nuevaCasilla = tab.getCasillas()[tab.getEnemiPos()[numEnemigo].getY() + (y)][tab.getEnemiPos()[numEnemigo].getX() + (x)];
            if (nuevaCasilla instanceof CasillaArbol || nuevaCasilla instanceof CasillaAgua
                    || nuevaCasilla instanceof CasillaJugable || nuevaCasilla instanceof CasillaEnemigo) {
                if (personaje[numEnemigo].esVolador() && !(nuevaCasilla instanceof CasillaJugable) && !(nuevaCasilla instanceof CasillaEnemigo)) {
                    enemigoCell.setSobreCasilla(nuevaCasilla); //Cambiamos la celda sobre la que se encuentra el jugador
                    tab.getCasillas()[tab.getEnemiPos()[numEnemigo].getY() + (y)][tab.getEnemiPos()[numEnemigo].getX() + (x)] = enemigoCell; //Cambiamos la celda a la que se moverá por una celda tipo jugador
                    tab.getCasillas()[tab.getEnemiPos()[numEnemigo].getY()][tab.getEnemiPos()[numEnemigo].getX()] = aux; //Colocamos la celda que estaba debajo del jugador en la posición donde estaba
                    tab.getEnemiPos()[numEnemigo].setY(tab.getEnemiPos()[numEnemigo].getY() + (y)); //modificamos la posición en la que se encuentra el jugador
                    tab.getEnemiPos()[numEnemigo].setX(tab.getEnemiPos()[numEnemigo].getX() + (x)); //modificamos la posición en la que se encuentra el jugador

                    modificarAccion(personaje[numEnemigo].getNombre()
                            + " se movio en a la casilla ["
                            + (tab.getEnemiPos()[numEnemigo].getX() + 1) + ","
                            + (tab.getEnemiPos()[numEnemigo].getY() + 1) + "]");
                } else {
                    System.out.println("No te puedes mover sobre un arbol, agua, o sobre un enemigo");
                }
            } else {
                enemigoCell.setSobreCasilla(nuevaCasilla); //Cambiamos la celda sobre la que se encuentra el jugador
                tab.getCasillas()[tab.getEnemiPos()[numEnemigo].getY() + (y)][tab.getEnemiPos()[numEnemigo].getX() + (x)] = enemigoCell; //Cambiamos la celda a la que se moverá por una celda tipo jugador
                tab.getCasillas()[tab.getEnemiPos()[numEnemigo].getY()][tab.getEnemiPos()[numEnemigo].getX()] = aux; //Colocamos la celda que estaba debajo del jugador en la posición donde estaba
                tab.getEnemiPos()[numEnemigo].setY(tab.getEnemiPos()[numEnemigo].getY() + (y)); //modificamos la posición en la que se encuentra el jugador
                tab.getEnemiPos()[numEnemigo].setX(tab.getEnemiPos()[numEnemigo].getX() + (x)); //modificamos la posición en la que se encuentra el jugador

                modificarAccion(personaje[numEnemigo].getNombre()
                        + " se movio en a la casilla ["
                        + (tab.getEnemiPos()[numEnemigo].getX() + 1) + ","
                        + (tab.getEnemiPos()[numEnemigo].getY() + 1) + "]");
            }
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("No te puedes mover fuera del mapa");
        }
    }

    public boolean cercaDeJugador(Tablero tab, int numPersj) {
        Casilla[][] cell = tab.getCasillas();
        int x = tab.getEnemiPos()[numPersj].getX();
        int y = tab.getEnemiPos()[numPersj].getY();

        for (int i = y - 1; i <= y + 1; i++) {
            if (i >= 0 && i < tab.getCasillas().length) {
                for (int j = x - 1; j <= x + 1; j++) {
                    if (j >= 0 && j < tab.getCasillas()[0].length) {
                        if (i != y || j != x) {
                            if (cell[i][j] instanceof CasillaJugable) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
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
        }
        if (cell[y][x] instanceof CasillaJugable) {
            CasillaJugable tmp = (CasillaJugable) cell[y][x];

            if (tmp.estaSobreArbol()) {
                CasillaArbol arbol = (CasillaArbol) tmp.getSobreCasilla();
                arbol.setVida(arbol.getVida() - this.getDanio());
                if (arbol.estaArbolVivo()) {
                    tmp.setSobreCasilla(arbol);
                } else {
                    tmp.setSobreCasilla(new CasillaPlanicie());
                    modificarAccion(getNombre() + " ha derivado un arbol donde se encontraba el usuario");
                }
            } else {
                tmp.getJugable().setVida(tmp.getJugable().getVida() - this.getDanio());
                if (tmp.getJugable().estaVivo()) {
                    cell[y][x] = tmp;
                } else {
                    modificarAccion(getNombre() + " ha derivado a un enemigo del tipo " + tmp.getJugable().getNombre());
                    cell[y][x] = new CasillaPlanicie();
                }
            }
        }
    }
}
