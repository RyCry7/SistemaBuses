/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.sistemabuses;

import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        MostrarcbxUbicacion();
        mostrarDatosParFavoritas();

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

// ...
    public void MostrarcbxUbicacion() {
        Conexion c1 = new Conexion();
        try {

            String combo = "SELECT UBI_Nombre FROM ubicacion; ";
            ResultSet resulSet = c1.EjecutarSQL(combo);
            while (resulSet.next()) {

                String nombre = resulSet.getString("UBI_Nombre");
                cbxUbicacionAdmin.addItem(nombre);

            }
        } catch (Exception e) {

        }

    }

    public void mostrarDatosActualizar() {
        //muestra en la secuencia que se encuentra ubicado en la tabla
        DefaultTableModel tenc1 = new DefaultTableModel();

        tenc1.addColumn("NOMBRE DE LA PARADA");
        tenc1.addColumn("LONGITUD");
        tenc1.addColumn("LATITUD");

        tblParadas.setModel(tenc1);
        String actualizar = "select PAR_NOMBRE,PAR_LATITUD, PAR_LONGITUD   from paradas ; ";
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
            tblParadas.setModel(tenc1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR EN LA CONSULTA", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mostrarDatosParFavoritas() {
        //muestra en la secuencia que se encuentra ubicado en la tabla
        DefaultTableModel tenc1 = new DefaultTableModel();

        tenc1.addColumn("USUARIO");
        tenc1.addColumn("PARADA FAVORITA");

        tblParadasFavoritasAdmi.setModel(tenc1);
        String actualizar = "SELECT parfav.USU_Cedula, paradas.PAR_Nombre FROM parfav  JOIN paradas ON parfav.PAR_ID = paradas.PAR_ID";

        String[] datos = new String[2];

        try {

            Conexion con = new Conexion();
            ResultSet resultado = con.EjecutarSQL(actualizar);
            while (resultado.next()) {
                datos[0] = resultado.getString(1);
                datos[1] = resultado.getString(2);

                tenc1.addRow(datos);
            }
            tblParadasFavoritasAdmi.setModel(tenc1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR EN LA CONSULTA", JOptionPane.ERROR_MESSAGE);
        }
    }/*

    public void mostrarDatosParBusqueda(int opbuscar, String valor) {
        //muestra en la secuencia que se encuentra ubicado en la tabla
        DefaultTableModel tenc1 = new DefaultTableModel();

        tenc1.addColumn("USUARIO");
        tenc1.addColumn("PARADA FAVORITA");

        tblParadasFavoritasAdmi.setModel(tenc1);
        String codsql = null;
        if (opbuscar == 0 && valor == null) {
            codsql =("SELECT parfav.USU_Cedula, paradas.PAR_Nombre FROM parfav  JOIN paradas ON parfav.PAR_ID = paradas.PAR_ID");
          } else {
            if (opbuscar == 1 && valor != null) {
                codsql =("SELECT parfav.USU_Cedula, paradas.PAR_Nombre FROM parfav JOIN paradas ON parfav.PAR_ID = paradas.PAR_ID WHERE paradas.PAR_Nombre = ?");
        } else {
                    codsql =("SELECT parfav.USU_Cedula, paradas.PAR_Nombre FROM parfav  JOIN paradas ON parfav.PAR_ID = paradas.PAR_ID");
            }
        }

        String[] datos = new String[2];

        try {

            Conexion con = new Conexion();
            ResultSet resultado = con.EjecutarSQL(codsql);
            while (resultado.next()) {
                datos[0] = resultado.getString(1);
                datos[1] = resultado.getString(2);

                tenc1.addRow(datos);
            }
            tblParadasFavoritasAdmi.setModel(tenc1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR EN LA CONSULTA", JOptionPane.ERROR_MESSAGE);
        }
    }*/

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tbpAdministrador = new javax.swing.JTabbedPane();
        pnParadasAdministrador = new javax.swing.JPanel();
        lblConsultaAdmin = new javax.swing.JLabel();
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
        txtTiempoSalida = new javax.swing.JTextField();
        txtLatitudPar = new javax.swing.JTextField();
        txtTiempoLLegada = new javax.swing.JTextField();
        lblSalida = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
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
        cbxUbicacionAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbxUbicacionAdminMouseClicked(evt);
            }
        });
        pnParadasAdministrador.add(cbxUbicacionAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 168, -1));

        lblnombreUbiAdmin.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblnombreUbiAdmin.setForeground(new java.awt.Color(255, 255, 255));
        lblnombreUbiAdmin.setText("NOMBRE DE LA UBICACION DE  PARADA");
        pnParadasAdministrador.add(lblnombreUbiAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(463, 36, -1, -1));
        pnParadasAdministrador.add(txtNombreParAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(463, 67, 276, -1));

        tblParadas.setBackground(new java.awt.Color(84, 229, 255));
        tblParadas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "NOMBRE DE LA PARADA", "LATITUD", "LONGITUD"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
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

        pnParadasAdministrador.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 300, 940, 147));

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
        pnParadasAdministrador.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 240, -1, -1));

        lblLongitudAdmin.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblLongitudAdmin.setForeground(new java.awt.Color(255, 255, 255));
        lblLongitudAdmin.setText("LONGITUD");
        pnParadasAdministrador.add(lblLongitudAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 40, -1, -1));
        pnParadasAdministrador.add(txtLongParAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 70, 173, -1));

        lblLatitud.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblLatitud.setForeground(new java.awt.Color(255, 255, 255));
        lblLatitud.setText("LATITUD");
        pnParadasAdministrador.add(lblLatitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(117, 107, -1, -1));

        txtTiempoSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTiempoSalidaActionPerformed(evt);
            }
        });
        pnParadasAdministrador.add(txtTiempoSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 130, 220, -1));
        pnParadasAdministrador.add(txtLatitudPar, new org.netbeans.lib.awtextra.AbsoluteConstraints(79, 132, 168, -1));

        txtTiempoLLegada.setForeground(new java.awt.Color(255, 255, 255));
        pnParadasAdministrador.add(txtTiempoLLegada, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 130, 210, 20));

        lblSalida.setBackground(new java.awt.Color(255, 255, 255));
        lblSalida.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblSalida.setForeground(new java.awt.Color(255, 255, 255));
        lblSalida.setText("TIEMPO ESTIMADO DE LLEGADA DESDE IBARRA");
        pnParadasAdministrador.add(lblSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 100, 410, -1));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("TIEMPO ESTIMADO DE LLEGADA DESDE  YACHAY");
        pnParadasAdministrador.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 100, 370, -1));
        pnParadasAdministrador.add(lblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-160, 0, 1420, 510));

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
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        pnFormularioParadasAdministrador.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(949, 48, -1, -1));

        tblParadasFavoritasAdmi.setBackground(new java.awt.Color(84, 229, 255));
        tblParadasFavoritasAdmi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "USUARIO", "NOMBRE DE LA UBICACION PARADA"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblParadasFavoritasAdmi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblParadasFavoritasAdmiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblParadasFavoritasAdmi);

        pnFormularioParadasAdministrador.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 914, 190));
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
        String tiempoIba = txtTiempoSalida.getText();
        String tiempo = txtTiempoLLegada.getText();

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

    private void txtTiempoSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTiempoSalidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTiempoSalidaActionPerformed

    private void cbxUbicacionAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxUbicacionAdminMouseClicked
        MostrarcbxUbicacion();
    }//GEN-LAST:event_cbxUbicacionAdminMouseClicked

    private void tblParadasFavoritasAdmiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblParadasFavoritasAdmiMouseClicked

                                         
    }//GEN-LAST:event_tblParadasFavoritasAdmiMouseClicked

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
      /* int opcion = cbxParadas.getSelectedIndex();
        String valorbus = txtParadasBusqueda.getText();
        mostrarDatosParBusqueda (opcion, valorbus);*/
    }//GEN-LAST:event_btnBuscarActionPerformed

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
    private javax.swing.JPanel HorarioAdministrador;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCrearAdmin;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificarAmin;
    private javax.swing.JComboBox<String> cbxParadas;
    private javax.swing.JComboBox<String> cbxUbicacionAdmin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblConsultaAdmin;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JLabel lblFondo1;
    private javax.swing.JLabel lblLatitud;
    private javax.swing.JLabel lblLongitudAdmin;
    private javax.swing.JLabel lblParadas;
    private javax.swing.JLabel lblSalida;
    private javax.swing.JLabel lblnombreUbiAdmin;
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
    private javax.swing.JTextField txtTiempoLLegada;
    private javax.swing.JTextField txtTiempoSalida;
    // End of variables declaration//GEN-END:variables
}
