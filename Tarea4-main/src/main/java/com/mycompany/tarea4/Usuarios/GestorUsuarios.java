/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tarea4.Usuarios;
import com.mycompany.tarea4.DatabaseConnection;
import com.mycompany.tarea4.Login.Usuario;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jeagil
 */
public class GestorUsuarios {
    private static GestorUsuarios instancia;
    private Map<String, Usuario> usuarios;
    
    private GestorUsuarios() {
        usuarios = new HashMap<>();
        loadUsuarios();
    }
    
    public static GestorUsuarios getInstance() {
        if (instancia == null) {
            instancia = new GestorUsuarios();
        }
        return instancia;
    }
    
    public void loadUsuarios() {
        usuarios.clear(); // Limpiar los usuarios actuales antes de recargar
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios")) {

            while (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getString("UserName"),
                    rs.getString("Nombre"),
                    rs.getString("Apellido"),
                    rs.getString("Telefono"),
                    rs.getString("Email"),
                    rs.getString("Password")
                );
                usuarios.put(usuario.getUsername(), usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Map<String, Usuario> getUsuarios() {
        return usuarios;
    }
    
    public void eliminarUsuario(String username) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM usuarios WHERE UserName = ?")) {
            
            pstmt.setString(1, username);
            pstmt.executeUpdate();
            usuarios.remove(username);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void registrarUsuario(Usuario usuario) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO usuarios (UserName, Nombre, Apellido, Telefono, Email, Password) VALUES (?, ?, ?, ?, ?, ?)")) {

            pstmt.setString(1, usuario.getUsername());
            pstmt.setString(2, usuario.getNombre());
            pstmt.setString(3, usuario.getApellido());
            pstmt.setString(4, usuario.getTelefono());
            pstmt.setString(5, usuario.getEmail());
            pstmt.setString(6, usuario.getPassword());
            pstmt.executeUpdate();
            usuarios.put(usuario.getUsername(), usuario);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void actualizarUsuario(Usuario usuario) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("UPDATE usuarios SET Nombre = ?, Apellido = ?, Telefono = ?, Email = ?, Password = ? WHERE UserName = ?")) {

            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getApellido());
            pstmt.setString(3, usuario.getTelefono());
            pstmt.setString(4, usuario.getEmail());
            pstmt.setString(5, usuario.getPassword());
            pstmt.setString(6, usuario.getUsername());
            pstmt.executeUpdate();
            usuarios.put(usuario.getUsername(), usuario);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Usuario obtenerUsuario(String username) {
        return usuarios.get(username);
    }    
}
