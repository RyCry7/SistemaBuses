/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.sistemabuses;

import com.google.protobuf.TextFormat.ParseException;
import java.awt.Color;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        // Agregar el WindowListener para escuchar el evento de cierre del JFrame
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // Mostrar nuevamente el JFrame "Administrador" cuando se cierre "ParadasFavoritas"
                Login1 log = new Login1();
                log.setVisible(true);
            }
        });
        
        mostrarDatosActualizar(tblParadas);
        inicializarListSelectionListener();
         try {
            ImageIcon wallpaper = new ImageIcon("C:\\Users\\Asus\\Documents\\ProyectoFinalll\\SistemaBuses\\src\\main\\java\\com\\mycompany\\Imagenes\\login.png");
            Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(), Image.SCALE_DEFAULT));
            lblFondo.setIcon(icono);
            this.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
         try {
            ImageIcon wallpaper = new ImageIcon("C:\\Users\\richi\\OneDrive\\Documentos\\NetBeansProjects\\SistemaBuses\\src\\main\\java\\Imagenes\\reporte.png");
            Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jlblReportePar.getWidth(), jlblReportePar.getHeight(), Image.SCALE_DEFAULT));
            jlblReportePar.setIcon(icono);
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
    
     public void inicializarListSelectionListener() {
        tblParadas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // Obtener la fila seleccionada
                    int filaSeleccionada = tblParadas.getSelectedRow();
                    if (filaSeleccionada != -1) {
                        // Obtener el modelo de la tabla y los datos de la fila seleccionada
                        DefaultTableModel modeloTabla = (DefaultTableModel) tblParadas.getModel();
                        String nombreParada = (String) modeloTabla.getValueAt(filaSeleccionada, 0);
                        String nombreUbicacion = (String) modeloTabla.getValueAt(filaSeleccionada, 1);

                        // Cargar los datos en los campos correspondientes
                        cargarDatosParada(nombreParada, nombreUbicacion);
                    }
                }
            }
        });
    }

    // Método para cargar los datos de una parada en los campos de texto, spinners y combobox
    public void cargarDatosParada(String nombreParada, String nombreUbicacion) {
        try {
            Connection connection = new Conexion().conectar();

            // Obtener los datos de la parada y ubicación correspondientes
            String sql = "SELECT * FROM PARADAS P JOIN UBICACION U ON P.UBI_ID = U.UBI_ID WHERE PAR_Nombre = ? AND UBI_Nombre = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nombreParada);
            statement.setString(2, nombreUbicacion);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                // Obtener los datos de la parada
                double latitud = result.getDouble("PAR_Latitud");
                double longitud = result.getDouble("PAR_Longitud");
                Time tiempoDesdeIbarra = result.getTime("tiempodesdeibarra");
                Time tiempoDesdeYachay = result.getTime("tiempodesdeyachay");

                // Cargar los datos en los campos de texto, spinners y combobox
                txtNombreParAdmin.setText(nombreParada);
                txtLatitudPar.setText(String.valueOf(latitud));
                txtLongParAdmin.setText(String.valueOf(longitud));

                // Convertir los tiempos a minutos para establecer los valores en los JSpinners
                int minutosIbarra = tiempoDesdeIbarra.getHours() * 60 + tiempoDesdeIbarra.getMinutes();
                int minutosYachay = tiempoDesdeYachay.getHours() * 60 + tiempoDesdeYachay.getMinutes();
                jspnTiempoIbarraParAdmin.setValue(minutosIbarra);
                jspnTiempoYachayParAdmin.setValue(minutosYachay);

                // Obtener el ID de ubicación y seleccionarlo en el JComboBox
                int ubiID = result.getInt("UBI_ID");
                seleccionarUbicacionEnComboBox(ubiID);
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

    // Método para seleccionar la ubicación correspondiente en el JComboBox
    public void seleccionarUbicacionEnComboBox(int ubiID) throws SQLException, ClassNotFoundException {
        for (int i = 0; i < cbxUbicacionAdmin.getItemCount(); i++) {
            String nombreUbicacion = (String) cbxUbicacionAdmin.getItemAt(i);
            int idUbicacion = obtenerUbiID(nombreUbicacion);
            if (idUbicacion == ubiID) {
                cbxUbicacionAdmin.setSelectedIndex(i);
                break;
            }
        }
    }

    public int obtenerIdParadaSeleccionada() {
    int filaSeleccionada = tblParadas.getSelectedRow();
    int idParada = -1; // Valor predeterminado si no se puede obtener el ID de la parada

    if (filaSeleccionada != -1) {
        String nombreParadaSeleccionada = (String) tblParadas.getValueAt(filaSeleccionada, 0); // Columna del nombre de la parada

        try {
            Connection connection = new Conexion().conectar();

            // Consultar el ID de la parada usando el nombre
            String sql = "SELECT PAR_ID FROM PARADAS WHERE PAR_Nombre = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nombreParadaSeleccionada);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                idParada = resultSet.getInt("PAR_ID");
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            idParada = -1; // Valor de retorno predeterminado o código de error
        }
    }

    return idParada;
}



    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tblParadas = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        txtNombreParAdmin = new javax.swing.JTextField();
        btnEliminar = new javax.swing.JButton();
        btnModificarAmin = new javax.swing.JButton();
        lblnombreUbiAdmin = new javax.swing.JLabel();
        cbxUbicacionAdmin = new javax.swing.JComboBox<>();
        lblnombreUbiAdmin1 = new javax.swing.JLabel();
        lblLongitudAdmin = new javax.swing.JLabel();
        txtLatitudPar = new javax.swing.JTextField();
        btnCrearAdmin = new javax.swing.JButton();
        lblConsultaAdmin = new javax.swing.JLabel();
        lblnombreUbiAdmin2 = new javax.swing.JLabel();
        txtLongParAdmin = new javax.swing.JTextField();
        lblLatitud = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jspnTiempoYachayParAdmin = new javax.swing.JSpinner();
        jspnTiempoIbarraParAdmin = new javax.swing.JSpinner();
        lblParadasFavoritas = new javax.swing.JLabel();
        jlblReportePar = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 530, 110));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 229, 255)));
        jPanel1.setMinimumSize(new java.awt.Dimension(640, 140));
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(640, 140));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNombreParAdmin.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(84, 229, 255), 1, true));
        txtNombreParAdmin.setMaximumSize(new java.awt.Dimension(64, 18));
        jPanel1.add(txtNombreParAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 130, 20));

        btnEliminar.setBackground(new java.awt.Color(0, 0, 0));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(84, 229, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 120, 90, -1));

        btnModificarAmin.setBackground(new java.awt.Color(0, 0, 0));
        btnModificarAmin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnModificarAmin.setForeground(new java.awt.Color(84, 229, 255));
        btnModificarAmin.setText("Actualizar");
        btnModificarAmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarAminActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificarAmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 80, 90, -1));

        lblnombreUbiAdmin.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblnombreUbiAdmin.setForeground(new java.awt.Color(255, 255, 255));
        lblnombreUbiAdmin.setText("Tiempo aprox. desde Ibarra");
        jPanel1.add(lblnombreUbiAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 120, 200, -1));

        cbxUbicacionAdmin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbxUbicacionAdmin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ibarra", "Antonio Ante", "Urcuquí", "Yachay", " " }));
        cbxUbicacionAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxUbicacionAdminActionPerformed(evt);
            }
        });
        jPanel1.add(cbxUbicacionAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 40, 100, -1));

        lblnombreUbiAdmin1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblnombreUbiAdmin1.setForeground(new java.awt.Color(255, 255, 255));
        lblnombreUbiAdmin1.setText("Nombre");
        jPanel1.add(lblnombreUbiAdmin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, 20));

        lblLongitudAdmin.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblLongitudAdmin.setForeground(new java.awt.Color(255, 255, 255));
        lblLongitudAdmin.setText("Longitud");
        jPanel1.add(lblLongitudAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 70, 20));

        txtLatitudPar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 229, 255)));
        txtLatitudPar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLatitudParActionPerformed(evt);
            }
        });
        jPanel1.add(txtLatitudPar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 130, 20));

        btnCrearAdmin.setBackground(new java.awt.Color(0, 0, 0));
        btnCrearAdmin.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnCrearAdmin.setForeground(new java.awt.Color(84, 229, 255));
        btnCrearAdmin.setText("Crear");
        btnCrearAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearAdminActionPerformed(evt);
            }
        });
        jPanel1.add(btnCrearAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 40, 90, -1));

        lblConsultaAdmin.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblConsultaAdmin.setForeground(new java.awt.Color(255, 255, 255));
        lblConsultaAdmin.setText("Ubicacion");
        jPanel1.add(lblConsultaAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 40, 70, 20));

        lblnombreUbiAdmin2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblnombreUbiAdmin2.setForeground(new java.awt.Color(255, 255, 255));
        lblnombreUbiAdmin2.setText("Tiempo aprox. desde Yachay");
        jPanel1.add(lblnombreUbiAdmin2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 210, 20));

        txtLongParAdmin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 229, 255)));
        jPanel1.add(txtLongParAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 130, 20));

        lblLatitud.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblLatitud.setForeground(new java.awt.Color(255, 255, 255));
        lblLatitud.setText("Latitud");
        jPanel1.add(lblLatitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 60, -1));

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Datos de las paradas");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jspnTiempoYachayParAdmin.setModel(new javax.swing.SpinnerNumberModel(0, 0, 50, 1));
        jPanel1.add(jspnTiempoYachayParAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 80, 100, 20));

        jspnTiempoIbarraParAdmin.setModel(new javax.swing.SpinnerNumberModel(0, 0, 50, 1));
        jPanel1.add(jspnTiempoIbarraParAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 120, 100, 20));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 680, 150));

        lblParadasFavoritas.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblParadasFavoritas.setForeground(new java.awt.Color(255, 255, 255));
        lblParadasFavoritas.setText("Paradas favoritas");
        lblParadasFavoritas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblParadasFavoritasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblParadasFavoritasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblParadasFavoritasMouseExited(evt);
            }
        });
        getContentPane().add(lblParadasFavoritas, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 340, 140, 60));
        getContentPane().add(jlblReportePar, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 270, 80, 80));

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Administracion de paradas");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 55, -1, -1));

        lblFondo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFondo.setMaximumSize(new java.awt.Dimension(700, 394));
        lblFondo.setMinimumSize(new java.awt.Dimension(700, 394));
        lblFondo.setPreferredSize(new java.awt.Dimension(700, 394));
        getContentPane().add(lblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
int filaSeleccionada = tblParadas.getSelectedRow();

    if (filaSeleccionada != -1) {
        String nombreParadaSeleccionada = (String) tblParadas.getValueAt(filaSeleccionada, 0); // Columna del nombre de la parada

        try {
            Connection connection = new Conexion().conectar();

            // Llamar al procedimiento almacenado para eliminar la parada
            String sql = "{CALL EliminarParada(?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setString(1, nombreParadaSeleccionada);
            statement.execute();

            statement.close();
            connection.close();

            // Actualizar la tabla después de eliminar el registro
            mostrarDatosActualizar(tblParadas);

            JOptionPane.showMessageDialog(this, "Parada eliminada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    } else {
        JOptionPane.showMessageDialog(this, "Selecciona una parada para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnModificarAminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarAminActionPerformed
        // TODO add your handling code here:
         try {
            // Obtener los datos modificados desde los campos de texto, spinners y combobox
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

            // Llamar al procedimiento almacenado para modificar la parada seleccionada
            String sql = "{CALL ModificarParada(?, ?, ?, ?, ?, ?, ?)}";
            CallableStatement statement = connection.prepareCall(sql);
            statement.setInt(1, obtenerIdParadaSeleccionada()); // Obtener el ID de la parada seleccionada
            statement.setString(2, nombre);
            statement.setDouble(3, latitud);
            statement.setDouble(4, longitud);
            statement.setInt(5, ubiID);
            statement.setTime(6, tiempoDesdeIbarra);
            statement.setTime(7, tiempoDesdeYachay);
            statement.executeUpdate();

            statement.close();
            connection.close();

            JOptionPane.showMessageDialog(this, "Parada modificada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error al ingresar los datos. Verifica que los campos numéricos contengan valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
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
      
    }//GEN-LAST:event_tblParadasMouseClicked

    private void cbxUbicacionAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxUbicacionAdminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxUbicacionAdminActionPerformed

    private void txtLatitudParActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLatitudParActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLatitudParActionPerformed

    private void lblParadasFavoritasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblParadasFavoritasMouseClicked
        // TODO add your handling code here:
    ParadasFavoritas paradasfav = new ParadasFavoritas();
    paradasfav.setVisible(true);
    this.setVisible(false); // Ocultar el JFrame "Administrador" (no lo cierra)
    }//GEN-LAST:event_lblParadasFavoritasMouseClicked

    private void lblParadasFavoritasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblParadasFavoritasMouseEntered
        // TODO add your handling code here:
    lblParadasFavoritas.setForeground(new Color(84,229,255));
    }//GEN-LAST:event_lblParadasFavoritasMouseEntered

    private void lblParadasFavoritasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblParadasFavoritasMouseExited
        // TODO add your handling code here:
    lblParadasFavoritas.setForeground(new Color(84,229,255));
    lblParadasFavoritas.setForeground(Color.WHITE);
    }//GEN-LAST:event_lblParadasFavoritasMouseExited

    
    
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
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jlblReportePar;
    private javax.swing.JSpinner jspnTiempoIbarraParAdmin;
    private javax.swing.JSpinner jspnTiempoYachayParAdmin;
    private javax.swing.JLabel lblConsultaAdmin;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JLabel lblLatitud;
    private javax.swing.JLabel lblLongitudAdmin;
    private javax.swing.JLabel lblParadasFavoritas;
    private javax.swing.JLabel lblnombreUbiAdmin;
    private javax.swing.JLabel lblnombreUbiAdmin1;
    private javax.swing.JLabel lblnombreUbiAdmin2;
    private javax.swing.JTable tblParadas;
    private javax.swing.JTextField txtLatitudPar;
    private javax.swing.JTextField txtLongParAdmin;
    private javax.swing.JTextField txtNombreParAdmin;
    // End of variables declaration//GEN-END:variables
}
