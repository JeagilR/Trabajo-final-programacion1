/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tarea4.Usuarios;
import com.mycompany.tarea4.Login.Usuario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class GestionUsuariosFrame extends JFrame {
    private JTable usuariosTable;
    private JButton volverButton;
    private JButton eliminarButton;
    private JButton actualizarButton;

    public GestionUsuariosFrame() {
        setTitle("Gestión de Usuarios");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        usuariosTable = new JTable();
        updateUsuariosTable();

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        
        //imagenes botones
        ImageIcon volverIcon = new ImageIcon(getClass().getResource("/27872_arrow_back_forward_redo_white_icon.png"));
        Image volverImg = volverIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        volverButton = new JButton(new ImageIcon(volverImg));
        
        ImageIcon eliminarIcon = new ImageIcon(getClass().getResource("/27842_cancelled_close_delete_exit_no_icon.png"));
        Image eliminarImg = eliminarIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        eliminarButton = new JButton(new ImageIcon(eliminarImg));
        
        ImageIcon actualizarIcon = new ImageIcon(getClass().getResource("/actualizar-flecha.png"));
        Image actualizarImg = actualizarIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        actualizarButton = new JButton(new ImageIcon(actualizarImg));

        buttonPanel.add(volverButton);
        buttonPanel.add(eliminarButton);
        buttonPanel.add(actualizarButton);

        add(new JScrollPane(usuariosTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        //metodos botones
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteUsuario();
            }
        });

        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUsuario();
            }
        });
    }
    //metodos
    private void updateUsuariosTable() {
        Map<String, Usuario> usuarios = GestorUsuarios.getInstance().getUsuarios();
        String[][] data = new String[usuarios.size()][5];
        int i = 0;
        for (Usuario usuario : usuarios.values()) {
            data[i][0] = usuario.getUsername();
            data[i][1] = usuario.getNombre();
            data[i][2] = usuario.getApellido();
            data[i][3] = usuario.getTelefono();
            data[i][4] = usuario.getEmail();
            i++;
        }

        String[] columnNames = {"Usuario", "Nombre", "Apellido", "Teléfono", "Correo Electrónico"};
        usuariosTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

    private void deleteUsuario() {
        int selectedRow = usuariosTable.getSelectedRow();
        if (selectedRow >= 0) {
            String username = (String) usuariosTable.getValueAt(selectedRow, 0);
            GestorUsuarios.getInstance().eliminarUsuario(username);
            updateUsuariosTable();
        }
    }

    private void updateUsuario() {
        int selectedRow = usuariosTable.getSelectedRow();
        if (selectedRow >= 0) {
            String username = (String) usuariosTable.getValueAt(selectedRow, 0);
            Usuario usuario = GestorUsuarios.getInstance().obtenerUsuario(username);

            if (usuario != null) {
                String nombre = JOptionPane.showInputDialog(this, "Nuevo Nombre", usuario.getNombre());
                String apellido = JOptionPane.showInputDialog(this, "Nuevo Apellido", usuario.getApellido());
                String telefono = JOptionPane.showInputDialog(this, "Nuevo Teléfono", usuario.getTelefono());
                String email = JOptionPane.showInputDialog(this, "Nuevo Correo Electrónico", usuario.getEmail());
                String password = JOptionPane.showInputDialog(this, "Nueva Contraseña", usuario.getPassword());

                usuario.setNombre(nombre);
                usuario.setApellido(apellido);
                usuario.setTelefono(telefono);
                usuario.setEmail(email);
                usuario.setPassword(password);

                GestorUsuarios.getInstance().actualizarUsuario(usuario);
                updateUsuariosTable();
            }
        }
    }
}