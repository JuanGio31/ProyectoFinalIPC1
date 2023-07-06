package com.mycompany.rpg.frontend;

import com.mycompany.rpg.backend.tablero.*;
import com.mycompany.rpg.backend.tablero.casilla.Casilla;
import com.mycompany.rpg.backend.util.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author giovanic Clase que tiene la infromacion de los tableros cargados, asi
 * como tambien tiene la posibilidad de cargar mapas y generarlos para despues
 * guardarlos
 */
public class GenTablero extends javax.swing.JDialog {

    private Listado<CreadorTablero> tabs;
    private CreadorTablero tableroTemporal = null;
    private final ControladorArchivos controladorArchivos;

    /**
     * Creates new form GenTablero
     *
     * @param parent frame duenio
     * @param tabs listado de tableros
     * @param controladorArchivos
     */
    public GenTablero(java.awt.Frame parent, Listado<CreadorTablero> tabs, ControladorArchivos controladorArchivos) {
        super(parent, true);
        initComponents();
        this.tabs = tabs;
        this.controladorArchivos = controladorArchivos;
        llenarComboBox();
        genMapPnl.setLocation(5, 5);

        colorPanel();
    }

    private void colorPanel() {
        this.pack();
        ColorPanel pVista = new ColorPanel(Color.cyan, Color.magenta);
        pVista.setSize(this.getSize());
        vistaPreviaPnl.add(pVista);
        ColorPanel p2Vista = new ColorPanel(Color.cyan, Color.white);
        p2Vista.setSize(this.getSize());
        crearTabPanel.add(p2Vista);
    }

    private void llenarComboBox() {
        if (!tabs.estaVacia()) {
            for (int i = 0; i < tabs.getSize(); i++) {
                try {
                    listMap.addItem(tabs.obtenerElemento(i).getIdentificador());
                } catch (ListaException ex) {
                    Logger.getLogger(GenTablero.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        vistaPreviaPnl = new javax.swing.JPanel();
        btnVer = new javax.swing.JButton();
        btnCargar = new javax.swing.JButton();
        listMap = new javax.swing.JComboBox<>();
        mapPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        crearTabPanel = new javax.swing.JPanel();
        inputFila = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        inputCol = new javax.swing.JTextField();
        inputPlanicie = new javax.swing.JTextField();
        inputArbol = new javax.swing.JTextField();
        inputAgua = new javax.swing.JTextField();
        inputLava = new javax.swing.JTextField();
        genMapPnl = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnLaunch = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        inputId = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnVer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ver.png"))); // NOI18N
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerActionPerformed(evt);
            }
        });

        btnCargar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/subir.png"))); // NOI18N
        btnCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarActionPerformed(evt);
            }
        });

        listMap.setMaximumSize(new java.awt.Dimension(32767, 34));
        listMap.setMinimumSize(new java.awt.Dimension(76, 34));
        listMap.setPreferredSize(new java.awt.Dimension(76, 34));

        mapPanel.setLayout(new java.awt.GridLayout(1, 0));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mayorMenor.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/menorMayor.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout vistaPreviaPnlLayout = new javax.swing.GroupLayout(vistaPreviaPnl);
        vistaPreviaPnl.setLayout(vistaPreviaPnlLayout);
        vistaPreviaPnlLayout.setHorizontalGroup(
            vistaPreviaPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vistaPreviaPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(vistaPreviaPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mapPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                    .addGroup(vistaPreviaPnlLayout.createSequentialGroup()
                        .addComponent(listMap, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btnVer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(0, 13, Short.MAX_VALUE)))
                .addContainerGap(201, Short.MAX_VALUE))
        );
        vistaPreviaPnlLayout.setVerticalGroup(
            vistaPreviaPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vistaPreviaPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(vistaPreviaPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(vistaPreviaPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnVer, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCargar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(listMap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(mapPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(215, 215, 215))
        );

        jTabbedPane1.addTab("Vista Previa", vistaPreviaPnl);

        jLabel1.setText("filas");

        jLabel2.setText("Columnas");

        jLabel3.setText("Planicie");

        jLabel4.setText("Arbol");

        jLabel5.setText("Agua");

        jLabel6.setText("Lava");

        genMapPnl.setLayout(new java.awt.GridLayout(1, 0));

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/save.png"))); // NOI18N
        btnSave.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnSave.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnLaunch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/crear.png"))); // NOI18N
        btnLaunch.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        btnLaunch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaunchActionPerformed(evt);
            }
        });

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/editar.png"))); // NOI18N
        btnEdit.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        jLabel7.setText("Nombre");

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mas.png"))); // NOI18N
        btnAgregar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnAgregar.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout crearTabPanelLayout = new javax.swing.GroupLayout(crearTabPanel);
        crearTabPanel.setLayout(crearTabPanelLayout);
        crearTabPanelLayout.setHorizontalGroup(
            crearTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crearTabPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(crearTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(crearTabPanelLayout.createSequentialGroup()
                        .addGroup(crearTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(inputFila, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(crearTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inputCol, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(crearTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(inputPlanicie, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(crearTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inputArbol, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(crearTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inputAgua, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(crearTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(inputLava, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(crearTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inputId, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addGroup(crearTabPanelLayout.createSequentialGroup()
                        .addComponent(genMapPnl, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(crearTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEdit)
                    .addComponent(btnLaunch)
                    .addComponent(btnSave)
                    .addComponent(btnAgregar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        crearTabPanelLayout.setVerticalGroup(
            crearTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crearTabPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(crearTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(crearTabPanelLayout.createSequentialGroup()
                        .addGroup(crearTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(crearTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inputFila, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inputCol, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inputPlanicie, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inputArbol, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inputAgua, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inputLava, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inputId, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(genMapPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(194, Short.MAX_VALUE))
                    .addGroup(crearTabPanelLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(btnLaunch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEdit)
                        .addGap(0, 109, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Crear mapa", crearTabPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed
        initMap();
    }//GEN-LAST:event_btnVerActionPerformed

    private void btnCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarActionPerformed
        crearNuevoTablero();
        listMap.removeAllItems();
        llenarComboBox();
    }//GEN-LAST:event_btnCargarActionPerformed

    public void crearNuevoTablero() {
        Listado<CreadorTablero> map = controladorArchivos.crearTablero();
        for (int i = 0; i < map.getSize(); i++) {
            try {
                this.tabs.agregarElemento(map.obtenerElemento(i));
            } catch (ListaException ex) {
                System.out.println("error -> " + ex.getMessage());
            }
        }

        listMap.removeAllItems();
        llenarComboBox();
    }

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        inputArbol.setText("");
        inputAgua.setText("");
        inputCol.setText("");
        inputFila.setText("");
        inputLava.setText("");
        inputPlanicie.setText("");
        inputId.setText("");

        limpiarTablero(genMapPnl);
        this.pack();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        guardar();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnLaunchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaunchActionPerformed
        //limpiarTablero(genMapPnl);
        try {
            int fila = Integer.parseInt(inputFila.getText().trim());
            int columna = Integer.parseInt(inputCol.getText().trim());
            int planicie = Integer.parseInt(inputPlanicie.getText().trim());
            int arbol = Integer.parseInt(inputArbol.getText().trim());
            int agua = Integer.parseInt(inputAgua.getText().trim());
            int lava = Integer.parseInt(inputLava.getText().trim());

            CreadorTablero tab = new CreadorTablero();
            tableroTemporal = tab.generarTablero(columna, fila, planicie, arbol, agua, lava, inputId.getText());
            if (tableroTemporal != null) {
                this.paintMap(new Tablero(tableroTemporal), genMapPnl);

            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Debe ingresar numeros",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            this.pack();
            colorPanel();
        }
    }//GEN-LAST:event_btnLaunchActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        if (tableroTemporal != null) {
            tabs.agregarElemento(tableroTemporal);
            listMap.removeAllItems();
            llenarComboBox();

            JOptionPane.showMessageDialog(
                    null,
                    "Se a agregado \""
                    + tableroTemporal.getIdentificador()
                    + "\" a la lista de mapas!"
            );
        } else {
            JOptionPane.showMessageDialog(null,
                    "Primero debe crear un tablero",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // MayorMenor
        ordenarTableroMayor();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // MenorMayor
        ordenarTableroMenor();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void guardar() {
        if (tableroTemporal != null) {
            controladorArchivos.escribirFicheroDeTablero(tableroTemporal);
            //System.out.println(data);
            JOptionPane.showMessageDialog(
                    null,
                    "Se ha guardado con exito!"
            );
        } else {
            JOptionPane.showMessageDialog(null,
                    "No hay tablero que guardar",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initMap() {
        CreadorTablero tb = null;
        if (listMap.getSelectedIndex() != -1) {
            try {
                tb = tabs.obtenerElemento(listMap.getSelectedIndex());
            } catch (ListaException ex) {
                System.out.println("error -> " + ex.getMessage());
            }

            if (tb != null) {
                this.paintMap(new Tablero(tb), mapPanel);
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "No se seleccionó un mapa valido",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void paintMap(Tablero mp, JPanel panel) {
        limpiarTablero(panel);
        GridLayout gl = (GridLayout) panel.getLayout();
        gl.setColumns(mp.getCasillas()[0].length);
        gl.setRows(mp.getCasillas().length);
        gl.setHgap(2);
        gl.setVgap(2);
        for (Casilla[] casilla : mp.getCasillas()) {
            for (int j = 0; j < mp.getCasillas()[0].length; j++) {
                panel.add(casilla[j]);
            }
        }

        panel.validate();
        panel.repaint();
        colorPanel();
    }

    private void limpiarTablero(JPanel panel) {
        panel.removeAll();
        panel.validate();
        panel.repaint();
    }

    /**
     * Metodo para mostrar un mensaje de error durante x-periodo de tiempo
     */
    private void mostrarAlerta() {
        JOptionPane msg = new JOptionPane("No hay datos que ordenar, debe al menos 2 tableros",
                JOptionPane.ERROR_MESSAGE);
        JDialog dlg = msg.createDialog("Advertencia");
        dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1250);
                } catch (InterruptedException e) {
                }
                dlg.setVisible(false);
            }
        }).start();
        dlg.setVisible(true);
        dlg.dispose();
    }

    private void ordenarTableroMenor() {
        if (tabs.getSize() > 1) {
            CreadorTablero[] mp = new CreadorTablero[tabs.getSize()];
            for (int i = 0; i < mp.length; i++) {
                try {
                    mp[i] = tabs.obtenerElemento(i);
                } catch (ListaException ex) {
                    System.out.println("error -> " + ex.getMessage());
                }
            }

            BubbleSort.ordenarMenorMayorTablero(mp);

            // Creamos una nueva lista.
            tabs = new Listado<>();

            for (CreadorTablero mp1 : mp) {
                tabs.agregarElemento(mp1);
            }

            listMap.removeAllItems();
            llenarComboBox();
        } else {
            mostrarAlerta();
        }
    }

    private void ordenarTableroMayor() {
        if (tabs.getSize() > 1) {
            CreadorTablero[] mp = new CreadorTablero[tabs.getSize()];
            for (int i = 0; i < mp.length; i++) {
                try {
                    mp[i] = tabs.obtenerElemento(i);
                } catch (ListaException ex) {
                    System.out.println("error -> " + ex.getMessage());
                }
            }

            BubbleSort.ordenarMayorMenorTablero(mp);
            tabs = new Listado<>();

            for (CreadorTablero mp1 : mp) {
                tabs.agregarElemento(mp1);
            }

            listMap.removeAllItems();
            llenarComboBox();
        } else {
            mostrarAlerta();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCargar;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnLaunch;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnVer;
    private javax.swing.JPanel crearTabPanel;
    private javax.swing.JPanel genMapPnl;
    private javax.swing.JTextField inputAgua;
    private javax.swing.JTextField inputArbol;
    private javax.swing.JTextField inputCol;
    private javax.swing.JTextField inputFila;
    private javax.swing.JTextField inputId;
    private javax.swing.JTextField inputLava;
    private javax.swing.JTextField inputPlanicie;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JComboBox<String> listMap;
    private javax.swing.JPanel mapPanel;
    private javax.swing.JPanel vistaPreviaPnl;
    // End of variables declaration//GEN-END:variables
}