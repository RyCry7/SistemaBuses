/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.sistemabuses;

import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Asus
 */
public class ParadasFavoritas extends javax.swing.JFrame {

    /**
     * Creates new form ParadasFavoritas
     */
    public ParadasFavoritas() {
        initComponents();
        mostrarDatosParFavoritas();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        // Agregar el WindowListener para escuchar el evento de cierre del JFrame
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // Mostrar nuevamente el JFrame "Administrador" cuando se cierre "ParadasFavoritas"
                Administrador administrador = new Administrador();
                administrador.setVisible(true);
            }
        });
        setLocationRelativeTo(null);
         
        try {
            ImageIcon wallpaper = new ImageIcon("C:\\Users\\richi\\OneDrive\\Documentos\\NetBeansProjects\\SistemaBuses\\src\\main\\java\\Imagenes\\tablareporte.png");
            Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), Image.SCALE_DEFAULT));
            lblImagen.setIcon(icono);
            this.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
       
    }

    public void mostrarDatosParFavoritas() {
        //muestra en la secuencia que se encuentra ubicado en la tabla
        DefaultTableModel tenc1 = new DefaultTableModel();

        tenc1.addColumn("USUARIO");
        tenc1.addColumn("PARADA FAVORITA");
        tenc1.addColumn("UBICACION");

        tblFormulario.setModel(tenc1);
        String actualizar = "SELECT usuario.usu_nombre, paradas.par_nombre, ubicacion.UBI_Nombre FROM parfav JOIN usuario ON parfav.usu_cedula = usuario.usu_cedula JOIN paradas ON parfav.par_id = paradas.par_id JOIN ubicacion ON paradas.ubi_id = ubicacion.UBI_ID";

        String[] datos = new String[3];

        try {

            Conexion con = new Conexion();
            ResultSet resultado = con.EjecutarSQL(actualizar);
            while (resultado.next()) {
                datos[0] = resultado.getString(1);
                datos[1] = resultado.getString(2);
                datos[2] = resultado.getString(3);

                tenc1.addRow(datos);
            }
            tblFormulario.setModel(tenc1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR EN LA CONSULTA", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFormulario = new javax.swing.JTable();
        lblFormulario1 = new javax.swing.JLabel();
        lblImagen = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(700, 396));
        setMinimumSize(new java.awt.Dimension(700, 396));
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblFormulario.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(84, 229, 255), 1, true));
        tblFormulario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "CEDULA", "PARADA FAVORITA", "UBICACION"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblFormulario);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 660, 260));

        lblFormulario1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblFormulario1.setForeground(new java.awt.Color(255, 255, 255));
        lblFormulario1.setText("Paradas Favoritas");
        jPanel1.add(lblFormulario1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, -1, -1));
        jPanel1.add(lblImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 396));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ParadasFavoritas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ParadasFavoritas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ParadasFavoritas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ParadasFavoritas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ParadasFavoritas().setVisible(true);
            }
        });
    }
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFormulario1;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JTable tblFormulario;
    // End of variables declaration//GEN-END:variables
}
