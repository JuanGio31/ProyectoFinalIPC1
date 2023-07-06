package com.mycompany.rpg.backend;

import com.mycompany.rpg.backend.tablero.CreadorTablero;
import com.mycompany.rpg.backend.tablero.Tablero;
import com.mycompany.rpg.backend.tablero.casilla.Casilla;
import com.mycompany.rpg.backend.tablero.casilla.CasillaEnemigo;
import com.mycompany.rpg.backend.util.*;
import com.mycompany.rpg.frontend.JuegoFrame;
import java.io.Serializable;

/**
 *
 * @author giovanic Clase encargadda de controlar los el frame donde se juega
 */
public class ControladorPartida implements Serializable {

    private JugadorUs jg;
    private Listado<CreadorTablero> tabs;
    private Partida juegoActivo;
    private JuegoFrame juegoFrame;
    private int contadorMov = 0;

    public ControladorPartida(Listado<CreadorTablero> tabs, JugadorUs jg) {
        this.tabs = tabs;
        this.jg = jg;
    }

    public Partida getJuegoActivo() {
        return juegoActivo;
    }

    public void setJuegoActivo(Partida activeGame) {
        this.juegoActivo = activeGame;
    }

    public void cambiarPersonajeJugable() {
        juegoActivo.cambioPersonaje(contadorMov, juegoFrame);
    }

    public void atacarEnDireccion(int direccion) {
        HiloJugador proceso = new HiloJugador(
                juegoActivo.getTab(),
                juegoActivo.getUsuario().getJugablesEnJuego()[0],
                direccion,
                juegoFrame);
        proceso.start();
        juegoActivo.cambiarTurno();
        contadorMov = 0;
    }

    /**
     * Metodo para mover al personaje dentro del mapa
     *
     * @param direccion m
     */
    public void moverJugador(int direccion) {
        switch (direccion) {
            case 38 -> {
                this.juegoActivo.moverPersonajeArriba();
                contadorMov++;
            }

            case 37 -> {
                this.juegoActivo.moverPersonajeIzquierda();
                contadorMov++;
            }
            case 40 -> {
                this.juegoActivo.moverPersonajeAbajo();
                contadorMov++;
            }
            case 39 -> {
                this.juegoActivo.moverPersonajeDerecha();
                contadorMov++;
            }

        }

        if (contadorMov == this.jg.getJugablesEnJuego()[0].getMovimineto()) {
            contadorMov = 0;
            this.juegoActivo.cambiarTurno();
        }

        juegoFrame.limpiarTablero();
        juegoFrame.pintarMapa();
    }

    /**
     * Metodo para seleccionar un tablero e iniciar la partida
     *
     * @param selectedIndex indice del tablero dentro del listado de tableros
     */
    public void seleccionarTablero(int selectedIndex) {
        try {
            juegoActivo = new Partida(new Tablero(this.tabs.obtenerElemento(selectedIndex)), jg);
        } catch (ListaException ex) {
            System.out.println("error -> " + ex.getMessage());
        }
    }

    public boolean estaEnJuegoActivo() {
        return this.juegoActivo != null;
    }

    public void finalizarJuego() {
        juegoActivo.llenarVidaPersonajes();
        this.juegoActivo = null;
    }

    public void setJuegoFrame(JuegoFrame juegoFrame) {
        this.juegoFrame = juegoFrame;
    }

    public void getMovExtra() {
        this.contadorMov = contadorMov - 1;
    }

    public void aplicarEfectoLava() {
        if (juegoActivo.getTab().getPlayerCell() != null) {
            juegoActivo.getTab().getPlayerCell().aplicarEfectoLava();
        }

        Casilla[][] cell = juegoActivo.getTab().getCasillas();
        for (Casilla[] cell1 : cell) {
            for (int j = 0; j < cell[0].length; j++) {
                if (cell1[j] instanceof CasillaEnemigo) {                    
                    CasillaEnemigo tmp = (CasillaEnemigo) cell1[j];
                    tmp.aplicarEfectoLava();
                    cell1[j] = tmp;
                }
            }
        }
    }
}
