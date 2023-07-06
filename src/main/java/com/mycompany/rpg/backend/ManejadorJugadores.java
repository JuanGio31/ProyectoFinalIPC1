package com.mycompany.rpg.backend;

import com.mycompany.rpg.backend.util.ListaException;
import com.mycompany.rpg.backend.util.Listado;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author giovanic Clase que sirve para agregar jugadores, y contiene toda la
 * informacion sobre ellos
 */
public class ManejadorJugadores implements Serializable {

    private Listado<JugadorUs> jugadores;

    public ManejadorJugadores() {
        this.jugadores = new Listado<>();
    }

    public void registrarJugador(JugadorUs jg) {
        if (jg != null) {
            this.jugadores.agregarElemento(jg);
        } else {
            JOptionPane msg = new JOptionPane("No se pudo agregar al usuario",
                    JOptionPane.ERROR_MESSAGE);
            JDialog dlg = msg.createDialog("Advertencia");
            dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1300);
                    } catch (InterruptedException ignored) {
                    }
                    dlg.setVisible(false);
                }
            }).start();
            dlg.setVisible(true);
            dlg.dispose();
        }
    }

    public Listado<JugadorUs> obtenerListadoJugadores() {
        return jugadores;
    }

    public JugadorUs obtenerJugador(int id) {
        try {
            return jugadores.obtenerElemento(id);
        } catch (ListaException ex) {
            System.out.println("error -> " + ex.getMessage());
        }
        return null;
    }

    public void modificarJugador(int indice, JugadorUs jugador) {
        try {
            jugadores.modificarContenido(indice, jugador);
        } catch (ListaException ex) {
            System.out.println("error -> " + ex.getMessage());
        }
    }

    public int obtenerTamanio() {
        return jugadores.getSize();
    }
}
