/**
 * Universidad del Valle de Gutemala
 * Programación Orinetada a Objetos 
 * Sección: 10
 * Ing. Kimberly Barrera
 * Proyecto - Pillas
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JPanel {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnRegister;
    private GuardarInformacion gestion;
    private App app;


    public LoginGUI(App app, GuardarInformacion gestion) {
        this.app = app;
        this.gestion= gestion;
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        //Imagen de logo FALTA CENTRAR LA IMAGEN!
        ImageIcon icon = new ImageIcon("resources/logo.png");

        Image image = icon.getImage().getScaledInstance(131, 153, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);

        //Se agrega al Label
        JLabel lblImagen = new JLabel(icon);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(lblImagen, gbc);

        JLabel lblTitulo = new JLabel("Inicio de sesión");
        lblTitulo.setFont(new Font("Impact", Font.CENTER_BASELINE, 30));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(lblTitulo, gbc);

        JLabel lblUsername = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(lblUsername, gbc);

        txtUsername = new JTextField(12);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(txtUsername, gbc);

        JLabel lblPassword = new JLabel("Contraseña:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(lblPassword, gbc);

        txtPassword = new JPasswordField(12);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(txtPassword, gbc);

        btnLogin = new JButton("Iniciar sesión");
        btnLogin.setBackground(new Color (0,41, 95, 255));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarInicioSesion();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(btnLogin, gbc);

        btnRegister = new JButton("Registrate");
        btnRegister.setBackground(new Color (228, 147, 19));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.mostrarRegistro();
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(btnRegister, gbc);
    }

    private void verificarInicioSesion() {
        String nombreUsuario = txtUsername.getText();
        String contraseña = new String(txtPassword.getPassword());

        if (gestion.autenticar(nombreUsuario, contraseña)) {
            JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso.");
            app.mostrarPacientesPanel();
            // Aquí podrías abrir el menú principal o la siguiente pantalla
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
