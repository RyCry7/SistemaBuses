/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabuses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Asus
 */
public class Archivo {
     private String url = "jdbc:mysql://localhost:3306/proyecfinall?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private String Usuario = "root";
    private String clave = "2000";

    public void obtener() {
        // Ruta y nombre del archivo de propiedades
        String rutaArchivo = "C:\\Users\\Asus\\Documents\\ProyectoFinalll\\SistemaBuses\\Conexion\\ConexionBdd.txt";

        try {
            File archivo = new File(rutaArchivo);

            // Verificar si el archivo ya existe
            if (archivo.exists()) {
                // Cargar las propiedades existentes desde el archivo
                Properties propiedadesExistentes = new Properties();
                propiedadesExistentes.load(new FileInputStream(archivo));

                // Obtener los valores actuales de las variables
                String urlActual = propiedadesExistentes.getProperty("url");
                String usuarioActual = propiedadesExistentes.getProperty("Usuario");
                String contraseniaActual = propiedadesExistentes.getProperty("clave");

                // Verificar si los valores actuales son diferentes a los valores existentes
                if (!url.equals(urlActual) || !Usuario.equals(usuarioActual) || !clave.equals(contraseniaActual)) {
                    // Crear objeto Properties
                    Properties conex = new Properties();

                    // Establecer las variables de conexión en las propiedades
                    conex.setProperty("url", url);
                    conex.setProperty("usuario", Usuario);
                    conex.setProperty("clave", clave);

                    // Guardar las propiedades actualizadas en el archivo
                    try (FileOutputStream archivoPropiedades = new FileOutputStream(archivo)) {
                        conex.store(archivoPropiedades, "Propiedades de conexión MySQL");
                    }

                    System.out.println("Archivo de propiedades actualizado en: " + rutaArchivo);
                } else {
                    System.out.println("El archivo de propiedades ya está actualizado en: " + rutaArchivo);
                }
            } else {
                // Crear objeto Properties
                Properties conex = new Properties();

                // Establecer las variables de conexión en las propiedades
                conex.setProperty("url", url);
                conex.setProperty("usuario", Usuario);
                conex.setProperty("clave", clave);

                // Guardar las propiedades en el archivo
                try (FileOutputStream archivoPropiedades = new FileOutputStream(archivo)) {
                    conex.store(archivoPropiedades, "Propiedades de conexión MySQL");
                }

                System.out.println("Archivo de propiedades creado en: " + rutaArchivo);
            }
        } catch (IOException e) {
            System.out.println("Error al crear o acceder al archivo de propiedades: " + e.getMessage());
        }
    }
}
