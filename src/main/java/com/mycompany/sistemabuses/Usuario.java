/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.sistemabuses;

import java.awt.Color;
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
        
        try {
            ImageIcon wallpaper = new ImageIcon("C:\\Users\\richi\\OneDrive\\Documentos\\NetBeansProjects\\SistemaBuses\\src\\main\\java\\Imagenes\\Usuario.png");
            Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(lblUsuarioFondo.getWidth(), lblUsuarioFondo.getHeight(), Image.SCALE_DEFAULT));
            lblUsuarioFondo.setIcon(icono);
            this.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {
            ImageIcon wallpaper = new ImageIcon("C:\\Users\\richi\\OneDrive\\Documentos\\NetBeansProjects\\SistemaBuses\\src\\main\\java\\Imagenes\\Usuario.png");
            Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(lblUsuarioFondo2.getWidth(), lblUsuarioFondo2.getHeight(), Image.SCALE_DEFAULT));
            lblUsuarioFondo2.setIcon(icono);
            this.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {
            ImageIcon wallpaper = new ImageIcon("C:\\Users\\richi\\OneDrive\\Documentos\\NetBeansProjects\\SistemaBuses\\src\\main\\java\\Imagenes\\Usuario.png");
            Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(lblUsuarioFondo3.getWidth(), lblUsuarioFondo3.getHeight(), Image.SCALE_DEFAULT));
            lblUsuarioFondo3.setIcon(icono);
            this.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
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

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lblUsuarioFondo1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        tbpUsuario = new javax.swing.JTabbedPane();
        pnParadasUsuario = new javax.swing.JPanel();
        lblSelecionAdmin = new javax.swing.JLabel();
        cbxCiudadesUsu = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblParadasCercanas = new javax.swing.JTable();
        lblSeleccionFavoritaUsu = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblbotonpar = new javax.swing.JLabel();
        lblbotonhor = new javax.swing.JLabel();
        lblbotontaf = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblUsuarioFondo = new javax.swing.JLabel();
        pnPrecioUsuario = new javax.swing.JPanel();
        lblEmbraque = new javax.swing.JLabel();
        lblPreTarifa = new javax.swing.JLabel();
        btnConsultar = new javax.swing.JButton();
        cbxEmbarque = new javax.swing.JComboBox<>();
        cbxDesembarqueUsu = new javax.swing.JComboBox<>();
        lblDesembarqueUsu1 = new javax.swing.JLabel();
        lblbotonhor1 = new javax.swing.JLabel();
        lblbotontaf1 = new javax.swing.JLabel();
        lblbotonpar1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblUsuarioFondo2 = new javax.swing.JLabel();
        pnHorarioUsuario = new javax.swing.JPanel();
        lblHorarioAdmin = new javax.swing.JLabel();
        cbxDESTINOuSU = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHorarioUsuario = new javax.swing.JTable();
        cbxHorario = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        lblbotonhor2 = new javax.swing.JLabel();
        lblbotontaf2 = new javax.swing.JLabel();
        lblbotonpar2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblUsuarioFondo3 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        lblUsuarioFondo1.setMaximumSize(new java.awt.Dimension(700, 396));
        lblUsuarioFondo1.setMinimumSize(new java.awt.Dimension(700, 396));
        lblUsuarioFondo1.setPreferredSize(new java.awt.Dimension(700, 396));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(700, 396));
        setMinimumSize(new java.awt.Dimension(700, 396));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setMaximumSize(new java.awt.Dimension(700, 396));
        jPanel1.setMinimumSize(new java.awt.Dimension(700, 396));
        jPanel1.setPreferredSize(new java.awt.Dimension(700, 396));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbpUsuario.setBackground(new java.awt.Color(0, 0, 0));
        tbpUsuario.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        tbpUsuario.setMaximumSize(new java.awt.Dimension(700, 396));
        tbpUsuario.setMinimumSize(new java.awt.Dimension(700, 396));
        tbpUsuario.setOpaque(true);
        tbpUsuario.setPreferredSize(new java.awt.Dimension(700, 396));

        pnParadasUsuario.setMaximumSize(new java.awt.Dimension(700, 396));
        pnParadasUsuario.setMinimumSize(new java.awt.Dimension(700, 396));
        pnParadasUsuario.setPreferredSize(new java.awt.Dimension(700, 396));
        pnParadasUsuario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSelecionAdmin.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblSelecionAdmin.setForeground(new java.awt.Color(255, 255, 255));
        lblSelecionAdmin.setText("Seleccione su ubicacion");
        pnParadasUsuario.add(lblSelecionAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, -1, -1));

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
        pnParadasUsuario.add(cbxCiudadesUsu, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 169, -1));

        jtblParadasCercanas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
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
            jtblParadasCercanas.getColumnModel().getColumn(0).setMinWidth(250);
            jtblParadasCercanas.getColumnModel().getColumn(0).setMaxWidth(250);
            jtblParadasCercanas.getColumnModel().getColumn(1).setMinWidth(50);
            jtblParadasCercanas.getColumnModel().getColumn(1).setMaxWidth(100);
            jtblParadasCercanas.getColumnModel().getColumn(2).setMinWidth(50);
            jtblParadasCercanas.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        pnParadasUsuario.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 250, 396, 130));

        lblSeleccionFavoritaUsu.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblSeleccionFavoritaUsu.setForeground(new java.awt.Color(255, 255, 255));
        lblSeleccionFavoritaUsu.setText("Paradas Cercanas, seleccione su favorita");
        pnParadasUsuario.add(lblSeleccionFavoritaUsu, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, -1, -1));

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Paradas");
        pnParadasUsuario.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 55, -1, -1));

        lblbotonpar.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblbotonpar.setText("PARADAS");
        lblbotonpar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblbotonparMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblbotonparMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblbotonparMouseExited(evt);
            }
        });
        pnParadasUsuario.add(lblbotonpar, new org.netbeans.lib.awtextra.AbsoluteConstraints(428, 182, 106, 48));

        lblbotonhor.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblbotonhor.setText("HORARIOS");
        lblbotonhor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblbotonhorMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblbotonhorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblbotonhorMouseExited(evt);
            }
        });
        pnParadasUsuario.add(lblbotonhor, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 325, -1, -1));

        lblbotontaf.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblbotontaf.setText("TARIFAS");
        lblbotontaf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblbotontafMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblbotontafMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblbotontafMouseExited(evt);
            }
        });
        pnParadasUsuario.add(lblbotontaf, new org.netbeans.lib.awtextra.AbsoluteConstraints(618, 186, -1, 22));

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("¿Qué deseas visualizar?");
        pnParadasUsuario.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 50, -1, -1));

        lblUsuarioFondo.setMaximumSize(new java.awt.Dimension(700, 396));
        lblUsuarioFondo.setMinimumSize(new java.awt.Dimension(700, 396));
        lblUsuarioFondo.setPreferredSize(new java.awt.Dimension(700, 396));
        pnParadasUsuario.add(lblUsuarioFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 409));

        tbpUsuario.addTab("PARADAS CERCANAS", pnParadasUsuario);

        pnPrecioUsuario.setMaximumSize(new java.awt.Dimension(700, 396));
        pnPrecioUsuario.setMinimumSize(new java.awt.Dimension(700, 396));
        pnPrecioUsuario.setPreferredSize(new java.awt.Dimension(700, 396));
        pnPrecioUsuario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblEmbraque.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblEmbraque.setForeground(new java.awt.Color(255, 255, 255));
        lblEmbraque.setText("Lugar de partida");
        pnPrecioUsuario.add(lblEmbraque, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, -1, 20));

        lblPreTarifa.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        lblPreTarifa.setForeground(new java.awt.Color(255, 255, 255));
        lblPreTarifa.setText("$0.00");
        pnPrecioUsuario.add(lblPreTarifa, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 300, 100, 40));

        btnConsultar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnConsultar.setText("CONSULTAR");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });
        pnPrecioUsuario.add(btnConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 571, -1, -1));

        cbxEmbarque.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        cbxEmbarque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxEmbarqueActionPerformed(evt);
            }
        });
        pnPrecioUsuario.add(cbxEmbarque, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 142, -1));

        cbxDesembarqueUsu.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        cbxDesembarqueUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxDesembarqueUsuActionPerformed(evt);
            }
        });
        pnPrecioUsuario.add(cbxDesembarqueUsu, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, 142, -1));

        lblDesembarqueUsu1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblDesembarqueUsu1.setForeground(new java.awt.Color(255, 255, 255));
        lblDesembarqueUsu1.setText("Lugar de destino");
        pnPrecioUsuario.add(lblDesembarqueUsu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, -1, 20));

        lblbotonhor1.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblbotonhor1.setText("HORARIOS");
        lblbotonhor1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblbotonhor1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblbotonhor1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblbotonhor1MouseExited(evt);
            }
        });
        pnPrecioUsuario.add(lblbotonhor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 315, 110, 40));

        lblbotontaf1.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblbotontaf1.setText("TARIFAS");
        lblbotontaf1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblbotontaf1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblbotontaf1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblbotontaf1MouseExited(evt);
            }
        });
        pnPrecioUsuario.add(lblbotontaf1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 180, 100, 40));

        lblbotonpar1.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblbotonpar1.setText("PARADAS");
        lblbotonpar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblbotonpar1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblbotonpar1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblbotonpar1MouseExited(evt);
            }
        });
        pnPrecioUsuario.add(lblbotonpar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 190, 110, 40));

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("¿Qué deseas visualizar?");
        pnPrecioUsuario.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 50, -1, -1));

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Tarifas");
        pnPrecioUsuario.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 55, -1, -1));

        lblUsuarioFondo2.setMaximumSize(new java.awt.Dimension(700, 396));
        lblUsuarioFondo2.setMinimumSize(new java.awt.Dimension(700, 396));
        lblUsuarioFondo2.setPreferredSize(new java.awt.Dimension(700, 396));
        pnPrecioUsuario.add(lblUsuarioFondo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 409));

        tbpUsuario.addTab("PRECIO", pnPrecioUsuario);

        pnHorarioUsuario.setBackground(new java.awt.Color(255, 255, 255));
        pnHorarioUsuario.setForeground(new java.awt.Color(255, 255, 255));
        pnHorarioUsuario.setMaximumSize(new java.awt.Dimension(700, 396));
        pnHorarioUsuario.setMinimumSize(new java.awt.Dimension(700, 396));
        pnHorarioUsuario.setOpaque(false);
        pnHorarioUsuario.setPreferredSize(new java.awt.Dimension(700, 396));
        pnHorarioUsuario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblHorarioAdmin.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblHorarioAdmin.setForeground(new java.awt.Color(255, 255, 255));
        lblHorarioAdmin.setText("Seleccion de punto de partida");
        pnHorarioUsuario.add(lblHorarioAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, -1, -1));

        cbxDESTINOuSU.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        cbxDESTINOuSU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxDESTINOuSUActionPerformed(evt);
            }
        });
        pnHorarioUsuario.add(cbxDESTINOuSU, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 150, -1));

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
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblHorarioUsuario);
        if (tblHorarioUsuario.getColumnModel().getColumnCount() > 0) {
            tblHorarioUsuario.getColumnModel().getColumn(0).setPreferredWidth(150);
        }

        pnHorarioUsuario.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 380, 140));

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
        pnHorarioUsuario.add(cbxHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, 150, -1));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Seleccione el horario a consultar");
        pnHorarioUsuario.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, -1, -1));

        lblbotonhor2.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblbotonhor2.setText("HORARIOS");
        lblbotonhor2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblbotonhor2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblbotonhor2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblbotonhor2MouseExited(evt);
            }
        });
        pnHorarioUsuario.add(lblbotonhor2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 310, 110, 50));

        lblbotontaf2.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblbotontaf2.setText("TARIFAS");
        lblbotontaf2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblbotontaf2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblbotontaf2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblbotontaf2MouseExited(evt);
            }
        });
        pnHorarioUsuario.add(lblbotontaf2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 170, 130, 50));

        lblbotonpar2.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblbotonpar2.setText("PARADAS");
        lblbotonpar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblbotonpar2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblbotonpar2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblbotonpar2MouseExited(evt);
            }
        });
        pnHorarioUsuario.add(lblbotonpar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(428, 182, 130, 50));

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Horarios");
        pnHorarioUsuario.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 55, -1, -1));

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("¿Qué deseas visualizar?");
        pnHorarioUsuario.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 50, -1, -1));

        lblUsuarioFondo3.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuarioFondo3.setMaximumSize(new java.awt.Dimension(700, 396));
        lblUsuarioFondo3.setMinimumSize(new java.awt.Dimension(700, 396));
        lblUsuarioFondo3.setPreferredSize(new java.awt.Dimension(700, 396));
        pnHorarioUsuario.add(lblUsuarioFondo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 410));

        tbpUsuario.addTab("HORARIO ", pnHorarioUsuario);

        jPanel1.add(tbpUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -50, 700, 450));

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

    private void lblbotonparMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblbotonparMouseClicked
        // TODO add your handling code here:
        tbpUsuario.setSelectedIndex(0);
    }//GEN-LAST:event_lblbotonparMouseClicked

    private void lblbotonparMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblbotonparMouseEntered
        // TODO add your handling code here:
        lblbotonpar.setForeground(Color.WHITE);

    }//GEN-LAST:event_lblbotonparMouseEntered

    private void lblbotonparMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblbotonparMouseExited
        // TODO add your handling code here:
        lblbotonpar.setForeground(Color.WHITE);
    lblbotonpar.setForeground(Color.BLACK);

    }//GEN-LAST:event_lblbotonparMouseExited

    private void lblbotonhorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblbotonhorMouseEntered
        // TODO add your handling code here:
                    lblbotonhor.setForeground(Color.WHITE);


    }//GEN-LAST:event_lblbotonhorMouseEntered

    private void lblbotonhorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblbotonhorMouseExited
        // TODO add your handling code here:
        
    lblbotonhor.setForeground(Color.WHITE);
    lblbotonhor.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblbotonhorMouseExited

    private void lblbotontafMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblbotontafMouseEntered
        // TODO add your handling code here:
            lblbotontaf.setForeground(Color.WHITE);
    }//GEN-LAST:event_lblbotontafMouseEntered

    private void lblbotontafMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblbotontafMouseExited
        // TODO add your handling code here:
          lblbotontaf.setForeground(Color.WHITE);
    lblbotontaf.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblbotontafMouseExited

    private void lblbotontafMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblbotontafMouseClicked
        // TODO add your handling code here:
                tbpUsuario.setSelectedIndex(1);

    }//GEN-LAST:event_lblbotontafMouseClicked

    private void lblbotonhorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblbotonhorMouseClicked
        // TODO add your handling code here:
                tbpUsuario.setSelectedIndex(2);

    }//GEN-LAST:event_lblbotonhorMouseClicked

    private void lblbotonpar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblbotonpar1MouseClicked
        // TODO add your handling code here:
                tbpUsuario.setSelectedIndex(0);
    }//GEN-LAST:event_lblbotonpar1MouseClicked

    private void lblbotonpar1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblbotonpar1MouseEntered
        // TODO add your handling code here:
                lblbotonpar1.setForeground(Color.WHITE);

    }//GEN-LAST:event_lblbotonpar1MouseEntered

    private void lblbotonpar1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblbotonpar1MouseExited
        // TODO add your handling code here:
        lblbotonpar1.setForeground(Color.WHITE);
    lblbotonpar1.setForeground(Color.BLACK);

    }//GEN-LAST:event_lblbotonpar1MouseExited

    private void lblbotonhor1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblbotonhor1MouseClicked
        // TODO add your handling code here:
                        tbpUsuario.setSelectedIndex(2);

    }//GEN-LAST:event_lblbotonhor1MouseClicked

    private void lblbotonhor1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblbotonhor1MouseEntered
        // TODO add your handling code here:
                            lblbotonhor1.setForeground(Color.WHITE);

    }//GEN-LAST:event_lblbotonhor1MouseEntered

    private void lblbotonhor1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblbotonhor1MouseExited
        // TODO add your handling code here:
        lblbotonhor1.setForeground(Color.WHITE);
    lblbotonhor1.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblbotonhor1MouseExited

    private void lblbotontaf1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblbotontaf1MouseClicked
        // TODO add your handling code here:
                        tbpUsuario.setSelectedIndex(1);

    }//GEN-LAST:event_lblbotontaf1MouseClicked

    private void lblbotontaf1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblbotontaf1MouseEntered
        // TODO add your handling code here:
                    lblbotontaf1.setForeground(Color.WHITE);

    }//GEN-LAST:event_lblbotontaf1MouseEntered

    private void lblbotontaf1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblbotontaf1MouseExited
        // TODO add your handling code here:
        lblbotontaf1.setForeground(Color.WHITE);
    lblbotontaf1.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblbotontaf1MouseExited

    private void lblbotonhor2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblbotonhor2MouseClicked
        // TODO add your handling code here:
                        tbpUsuario.setSelectedIndex(2);

        
    }//GEN-LAST:event_lblbotonhor2MouseClicked

    private void lblbotonhor2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblbotonhor2MouseEntered
        // TODO add your handling code here:
                            lblbotonhor2.setForeground(Color.WHITE);

        
    }//GEN-LAST:event_lblbotonhor2MouseEntered

    private void lblbotonhor2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblbotonhor2MouseExited
        // TODO add your handling code here:
        lblbotonhor2.setForeground(Color.WHITE);
    lblbotonhor2.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblbotonhor2MouseExited

    private void lblbotontaf2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblbotontaf2MouseClicked
        // TODO add your handling code here:
                        tbpUsuario.setSelectedIndex(1);

    }//GEN-LAST:event_lblbotontaf2MouseClicked

    private void lblbotontaf2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblbotontaf2MouseEntered
        // TODO add your handling code here:
                    lblbotontaf2.setForeground(Color.WHITE);

    }//GEN-LAST:event_lblbotontaf2MouseEntered

    private void lblbotontaf2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblbotontaf2MouseExited
        // TODO add your handling code here:
        lblbotontaf2.setForeground(Color.WHITE);
    lblbotontaf2.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblbotontaf2MouseExited

    private void lblbotonpar2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblbotonpar2MouseClicked
        // TODO add your handling code here:
                tbpUsuario.setSelectedIndex(0);

    }//GEN-LAST:event_lblbotonpar2MouseClicked

    private void lblbotonpar2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblbotonpar2MouseEntered
        // TODO add your handling code here:
                lblbotonpar2.setForeground(Color.WHITE);

    }//GEN-LAST:event_lblbotonpar2MouseEntered

    private void lblbotonpar2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblbotonpar2MouseExited
        // TODO add your handling code here:}
        lblbotonpar2.setForeground(Color.WHITE);
    lblbotonpar2.setForeground(Color.BLACK);

    }//GEN-LAST:event_lblbotonpar2MouseExited





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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jtblParadasCercanas;
    private javax.swing.JLabel lblDesembarqueUsu1;
    private javax.swing.JLabel lblEmbraque;
    private javax.swing.JLabel lblHorarioAdmin;
    private javax.swing.JLabel lblPreTarifa;
    private javax.swing.JLabel lblSeleccionFavoritaUsu;
    private javax.swing.JLabel lblSelecionAdmin;
    private javax.swing.JLabel lblUsuarioFondo;
    private javax.swing.JLabel lblUsuarioFondo1;
    private javax.swing.JLabel lblUsuarioFondo2;
    private javax.swing.JLabel lblUsuarioFondo3;
    private javax.swing.JLabel lblbotonhor;
    private javax.swing.JLabel lblbotonhor1;
    private javax.swing.JLabel lblbotonhor2;
    private javax.swing.JLabel lblbotonpar;
    private javax.swing.JLabel lblbotonpar1;
    private javax.swing.JLabel lblbotonpar2;
    private javax.swing.JLabel lblbotontaf;
    private javax.swing.JLabel lblbotontaf1;
    private javax.swing.JLabel lblbotontaf2;
    private javax.swing.JPanel pnHorarioUsuario;
    private javax.swing.JPanel pnParadasUsuario;
    private javax.swing.JPanel pnPrecioUsuario;
    private javax.swing.JTable tblHorarioUsuario;
    private javax.swing.JTabbedPane tbpUsuario;
    // End of variables declaration//GEN-END:variables
}
