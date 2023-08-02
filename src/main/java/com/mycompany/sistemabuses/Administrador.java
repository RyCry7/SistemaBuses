/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.sistemabuses;

import java.awt.Image;
import java.sql.ResultSet;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Asus
 */
public class Administrador extends javax.swing.JFrame {

    int filas;

    Conexion con = new Conexion();

    public Administrador() {
        initComponents();
        setLocationRelativeTo(null);
        mostrarDatosActualizar();
         try {
            ImageIcon wallpaper = new ImageIcon("C:\\Users\\Asus\\Documents\\ProyectoFinalll\\SistemaBuses\\src\\main\\java\\com\\mycompany\\Imagenes\\WhatsApp Image 2023-08-02 at 10.53.41.jpeg");
            Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(), Image.SCALE_DEFAULT));
            lblFondo.setIcon(icono);
            this.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
         try {
            ImageIcon wallpaper = new ImageIcon("C:\\Users\\Asus\\Documents\\ProyectoFinalll\\SistemaBuses\\src\\main\\java\\com\\mycompany\\Imagenes\\WhatsApp Image 2023-08-02 at 10.53.41.jpeg");
            Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(lblFondo1.getWidth(), lblFondo1.getHeight(), Image.SCALE_DEFAULT));
            lblFondo1.setIcon(icono);
            this.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void mostrarDatosActualizar() {
        //muestra en la secuencia que se encuentra ubicado en la tabla
        DefaultTableModel tenc1 = new DefaultTableModel();
        tenc1.addColumn("N° PARADA");
        tenc1.addColumn("UBICACION");
        tenc1.addColumn("NOMBRE DE LA PARADA");
        tenc1.addColumn("LONGITUD");
        tenc1.addColumn("LATITUD");

        tblParadas.setModel(tenc1);
        String actualizar = "select * from paradas";
        String[] datos = new String[5];

        try {

            Conexion con = new Conexion();
            ResultSet resultado = con.EjecutarSQL(actualizar);
            while (resultado.next()) {
                datos[0] = resultado.getString(1);
                datos[1] = resultado.getString(2);
                datos[2] = resultado.getString(3);
                datos[3] = resultado.getString(4);
                datos[4] = resultado.getString(5);

                tenc1.addRow(datos);
            }
            tblParadas.setModel(tenc1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR EN LA CONSULTA", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbxUbicacion = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtNombrePar = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblParadas = new javax.swing.JTable();
        btnCrear = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtLongPar = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtLatitudPar = new javax.swing.JTextField();
        lblFondo = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lblFondo1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setBackground(new java.awt.Color(0, 0, 0));
        jTabbedPane1.setForeground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("UBICACION ");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 36, -1, -1));

        cbxUbicacion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbxUbicacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "IBARRA", "ANTONIO ANTE", "URCUQUI" }));
        jPanel2.add(cbxUbicacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(79, 67, 168, -1));

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("NOMBRE DE LA UBICACION DE  PARADA");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(463, 36, -1, -1));
        jPanel2.add(txtNombrePar, new org.netbeans.lib.awtextra.AbsoluteConstraints(463, 67, 276, -1));

        tblParadas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "N°PARADAS", "UBICACION", "NOMBRE DE LA PARADA", "LONGITUD", "LATITUD"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblParadas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblParadasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblParadas);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 300, 955, 147));

        btnCrear.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnCrear.setText("CREAR");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });
        jPanel2.add(btnCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, -1, -1));

        btnModificar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnModificar.setText("MODIFICAR");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel2.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 240, -1, -1));

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel2.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 230, -1, -1));

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("LONGITUD");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1007, 36, -1, -1));
        jPanel2.add(txtLongPar, new org.netbeans.lib.awtextra.AbsoluteConstraints(953, 67, 173, -1));

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("LATITUD");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(117, 107, -1, -1));
        jPanel2.add(txtLatitudPar, new org.netbeans.lib.awtextra.AbsoluteConstraints(79, 132, 168, -1));
        jPanel2.add(lblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-3, -4, 1260, 510));

        jTabbedPane1.addTab("CREACION DE PARADAS", jPanel2);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SELECCIONE LA BUSQUEDA QUE DESEA REALIZAR");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(505, 20, -1, -1));

        jComboBox1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas las paradas", "Ubicacion", " " }));
        jPanel3.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 48, 198, -1));
        jPanel3.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(461, 48, 367, -1));

        jButton1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jButton1.setText("CONSULTAR");
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(949, 48, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "USUARIO", "UBICACION", "NOMBRE DE LA UBICACION PARADA"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(79, 157, 914, 190));
        jPanel3.add(lblFondo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-3, -4, 1260, 510));

        jTabbedPane1.addTab("FORMULARIO DE PARADAS FAVORITAS", jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1260, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 509, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("TARIFAS", jPanel4);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1260, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 509, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("HORARIOS", jPanel5);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1260, 550));

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

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        String ubicacion = (String) cbxUbicacion.getSelectedItem();
        String nombre = txtNombrePar.getText();
        String latitud = txtLatitudPar.getText();
        String longitud = txtLongPar.getText();

        if (nombre.isEmpty() || latitud.isEmpty() || longitud.isEmpty()) {
            JOptionPane.showMessageDialog(null, "NO DEJAR CAMPOS VACIOS");
        } else {
            String insertar = ("call proyecfinall.Nparadas('" + ubicacion + "', '" + nombre + "','" + latitud + "','" + longitud + "');");
            System.out.println("////" + insertar);
            Conexion con = new Conexion();
            JOptionPane.showMessageDialog(null, "INGRESO EXITOSO");

            try {
                con.EjecutarCli(insertar);

                // Agregar datos a la tabla en la primera fila
                DefaultTableModel model = (DefaultTableModel) tblParadas.getModel();
                model.insertRow(0, new Object[]{ubicacion, nombre, latitud, longitud});

            } catch (Exception e) {
                // e.printStackTrace();
                JOptionPane.showMessageDialog(null, "NO SE INGRESO LA PARADA");
            }
        }


    }//GEN-LAST:event_btnCrearActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
      
        String eliminar = ("call proyecfinall.eli_Par(?);");
        System.out.println("/////" + eliminar);

        Conexion con = new Conexion();

        try {
            con.EjecutarCli(eliminar);
            JOptionPane.showMessageDialog(null, "Se eliminó el registro correctamente");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModificarActionPerformed

    private void tblParadasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblParadasMouseClicked
        int seleccion = tblParadas.getSelectedRow();
        cbxUbicacion.setSelectedItem(tblParadas.getValueAt(seleccion, 0).toString());
        txtNombrePar.setText(tblParadas.getValueAt(seleccion, 1).toString());
        txtLongPar.setText(tblParadas.getValueAt(seleccion, 2).toString());
        txtLatitudPar.setText(tblParadas.getValueAt(seleccion, 3).toString());

        filas = seleccion;
    }//GEN-LAST:event_tblParadasMouseClicked

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
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Administrador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cbxUbicacion;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JLabel lblFondo1;
    private javax.swing.JTable tblParadas;
    private javax.swing.JTextField txtLatitudPar;
    private javax.swing.JTextField txtLongPar;
    private javax.swing.JTextField txtNombrePar;
    // End of variables declaration//GEN-END:variables
}
