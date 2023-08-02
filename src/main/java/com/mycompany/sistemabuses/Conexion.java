/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabuses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Asus
 */
public class Conexion {




    private String url = "jdbc:mysql://localhost:3306/proyecfinall?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private String usuario = "root";
    private String clave = "2000";

    public Connection conectar() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, usuario, clave);
    }

    public ResultSet EjecutarSQL(String sql) throws ClassNotFoundException, SQLException {
        ResultSet resultado = null;
        try {
            Connection con = conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            resultado = pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public void EjecutarCli(String sql) throws ClassNotFoundException {
        try {
            Connection con = conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException, ClassNotFoundException {
        Connection con = conectar();
        return con.prepareStatement(sql);
    }


   // PreparedStatement prepareStatement(String consulta) {
    //    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  //  }


    Object getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
