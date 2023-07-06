package com.mycompany.rpg.backend.tablero;

import java.io.Serializable;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author giovanic
 */
public class CreadorTablero implements Serializable {

    private final Random rand = new Random();

    private String identificador;
    private int filas;
    private int columnas;
    private String[][] cell;

    public CreadorTablero(String identificador, int filas, int columnas, String[][] cell) {
        this.identificador = identificador;
        this.filas = filas;
        this.columnas = columnas;
        this.cell = cell;
    }

    public CreadorTablero() {
    }

    /**
     * *Metodo para crear tableros a partir de los porcentajes de terrenos que
     * el usuario decida, asi com numero de filas, columnas y el nombre que este
     * recibira.
     *
     * @param col recibe el numero de columnas
     * @param rows recibe el tamaño de filas
     * @param planicie recibe el porcentaje del terreno planicie
     * @param arbol recibe el porcentaje del terreno arbol
     * @param agua recibe el porcentaje del terreno agua
     * @param lava recibe el porcentaje del terreno lava
     * @param id nombre con el cual se identificara el tablero
     * @return tablero con los porcentajes del terreno recibidos
     */
    public CreadorTablero generarTablero(int col, int rows, int planicie, int arbol, int agua, int lava, String id) {
        String[][] matrix = new String[rows][col];
        int totalCantidadCasillas = rows * col;

        // calcular la cantidad de casillas de cada tipo para el tablero.
        int numeroCasillasPlanicie = Math.round(totalCantidadCasillas * planicie / 100);
        int numeroCasillasArbol = Math.round(totalCantidadCasillas * arbol / 100);
        int numeroCasillasAgua = Math.round(totalCantidadCasillas * agua / 100);
        int numeroCasillasLava = Math.round(totalCantidadCasillas * lava / 100);

        if (numeroCasillasAgua + numeroCasillasArbol + numeroCasillasLava
                + numeroCasillasPlanicie != totalCantidadCasillas) {
            numeroCasillasPlanicie = totalCantidadCasillas - numeroCasillasAgua - numeroCasillasArbol - numeroCasillasLava;
        }

        int sum = planicie + arbol + agua + lava;
        if (sum == 100) {
            // int sumCasillasPlanicieAgua = numeroCasillasPlanicie + numeroCasillasAgua;
            while (numeroCasillasPlanicie > 1) {
                for (String[] cell1 : matrix) {
                    for (int j = 0; j < matrix[0].length; j++) {
                        int random = rand.nextInt(3);
                        if (random == 0) {
                            cell1[j] = "-";
                            numeroCasillasPlanicie--;
                        }
                    }
                }
            }
            while (numeroCasillasArbol > 1) {
                for (String[] cell1 : matrix) {
                    for (int j = 0; j < matrix[0].length; j++) {
                        if (cell1[j] == null) {
                            int random = rand.nextInt(2);
                            if (random == 1) {
                                cell1[j] = "T";
                                numeroCasillasArbol--;
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (i == 0 || i == matrix.length - 1 || j == 0 || j == matrix[i].length - 1) {
                        int rd = rand.nextInt(2);
                        if (numeroCasillasAgua > 0) {
                            if (rd == 0 && (matrix[i][j] == null || matrix[i][j].equals("-"))) {
                                matrix[i][j] = "~";
                                numeroCasillasAgua--;
                            }
                        }
                    }
                }
            }

            if (numeroCasillasLava > 1) {
                for (String[] cell1 : matrix) {
                    for (int j = 0; j < matrix[0].length; j++) {
                        if (numeroCasillasLava > 0) {
                            if (cell1[j] == null) {
                                cell1[j] = "#";
                                numeroCasillasLava--;
                            }
                        }
                    }
                }
            }

            for (String[] cell1 : matrix) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (cell1[j] == null) {
                        cell1[j] = "-";
                    }
                }
            }
            return new CreadorTablero(id, rows, col, matrix);
        } else {
            JOptionPane.showMessageDialog(null,
                    "La suma de cada porcentaje de terreno debe ser 100!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public String[][] getCell() {
        return cell;
    }

    public void setCell(String[][] cell) {
        this.cell = cell;
    }

}
