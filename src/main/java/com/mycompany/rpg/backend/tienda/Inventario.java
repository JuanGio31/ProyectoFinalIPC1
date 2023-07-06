package com.mycompany.rpg.backend.tienda;

import com.mycompany.rpg.backend.personaje.jugable.Jugable;
import com.mycompany.rpg.backend.tienda.item.Item;
import com.mycompany.rpg.backend.tienda.item.SemillaDeLaVida;
import com.mycompany.rpg.backend.tienda.mejora.Mejora;
import com.mycompany.rpg.backend.util.ListaException;
import com.mycompany.rpg.backend.util.Listado;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author giovanic Clase que contiene la informacion de las compras realizadas
 */
public class Inventario implements Serializable {

    private Listado<Mejora> mejoras;
    private Listado<Item> items;
    private Listado<Jugable> jugables;

    public Inventario() {
        mejoras = new Listado<>();
        items = new Listado<>();
        jugables = new Listado<>();
    }

    public boolean existeItemSemillaDeLaVida() {
        if (items != null && !items.estaVacia()) {
            for (int i = 0; i < items.getSize(); i++) {
                try {
                    if (items.obtenerElemento(i) instanceof SemillaDeLaVida) {
                        return true;
                    }
                } catch (ListaException ex) {
                    System.out.println("error -> " + ex.getMessage());
                }
            }
        }
        return false;
    }

    public Listado<Mejora> getMejoras() {
        return mejoras;
    }

    public void setMejoras(Listado<Mejora> mejoras) {
        this.mejoras = mejoras;
    }

    public Listado<Item> getItems() {
        return items;
    }

    public void setItems(Listado<Item> items) {
        this.items = items;
    }

    public Listado<Jugable> getJugables() {
        return jugables;
    }

    public void setJugables(Listado<Jugable> jugables) {
        this.jugables = jugables;
    }

}
