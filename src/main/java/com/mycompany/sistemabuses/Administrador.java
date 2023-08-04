/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.sistemabuses;

import com.google.protobuf.TextFormat.ParseException;
import java.awt.Image;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
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
        mostrarDatosActualizar(tblParadas);
         try {
            ImageIcon wallpaper = new ImageIcon("C:\\Users\\richi\\OneDrive\\Documentos\\NetBeansProjects\\SistemaBuses\\src\\main\\java\\Imagenes\\paradas.png");
            Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(), Image.SCALE_DEFAULT));
            lblFondo.setIcon(icono);
            this.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
         

    }

    public void mostrarDatosActualizar(JTable tabla) {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.setRowCount(0); // Limpiamos la tabla antes de llenarla con nuevos datos

        try {
            Connection connection = new Conexion().conectar();

            String sql = "SELECT p.PAR_Nombre, u.UBI_Nombre " +
                         "FROM PARADAS p " +
                         "JOIN UBICACION u ON p.UBI_ID = u.UBI_ID";

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            // Aquí llenamos la tabla con los datos obtenidos
            while (result.next()) {
                String nombreParada = result.getString("PAR_Nombre");
                String nombreUbicacion = result.getString("UBI_Nombre");

                // Agregamos una nueva fila a la tabla con los datos obtenidos
                Object[] fila = {nombreParada, nombreUbicacion};
                model.addRow(fila);
            }

            result.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbxUbicacionAdmin = new javax.swing.JComboBox<>();
        lblnombreUbiAdmin = new javax.swing.JLabel();
        txtNombreParAdmin = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblParadas = new javax.swing.JTable();
        btnCrearAdmin = new javax.swing.JButton();
        btnModificarAmin = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        lblLongitudAdmin = new javax.swing.JLabel();
        txtLongParAdmin = new javax.swing.JTextField();
        lblLatitud = new javax.swing.JLabel();
        txtLatitudPar = new javax.swing.JTextField();
        jspnTiempoYachayParAdmin = new javax.swing.JSpinner();
        lblnombreUbiAdmin1 = new javax.swing.JLabel();
        lblnombreUbiAdmin2 = new javax.swing.JLabel();
        jspnTiempoIbarraParAdmin = new javax.swing.JSpinner();
        lblFondo = new javax.swing.JLabel();
        lblConsultaAdmin = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbxUbicacionAdmin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbxUbicacionAdmin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ibarra", "Antonio Ante", "Urcuquí", "Yachay", " " }));
        cbxUbicacionAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxUbicacionAdminActionPerformed(evt);
            }
        });
        getContentPane().add(cbxUbicacionAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 168, -1));

        lblnombreUbiAdmin.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblnombreUbiAdmin.setForeground(new java.awt.Color(255, 255, 255));
        lblnombreUbiAdmin.setText("Tiempo desde ibarra");
        getContentPane().add(lblnombreUbiAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 120, -1));
        getContentPane().add(txtNombreParAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 276, -1));

        tblParadas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "NOMBRE", "UBICACION"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
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
        if (tblParadas.getColumnModel().getColumnCount() > 0) {
            tblParadas.getColumnModel().getColumn(0).setPreferredWidth(100);
        }

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 110, 240, 250));

        btnCrearAdmin.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnCrearAdmin.setText("Crear");
        btnCrearAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearAdminActionPerformed(evt);
            }
        });
        getContentPane().add(btnCrearAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, -1));

        btnModificarAmin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnModificarAmin.setText("Modificar");
        btnModificarAmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarAminActionPerformed(evt);
            }
        });
        getContentPane().add(btnModificarAmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, -1, -1));

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, -1, -1));

        lblLongitudAdmin.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblLongitudAdmin.setForeground(new java.awt.Color(255, 255, 255));
        lblLongitudAdmin.setText("LONGITUD");
        getContentPane().add(lblLongitudAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, -1, -1));
        getContentPane().add(txtLongParAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 173, -1));

        lblLatitud.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblLatitud.setForeground(new java.awt.Color(255, 255, 255));
        lblLatitud.setText("LATITUD");
        getContentPane().add(lblLatitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, -1, -1));
        getContentPane().add(txtLatitudPar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 168, -1));

        jspnTiempoYachayParAdmin.setModel(new javax.swing.SpinnerNumberModel(0, 0, 50, 1));
        getContentPane().add(jspnTiempoYachayParAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 360, -1, -1));

        lblnombreUbiAdmin1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblnombreUbiAdmin1.setForeground(new java.awt.Color(255, 255, 255));
        lblnombreUbiAdmin1.setText("NOMBRE DE LA UBICACION DE  PARADA");
        getContentPane().add(lblnombreUbiAdmin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, -1, -1));

        lblnombreUbiAdmin2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblnombreUbiAdmin2.setForeground(new java.awt.Color(255, 255, 255));
        lblnombreUbiAdmin2.setText("Tiempo desde YAchay");
        getContentPane().add(lblnombreUbiAdmin2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 120, -1));

        jspnTiempoIbarraParAdmin.setModel(new javax.swing.SpinnerNumberModel(0, 0, 50, 1));
        getContentPane().add(jspnTiempoIbarraParAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 310, -1, -1));

        lblFondo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFondo.setMaximumSize(new java.awt.Dimension(700, 394));
        lblFondo.setMinimumSize(new java.awt.Dimension(700, 394));
        lblFondo.setPreferredSize(new java.awt.Dimension(700, 394));
        getContentPane().add(lblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 394));

        lblConsultaAdmin.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblConsultaAdmin.setForeground(new java.awt.Color(255, 255, 255));
        lblConsultaAdmin.setText("UBICACION ");
        getContentPane().add(lblConsultaAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void btnModificarAminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarAminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModificarAminActionPerformed

    private void btnCrearAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearAdminActionPerformed
        try {
            String nombre = txtNombreParAdmin.getText();
            double latitud = Double.parseDouble(txtLatitudPar.getText());
            double longitud = Double.parseDouble(txtLongParAdmin.getText());
            int ubiID = obtenerUbiID((String) cbxUbicacionAdmin.getSelectedItem()); // Obtener el ID de ubicación del combo box

            // Obtener los valores de los JSpinners como objetos Object
            Object tiempoDesdeIbarraObj = jspnTiempoIbarraParAdmin.getValue();
            Object tiempoDesdeYachayObj = jspnTiempoYachayParAdmin.getValue();

            // Convertir los valores Object a enteros representando los minutos
            int minutosIbarra = (int) tiempoDesdeIbarraObj;
            int minutosYachay = (int) tiempoDesdeYachayObj;

            // Crear objetos Time y establecer los minutos obtenidos
            Time tiempoDesdeIbarra = Time.valueOf(String.format("00:%02d:00", minutosIbarra));
            Time tiempoDesdeYachay = Time.valueOf(String.format("00:%02d:00", minutosYachay));

            Connection connection = new Conexion().conectar();

            // Llamar al procedimiento almacenado para insertar una nueva parada
            String sql = "{CALL InsertarParada(?, ?, ?, ?, ?, ?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setString(1, nombre);
            statement.setDouble(2, latitud);
            statement.setDouble(3, longitud);
            statement.setInt(4, ubiID);
            statement.setTime(5, tiempoDesdeIbarra);
            statement.setTime(6, tiempoDesdeYachay);
            statement.execute();

            statement.close();
            connection.close();

            JOptionPane.showMessageDialog(this, "Parada ingresada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error al ingresar los datos. Verifica que los campos numéricos contengan valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCrearAdminActionPerformed

    private void tblParadasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblParadasMouseClicked
        int seleccion = tblParadas.getSelectedRow();
        cbxUbicacionAdmin.setSelectedItem(tblParadas.getValueAt(seleccion, 0).toString());
        txtNombreParAdmin.setText(tblParadas.getValueAt(seleccion, 1).toString());
        txtLongParAdmin.setText(tblParadas.getValueAt(seleccion, 2).toString());
        txtLatitudPar.setText(tblParadas.getValueAt(seleccion, 3).toString());

        filas = seleccion;
    }//GEN-LAST:event_tblParadasMouseClicked

    private void cbxUbicacionAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxUbicacionAdminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxUbicacionAdminActionPerformed

    
    
   private int obtenerUbiID(String nombreUbicacion) throws SQLException, ClassNotFoundException {
    int ubiID = -1; // Valor por defecto si no se encuentra el nombre de ubicación

    String sql = "SELECT ubi_id FROM ubicacion WHERE ubi_nombre = ?";
    Connection connection = new Conexion().conectar();
    PreparedStatement statement = connection.prepareStatement(sql);
    statement.setString(1, nombreUbicacion);
    ResultSet resultSet = statement.executeQuery();

    if (resultSet.next()) {
        ubiID = resultSet.getInt("ubi_id");
    }

    resultSet.close();
    statement.close();
    connection.close();

    return ubiID;
}


    
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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Administrador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrearAdmin;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificarAmin;
    private javax.swing.JComboBox<String> cbxUbicacionAdmin;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jspnTiempoIbarraParAdmin;
    private javax.swing.JSpinner jspnTiempoYachayParAdmin;
    private javax.swing.JLabel lblConsultaAdmin;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JLabel lblLatitud;
    private javax.swing.JLabel lblLongitudAdmin;
    private javax.swing.JLabel lblnombreUbiAdmin;
    private javax.swing.JLabel lblnombreUbiAdmin1;
    private javax.swing.JLabel lblnombreUbiAdmin2;
    private javax.swing.JTable tblParadas;
    private javax.swing.JTextField txtLatitudPar;
    private javax.swing.JTextField txtLongParAdmin;
    private javax.swing.JTextField txtNombreParAdmin;
    // End of variables declaration//GEN-END:variables
}
