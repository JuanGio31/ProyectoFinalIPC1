package com.mycompany.rpg.frontend;

import com.mycompany.rpg.backend.JugadorUs;
import com.mycompany.rpg.backend.ManejadorJugadores;
import com.mycompany.rpg.backend.util.BubbleSort;
import java.awt.Frame;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author giovanic 
 * Clase que almacena la informacion de puntajes
 */
public class ScoreDialog extends javax.swing.JDialog {

    private ManejadorJugadores manejadorJugadores;
    private DefaultTableModel modelo;

    /**
     * Creates new form ScoreDialog
     *
     * @param parent frame que lo invoca
     * @param manejadorJugadores listado de usuarios para mostrar su puntaje
     */
    public ScoreDialog(Frame parent, ManejadorJugadores manejadorJugadores) {
        super(parent, true);
        initComponents();
        this.manejadorJugadores = manejadorJugadores;

        setLocationRelativeTo(null);
        setTitle("Ranking");
        loadTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        scoresTable = new javax.swing.JTable();
        btnMayorMenor = new javax.swing.JButton();
        btnMenorMayor = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(452, 402));

        scoresTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        jScrollPane1.setViewportView(scoresTable);

        btnMayorMenor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mayorMenor.png"))); // NOI18N
        btnMayorMenor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMayorMenorActionPerformed(evt);
            }
        });

        btnMenorMayor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/menorMayor.png"))); // NOI18N
        btnMenorMayor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenorMayorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMenorMayor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnMayorMenor)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMayorMenor)
                    .addComponent(btnMenorMayor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMenorMayorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenorMayorActionPerformed
        if (manejadorJugadores.obtenerTamanio() > 0) {
            JugadorUs[] jug = new JugadorUs[manejadorJugadores.obtenerTamanio()];
            for (int i = 0; i < jug.length; i++) {
                jug[i] = manejadorJugadores.obtenerJugador(i);
            }
            BubbleSort.ordenarMenorMayorPuntuacion(jug);
            loadTable(jug);
        } else {
            mostrarAlerta();
        }
    }//GEN-LAST:event_btnMenorMayorActionPerformed

    private void btnMayorMenorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMayorMenorActionPerformed
        if (manejadorJugadores.obtenerTamanio() > 0) {
            JugadorUs[] jug = new JugadorUs[manejadorJugadores.obtenerTamanio()];
            for (int i = 0; i < jug.length; i++) {
                jug[i] = manejadorJugadores.obtenerJugador(i);
            }
            BubbleSort.ordenarMayorMenorPuntuacion(jug);
            loadTable(jug);
        } else {
            mostrarAlerta();
        }
    }//GEN-LAST:event_btnMayorMenorActionPerformed

    private void loadTable() {
        String[] nombreColumnas = new String[]{"Id", "Jugador", "Puntaje"};
        modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(nombreColumnas);

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        for (int i = 0; i < manejadorJugadores.obtenerTamanio(); i++) {
            Object[] tmp = new Object[3];
            tmp[0] = i;
            tmp[1] = manejadorJugadores.obtenerJugador(i).getNombre();
            tmp[2] = manejadorJugadores.obtenerJugador(i).getPuntuacion();
            modelo.addRow(tmp);
        }
        scoresTable.setModel(modelo);
    }

    private void loadTable(JugadorUs[] jug) {
        String[] nombreColumnas = new String[]{"Id", "Jugador", "Puntaje"};
        modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(nombreColumnas);

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        for (int i = 0; i < jug.length; i++) {
            Object[] tmp = new Object[3];
            tmp[0] = i;
            tmp[1] = jug[i].getNombre();
            tmp[2] = jug[i].getPuntuacion();
            modelo.addRow(tmp);
        }
        scoresTable.setModel(modelo);
    }

    private void mostrarAlerta() {
        JOptionPane msg = new JOptionPane("No hay datos que ordenar",
                JOptionPane.ERROR_MESSAGE);
        JDialog dlg = msg.createDialog("Advertencia");
        dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1200);
                } catch (InterruptedException e) {
                }
                dlg.setVisible(false);
            }
        }).start();
        dlg.setVisible(true);
        dlg.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMayorMenor;
    private javax.swing.JButton btnMenorMayor;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable scoresTable;
    // End of variables declaration//GEN-END:variables
}
