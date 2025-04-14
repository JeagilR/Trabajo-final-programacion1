/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tarea4.Crud;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

public class GestionProductosFrame extends JFrame {
    private JTable productosTable;
    private JButton nuevoButton;
    private JButton volverButton;

    public GestionProductosFrame() {
        setTitle("Gestión de Productos");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        productosTable = new JTable();
        updateProductosTable();

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        
        ImageIcon nuevoIcon = new ImageIcon(getClass().getResource("/27831_add_blue_minus_new_plus_icon.png"));
        Image nuevoImg = nuevoIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        nuevoButton = new JButton(new ImageIcon(nuevoImg));
        
        
        ImageIcon volverIcon = new ImageIcon(getClass().getResource("/27872_arrow_back_forward_redo_white_icon.png"));
        Image volverImg = volverIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        volverButton = new JButton(new ImageIcon(volverImg));

        buttonPanel.add(nuevoButton);
        buttonPanel.add(volverButton);

        add(new JScrollPane(productosTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        nuevoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProductoForm(GestionProductosFrame.this, null).setVisible(true);
            }
        });

        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //MouseListener para editar productos al hacer clic en una fila
        productosTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) { // Doble clic
                    int row = productosTable.getSelectedRow();
                    if (row != -1) {
                        // Obtener ID del producto seleccionado
                        int idProducto = Integer.parseInt(productosTable.getValueAt(row, 0).toString());
                        // Buscar el producto por ID
                        Producto producto = GestorProductos.getInstance().getProductoById(idProducto);
                        // Abrir el formulario de edición
                        new ProductoForm(GestionProductosFrame.this, producto).setVisible(true);
                    }
                }
            }
        });
    }

    public void updateProductosTable() {
        Map<Integer, Producto> productos = GestorProductos.getInstance().getProductos();
        String[][] data = new String[productos.size()][6]; // Cambiado a 6 columnas
        int i = 0;
        for (Producto producto : productos.values()) {
            data[i][0] = String.valueOf(producto.getIdProducto());
            data[i][1] = producto.getNombre();
            data[i][2] = producto.getMarca();
            data[i][3] = producto.getCategoria();
            data[i][4] = String.valueOf(producto.getPrecio());
            data[i][5] = String.valueOf(producto.getCantidadDisponible()); // Nueva columna para cantidad de productos
            i++;
        }

        String[] columnNames = {"ID", "Nombre", "Marca", "Categoría", "Precio", "Cantidad"}; // Añadido "Cantidad"
        productosTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }
}