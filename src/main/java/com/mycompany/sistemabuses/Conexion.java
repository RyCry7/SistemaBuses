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

public class Conexion {

    private String url = "jdbc:mysql://localhost:3306/proyectobuses?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private String usuario = "root";
    private String clave = "2023";

    public ResultSet
            
            EjecutaSQL(String Sql) throws ClassNotFoundException, SQLException {
        ResultSet resultado = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection Con = DriverManager.getConnection(url, usuario, clave);
            PreparedStatement pst = Con.prepareStatement(Sql);
            resultado = pst.executeQuery();
            return resultado;
        } catch (SQLException e) {
            return resultado;
        }

    }
    
    public void EjecutarCli (String Sql) throws ClassNotFoundException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection Con = DriverManager.getConnection(url, usuario, clave);
            PreparedStatement pst = Con.prepareStatement(Sql);
            pst.execute();
        } catch (SQLException e){
            
        }
    }

    ResultSet EjecutarSQL(String select__from_usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    PreparedStatement prepareStatement(String consulta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}