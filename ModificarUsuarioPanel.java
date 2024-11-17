/**
 * Universidad del Valle de Gutemala
 * Programación Orinetada a Objetos 
 * Sección: 10
 * Ing. Kimberly Barrera
 * Proyecto - Pillas
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ModificarUsuarioPanel extends JPanel {
    private JTextField txtNombre, txtNombreUsuario, txtEdad, txtSexo;
    private JPasswordField txtContrasena;
    private JButton btnGuardar, btnCancelar;
    
    public ModificarUsuarioPanel() {
        setLayout(new GridLayout(6, 2, 10, 10)); // 6 filas, 2 columnas, 10px de separación
        
        // Etiquetas y campos de texto
        add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("Nombre de Usuario:"));
        txtNombreUsuario = new JTextField();
        add(txtNombreUsuario);

        add(new JLabel("Edad:"));
        txtEdad = new JTextField();
        add(txtEdad);

        add(new JLabel("Sexo:"));
        txtSexo = new JTextField();
        add(txtSexo);

        add(new JLabel("Contraseña:"));
        txtContrasena = new JPasswordField();
        add(txtContrasena);

        // Botones
        btnGuardar = new JButton("Guardar");
        add(btnGuardar);

        btnCancelar = new JButton("Cancelar");
        add(btnCancelar);
    }

    // Getters para acceder a los campos desde el Controlador
    public JTextField getTxtNombre() { return txtNombre; }
    public JTextField getTxtNombreUsuario() { return txtNombreUsuario; }
    public JTextField getTxtEdad() { return txtEdad; }
    public JTextField getTxtSexo() { return txtSexo; }
    public JPasswordField getTxtContrasena() { return txtContrasena; }
    public JButton getBtnGuardar() { return btnGuardar; }
    public JButton getBtnCancelar() { return btnCancelar; }
}
