/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tarea4;

import com.mycompany.tarea4.Login.LoginFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Jeagil
 */
public class Tarea4 {

    public static void main(String[] args) {
        // Iniciar la interfaz gráfica en el hilo de eventos de Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }
}