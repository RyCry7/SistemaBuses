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
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Asus
 */
public class Usuario extends javax.swing.JFrame {

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
         MostrarcbxFavorito();
       
        
         

          
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
        DefaultTableModel modeloTabla = (DefaultTableModel) tblDatosPrecio.getModel();
        modeloTabla.setRowCount(0); // Limpiar la tabla antes de cargar los datos

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

            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator('.');
            DecimalFormat df = new DecimalFormat("0.00", symbols);
            
            // Agregar las tarifas a la tabla
            while (result.next()) {
                double tarifa = result.getDouble("RUT_Tarifa");
                String tarifaFormateada = df.format(tarifa);
                modeloTabla.addRow(new Object[]{nombreCiudadEmbarque, nombreCiudadDesembarque, tarifaFormateada});
            }

            result.close();
            statement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
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
    public void MostrarcbxFavorito() {
        Conexion c1 = new Conexion();
        try {

            String combo = "SELECT UBI_Nombre FROM ubicacion; ";
            ResultSet resulSet = c1.EjecutarSQL(combo);
            while (resulSet.next()) {

                String nombre = resulSet.getString("UBI_Nombre");
                cbxUbicacionFavorita.addItem(nombre);

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

    if (horarioSeleccionado == null) {
        // No se ha seleccionado ningún horario, no realizar ninguna acción
        return;
    }

    Conexion c1 = new Conexion();
    try {
        String sql = "SELECT DISTINCT "
                     + "paradas.PAR_Nombre AS Parada, "
                     + "TIME_FORMAT(ADDTIME(horario.HOR_Hora, paradas.tiempodesdeibarra), '%H:%i:%s') AS HoraSumada "
                     + "FROM horario "
                     + "JOIN paradas ON true "
                     + "WHERE horario.HOR_Hora = ?";

        PreparedStatement statement = c1.prepareStatement(sql);
        statement.setString(1, horarioSeleccionado);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String parada = resultSet.getString("Parada");
            String horaSumada = resultSet.getString("HoraSumada");

            modeloTabla.addRow(new Object[]{parada, horaSumada});
        }

        resultSet.close();
        statement.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
   
   public void MostrarHorarioUsuarioyACHAY() {
    DefaultTableModel modeloTabla = (DefaultTableModel) tblHorarioUsuario.getModel();
    modeloTabla.setRowCount(0); // Limpiar la tabla antes de cargar los datos

    Conexion c1 = new Conexion();
    try {
        String sql = "SELECT DISTINCT "
                     + "paradas.PAR_Nombre AS Parada, "
                     + "TIME_FORMAT(ADDTIME(horario.HOR_Hora, paradas.tiempodesdeyachay), '%H:%i:%s') AS HoraSumada "
                     + "FROM horario "
                     + "JOIN paradas ON true "
                     + "WHERE (paradas.PAR_ID >= 13 OR paradas.PAR_ID <= 1) "
                     + "ORDER BY paradas.PAR_ID ASC";

        PreparedStatement statement = c1.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String parada = resultSet.getString("Parada");
            String horaSumada = resultSet.getString("HoraSumada");

            modeloTabla.addRow(new Object[]{parada, horaSumada});
        }

        resultSet.close();
        statement.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}




    public void MostrarParFavorita() {
    DefaultTableModel modeloTabla = (DefaultTableModel) tblUbicacion.getModel();
    modeloTabla.setRowCount(0); // Limpiar la tabla antes de cargar los datos
    
    int idSeleccionado = cbxUbicacionFavorita.getSelectedIndex() + 1; // Sumar 1 porque los índices son 0-based
    
    Conexion c1 = new Conexion();
    try {
        String sql = "SELECT PAR_Nombre, UBI_Nombre FROM PARADAS INNER JOIN UBICACION ON PARADAS.UBI_ID = UBICACION.UBI_ID WHERE PARADAS.UBI_ID = ?";

        PreparedStatement statement = c1.conectar().prepareStatement(sql);
        statement.setInt(1, idSeleccionado);
        ResultSet resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            String ubicacion = resultSet.getString("UBI_Nombre");
            String parada = resultSet.getString("PAR_Nombre");
            modeloTabla.addRow(new Object[]{ubicacion, parada});
        }
        
        resultSet.close();
        statement.close();
    } catch (Exception e) {
        e.printStackTrace();
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
        jScrollPane4 = new javax.swing.JScrollPane();
        tblUbicacion = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        cbxUbicacionFavorita = new javax.swing.JComboBox<>();
        btnParadaFav = new javax.swing.JButton();

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
                java.lang.String.class, java.lang.String.class, java.lang.Object.class
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
        jScrollPane2.setViewportView(tblDatosPrecio);

        cbxEmbarque.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        cbxDesembarqueUsu.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        javax.swing.GroupLayout pnPrecioUsuarioLayout = new javax.swing.GroupLayout(pnPrecioUsuario);
        pnPrecioUsuario.setLayout(pnPrecioUsuarioLayout);
        pnPrecioUsuarioLayout.setHorizontalGroup(
            pnPrecioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPrecioUsuarioLayout.createSequentialGroup()
                .addGroup(pnPrecioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnPrecioUsuarioLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(pnPrecioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnPrecioUsuarioLayout.createSequentialGroup()
                                .addGroup(pnPrecioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbxEmbarque, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnPrecioUsuarioLayout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addComponent(lblEmbraque)))
                                .addGap(101, 101, 101)
                                .addGroup(pnPrecioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDesembarqueUsu)
                                    .addComponent(cbxDesembarqueUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(pnPrecioUsuarioLayout.createSequentialGroup()
                        .addGap(198, 198, 198)
                        .addComponent(btnConsultar)))
                .addContainerGap(2132, Short.MAX_VALUE))
        );
        pnPrecioUsuarioLayout.setVerticalGroup(
            pnPrecioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPrecioUsuarioLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(pnPrecioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblEmbraque)
                    .addComponent(lblDesembarqueUsu))
                .addGroup(pnPrecioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnPrecioUsuarioLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(cbxEmbarque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnPrecioUsuarioLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxDesembarqueUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(btnConsultar)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(381, Short.MAX_VALUE))
        );

        tbpUsuario.addTab("PRECIO", pnPrecioUsuario);

        lblHorarioAdmin.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblHorarioAdmin.setText("SELECCIONE SU DESTINO DE SALIDA");

        cbxDESTINOuSU.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

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
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblHorarioUsuario);

        cbxHorario.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxHorarioItemStateChanged(evt);
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
                .addContainerGap(1981, Short.MAX_VALUE))
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
                .addContainerGap(168, Short.MAX_VALUE))
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
        lblSeleccionFavoritaUsu.setText("SELECCIONE SU PARADA FAVORITA");

        tblUbicacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "UBICACION", "NOMBRE PARADA"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblUbicacion);

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel2.setText("SELECCIONE LA UBICACION");

        cbxUbicacionFavorita.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxUbicacionFavoritaItemStateChanged(evt);
            }
        });

        btnParadaFav.setText("GUARDAR PARADA FAVORITA");
        btnParadaFav.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                btnParadaFavItemStateChanged(evt);
            }
        });
        btnParadaFav.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnParadaFavActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnParadasUsuarioLayout = new javax.swing.GroupLayout(pnParadasUsuario);
        pnParadasUsuario.setLayout(pnParadasUsuarioLayout);
        pnParadasUsuarioLayout.setHorizontalGroup(
            pnParadasUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnParadasUsuarioLayout.createSequentialGroup()
                .addGroup(pnParadasUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnParadasUsuarioLayout.createSequentialGroup()
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
                        .addGap(409, 409, 409)
                        .addComponent(lblSeleccionFavoritaUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnParadasUsuarioLayout.createSequentialGroup()
                        .addGap(236, 236, 236)
                        .addComponent(jLabel2))
                    .addGroup(pnParadasUsuarioLayout.createSequentialGroup()
                        .addGap(218, 218, 218)
                        .addGroup(pnParadasUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnParadasUsuarioLayout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(btnParadaFav))
                            .addComponent(cbxUbicacionFavorita, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(1633, Short.MAX_VALUE))
        );
        pnParadasUsuarioLayout.setVerticalGroup(
            pnParadasUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnParadasUsuarioLayout.createSequentialGroup()
                .addGroup(pnParadasUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnParadasUsuarioLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(lblSelecionAdmin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxCiudadesUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnParadasUsuarioLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblSeleccionFavoritaUsu)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbxUbicacionFavorita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnParadasUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnParadasUsuarioLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnParadasUsuarioLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(btnParadaFav)))
                .addGap(183, 183, 183))
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
     MostrarHorarioUsuarioyACHAY();
    
    }//GEN-LAST:event_cbxHorarioItemStateChanged

    private void cbxUbicacionFavoritaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxUbicacionFavoritaItemStateChanged
      MostrarParFavorita();
    }//GEN-LAST:event_cbxUbicacionFavoritaItemStateChanged

    private void btnParadaFavActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnParadaFavActionPerformed
btnParadaFav.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la cédula ingresada por el usuario
                String cedulaIngresada = JOptionPane.showInputDialog("Ingrese su número de cédula:");
                if (cedulaIngresada != null && !cedulaIngresada.isEmpty()) {
                    int cedula = Integer.parseInt(cedulaIngresada);

                    // Obtener el índice seleccionado en el JComboBox
                    int idSeleccionado = cbxUbicacionFavorita.getSelectedIndex() + 1;

                    // Verificar si la cédula ingresada existe en la base de datos
                    if (verificarCedulaExistente(cedula)) {
                        // Guardar la cédula y el idSeleccionado en la tabla parfav
                        guardarParadaFavorita(cedula, idSeleccionado);
                        JOptionPane.showMessageDialog(null, "Parada guardada como favorita.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario incorrecto. La cédula no existe.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese su número de cédula.");
                }
            }
        });
    }

    private boolean verificarCedulaExistente(int cedula) {
       Conexion c1 = new Conexion();
    try {
        String sql = "SELECT COUNT(*) FROM USUARIO WHERE USU_Cedula = ?";
        PreparedStatement statement = c1.conectar().prepareStatement(sql);
        statement.setInt(1, cedula);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);
        resultSet.close();
        statement.close();
        return count > 0;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }

       
    }

    private void guardarParadaFavorita(int cedula, int idParada) {
    Conexion c1 = new Conexion();
    try {
        String sql = "INSERT INTO PARFAV (USU_Cedula, PAR_ID) VALUES (?, ?)";
        PreparedStatement statement = c1.conectar().prepareStatement(sql);
        statement.setInt(1, cedula);
        statement.setInt(2, idParada);
        statement.executeUpdate();
        statement.close();
    } catch (Exception e) {
        e.printStackTrace();
    }

         
    }//GEN-LAST:event_btnParadaFavActionPerformed

    private void btnParadaFavItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_btnParadaFavItemStateChanged
       
        
      
    }//GEN-LAST:event_btnParadaFavItemStateChanged

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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Usuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnParadaFav;
    private javax.swing.JComboBox<String> cbxCiudadesUsu;
    private javax.swing.JComboBox<String> cbxDESTINOuSU;
    private javax.swing.JComboBox<String> cbxDesembarqueUsu;
    private javax.swing.JComboBox<String> cbxEmbarque;
    private javax.swing.JComboBox<String> cbxHorario;
    private javax.swing.JComboBox<String> cbxUbicacionFavorita;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jtblParadasCercanas;
    private javax.swing.JLabel lblDesembarqueUsu;
    private javax.swing.JLabel lblEmbraque;
    private javax.swing.JLabel lblHorarioAdmin;
    private javax.swing.JLabel lblSeleccionFavoritaUsu;
    private javax.swing.JLabel lblSelecionAdmin;
    private javax.swing.JPanel pnHorarioUsuario;
    private javax.swing.JPanel pnParadasUsuario;
    private javax.swing.JPanel pnPrecioUsuario;
    private javax.swing.JTable tblDatosPrecio;
    private javax.swing.JTable tblHorarioUsuario;
    private javax.swing.JTable tblUbicacion;
    private javax.swing.JTabbedPane tbpUsuario;
    // End of variables declaration//GEN-END:variables
}
