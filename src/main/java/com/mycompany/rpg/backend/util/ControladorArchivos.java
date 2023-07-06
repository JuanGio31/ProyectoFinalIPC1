package com.mycompany.rpg.backend.util;

import com.mycompany.rpg.backend.*;
import com.mycompany.rpg.backend.Partida;
import com.mycompany.rpg.backend.tablero.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Clase que gestiona todos los ficheros, ya sea para leerlos o escribirlos
 *
 * @author giovanic
 */
public class ControladorArchivos {

    private int tablerosRepetidos = 0;

    /**
     * Metodo constructor
     */
    public ControladorArchivos() {
    }

    /**
     * Metodo para leer varios tableros al mismo tiempo a partir de un archivo
     * de texto
     *
     * @return devuelve una lista de tableros
     */
    public Listado<CreadorTablero> crearTablero() {
        Listado<CreadorTablero> tab = new Listado<>();
        try {
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.txt", "txt");
            File myObj = seleccionarArchivo(filtro);
            Scanner myReader = new Scanner(myObj);

            boolean nombreEncontrado = false;
            boolean finLecturaCasilla;
            String str = null;
            String[] dim = new String[2];
            String[][] tmp = null;
            int i = 0;
            while (myReader.hasNextLine()) {
                CreadorTablero tablero = new CreadorTablero();
                String linea = myReader.nextLine();

                if (linea.contains("tablero")) {
                    str = linea.replaceAll("tablero", "").trim();
                    nombreEncontrado = true;
                } else if (linea.contains("dimension")) {
                    // x X y -> Columna X Fila
                    String tam = linea.replaceAll("dimension", "").trim();
                    dim = tam.split("X");
                    tmp = new String[Integer.parseInt(dim[1])][Integer.parseInt(dim[0])];
                } else {
                    // Inicializar valores del objeto tablero
                    if (str != null) {
                        tablero.setIdentificador(str);
                        tablero.setFilas(Integer.parseInt(dim[1]));
                        tablero.setColumnas(Integer.parseInt(dim[0]));
                    }
                    String[] valorLinea = linea.split(",");
                    for (int columnas = 0; columnas < Integer.parseInt(dim[0]); columnas++) {
                        switch (valorLinea[columnas]) {
                            case "-" ->
                                tmp[i][columnas] = valorLinea[columnas];
                            case "~" ->
                                tmp[i][columnas] = valorLinea[columnas];
                            case "#" ->
                                tmp[i][columnas] = valorLinea[columnas];
                            case "T" ->
                                tmp[i][columnas] = valorLinea[columnas];
                        }
                    }

                    // Si i es igual al tamanio de la fila menos 1
                    if (i == Integer.parseInt(dim[1]) - 1) {
                        i = 0;
                        nombreEncontrado = false;
                        finLecturaCasilla = true;
                        if (finLecturaCasilla) {
                            llenarCasillaVacia(tmp);
                            tablero.setCell(tmp);

                            if (tab.getSize() > 1) {
                                boolean repetido = false;
                                for (int index = 0; index < tab.getSize(); index++) {
                                    if (tab.obtenerElemento(index).getIdentificador().equalsIgnoreCase(tablero.getIdentificador())) {
                                        tablerosRepetidos++;
                                        repetido = true;
                                    }
                                }
                                if (!repetido) {
                                    tab.agregarElemento(tablero);
                                }
                            } else {
                                tab.agregarElemento(tablero);
                            }
                            finLecturaCasilla = false;
                        }
                    } else {
                        if (nombreEncontrado) {
                            i++;
                        }
                    }
                }
            }
        } catch (FileNotFoundException | NullPointerException e) {
            System.out.println("error -> No se pudo leer el archivo");
        } catch (ListaException ex) {
            System.out.println("error ->" + ex.getMessage());
        }

        tablerosRepetidos = 0;
        return tab;
    }

    /**
     * Metodo para obtener un file
     *
     * @param filtro la extension predeterminada que se muestra en el
     * filechooser
     * @return File
     * @throws FileNotFoundException
     */
    public File seleccionarArchivo(FileNameExtensionFilter filtro) throws FileNotFoundException {
        JFileChooser fileChooser = new JFileChooser("src");
        fileChooser.setFileFilter(filtro);
        int respuesta = fileChooser.showOpenDialog(null);
        switch (respuesta) {
            case JFileChooser.APPROVE_OPTION -> {
                return fileChooser.getSelectedFile();
            }
            case JFileChooser.CANCEL_OPTION, JFileChooser.ERROR_OPTION -> {
                return null;
            }
        }
        return null;
    }

    /**
     * Metodo para llenar las casillas que esten vacias
     *
     * @param cells
     */
    private void llenarCasillaVacia(String[][] cells) {
        for (String[] cell : cells) {
            for (int j = 0; j < cell.length; j++) {
                if (cell[j] == null) {
                    cell[j] = "-";
                }
            }
        }
    }

    public void mostrarRepetido(int repetidos) {
        if (repetidos > 0) {
            JOptionPane msg = new JOptionPane("Se encontraron " + repetidos + " Tablero (s) repetidos.",
                    JOptionPane.WARNING_MESSAGE);
            JDialog dlg = msg.createDialog("Advertencia");
            dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    dlg.setVisible(false);
                }
            }).start();
            dlg.setVisible(true);
            dlg.dispose();
        }
    }

    public void guardarPartida(Partida partida, JugadorUs jg) {
        String nombre = JOptionPane.showInputDialog("Guardar como: ");
        if (nombre.length() < 1) {
            //mostrar un mensaje informativo
            JOptionPane msg = new JOptionPane("Debe ingresar por lo menos un caracter",
                    JOptionPane.WARNING_MESSAGE);
            JDialog dlg = msg.createDialog("Advertencia");
            dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    dlg.setVisible(false);
                }
            }).start();
            dlg.setVisible(true);
            dlg.dispose();
        } else {
            ObjectOutputStream escribiendoFichero = null;
            try {
                jg.setNombrePartidaGuardada(nombre + ".bin");
                escribiendoFichero = new ObjectOutputStream(new FileOutputStream("persistencia/partidas/" + nombre + ".bin"));
                escribiendoFichero.writeObject(partida);
                escribiendoFichero.close();

            } catch (IOException ex) {
                Logger.getLogger(ControladorArchivos.class
                        .getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    escribiendoFichero.close();

                } catch (IOException ex) {
                    Logger.getLogger(ControladorArchivos.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public Partida leerPartida(FileInputStream file) {
        Partida tmp = null;
        try {
            ObjectInputStream leer = new ObjectInputStream(file);
            tmp = (Partida) leer.readObject();

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ControladorArchivos.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return tmp;
    }

    public void guardarTableros(Listado<CreadorTablero> tableros) {
        ObjectOutputStream escribiendoFichero = null;
        try {
            escribiendoFichero = new ObjectOutputStream(new FileOutputStream("persistencia/tableros/ListaDeTableros.bin"));
            escribiendoFichero.writeObject(tableros);
            escribiendoFichero.close();

        } catch (IOException ex) {
            Logger.getLogger(ControladorArchivos.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                escribiendoFichero.close();

            } catch (IOException ex) {
                Logger.getLogger(ControladorArchivos.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Listado<CreadorTablero> leerLogTablero(FileInputStream file) {
        Listado<CreadorTablero> tmp = null;
        try {
            ObjectInputStream leer = new ObjectInputStream(file);
            tmp = (Listado<CreadorTablero>) leer.readObject();

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ControladorArchivos.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return tmp;
    }

    public ManejadorJugadores leerSesionJugadores(FileInputStream file) {
        ManejadorJugadores tmp = null;
        try {
            ObjectInputStream leer = new ObjectInputStream(file);
            tmp = (ManejadorJugadores) leer.readObject();

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ControladorArchivos.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return tmp;
    }

    public void guardarSesionJugadores(ManejadorJugadores manejadorJugadores) {
        ObjectOutputStream escribiendoFichero = null;
        try {
            escribiendoFichero = new ObjectOutputStream(new FileOutputStream("persistencia/login/ManejadorJugadores.bin"));
            escribiendoFichero.writeObject(manejadorJugadores);
            escribiendoFichero.close();

        } catch (IOException ex) {
            Logger.getLogger(ControladorArchivos.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                escribiendoFichero.close();

            } catch (IOException ex) {
                Logger.getLogger(ControladorArchivos.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Elimina los archivos con una determinada extensión de una carpeta
     *
     * @param path Carpeta de la cual eliminar los archivos
     * @param extension Extensión de los archivos a eliminar
     */
    public void eliminarPorExtension(String path, final String extension) {
        File[] archivos = new File(path).listFiles(new FileFilter() {
            @Override
            public boolean accept(File archivo) {
                if (archivo.isFile()) {
                    return archivo.getName().endsWith('.' + extension);
                }
                return false;
            }
        });
        for (File archivo : archivos) {
            archivo.delete();
        }
    }

    /**
     * Metodo para eliminar un archivo.
     *
     * @param file_name ruta del archivo y nombre
     */
    public void eliminarArchivo(String file_name) {
        Path path = Paths.get(file_name);
        try {
            boolean result = Files.deleteIfExists(path);
            if (result) {
                System.out.println("Archivo eliminado!");
            }
        } catch (IOException e) {
            System.out.println("error -> " + e);
        }
    }

    public boolean comprobarExistencia(String file_name) {
        File file = new File(file_name);
        return file.exists();
    }

    public void escribirFicheroDeTablero(CreadorTablero tableroTemporal) {
        String data;
        String[][] valorLinea = new String[tableroTemporal.getFilas()][tableroTemporal.getColumnas()];
        for (int i = 0; i < tableroTemporal.getFilas(); i++) {
            System.arraycopy(tableroTemporal.getCell()[i], 0, valorLinea[i], 0, tableroTemporal.getColumnas());
        }

        StringBuffer cadena = new StringBuffer();
        for (String[] valorLinea1 : valorLinea) {
            for (int index2 = 0; index2 < valorLinea[0].length; index2++) {
                cadena = cadena.append(valorLinea1[index2]).append((index2 < valorLinea[0].length - 1) ? "," : "");
            }
            cadena = cadena.append("\n");
        }

        data = "tablero " + tableroTemporal.getIdentificador() + "\ndimension "
                + tableroTemporal.getFilas() + "X" + tableroTemporal.getColumnas() + "\n" + cadena;

        final String RUTA_ARCHIVO_ESCRITURA = tableroTemporal.getIdentificador() + ".txt";
        try {
            File myObj = new File(RUTA_ARCHIVO_ESCRITURA);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred. " + e);
        }

        try {
            FileWriter fw = new FileWriter(RUTA_ARCHIVO_ESCRITURA, true);
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(data);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
