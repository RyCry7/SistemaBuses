/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.sistemabuses;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Asus
 */
public class Usuario extends javax.swing.JFrame {

    /**
     * Creates new form Usuario
     */
    public Usuario() {
        initComponents();
        inicializarComboBoxCiudades();
        cargarCiudadesEnComboBox(cbxCiudadesUsu);
        setLocationRelativeTo(null);
        try {
            ImageIcon wallpaper = new ImageIcon("C:\\Users\\Asus\\Documents\\ProyectoFinalll\\SistemaBuses\\src\\main\\java\\com\\mycompany\\Imagenes\\mapaIbarra.jpg");
            Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(lblImagen1.getWidth(), lblImagen1.getHeight(), Image.SCALE_DEFAULT));
            lblImagen1.setIcon(icono);
            this.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ImageIcon wallpaper = new ImageIcon("C:\\Users\\Asus\\Documents\\ProyectoFinalll\\SistemaBuses\\src\\main\\java\\com\\mycompany\\Imagenes\\mapaImb.jpg");
            Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(lblImagen1.getWidth(), lblImagen1.getHeight(), Image.SCALE_DEFAULT));
            lblImagen1.setIcon(icono);
            this.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ImageIcon wallpaper = new ImageIcon("C:\\Users\\Asus\\Documents\\ProyectoFinalll\\SistemaBuses\\src\\main\\java\\com\\mycompany\\Imagenes\\mapaUrcuqui.jpg");
            Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(lblImagen1.getWidth(), lblImagen1.getHeight(), Image.SCALE_DEFAULT));
            lblImagen1.setIcon(icono);
            this.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }
    
    
         public void cargarParadasCercanas() {
        DefaultTableModel modeloTabla = (DefaultTableModel) jtblParadasCercanas.getModel();
        modeloTabla.setRowCount(0); // Limpiar la tabla antes de cargar los datos

        String nombreCiudadSeleccionada = (String) cbxCiudadesUsu.getSelectedItem();
        int ubiID = obtenerIDUbicacion(nombreCiudadSeleccionada);

        try {
            Connection connection = new Conexion().conectar();

            String sql = "SELECT PAR_Nombre, PAR_Latitud, PAR_Longitud FROM PARADAS WHERE UBI_ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, ubiID);
            ResultSet result = statement.executeQuery();

            // Agregar las paradas cercanas a la tabla
            while (result.next()) {
                String nombreParada = result.getString("PAR_Nombre");
                double latitud = result.getDouble("PAR_Latitud");
                double longitud = result.getDouble("PAR_Longitud");
                modeloTabla.addRow(new Object[] { nombreParada, latitud, longitud });
            }

            result.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    // Método para obtener el ID de una ubicación a partir de su nombre
    public int obtenerIDUbicacion(String nombreUbicacion) {
        int ubiID = -1;
        try {
            Connection connection = new Conexion().conectar();

            String sql = "SELECT UBI_ID FROM UBICACION WHERE UBI_Nombre = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nombreUbicacion);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                ubiID = result.getInt("UBI_ID");
            }

            result.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return ubiID;
    }
    
    public void cargarCiudadesEnComboBox(JComboBox<String> comboBox) {
        comboBox.removeAllItems(); // Limpiamos el combo box antes de cargar los datos

        try {
            Connection connection = new Conexion().conectar();

            String sql = "SELECT UBI_Nombre FROM UBICACION";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            // Agregamos los nombres de las ciudades al combo box
            while (result.next()) {
                String nombreCiudad = result.getString("UBI_Nombre");
                comboBox.addItem(nombreCiudad);
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
public void inicializarComboBoxCiudades() {
        cbxCiudadesUsu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarParadasCercanas();
            }
        });
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tbpUsuario = new javax.swing.JTabbedPane();
        pnPrecioUsuario = new javax.swing.JPanel();
        lblEmbraque = new javax.swing.JLabel();
        lblDesembarqueUsu = new javax.swing.JLabel();
        btnConsultar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDatosPrecio = new javax.swing.JTable();
        cbxEmbarque = new javax.swing.JComboBox<>();
        cbxDesembarqueUsu = new javax.swing.JComboBox<>();
        pnHorarioUsuario = new javax.swing.JPanel();
        lblHorarioAdmin = new javax.swing.JLabel();
        cbxHorarioAdmin = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHorarioAdmin = new javax.swing.JTable();
        btnConsultarAdmin = new javax.swing.JButton();
        pnParadasUsuario = new javax.swing.JPanel();
        lblSelecionAdmin = new javax.swing.JLabel();
        cbxCiudadesUsu = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblParadasCercanas = new javax.swing.JTable();
        lblSeleccionFavoritaUsu = new javax.swing.JLabel();
        lblImagen1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        tbpUsuario.setBackground(new java.awt.Color(0, 0, 0));
        tbpUsuario.setForeground(new java.awt.Color(255, 255, 255));
        tbpUsuario.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N

        lblEmbraque.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblEmbraque.setText("Lugar de embarque");

        lblDesembarqueUsu.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblDesembarqueUsu.setText("Lugar de desembarque");

        btnConsultar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnConsultar.setText("CONSULTAR");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        tblDatosPrecio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Lugar Embarque", "Lugar de Desembarque", "Tarifa a Cancelar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblDatosPrecio);

        cbxEmbarque.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        cbxEmbarque.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Terminal Ibarra", "Panaderia los Colombianos", "Milagro de Ibarra", "Parque central Imbaya", "Santiago de Rey", "Urcuqui", "IST 17 Julio" }));

        cbxDesembarqueUsu.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        cbxDesembarqueUsu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Terminal Ibarra", "Panaderia los Colombianos", "Milagro de Ibarra", "Parque central Imbaya", "Santiago de Rey", "Urcuqui", "IST 17 Julio" }));

        javax.swing.GroupLayout pnPrecioUsuarioLayout = new javax.swing.GroupLayout(pnPrecioUsuario);
        pnPrecioUsuario.setLayout(pnPrecioUsuarioLayout);
        pnPrecioUsuarioLayout.setHorizontalGroup(
            pnPrecioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPrecioUsuarioLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(cbxEmbarque, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbxDesembarqueUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(183, 183, 183))
            .addGroup(pnPrecioUsuarioLayout.createSequentialGroup()
                .addGroup(pnPrecioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnPrecioUsuarioLayout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnPrecioUsuarioLayout.createSequentialGroup()
                        .addGap(311, 311, 311)
                        .addComponent(btnConsultar)))
                .addContainerGap(640, Short.MAX_VALUE))
            .addGroup(pnPrecioUsuarioLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(lblEmbraque)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDesembarqueUsu)
                .addGap(203, 203, 203))
        );
        pnPrecioUsuarioLayout.setVerticalGroup(
            pnPrecioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPrecioUsuarioLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(pnPrecioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmbraque)
                    .addComponent(lblDesembarqueUsu))
                .addGap(8, 8, 8)
                .addGroup(pnPrecioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxEmbarque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxDesembarqueUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(btnConsultar)
                .addGap(59, 59, 59)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(225, Short.MAX_VALUE))
        );

        tbpUsuario.addTab("PRECIO", pnPrecioUsuario);

        lblHorarioAdmin.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblHorarioAdmin.setText("SELECCIONE EL HORARIO A CONSULTAR");

        cbxHorarioAdmin.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        cbxHorarioAdmin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ibarra-Yachay 06:40 ", "Ibarra-Yachay 07:10", "Ibarra-Yachay 07:20", "Ibarra-Yachay 07:30", "Ibarra-Yachay 07:50" }));

        tblHorarioAdmin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "PARADA", "TIEMPO ESTIMADO DE LLEGADA"
            }
        ));
        jScrollPane3.setViewportView(tblHorarioAdmin);

        btnConsultarAdmin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnConsultarAdmin.setText("CONSULTA TIEMPO");
        btnConsultarAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarAdminActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnHorarioUsuarioLayout = new javax.swing.GroupLayout(pnHorarioUsuario);
        pnHorarioUsuario.setLayout(pnHorarioUsuarioLayout);
        pnHorarioUsuarioLayout.setHorizontalGroup(
            pnHorarioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHorarioUsuarioLayout.createSequentialGroup()
                .addGroup(pnHorarioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnHorarioUsuarioLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(pnHorarioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnHorarioUsuarioLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(cbxHorarioAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69)
                                .addComponent(btnConsultarAdmin))
                            .addComponent(lblHorarioAdmin)))
                    .addGroup(pnHorarioUsuarioLayout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 696, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(414, Short.MAX_VALUE))
        );
        pnHorarioUsuarioLayout.setVerticalGroup(
            pnHorarioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHorarioUsuarioLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(lblHorarioAdmin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnHorarioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxHorarioAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsultarAdmin))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(270, Short.MAX_VALUE))
        );

        tbpUsuario.addTab("HORARIO ", pnHorarioUsuario);

        lblSelecionAdmin.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblSelecionAdmin.setText("SELECCIONE SU UBICACION");

        cbxCiudadesUsu.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        cbxCiudadesUsu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbxCiudadesUsuMouseClicked(evt);
            }
        });
        cbxCiudadesUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxCiudadesUsuActionPerformed(evt);
            }
        });

        jtblParadasCercanas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nombre", "Latitud", "Longitud"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtblParadasCercanas);

        lblSeleccionFavoritaUsu.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblSeleccionFavoritaUsu.setText("SELECCIONE SU PARADA FAVOTITA");

        javax.swing.GroupLayout pnParadasUsuarioLayout = new javax.swing.GroupLayout(pnParadasUsuario);
        pnParadasUsuario.setLayout(pnParadasUsuarioLayout);
        pnParadasUsuarioLayout.setHorizontalGroup(
            pnParadasUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnParadasUsuarioLayout.createSequentialGroup()
                .addGroup(pnParadasUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnParadasUsuarioLayout.createSequentialGroup()
                        .addGroup(pnParadasUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnParadasUsuarioLayout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(lblSelecionAdmin))
                            .addGroup(pnParadasUsuarioLayout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(cbxCiudadesUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(199, 199, 199)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnParadasUsuarioLayout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addGroup(pnParadasUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnParadasUsuarioLayout.createSequentialGroup()
                                .addComponent(lblImagen1, javax.swing.GroupLayout.PREFERRED_SIZE, 896, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51))
                            .addGroup(pnParadasUsuarioLayout.createSequentialGroup()
                                .addComponent(lblSeleccionFavoritaUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(455, 455, 455)))))
                .addContainerGap(265, Short.MAX_VALUE))
        );
        pnParadasUsuarioLayout.setVerticalGroup(
            pnParadasUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnParadasUsuarioLayout.createSequentialGroup()
                .addGroup(pnParadasUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnParadasUsuarioLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(lblSelecionAdmin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxCiudadesUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnParadasUsuarioLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(lblSeleccionFavoritaUsu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblImagen1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbpUsuario.addTab("PARADAS CERCANAS", pnParadasUsuario);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbpUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbpUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConsultarAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarAdminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConsultarAdminActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void cbxCiudadesUsuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxCiudadesUsuMouseClicked
    //  Object seleObject = cbxCiudades.getSelectedItem();
 //if(seleObject.equals("IBARRA"));
 //{
     //JOptionPane.showMessageDialog(null,"IBARRA" );
    //ImageIcon IBARRA = new ImageIcon(getClass().getResource("com.mycompany.Imagenes//mapaIbarra"));
     //ImageIcon icon = new ImageIcon(IBARRA.getImage().getScaledInstance(lblImagen1.getWidth(), lblImagen1.getHeight(),Image.SCALE_DEFA));
              
         //    }ICONE.
                   
    
     

     
    }//GEN-LAST:event_cbxCiudadesUsuMouseClicked

    private void cbxCiudadesUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCiudadesUsuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxCiudadesUsuActionPerformed

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
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Usuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnConsultarAdmin;
    private javax.swing.JComboBox<String> cbxCiudadesUsu;
    private javax.swing.JComboBox<String> cbxDesembarqueUsu;
    private javax.swing.JComboBox<String> cbxEmbarque;
    private javax.swing.JComboBox<String> cbxHorarioAdmin;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jtblParadasCercanas;
    private javax.swing.JLabel lblDesembarqueUsu;
    private javax.swing.JLabel lblEmbraque;
    private javax.swing.JLabel lblHorarioAdmin;
    private javax.swing.JLabel lblImagen1;
    private javax.swing.JLabel lblSeleccionFavoritaUsu;
    private javax.swing.JLabel lblSelecionAdmin;
    private javax.swing.JPanel pnHorarioUsuario;
    private javax.swing.JPanel pnParadasUsuario;
    private javax.swing.JPanel pnPrecioUsuario;
    private javax.swing.JTable tblDatosPrecio;
    private javax.swing.JTable tblHorarioAdmin;
    private javax.swing.JTabbedPane tbpUsuario;
    // End of variables declaration//GEN-END:variables
}
