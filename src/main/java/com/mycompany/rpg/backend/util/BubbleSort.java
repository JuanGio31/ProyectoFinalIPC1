package com.mycompany.rpg.backend.util;

import com.mycompany.rpg.backend.JugadorUs;
import com.mycompany.rpg.backend.tablero.CreadorTablero;

/**
 *
 * @author giovanic
 */
public class BubbleSort {

    public BubbleSort() {
    }

    public static void ordenarMenorMayorPuntuacion(JugadorUs[] arr) {
        JugadorUs tmp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i].getPuntuacion() > arr[j].getPuntuacion()) {
                    tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }

    public static void ordenarMayorMenorPuntuacion(JugadorUs[] arr) {
        JugadorUs tmp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i].getPuntuacion() < arr[j].getPuntuacion()) {
                    tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }

    public static void ordenarMayorMenorTablero(CreadorTablero[] arr) {
        CreadorTablero tmp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i].getColumnas() * arr[i].getFilas() < arr[j].getColumnas() * arr[j].getFilas()) {
                    tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }

    public static void ordenarMenorMayorTablero(CreadorTablero[] arr) {
        CreadorTablero tmp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i].getColumnas() * arr[i].getFilas() > arr[j].getColumnas() * arr[j].getFilas()) {
                    tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }
}
