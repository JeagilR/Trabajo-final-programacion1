/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tarea4.Crud;

/**
 *
 * @author Jeagil
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductoForm extends JDialog {
    private JTextField nombreField;
    private JTextField marcaField;
    private JTextField categoriaField;
    private JTextField precioField;
    private JTextField cantidadField;
    private JButton guardarButton;
    private JButton eliminarButton;
    private JButton cancelarButton;
    private Producto producto;

    public ProductoForm(JFrame parent, Producto producto) {
        super(parent, producto == null ? "Nuevo Producto" : "Editar Producto", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nombreLabel = new JLabel("Nombre:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(nombreLabel, gbc);

        nombreField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(nombreField, gbc);

        JLabel marcaLabel = new JLabel("Marca:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(marcaLabel, gbc);

        marcaField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(marcaField, gbc);

        JLabel categoriaLabel = new JLabel("Categoría:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(categoriaLabel, gbc);

        categoriaField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(categoriaField, gbc);

        JLabel precioLabel = new JLabel("Precio:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(precioLabel, gbc);

        precioField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(precioField, gbc);

        JLabel cantidadLabel = new JLabel("Cantidad:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(cantidadLabel, gbc);

        cantidadField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(cantidadField, gbc);

        //imagenes de botones 
        ImageIcon guardarIcon = new ImageIcon(getClass().getResource("/27876_add_cancel_delete_edit_guardar_icon.png"));
        Image guardarImg = guardarIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon eliminarIcon = new ImageIcon(getClass().getResource("/27842_cancelled_close_delete_exit_no_icon.png"));
        Image eliminarImg = eliminarIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        
        guardarButton = new JButton(new ImageIcon(guardarImg));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        add(guardarButton, gbc);

        eliminarButton = new JButton(new ImageIcon(eliminarImg));
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(eliminarButton, gbc);

        cancelarButton = new JButton("Cancelar");
        gbc.gridx = 2;
        gbc.gridy = 5;
        add(cancelarButton, gbc);

        if (producto != null) {
            nombreField.setText(producto.getNombre());
            marcaField.setText(producto.getMarca());
            categoriaField.setText(producto.getCategoria());
            precioField.setText(String.valueOf(producto.getPrecio()));
            cantidadField.setText(String.valueOf(producto.getCantidadDisponible()));
            eliminarButton.setEnabled(true);
        } else {
            eliminarButton.setEnabled(false);
        }

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // obtener los datos del formulario
                String nombre = nombreField.getText();
                String marca = marcaField.getText();
                String categoria = categoriaField.getText();
                double precio = Double.parseDouble(precioField.getText());
                int cantidad = Integer.parseInt(cantidadField.getText());

                // verificar si estamos creando un nuevo producto o editando uno existente
                if (producto == null) {
                    // crear un nuevo producto
                    Producto nuevoProducto = new Producto(nombre, marca, categoria, precio, cantidad);
                    GestorProductos.getInstance().registrarProducto(nuevoProducto);
                } else {
                    // actualizar el producto existente
                    producto.setNombre(nombre);
                    producto.setMarca(marca);
                    producto.setCategoria(categoria);
                    producto.setPrecio(precio);
                    producto.setCantidadDisponible(cantidad);

                    // llama al método con el ID y el producto actualizado
                    GestorProductos.getInstance().actualizarProducto(producto.getIdProducto(), producto);
                }

                // actualizar la tabla en la ventana principal
                ((GestionProductosFrame) getParent()).updateProductosTable();

                // cerrar el formulario
                dispose();
            }
        });
        
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verificar si estamos eliminando un producto existente
                if (producto != null) {
                    // Elimina el producto
                    GestorProductos.getInstance().eliminarProducto(producto.getIdProducto());

                    // actualizar la tabla en la ventana principal
                    ((GestionProductosFrame) getParent()).updateProductosTable();
                }

                // cerramos el formulario
                dispose();
            }
        });
        
        cancelarButton.addActionListener(new ActionListener(){   
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        });
    }
}
