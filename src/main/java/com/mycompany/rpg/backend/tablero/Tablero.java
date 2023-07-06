package com.mycompany.rpg.backend.tablero;

import com.mycompany.rpg.backend.Posicion;
import com.mycompany.rpg.backend.personaje.enemigo.Enemigo;
import com.mycompany.rpg.backend.personaje.jugable.Jugable;
import com.mycompany.rpg.backend.tablero.casilla.*;
import java.awt.HeadlessException;
import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 * Clase que almacena la informacion del tablero de partida
 *
 * @author giovanic
 */
public class Tablero implements Serializable {

    private Casilla[][] casillas;
    private Jugable jugable;
    private Enemigo[] enemigo;
    private Posicion[] enemiPos;
    private Posicion playerPosicion;

    public Tablero(CreadorTablero creadorTablero) {
        this.casillas = generarCasillas(creadorTablero);
    }

    /**
     * Metodo para cargar los enemigos en el mapa
     */
    public void cargarEnemigos() {
        this.enemigo = inicializarEnemigos(dificultad());
        this.enemiPos = new Posicion[enemigo.length];
    }

    /**
     * Obtiene la casilla en la que se encuentra el jugador
     *
     * @return Casilla del jugador
     */
    public CasillaJugable getPlayerCell() {
        return (CasillaJugable) this.casillas[this.playerPosicion.getY()][this.playerPosicion.getX()];
    }

    /**
     * Metodo para colocar al jugador en una posición al azar sobre el mapa
     */
    public void colocarJugador() {
        eliminarJugador();
        boolean estaJugadorEnMapa = false;
        while (!estaJugadorEnMapa) {
            int row = 0;
            int colum = 0;
            do {
                row = elegirPosicion("Ingresar fila");
                colum = elegirPosicion("Ingresar columna");
            } while (row >= this.casillas.length && colum >= this.casillas[0].length);

            if (this.casillas[row][colum] instanceof CasillaPlanicie) {
                estaJugadorEnMapa = true;
                Casilla cell = this.getCasillas()[row][colum];
                this.casillas[row][colum] = new CasillaJugable(jugable, cell);
                this.playerPosicion = new Posicion(row, colum);
            }
        }
    }

    /**
     * *
     * Metodo que sirve para que el usuario indique en que posicion del tablero
     * ubicarse
     *
     * @param msj El menaje con el cual se lanzara el JOpctionPane
     * @return devuelve un numero entero
     */
    private int elegirPosicion(String msj) {
        boolean esValido = false;
        int pos = 0;
        while (!esValido) {
            try {
                pos = Integer.parseInt(JOptionPane.showInputDialog(msj)) - 1;
                esValido = pos > -1;
            } catch (HeadlessException | NumberFormatException e) {
                if (e instanceof NumberFormatException) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Debe ingresar numeros",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                esValido = false;
            }
        }
        return pos;
    }

    /**
     * Metodo para posicionar a los enemigos de manera aleatotria dentro del
     * tablero
     */
    public void posicionarEnemigos() {
        //eliminar posibles enemigos en el mapa
        for (int i = 0; i < enemiPos.length; i++) {
            if (this.enemiPos[i] != null) {
                this.casillas[this.enemiPos[i].getY()][this.enemiPos[i].getX()] = new CasillaPlanicie();
                this.enemiPos[i] = null;
            }
        }

        //colocar a los enemigos en el mapa de manera aleatoria
        for (int i = 0; i < enemiPos.length; i++) {
            boolean isPlayerInMap = false;
            while (!isPlayerInMap) {
                int row = (int) (Math.random() * (this.casillas.length - 1));
                int column = (int) (Math.random() * (this.casillas[0].length - 1));
                if (this.casillas[row][column] instanceof CasillaPlanicie) {
                    isPlayerInMap = true;
                    Casilla cell = this.getCasillas()[row][column];
                    this.casillas[row][column] = new CasillaEnemigo(enemigo[i], cell);
                    this.enemiPos[i] = new Posicion(row, column);
                }
            }
        }
    }

    /**
     * Eliminar personaje del usuario del mapa
     */
    private void eliminarJugador() {
        if (this.playerPosicion != null) {
            this.casillas[this.playerPosicion.getY()][this.playerPosicion.getX()] = new CasillaPlanicie();
            this.playerPosicion = null;
        }
    }

    /**
     * Metodo que indica la cantidad de enemigos que apareceran en el tablero
     *
     * @return devuelve un numero entero 3, 5 o 8
     */
    private int dificultad() {
        String msj = """
                     Seleccione una opcion.
                     Facil    -> 3 enemigos.
                     Medio -> 5 enemigos.
                     Dificil  -> 8 enemigos.""";
        int seleccion = JOptionPane.showOptionDialog(null,
                msj,
                "Dificultad",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,// null para icono por defecto.
                new Object[]{"Facil", "Medio", "Dificil"}, "Facil");
        switch (seleccion) {
            case 0 -> {
                return 3;
            }
            case 1 -> {
                return 5;
            }
            case 2 -> {
                return 8;
            }
        }
        return 3;
    }

    /**
     * Metodo para crear la cantidad de enemigos segun la dificultad
     *
     * @param dif La dificultad del tablero
     * @return devuelve un arreglo de enemigos segun la dificultad
     */
    private Enemigo[] inicializarEnemigos(int dif) {
        Enemigo[] eCreacion = new Enemigo[5];
        Enemigo tmp = new Enemigo();

        for (int i = 0; i < eCreacion.length; i++) {
            eCreacion[i] = tmp.crearEnemigo(i + 1);
        }

        switch (dif) {
            case 3 -> {
                Enemigo[] emg = new Enemigo[dif];
                emg[0] = eCreacion[0]; //ogro
                emg[1] = eCreacion[1]; //gargola
                emg[2] = eCreacion[4]; //flor
                return emg;
            }
            case 5 -> {
                Enemigo[] emg = new Enemigo[dif];
                emg[0] = eCreacion[0]; //ogro
                emg[1] = eCreacion[1]; //gargola
                emg[2] = eCreacion[2]; //bruja
                emg[3] = eCreacion[3]; //cancerbero
                emg[4] = eCreacion[4]; //flor
                return emg;
            }
            case 8 -> {
                Enemigo[] emg = new Enemigo[dif];
                emg[0] = eCreacion[0]; //ogros
                emg[1] = eCreacion[0];
                emg[2] = eCreacion[1]; //gargola
                emg[3] = eCreacion[2]; //brujas
                emg[4] = eCreacion[2];
                emg[5] = eCreacion[3]; //cancerbero
                emg[6] = eCreacion[4]; //flores
                emg[7] = eCreacion[4];
                return emg;
            }
        }
        return null;
    }

    /**
     * Metodo para crear objetos del tipo casilla a partir de una arreglo de
     * String de dos dimensiones
     *
     * @param creadorTablero Objeto donde se encuentra el arreglo de String
     */
    private Casilla[][] generarCasillas(CreadorTablero creadorTablero) {
        Casilla[][] tmp = new Casilla[creadorTablero.getFilas()][creadorTablero.getColumnas()];

        for (int i = 0; i < creadorTablero.getCell().length; i++) {
            for (int j = 0; j < creadorTablero.getCell()[0].length; j++) {
                switch (creadorTablero.getCell()[i][j]) {
                    case "-" ->
                        tmp[i][j] = crearCasillaPlanicie();
                    case "~" ->
                        tmp[i][j] = crearCasillaAgua();
                    case "#" ->
                        tmp[i][j] = crearCasillaLava();
                    case "T" ->
                        tmp[i][j] = crearCasillaArbol();
                }
            }
        }
        llenarCasillaVacia(tmp);
        return tmp;
    }

    private void llenarCasillaVacia(Casilla[][] cells) {
        for (Casilla[] cell : cells) {
            for (int j = 0; j < cell.length; j++) {
                if (cell[j] == null) {
                    cell[j] = crearCasillaPlanicie();
                }
            }
        }
    }

    private CasillaAgua crearCasillaAgua() {
        return new CasillaAgua();
    }

    private CasillaArbol crearCasillaArbol() {
        return new CasillaArbol(75);
    }

    private CasillaLava crearCasillaLava() {
        return new CasillaLava();
    }

    private CasillaPlanicie crearCasillaPlanicie() {
        return new CasillaPlanicie();
    }

    public Casilla[][] getCasillas() {
        return casillas;
    }

    public void setCasillas(Casilla[][] casillas) {
        this.casillas = casillas;
    }

    public Jugable getJugable() {
        return jugable;
    }

    public void setJugable(Jugable jugable) {
        this.jugable = jugable;
    }

    public Posicion getPlayerPosicion() {
        return playerPosicion;
    }

    public Enemigo[] getEnemigo() {
        return enemigo;
    }

    public Posicion[] getEnemiPos() {
        return enemiPos;
    }

}
