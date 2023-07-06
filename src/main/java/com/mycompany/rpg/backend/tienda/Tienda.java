package com.mycompany.rpg.backend.tienda;

import com.mycompany.rpg.backend.JugadorUs;
import com.mycompany.rpg.backend.personaje.jugable.Jugable;
import com.mycompany.rpg.backend.tienda.item.Item;
import com.mycompany.rpg.backend.tienda.mejora.Mejora;
import com.mycompany.rpg.backend.util.Listado;

import javax.swing.JOptionPane;

/**
 * @author giovanic Clase que contiene la informacion de personajes, objetos y
 * mejoras para su compra
 */
public class Tienda {

    private JugadorUs jg;
    private Listado<Jugable> prjComprado;
    private Listado<Item> objetos;
    private Listado<Mejora> mejoras;
    private int oro;

    public Tienda(JugadorUs jg) {
        this.jg = jg;
        this.oro = jg.getOro();
        prjComprado = jg.getInventario().getJugables();
        mejoras = jg.getInventario().getMejoras();
        objetos = jg.getInventario().getItems();
    }

    public Tienda() {
        this.oro = 500;
    }

    public int getOro() {
        return oro;
    }

    public void setOro(int oro) {
        this.oro = oro;
    }

    public void comprarPersonajeJugable(int opcion) {
        boolean compraInvalida = true;

        switch (opcion) {
            case 1 -> {
                if (getOro() < Jugable.COSTO_PERSONAJE) {
                    viewMsjError("No tiene suficiente oro para comprar personajes");
                } else {
                    prjComprado.agregarElemento(new Jugable().crearPersonajeJugable(1));
                    setOro(getOro() - Jugable.COSTO_PERSONAJE);
                    compraInvalida = false;
                }
            }
            case 2 -> {
                if (getOro() < Jugable.COSTO_PERSONAJE) {
                    viewMsjError("No tiene suficiente oro para comprar personajes");
                } else {
                    prjComprado.agregarElemento(new Jugable().crearPersonajeJugable(2));
                    setOro(getOro() - Jugable.COSTO_PERSONAJE);
                    compraInvalida = false;
                }
            }
            case 3 -> {
                if (getOro() < Jugable.COSTO_PERSONAJE) {
                    viewMsjError("No tiene suficiente oro para comprar personajes");
                } else {
                    prjComprado.agregarElemento(new Jugable().crearPersonajeJugable(3));
                    setOro(getOro() - 200);
                    compraInvalida = false;
                }
            }
            case 4 -> {
                if (getOro() < Jugable.COSTO_PERSONAJE) {
                    viewMsjError("No tiene suficiente oro para comprar personajes");
                } else {
                    prjComprado.agregarElemento(new Jugable().crearPersonajeJugable(4));
                    setOro(getOro() - Jugable.COSTO_PERSONAJE);
                    compraInvalida = false;
                }
            }
            case 5 -> {
                if (getOro() < Jugable.COSTO_PERSONAJE) {
                    viewMsjError("No tiene suficiente oro para comprar personajes");
                } else {
                    prjComprado.agregarElemento(new Jugable().crearPersonajeJugable(5));
                    setOro(getOro() - Jugable.COSTO_PERSONAJE);
                    compraInvalida = false;
                }
            }
        }

        if (!compraInvalida) {
            jg.setOro(oro);
            jg.getInventario().setJugables(prjComprado);
        }
    }

    private void viewMsjError(String msj) {
        JOptionPane.showMessageDialog(null,
                msj,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    public void comprarMejoras(int opcion) {
        Mejora[] a = crearMejoras();
        boolean compraInvalida = true;

// a[0] -> 80, a[1] -> 125, a[2] -> 500
        switch (opcion) {
            case 1 -> {
                if (getOro() < a[0].getPrecio()) {
                    viewMsjError("No tiene suficiente oro para comprar mejoras");
                } else {
                    mejoras.agregarElemento(a[0]);
                    setOro(getOro() - a[0].getPrecio());
                    compraInvalida = false;
                }
            }
            case 2 -> {
                if (getOro() < a[1].getPrecio()) {
                    viewMsjError("No tiene suficiente oro para comprar mejoras");
                } else {
                    mejoras.agregarElemento(a[1]);
                    setOro(getOro() - a[1].getPrecio());
                    compraInvalida = false;
                }
            }
            case 3 -> {
                if (getOro() < a[2].getPrecio()) {
                    viewMsjError("No tiene suficiente oro para comprar mejoras");
                } else {
                    mejoras.agregarElemento(a[2]);
                    setOro(getOro() - a[2].getPrecio());
                    compraInvalida = false;
                }
            }
        }

        if (!compraInvalida) {
            jg.setOro(oro);
            jg.getInventario().setMejoras(mejoras);
            //jg.setMejoras(mejoras);
        }
    }

    private Mejora[] crearMejoras() {
        Mejora[] matrix = new Mejora[3];
        Mejora mej = new Mejora();
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = mej.crearMejora(i + 1);
        }
        return matrix;
    }

    public void comprarObjetos(int opcion) {
        Item[] a = crearItems();
        boolean compraInvalida = true;

        switch (opcion) {
            case 1 -> {
                if (getOro() < a[0].getPrecio()) {
                    viewMsjError("No tiene suficiente oro para comprar mas objetos");
                } else {
                    objetos.agregarElemento(a[0]);
                    setOro(getOro() - a[0].getPrecio());
                    compraInvalida = false;
                }
            }
            case 2 -> {
                if (getOro() < a[1].getPrecio()) {
                    viewMsjError("No tiene suficiente oro para comprar mas objetos");
                } else {
                    objetos.agregarElemento(a[1]);
                    setOro(getOro() - a[1].getPrecio());
                    compraInvalida = false;
                }
            }
            case 3 -> {
                if (getOro() < a[2].getPrecio()) {
                    viewMsjError("No tiene suficiente oro para comprar mas objetos");
                } else {
                    objetos.agregarElemento(a[2]);
                    setOro(getOro() - a[2].getPrecio());
                    compraInvalida = false;
                }
            }
        }

        if (!compraInvalida) {
            jg.setOro(oro);
            jg.getInventario().setItems(objetos);
            //jg.setItems(objetos);
        }
    }

    private Item[] crearItems() {
        Item[] matrix = new Item[3];
        Item objeto = new Item();

        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = objeto.crearItem(i + 1);
        }
        return matrix;
    }

    public JugadorUs getJg() {
        return jg;
    }

    public void setJg(JugadorUs jg) {
        this.jg = jg;
    }
}
