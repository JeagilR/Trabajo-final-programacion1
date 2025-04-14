/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tarea4.Crud;

import com.mycompany.tarea4.DatabaseConnection;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jeagil
 */
public class GestorProductos {
    private static GestorProductos instance;
    private Map<Integer, Producto> productos;
    
    private GestorProductos() {
        productos = new HashMap<>();
        cargarProductosDesdeBD();
    }
    
    public static GestorProductos getInstance() {
        if (instance == null) {
            instance = new GestorProductos();
        }
        return instance;
    }
    
    private void cargarProductosDesdeBD() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM productos")) {
            
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setNombre(rs.getString("NombreProducto"));
                producto.setMarca(rs.getString("MarcaProducto"));
                producto.setCategoria(rs.getString("CategoriaProducto"));
                producto.setPrecio(rs.getDouble("PrecioProducto"));
                producto.setCantidadDisponible(rs.getInt("StockProducto"));
                productos.put(producto.getIdProducto(), producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void registrarProducto(Producto producto) {
        productos.put(producto.getIdProducto(), producto);
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "INSERT INTO productos (NombreProducto, MarcaProducto, CategoriaProducto, PrecioProducto, StockProducto) VALUES (?, ?, ?, ?, ?)")) {

            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getMarca());
            stmt.setString(3, producto.getCategoria());
            stmt.setDouble(4, producto.getPrecio());
            stmt.setInt(5, producto.getCantidadDisponible());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void actualizarProducto(int idProducto, Producto productoActualizado) {
        // Actualiza la colección en memoria si es necesario
        productos.put(idProducto, productoActualizado);

        // Actualiza el producto en la base de datos
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "UPDATE productos SET NombreProducto = ?, MarcaProducto = ?, CategoriaProducto = ?, PrecioProducto = ?, StockProducto = ? WHERE idProducto = ?")) {

            stmt.setString(1, productoActualizado.getNombre());
            stmt.setString(2, productoActualizado.getMarca());
            stmt.setString(3, productoActualizado.getCategoria());
            stmt.setDouble(4, productoActualizado.getPrecio());
            stmt.setInt(5, productoActualizado.getCantidadDisponible());
            stmt.setInt(6, idProducto);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void eliminarProducto(int idProducto) {
        productos.remove(idProducto);
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM productos WHERE idProducto = ?")) {

            stmt.setInt(1, idProducto);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Producto getProductoById(int idProducto) {
        return productos.get(idProducto);
    }
     
    public Map<Integer, Producto> getProductos() {
        return productos;
    }
}
