/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tarea4;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Jeagil
 */
public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://almacenitla-db.ctam6uiuy8ez.us-east-1.rds.amazonaws.com:3306/almacenitlafinal";
    private static final String USER = "estuditlafinal";
    private static final String PASSWORD = "itla123.";

     /**
     * Obtiene una conexión a la base de datos.
     * <p>
     * Este método utiliza el patrón de diseño Singleton para garantizar que
     * solo se cree una instancia de la conexión a la base de datos.
     * </p>
     * @return una conexión a la base de datos o null si ocurre un error
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            return null;
        }
    }
}