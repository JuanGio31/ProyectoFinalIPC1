package com.mycompany.rpg.backend;

import com.mycompany.rpg.backend.personaje.enemigo.*;
import com.mycompany.rpg.backend.personaje.jugable.Jugable;
import com.mycompany.rpg.backend.tablero.Tablero;
import com.mycompany.rpg.backend.util.HiloIA;
import com.mycompany.rpg.frontend.JuegoFrame;
import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 * Clase donde se almacena la informacion de la partida
 *
 * @author giovanic
 */
public class Partida implements Serializable {

    private Tablero tab;
    private JugadorUs usuario;
    private IA bot;
    private boolean turno;
    private int contador = 0;

    /**
     * Metodo constructor
     *
     * @param tab Tablero en el cual se jugara
     * @param us el usuario/jugador en la partida
     */
    public Partida(Tablero tab, JugadorUs us) {
        this.tab = tab;
        this.usuario = us;
        this.tab.setJugable(this.usuario.getJugablesEnJuego()[0]);

        this.bot = new IA("IA");
        tab.cargarEnemigos();
        this.bot.setEnemigos(tab.getEnemigo());

        this.turno = true;
    }

    public void cambiarTurno() {
        Runnable cambio = new Runnable() {
            @Override
            public void run() {
                try {
                    turno = !turno;
                    Thread.sleep(350);
                } catch (InterruptedException e) {
                }
            }
        };
        Thread hilo = new Thread(cambio);
        hilo.start();
    }

    public void comprobarGanador(JuegoFrame fr) {
        if (usuario.estaVivo() && bot.estaVivo()) {
            if (turno) {
                fr.modificarTxtArea("Turno: " + usuario.getNombre());
                if (!usuario.getJugablesEnJuego()[0].estaVivo() || !usuario.getJugablesEnJuego()[1].estaVivo()) {
                    contador++;
                    if (usuario.getInventario().existeItemSemillaDeLaVida()) {
                        fr.cargarSelectorObjetos();
                        tab.colocarJugador();
                        fr.limpiarTablero();
                        fr.pintarMapa();
                    } else if (contador == 1) {
                        usuario.cambiar();
                        tab.setJugable(usuario.getJugablesEnJuego()[0]);
                        tab.colocarJugador();
                        fr.limpiarTablero();
                        fr.pintarMapa();
                    }
                }

                fr.modificarTxtArea(usuario.getJugablesEnJuego()[0].obtenerAccionRealizada());
            } else {
                fr.modificarTxtArea("Turno: " + bot.getNombre());
                accionarIA(fr);
                cambiarTurno();
                if (usuario.estaVivo()) {
                    usuario.setPuntuacion(bot.darPuntos());
                    if (!usuario.getJugablesEnJuego()[0].estaVivo()) {
                        contador = 0;
                        cambiarTurno();
                    }
                }
                fr.limpiarTablero();
                fr.pintarMapa();
            }
        } else {
            if (usuario.estaVivo()) {
                usuario.setOro(usuario.getOro() + 250);
            }
            String msj = "Juego terminado!\nPuntos obtenidos: " + usuario.getPuntuacion() + " ";
            fr.limpiarTablero();
            JOptionPane.showMessageDialog(null, msj, "Informacion", JOptionPane.INFORMATION_MESSAGE);
            fr.dispose();
            fr.getMenu().setVisible(true);
            llenarVidaPersonajes();
            contador = 0;
        }
    }

    /**
     * Mueve el personaje hacia arriba
     */
    public void moverPersonajeArriba() {
        this.usuario.getJugablesEnJuego()[0].moverPersonaje(tab,
                0,
                -1,
                usuario.getJugablesEnJuego()[0]);
    }

    /**
     * Mueve el personaje hacia abajo
     */
    public void moverPersonajeAbajo() {
        this.usuario.getJugablesEnJuego()[0].moverPersonaje(tab,
                0,
                1,
                usuario.getJugablesEnJuego()[0]);
    }

    /**
     * Mueve el personaje hacia la derecha
     */
    public void moverPersonajeDerecha() {
        this.usuario.getJugablesEnJuego()[0].moverPersonaje(tab,
                1,
                0,
                usuario.getJugablesEnJuego()[0]);
    }

    /**
     * Mueve el personaje hace la izquierda
     */
    public void moverPersonajeIzquierda() {
        this.usuario.getJugablesEnJuego()[0].moverPersonaje(tab,
                -1,
                0,
                usuario.getJugablesEnJuego()[0]);
    }

    /**
     * Metodo que define que accion tomara la ia.
     *
     * @param fr frame del juego
     */
    public void accionarIA(JuegoFrame fr) {
        for (int i = 0; i < bot.getEnemigos().length; i++) {
            int aleatorio = (int) (Math.random() * 4 + 1);
            if (bot.getEnemigos()[i].estaVivo()) {
                if (bot.getEnemigos()[i] instanceof FlorCarnivora) {
                    realizarAtaqueIa(i, aleatorio);
                    fr.modificarTxtArea(bot.getEnemigos()[i].obtenerAccionRealizada());
                } else {
                    if (bot.getEnemigos()[i].cercaDeJugador(tab, i)) {
                        realizarAtaqueIa(i, aleatorio);
                    } else {
                        if (bot.getEnemigos()[i] instanceof Bruja) {
                            int lim = this.tab.getCasillas().length;
                            int rd = (int) (Math.random() * lim + 2);
                            int tmp = 0;
                            while (tmp < rd) {
                                definirMovimiento(i, rd);
                                fr.modificarTxtArea(bot.getEnemigos()[i].obtenerAccionRealizada());
                                tmp++;
                            }
                        } else {
                            for (int j = 0; j < bot.getEnemigos()[i].getMovimineto(); j++) {
                                int rd = (int) (Math.random() * 4 + 1);
                                definirMovimiento(i, rd);
                                fr.modificarTxtArea(bot.getEnemigos()[i].obtenerAccionRealizada());
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Metodo que sirve para que el enemigo realice un ataque
     *
     * @param numPersonaje indice de la lista de enemigos
     * @param op hacia que direccion ataca por ejemplo 1-derecha
     */
    public void realizarAtaqueIa(int numPersonaje, int op) {
        HiloIA proceso = new HiloIA(
                tab,
                bot.getEnemigos()[numPersonaje],
                op,
                numPersonaje);
        proceso.start();
    }

    /**
     * Metodo para mover a los enemigos de manera aleatoria
     *
     * @param numPersonaje el numero de enemigo en un arreglo de enemigos
     * @param op hacia que direccion mover el enemigo
     */
    public void definirMovimiento(int numPersonaje, int op) {
//        int random = (int) (Math.random() * 4 + 1);
        switch (op) {
            case 1 ->
                moverPersonajeArriba(numPersonaje);
            case 2 ->
                moverPersonajeAbajo(numPersonaje);
            case 3 ->
                moverPersonajeDerecha(numPersonaje);
            case 4 ->
                moverPersonajeIzquierda(numPersonaje);
        }
    }

    /**
     * Mueve el personaje hacia arriba
     *
     * @param numPersonaje indice del personaje a accionar
     */
    public void moverPersonajeArriba(int numPersonaje) {
        this.bot.getEnemigos()[numPersonaje].moverPersonaje(tab,
                0,
                -1,
                bot.getEnemigos(),
                numPersonaje);
    }

    /**
     * Mueve el personaje hacia abajo
     *
     * @param numPersonaje indice del personaje a accionar
     */
    public void moverPersonajeAbajo(int numPersonaje) {
        this.bot.getEnemigos()[numPersonaje].moverPersonaje(tab,
                0,
                1,
                bot.getEnemigos(),
                numPersonaje);
    }

    /**
     * Mueve el personaje hacia la derecha
     *
     * @param numPersonaje indice del personaje a accionar
     */
    public void moverPersonajeDerecha(int numPersonaje) {
        this.bot.getEnemigos()[numPersonaje].moverPersonaje(tab,
                1,
                0,
                bot.getEnemigos(),
                numPersonaje);
    }

    /**
     * Mueve el personaje hace la izquierda
     *
     * @param numPersonaje indice del personaje a accionar
     */
    public void moverPersonajeIzquierda(int numPersonaje) {
        this.bot.getEnemigos()[numPersonaje].moverPersonaje(tab,
                -1,
                0,
                bot.getEnemigos(),
                numPersonaje);
    }

    /**
     * Metodo para cambiar de personaje
     *
     * @param contMov los movimientos que el jugador tiene.
     * @param fr JuegoFrame para registrar los moviminetos del jugador
     */
    public void cambioPersonaje(int contMov, JuegoFrame fr) {
        if (usuario.comprobarCambioPersonaje()) {
            usuario.aplicarCambioPersonaje();
            tab.getPlayerCell().setJugable(usuario.getJugablesEnJuego()[0]);
            tab.getPlayerCell().modificarImage();
            contMov = 0;
            fr.modificarTxtArea("Ha cambiado de jugador");
            cambiarTurno();
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Debes tener mas de un jugador vivo");
        }
    }

    /**
     * Metodo parra posicionar al jugador dentro del mapa y ubicar a los
     * enemigos de manera aleatoria dentro del mapa
     */
    public void colocarPersonajes() {
        this.tab.colocarJugador();
        this.tab.posicionarEnemigos();
    }

    public void llenarVidaPersonajes() {
        for (Jugable jugablesEnJuego : usuario.getJugablesEnJuego()) {
            jugablesEnJuego.revivir();
        }
    }

    public JugadorUs getUsuario() {
        return usuario;
    }

    public IA getBot() {
        return bot;
    }

    public Tablero getTab() {
        return tab;
    }

    public void setTab(Tablero tab) {
        this.tab = tab;
    }

    public boolean isTurno() {
        return turno;
    }

}
