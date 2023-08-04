/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.sistemabuses;

import java.awt.Color;
import java.awt.Image;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class Login1 extends javax.swing.JFrame {

    public static String regUsuario = "";
    String regClave = "";
    
    public Login1() {
        initComponents();
        setLocationRelativeTo(null);
        try {
            ImageIcon wallpaper = new ImageIcon("C:\\Users\\richi\\OneDrive\\Documentos\\NetBeansProjects\\SistemaBuses\\src\\main\\java\\com\\mycompany\\Imagenes\\login.png");
            Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), Image.SCALE_DEFAULT));
            lblImagen.setIcon(icono);
            this.repaint();
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

        jLabel1 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lblCrearUsu = new javax.swing.JLabel();
        lblRegistro = new javax.swing.JLabel();
        pswClave = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        lblImagen = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Usuario");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, -1, -1));

        txtUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 229, 255)));
        getContentPane().add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, 236, -1));

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Clave");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, -1, -1));

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(84, 229, 255));
        jButton1.setText("INGRESO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 300, -1, -1));

        lblCrearUsu.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblCrearUsu.setForeground(new java.awt.Color(255, 255, 255));
        lblCrearUsu.setText("¿No tienes una cuenta?");
        lblCrearUsu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCrearUsuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblCrearUsuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblCrearUsuMouseExited(evt);
            }
        });
        getContentPane().add(lblCrearUsu, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 340, 170, -1));

        lblRegistro.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblRegistro.setText("Registrate");
        lblRegistro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRegistroMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblRegistroMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblRegistroMouseExited(evt);
            }
        });
        getContentPane().add(lblRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 340, 90, -1));

        pswClave.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 229, 255)));
        pswClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pswClaveActionPerformed(evt);
            }
        });
        getContentPane().add(pswClave, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, 236, -1));

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Ingrese sus credenciales para iniciar sesion");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, -1, -1));
        getContentPane().add(lblImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 394));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblCrearUsuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCrearUsuMouseClicked

      
    }//GEN-LAST:event_lblCrearUsuMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
try {
    String usuario = txtUsuario.getText();
    String clave = String.valueOf(pswClave.getPassword());

    String consulta = "SELECT USU_Usuario, ROL_ID FROM usuario WHERE USU_Usuario = ? AND USU_Contraseña = ?";
    Conexion cn = new Conexion();
    PreparedStatement pstmt = cn.prepareStatement(consulta);
    pstmt.setString(1, usuario);
    pstmt.setString(2, clave);
    ResultSet rs = pstmt.executeQuery();

    if (rs.next()) {
        String rol = rs.getString("ROL_ID");
        // Aquí puedes redirigir a diferentes JFrames según el rol del usuario
        if (rol.equalsIgnoreCase("1")) {
            Administrador admin = new Administrador();
            admin.setVisible(true);
        } else if (rol.equalsIgnoreCase("2")) {
            Usuario user = new Usuario();
            user.setVisible(true);
        }

        // Cerrar el JFrame actual
        this.dispose();
    } else {
        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.", "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
    }
} catch (SQLException ex) {
    Logger.getLogger(Login1.class.getName()).log(Level.SEVERE, null, ex);
}       catch (ClassNotFoundException ex) {
            Logger.getLogger(Login1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
private void guardarRegistro(String registro) {
    String rutaArchivo = "C:\\Users\\Asus\\Documents\\ProyectoFinalll\\SistemaBuses\\Login\\log.txt";
    try {
        FileWriter escritor = new FileWriter(rutaArchivo, true);
        PrintWriter out = new PrintWriter(new BufferedWriter(escritor));

        out.println(registro);

        out.close();
        escritor.close();

        System.out.println("Registro guardado en: " + rutaArchivo);
    } catch (IOException e) {
        System.out.println("Error al guardar el registro: " + e.getMessage());
        e.printStackTrace();
    }



    }//GEN-LAST:event_jButton1ActionPerformed

    private void pswClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pswClaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pswClaveActionPerformed

    private void lblCrearUsuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCrearUsuMouseEntered
    
    }//GEN-LAST:event_lblCrearUsuMouseEntered

    private void lblCrearUsuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCrearUsuMouseExited

    }//GEN-LAST:event_lblCrearUsuMouseExited

    private void lblRegistroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRegistroMouseEntered
         lblRegistro.setForeground(Color.WHITE);
    }//GEN-LAST:event_lblRegistroMouseEntered

    private void lblRegistroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRegistroMouseExited
      lblRegistro.setForeground(new Color(84,229,255));
    }//GEN-LAST:event_lblRegistroMouseExited

    private void lblRegistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRegistroMouseClicked
           CreacionLogin log = new CreacionLogin();
           log.setVisible(true);
           this.setVisible(false);
    }//GEN-LAST:event_lblRegistroMouseClicked

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
            java.util.logging.Logger.getLogger(Login1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Login1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblCrearUsu;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblRegistro;
    private javax.swing.JPasswordField pswClave;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
