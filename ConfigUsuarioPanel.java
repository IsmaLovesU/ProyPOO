/**
 * Universidad del Valle de Gutemala
 * Programación Orinetada a Objetos 
 * Sección: 10
 * Ing. Kimberly Barrera
 * Proyecto - Pillas
*/

import javax.swing.*;
import java.awt.*;

public class ConfigUsuarioPanel extends JPanel {
    private JTextField txtNombre;
    private JTextField txtUsername;
    private JTextField txtEdad;
    private JTextField txtGenero;
    private JTextField txtTipoUsuario;
    private JPasswordField txtPassword;
    private JButton btnGuardar;
    private JButton btnCancelar;


    public ConfigUsuarioPanel(App app, Usuario usuario, Paciente paciente, GuardarInformacion gestion) {
        setLayout(new GridBagLayout());
        setBackground(new Color(248, 240, 255)); // Color de fondo suave
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        JLabel lblTitulo = new JLabel("Editar Información de Usuario");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(lblTitulo, gbc);
        
        // Nombre
        JLabel lblNombre = new JLabel("Nombre:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(lblNombre, gbc);
        
        txtNombre = new JTextField(usuario.getNombre(), 12);
        gbc.gridx = 1;
        add(txtNombre, gbc);
        
        // Username
        JLabel lblUsername = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblUsername, gbc);
        
        txtUsername = new JTextField(usuario.getNombreUsuario(), 12);
        gbc.gridx = 1;
        add(txtUsername, gbc);
        
        // Contraseña
        JLabel lblPassword = new JLabel("Contraseña:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(lblPassword, gbc);
        
        txtPassword = new JPasswordField(12);
        txtPassword.setText(usuario.getContrasena()); // Precaución: solo si es seguro
        gbc.gridx = 1;
        add(txtPassword, gbc);
        
        // Edad
        JLabel lblEdad = new JLabel("Edad:");
        gbc.gridx = 2;
        gbc.gridy = 1;
        add(lblEdad, gbc);
        
        txtEdad = new JTextField(String.valueOf(usuario.getEdad()), 12);
        gbc.gridx = 3;
        add(txtEdad, gbc);
        
        // Género
        JLabel lblGenero = new JLabel("Género:");
        gbc.gridx = 2;
        gbc.gridy = 2;
        add(lblGenero, gbc);
        
        txtGenero = new JTextField(usuario.getSexo(), 12);
        gbc.gridx = 3;
        add(txtGenero, gbc);
        
        // Tipo de usuario
        JLabel lblTipoUsuario = new JLabel("Tipo de usuario:");
        gbc.gridx = 2;
        gbc.gridy = 3;
        add(lblTipoUsuario, gbc);
        
        txtTipoUsuario = new JTextField(usuario.getTipoUsuario(), 12);
        gbc.gridx = 3;
        add(txtTipoUsuario, gbc);
        
        // Botón Guardar
        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.setBackground(new Color(34, 139, 34)); // Verde para indicar acción de guardado
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.addActionListener(e -> guardarCambios(usuario, app, gestion));
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(btnGuardar, gbc);
        
        // Botón Cancelar
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(Color.RED);
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.addActionListener(e -> app.mostrarMedicamentosPanel(paciente)); // Vuelve al panel anterior
        gbc.gridx = 2;
        add(btnCancelar, gbc);
    }

    private void guardarCambios(Usuario usuario, App app, GuardarInformacion gestion) {
        usuario.setNombre(txtNombre.getText());
        usuario.setNombreUsuario(txtUsername.getText());
        usuario.setContrasena(new String(txtPassword.getPassword()));
        usuario.setEdad(Integer.parseInt(txtEdad.getText()));
        usuario.setSexo(txtGenero.getText());
        usuario.setTipoUsuario(txtTipoUsuario.getText());

        gestion.actualizarUsuario(usuario);

        JOptionPane.showMessageDialog(this, "Información actualizada exitosamente.");
   
    }
}
