/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tarea4.Login;
import com.mycompany.tarea4.Usuarios.GestionUsuariosFrame;
import com.mycompany.tarea4.Crud.GestionProductosFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Jeagil
 */
public class MainFrame extends JFrame {
    private JButton gestionarUsuariosButton;
    private JButton gestionarProductosButton;
    private JButton logoutButton;

    public MainFrame() {
        setTitle("Panel Principal");
        setSize(600, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        
        ImageIcon gestionarUsuariosIcon = new ImageIcon(getClass().getResource("/avatar.png"));
        Image gestionarUsuariosImg= gestionarUsuariosIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        gestionarUsuariosButton = new JButton(new ImageIcon(gestionarUsuariosImg));
        
        ImageIcon gestionarProductosIcon = new ImageIcon(getClass().getResource("/bosquejo.png"));
        Image gestionarProductosImg= gestionarProductosIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        gestionarProductosButton = new JButton(new ImageIcon(gestionarProductosImg)); 
        
        ImageIcon logOutIcon = new ImageIcon(getClass().getResource("/4115235_exit_logout_sign out_icon.png"));
        Image logOutImg= logOutIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        logoutButton = new JButton(new ImageIcon(logOutImg));

        add(gestionarUsuariosButton);
        add(gestionarProductosButton);
        add(logoutButton);

        gestionarUsuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GestionUsuariosFrame().setVisible(true);
            }
        });

        gestionarProductosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GestionProductosFrame().setVisible(true);
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });
    }

    private void logout() {
        new LoginFrame().setVisible(true);
        this.dispose();
    }
}