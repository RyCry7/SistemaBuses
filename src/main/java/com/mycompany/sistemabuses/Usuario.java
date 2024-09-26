/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.sistemabuses;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Asus
 */
public class Usuario extends javax.swing.JFrame {
// Agregar fechas u horarios como objetos java.sql.Time
    private String cedulaUsuario;

    public Usuario() {
        initComponents();
        inicializarComboBoxCiudades();
        cargarCiudadesEnComboBox(cbxCiudadesUsu);
        setLocationRelativeTo(null);
         MostrarcbxEmbarque();
         Mostrarcbxdesembarque() ;
         MostrarcbxDestinoSalida();
         MostrarcbxHorario();
        inicializarComboBoxUbicaciones();
        
        // Agregar fechas u horarios como objetos java.sql.Time

         

        
       

    
   }
    public void MostrarcbxHorario(int ubicacionID) {
    cbxHorario.removeAllItems(); // Limpiar el combo box antes de cargar los horarios

    Conexion c1 = new Conexion();
    
    try {
        String combo = "SELECT HOR_Hora FROM horario WHERE UBI_ID = ?;";
        PreparedStatement statement = c1.prepareStatement(combo);
        statement.setInt(1, ubicacionID);
        ResultSet resulSet = statement.executeQuery();
        
        while (resulSet.next()) {
            String hora = resulSet.getString("HOR_Hora");
            cbxHorario.addItem(hora);
        }
        
        resulSet.close();
        statement.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public void inicializarComboBoxUbicaciones() {
    cbxDESTINOuSU.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedUbicacion = (String) cbxDESTINOuSU.getSelectedItem();
            if (selectedUbicacion != null) {
                int ubicacionID = obtenerIDUbicacion(selectedUbicacion);
                MostrarcbxHorario(ubicacionID);
            }
        }
    });
}

    
    

    
    
   public void cargarPrecio() {
    String nombreCiudadEmbarque = (String) cbxEmbarque.getSelectedItem();
    String nombreCiudadDesembarque = (String) cbxDesembarqueUsu.getSelectedItem();

    int idCiudadEmbarque = obtenerIDUbicacion(nombreCiudadEmbarque);
    int idCiudadDesembarque = obtenerIDUbicacion(nombreCiudadDesembarque);

    try {
        Connection connection = new Conexion().conectar();

        String sql = "SELECT RUT_Tarifa FROM rutas WHERE (RUT_Ubi_A = ? AND RUT_Ubi_B = ?) OR (RUT_Ubi_A = ? AND RUT_Ubi_B = ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idCiudadEmbarque);
        statement.setInt(2, idCiudadDesembarque);
        statement.setInt(3, idCiudadDesembarque);
        statement.setInt(4, idCiudadEmbarque);

        ResultSet result = statement.executeQuery();

        double totalTarifa = 0;
        int count = 0;

        // Calcular el promedio de las tarifas
        while (result.next()) {
            double tarifa = result.getDouble("RUT_Tarifa");
            totalTarifa += tarifa;
            count++;
        }

        result.close();
        statement.close();
        connection.close();

        if (count > 0) {
            double promedioTarifa = totalTarifa / count;
            // Formatear el promedioTarifa a dos decimales
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator('.');
            DecimalFormat df = new DecimalFormat("$0.00", symbols);
            String tarifaFormateada = df.format(promedioTarifa);
            lblPreTarifa.setText(tarifaFormateada);
        } else {
            lblPreTarifa.setText("$0.00"); // Si no hay tarifas, mostrar "N/A"
        }
    } catch (SQLException | ClassNotFoundException ex) {
        ex.printStackTrace();
        lblPreTarifa.setText("$0.00"); // Si ocurre una excepción, mostrar "N/A"
    }
}


    public void configurarFormatoCeldas(JTable table) {
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
        table.getColumnModel().getColumn(2).setCellRenderer(renderer);
    }
     public void MostrarcbxHorario() {
           Conexion c1 = new Conexion();
        try {

            String combo = "SELECT HOR_Hora FROM horario; ";
            ResultSet resulSet = c1.EjecutarSQL(combo);
            while (resulSet.next()) {

                String nombre = resulSet.getString("HOR_Hora");
                cbxHorario.addItem(nombre);

            }
        } catch (Exception e) {

        }
     }

    
   
    public void MostrarcbxDestinoSalida() {
        Conexion c1 = new Conexion();
        try {

            String combo = "SELECT UBI_Nombre FROM ubicacion WHERE UBI_Nombre IN ('Ibarra', 'Yachay');";
            ResultSet resulSet = c1.EjecutarSQL(combo);
            while (resulSet.next()) {

                String nombre = resulSet.getString("UBI_Nombre");
                cbxDESTINOuSU.addItem(nombre);

            }
        } catch (Exception e) {

        }
    }

    public void MostrarcbxEmbarque() {
        Conexion c1 = new Conexion();
        try {

            String combo = "SELECT UBI_Nombre FROM ubicacion; ";
            ResultSet resulSet = c1.EjecutarSQL(combo);
            while (resulSet.next()) {

                String nombre = resulSet.getString("UBI_Nombre");
                cbxEmbarque.addItem(nombre);

            }
        } catch (Exception e) {

        }
    }
    public void Mostrarcbxdesembarque() {
        Conexion c1 = new Conexion();
        try {

            String combo = "SELECT UBI_Nombre FROM ubicacion; ";
            ResultSet resulSet = c1.EjecutarSQL(combo);
            while (resulSet.next()) {

                String nombre = resulSet.getString("UBI_Nombre");
                cbxDesembarqueUsu.addItem(nombre);

            }
        } catch (Exception e) {

        }
    }

    public void MostrarFormulario() {
        Conexion c1 = new Conexion();
        try {

            String combo = "SELECT UBI_Nombre FROM ubicacion; ";
            ResultSet resulSet = c1.EjecutarSQL(combo);
            while (resulSet.next()) {

                String nombre = resulSet.getString("UBI_Nombre");
                cbxDESTINOuSU.addItem(nombre);

            }
        } catch (Exception e) {

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
                modeloTabla.addRow(new Object[]{nombreParada, latitud, longitud});
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
    
   public void MostrarHorarioUsuario() {
    DefaultTableModel modeloTabla = (DefaultTableModel) tblHorarioUsuario.getModel();
    modeloTabla.setRowCount(0); // Limpiar la tabla antes de cargar los datos

    String horarioSeleccionado = (String) cbxHorario.getSelectedItem();
    String destinoSeleccionado = (String) cbxDESTINOuSU.getSelectedItem();

    if (horarioSeleccionado == null || destinoSeleccionado == null) {
        // No se ha seleccionado algún horario o destino, no realizar ninguna acción
        return;
    }

    boolean esYachay = destinoSeleccionado.equals("Yachay");
    String tiempoColumn;

    if (esYachay) {
        tiempoColumn = "TiempoDesdeYachay";
    } else {
        tiempoColumn = "TiempoDesdeIbarra";
    }

    Conexion c1 = new Conexion();
    try {
        String sql = "SELECT PAR_Nombre, TIME_FORMAT(ADDTIME(" + tiempoColumn + ", ?), '%H:%i:%s') AS HoraSumada " +
                     "FROM PARADAS_TIEMPO_" + (esYachay ? "YACHAY" : "IBARRA");

        PreparedStatement statement = c1.prepareStatement(sql);
        statement.setString(1, horarioSeleccionado);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String parada = resultSet.getString("PAR_Nombre");
            String horaSumada = resultSet.getString("HoraSumada");
            modeloTabla.addRow(new Object[]{parada, horaSumada});
        }

        resultSet.close();
        statement.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}



public void setCedulaUsuario(String cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
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
        lblPreTarifa = new javax.swing.JLabel();
        btnConsultar = new javax.swing.JButton();
        cbxEmbarque = new javax.swing.JComboBox<>();
        cbxDesembarqueUsu = new javax.swing.JComboBox<>();
        lblDesembarqueUsu1 = new javax.swing.JLabel();
        pnHorarioUsuario = new javax.swing.JPanel();
        lblHorarioAdmin = new javax.swing.JLabel();
        cbxDESTINOuSU = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHorarioUsuario = new javax.swing.JTable();
        cbxHorario = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        pnParadasUsuario = new javax.swing.JPanel();
        lblSelecionAdmin = new javax.swing.JLabel();
        cbxCiudadesUsu = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblParadasCercanas = new javax.swing.JTable();
        lblSeleccionFavoritaUsu = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbpUsuario.setBackground(new java.awt.Color(0, 0, 0));
        tbpUsuario.setForeground(new java.awt.Color(255, 255, 255));
        tbpUsuario.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N

        lblEmbraque.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblEmbraque.setText("Lugar de partida");

        lblPreTarifa.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblPreTarifa.setText("$0.00");

        btnConsultar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnConsultar.setText("CONSULTAR");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        cbxEmbarque.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        cbxEmbarque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxEmbarqueActionPerformed(evt);
            }
        });

        cbxDesembarqueUsu.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        cbxDesembarqueUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxDesembarqueUsuActionPerformed(evt);
            }
        });

        lblDesembarqueUsu1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblDesembarqueUsu1.setText("Lugar de destino");

        javax.swing.GroupLayout pnPrecioUsuarioLayout = new javax.swing.GroupLayout(pnPrecioUsuario);
        pnPrecioUsuario.setLayout(pnPrecioUsuarioLayout);
        pnPrecioUsuarioLayout.setHorizontalGroup(
            pnPrecioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPrecioUsuarioLayout.createSequentialGroup()
                .addGroup(pnPrecioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnPrecioUsuarioLayout.createSequentialGroup()
                        .addGap(198, 198, 198)
                        .addComponent(btnConsultar))
                    .addGroup(pnPrecioUsuarioLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblEmbraque))
                    .addGroup(pnPrecioUsuarioLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cbxEmbarque, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnPrecioUsuarioLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cbxDesembarqueUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnPrecioUsuarioLayout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(lblPreTarifa)))
                .addContainerGap(1443, Short.MAX_VALUE))
            .addGroup(pnPrecioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnPrecioUsuarioLayout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addComponent(lblDesembarqueUsu1)
                    .addContainerGap(1606, Short.MAX_VALUE)))
        );
        pnPrecioUsuarioLayout.setVerticalGroup(
            pnPrecioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPrecioUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblEmbraque)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxEmbarque, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnPrecioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnPrecioUsuarioLayout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(btnConsultar))
                    .addGroup(pnPrecioUsuarioLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(cbxDesembarqueUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblPreTarifa)))
                .addContainerGap(413, Short.MAX_VALUE))
            .addGroup(pnPrecioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnPrecioUsuarioLayout.createSequentialGroup()
                    .addGap(75, 75, 75)
                    .addComponent(lblDesembarqueUsu1)
                    .addContainerGap(504, Short.MAX_VALUE)))
        );

        tbpUsuario.addTab("PRECIO", pnPrecioUsuario);

        lblHorarioAdmin.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblHorarioAdmin.setText("SELECCIONE SU DESTINO DE SALIDA");

        cbxDESTINOuSU.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        cbxDESTINOuSU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxDESTINOuSUActionPerformed(evt);
            }
        });

        tblHorarioUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "PARADA", "TIEMPO ESTIMADO DE LLEGADA"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblHorarioUsuario);

        cbxHorario.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxHorarioItemStateChanged(evt);
            }
        });
        cbxHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxHorarioActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel1.setText("SELECCIONE EL HORARIO ");

        javax.swing.GroupLayout pnHorarioUsuarioLayout = new javax.swing.GroupLayout(pnHorarioUsuario);
        pnHorarioUsuario.setLayout(pnHorarioUsuarioLayout);
        pnHorarioUsuarioLayout.setHorizontalGroup(
            pnHorarioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHorarioUsuarioLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(pnHorarioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 696, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnHorarioUsuarioLayout.createSequentialGroup()
                        .addGroup(pnHorarioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnHorarioUsuarioLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(cbxDESTINOuSU, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblHorarioAdmin))
                        .addGap(183, 183, 183)
                        .addGroup(pnHorarioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnHorarioUsuarioLayout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jLabel1)))))
                .addContainerGap(1017, Short.MAX_VALUE))
        );
        pnHorarioUsuarioLayout.setVerticalGroup(
            pnHorarioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHorarioUsuarioLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(pnHorarioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHorarioAdmin)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnHorarioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxDESTINOuSU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(109, 109, 109)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(155, Short.MAX_VALUE))
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
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblParadasCercanas.setColumnSelectionAllowed(true);
        jtblParadasCercanas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblParadasCercanasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtblParadasCercanas);
        jtblParadasCercanas.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jtblParadasCercanas.getColumnModel().getColumnCount() > 0) {
            jtblParadasCercanas.getColumnModel().getColumn(0).setResizable(false);
            jtblParadasCercanas.getColumnModel().getColumn(1).setResizable(false);
            jtblParadasCercanas.getColumnModel().getColumn(2).setResizable(false);
        }

        lblSeleccionFavoritaUsu.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblSeleccionFavoritaUsu.setText("Paradas Cercanas, seleccione su favorita");

        javax.swing.GroupLayout pnParadasUsuarioLayout = new javax.swing.GroupLayout(pnParadasUsuario);
        pnParadasUsuario.setLayout(pnParadasUsuarioLayout);
        pnParadasUsuarioLayout.setHorizontalGroup(
            pnParadasUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnParadasUsuarioLayout.createSequentialGroup()
                .addGroup(pnParadasUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnParadasUsuarioLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(lblSelecionAdmin))
                    .addGroup(pnParadasUsuarioLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(cbxCiudadesUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(pnParadasUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnParadasUsuarioLayout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(lblSeleccionFavoritaUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnParadasUsuarioLayout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(894, Short.MAX_VALUE))
        );
        pnParadasUsuarioLayout.setVerticalGroup(
            pnParadasUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnParadasUsuarioLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pnParadasUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSelecionAdmin)
                    .addComponent(lblSeleccionFavoritaUsu))
                .addGroup(pnParadasUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnParadasUsuarioLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxCiudadesUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnParadasUsuarioLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(388, Short.MAX_VALUE))
        );

        tbpUsuario.addTab("PARADAS CERCANAS", pnParadasUsuario);

        jPanel1.add(tbpUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
     cargarPrecio();
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

    private void cbxHorarioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxHorarioItemStateChanged
     MostrarHorarioUsuario();
    
    }//GEN-LAST:event_cbxHorarioItemStateChanged

    private void cbxDESTINOuSUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxDESTINOuSUActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxDESTINOuSUActionPerformed

    private void cbxHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxHorarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxHorarioActionPerformed

    
    
    private void jtblParadasCercanasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblParadasCercanasMouseClicked
 int filaSeleccionada = jtblParadasCercanas.getSelectedRow();

    if (filaSeleccionada != -1) {
        // Obtener el nombre de la parada seleccionada en la tabla
        String nombreParadaSeleccionada = (String) jtblParadasCercanas.getValueAt(filaSeleccionada, 0);

        try {
            Connection connection = new Conexion().conectar();

            // Consulta para obtener el PAR_ID de la parada seleccionada
            String sql = "SELECT PAR_ID FROM PARADAS WHERE PAR_Nombre = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nombreParadaSeleccionada);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Obtener el PAR_ID de la parada seleccionada
                int paradaID = resultSet.getInt("PAR_ID");
                System.out.println("PAR_ID de la parada seleccionada: " + paradaID);

                // Now, retrieve the USU_Cedula of the logged-in user using regUsuario
                String sql2 = "SELECT USU_Cedula FROM USUARIO WHERE USU_Usuario = ?";
                PreparedStatement statement2 = connection.prepareStatement(sql2);
                statement2.setString(1, Login1.regUsuario); // Use regUsuario to get USU_Cedula
                ResultSet resultSet2 = statement2.executeQuery();

                if (resultSet2.next()) {
                    // Obtener el USU_Cedula del usuario
                    String cedulaUsuario = resultSet2.getString("USU_Cedula");
                    System.out.println("Cédula del usuario: " + cedulaUsuario);

                    // Call the stored procedure to insert the data
                    String insertProcedure = "{CALL InsertarParadaFavorita(?, ?)}";
                    CallableStatement callableStatement = connection.prepareCall(insertProcedure);
                    callableStatement.setString(1, cedulaUsuario);
                    callableStatement.setInt(2, paradaID);
                    callableStatement.execute();
                    callableStatement.close();

                    // Show success message
                    JOptionPane.showMessageDialog(this, "La parada favorita se guardó exitosamente.");
                } else {
                    System.out.println("No se encontró la cédula del usuario.");
                }

                resultSet2.close();
                statement2.close();
            } else {
                System.out.println("No se encontró el PAR_ID para la parada seleccionada.");
            }

            // Cerrar recursos
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    }//GEN-LAST:event_jtblParadasCercanasMouseClicked

    private void cbxEmbarqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxEmbarqueActionPerformed
        // TODO add your handling code here:
        cargarPrecio();
    }//GEN-LAST:event_cbxEmbarqueActionPerformed

    private void cbxDesembarqueUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxDesembarqueUsuActionPerformed
        // TODO add your handling code here:
        cargarPrecio();
    }//GEN-LAST:event_cbxDesembarqueUsuActionPerformed

    
     
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
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new Usuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultar;
    private javax.swing.JComboBox<String> cbxCiudadesUsu;
    private javax.swing.JComboBox<String> cbxDESTINOuSU;
    private javax.swing.JComboBox<String> cbxDesembarqueUsu;
    private javax.swing.JComboBox<String> cbxEmbarque;
    private javax.swing.JComboBox<String> cbxHorario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jtblParadasCercanas;
    private javax.swing.JLabel lblDesembarqueUsu1;
    private javax.swing.JLabel lblEmbraque;
    private javax.swing.JLabel lblHorarioAdmin;
    private javax.swing.JLabel lblPreTarifa;
    private javax.swing.JLabel lblSeleccionFavoritaUsu;
    private javax.swing.JLabel lblSelecionAdmin;
    private javax.swing.JPanel pnHorarioUsuario;
    private javax.swing.JPanel pnParadasUsuario;
    private javax.swing.JPanel pnPrecioUsuario;
    private javax.swing.JTable tblHorarioUsuario;
    private javax.swing.JTabbedPane tbpUsuario;
    // End of variables declaration//GEN-END:variables
}
