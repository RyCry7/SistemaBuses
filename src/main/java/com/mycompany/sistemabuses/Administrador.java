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
        tbpAdministrador = new javax.swing.JTabbedPane();
        pnParadasAdministrador = new javax.swing.JPanel();
        lblConsultaAdmin = new javax.swing.JLabel();
        cbxUbicacionAdmin = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtNombreParAdmin = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblParadas = new javax.swing.JTable();
        btnCrearAdmin = new javax.swing.JButton();
        btnModificarAmin = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        lblLongitudAdmin = new javax.swing.JLabel();
        txtLongParAdmin = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtLatitudPar = new javax.swing.JTextField();
        lblFondo = new javax.swing.JLabel();
        pnFormularioParadasAdministrador = new javax.swing.JPanel();
        lblParadas = new javax.swing.JLabel();
        cbxParadas = new javax.swing.JComboBox<>();
        txtParadasBusqueda = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblParadasFavoritasAdmi = new javax.swing.JTable();
        lblFondo1 = new javax.swing.JLabel();
        pnTarifaAdministrador = new javax.swing.JPanel();
        HorarioAdministrador = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbpAdministrador.setBackground(new java.awt.Color(0, 0, 0));
        tbpAdministrador.setForeground(new java.awt.Color(255, 255, 255));
        tbpAdministrador.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N

        pnParadasAdministrador.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblConsultaAdmin.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblConsultaAdmin.setForeground(new java.awt.Color(255, 255, 255));
        lblConsultaAdmin.setText("UBICACION ");
        pnParadasAdministrador.add(lblConsultaAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 36, -1, -1));

        cbxUbicacionAdmin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbxUbicacionAdmin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "IBARRA", "ANTONIO ANTE", "URCUQUI" }));
        pnParadasAdministrador.add(cbxUbicacionAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(79, 67, 168, -1));

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("NOMBRE DE LA UBICACION DE  PARADA");
        pnParadasAdministrador.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(463, 36, -1, -1));
        pnParadasAdministrador.add(txtNombreParAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(463, 67, 276, -1));

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

        pnParadasAdministrador.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 300, 955, 147));

        btnCrearAdmin.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnCrearAdmin.setText("CREAR");
        btnCrearAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearAdminActionPerformed(evt);
            }
        });
        pnParadasAdministrador.add(btnCrearAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, -1, -1));

        btnModificarAmin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnModificarAmin.setText("MODIFICAR");
        btnModificarAmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarAminActionPerformed(evt);
            }
        });
        pnParadasAdministrador.add(btnModificarAmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 240, -1, -1));

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        pnParadasAdministrador.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 230, -1, -1));

        lblLongitudAdmin.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblLongitudAdmin.setForeground(new java.awt.Color(255, 255, 255));
        lblLongitudAdmin.setText("LONGITUD");
        pnParadasAdministrador.add(lblLongitudAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(1007, 36, -1, -1));
        pnParadasAdministrador.add(txtLongParAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(953, 67, 173, -1));

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("LATITUD");
        pnParadasAdministrador.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(117, 107, -1, -1));
        pnParadasAdministrador.add(txtLatitudPar, new org.netbeans.lib.awtextra.AbsoluteConstraints(79, 132, 168, -1));
        pnParadasAdministrador.add(lblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-3, -4, 1260, 510));

        tbpAdministrador.addTab("CREACION DE PARADAS", pnParadasAdministrador);

        pnFormularioParadasAdministrador.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblParadas.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblParadas.setForeground(new java.awt.Color(255, 255, 255));
        lblParadas.setText("SELECCIONE LA BUSQUEDA QUE DESEA REALIZAR");
        pnFormularioParadasAdministrador.add(lblParadas, new org.netbeans.lib.awtextra.AbsoluteConstraints(505, 20, -1, -1));

        cbxParadas.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        cbxParadas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas las paradas", "Ubicacion", " " }));
        pnFormularioParadasAdministrador.add(cbxParadas, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 48, 198, -1));
        pnFormularioParadasAdministrador.add(txtParadasBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(461, 48, 367, -1));

        btnBuscar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnBuscar.setText("CONSULTAR");
        pnFormularioParadasAdministrador.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(949, 48, -1, -1));

        tblParadasFavoritasAdmi.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblParadasFavoritasAdmi);

        pnFormularioParadasAdministrador.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(79, 157, 914, 190));
        pnFormularioParadasAdministrador.add(lblFondo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-3, -4, 1260, 510));

        tbpAdministrador.addTab("FORMULARIO DE PARADAS FAVORITAS", pnFormularioParadasAdministrador);

        javax.swing.GroupLayout pnTarifaAdministradorLayout = new javax.swing.GroupLayout(pnTarifaAdministrador);
        pnTarifaAdministrador.setLayout(pnTarifaAdministradorLayout);
        pnTarifaAdministradorLayout.setHorizontalGroup(
            pnTarifaAdministradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1260, Short.MAX_VALUE)
        );
        pnTarifaAdministradorLayout.setVerticalGroup(
            pnTarifaAdministradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 509, Short.MAX_VALUE)
        );

        tbpAdministrador.addTab("TARIFAS", pnTarifaAdministrador);

        javax.swing.GroupLayout HorarioAdministradorLayout = new javax.swing.GroupLayout(HorarioAdministrador);
        HorarioAdministrador.setLayout(HorarioAdministradorLayout);
        HorarioAdministradorLayout.setHorizontalGroup(
            HorarioAdministradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1260, Short.MAX_VALUE)
        );
        HorarioAdministradorLayout.setVerticalGroup(
            HorarioAdministradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 509, Short.MAX_VALUE)
        );

        tbpAdministrador.addTab("HORARIOS", HorarioAdministrador);

        jPanel1.add(tbpAdministrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1260, 550));

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

    private void btnCrearAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearAdminActionPerformed
        String ubicacion = (String) cbxUbicacionAdmin.getSelectedItem();
        String nombre = txtNombreParAdmin.getText();
        String latitud = txtLatitudPar.getText();
        String longitud = txtLongParAdmin.getText();

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


    }//GEN-LAST:event_btnCrearAdminActionPerformed

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

    private void tblParadasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblParadasMouseClicked
        int seleccion = tblParadas.getSelectedRow();
        cbxUbicacionAdmin.setSelectedItem(tblParadas.getValueAt(seleccion, 0).toString());
        txtNombreParAdmin.setText(tblParadas.getValueAt(seleccion, 1).toString());
        txtLongParAdmin.setText(tblParadas.getValueAt(seleccion, 2).toString());
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
    private javax.swing.JPanel HorarioAdministrador;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCrearAdmin;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificarAmin;
    private javax.swing.JComboBox<String> cbxParadas;
    private javax.swing.JComboBox<String> cbxUbicacionAdmin;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblConsultaAdmin;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JLabel lblFondo1;
    private javax.swing.JLabel lblLongitudAdmin;
    private javax.swing.JLabel lblParadas;
    private javax.swing.JPanel pnFormularioParadasAdministrador;
    private javax.swing.JPanel pnParadasAdministrador;
    private javax.swing.JPanel pnTarifaAdministrador;
    private javax.swing.JTable tblParadas;
    private javax.swing.JTable tblParadasFavoritasAdmi;
    private javax.swing.JTabbedPane tbpAdministrador;
    private javax.swing.JTextField txtLatitudPar;
    private javax.swing.JTextField txtLongParAdmin;
    private javax.swing.JTextField txtNombreParAdmin;
    private javax.swing.JTextField txtParadasBusqueda;
    // End of variables declaration//GEN-END:variables
}
