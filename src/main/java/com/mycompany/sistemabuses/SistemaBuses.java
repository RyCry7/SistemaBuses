/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sistemabuses;

import java.sql.ResultSet;

/**
 *
 * @author Richely
 */
public class SistemaBuses {

     public static void main(String[] args)throws ClassNotFoundException {
         Conexion con =new  Conexion ();
        ResultSet resultado = con.EjecutarSQL("SELECT * FROM roles");
        try {
            while(resultado.next()){
              System.out.println(resultado.getString(2));
            }
        } catch (Exception e) {
            System.out.println("error de consulta");
        }
        
    }
}
