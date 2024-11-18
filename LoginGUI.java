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

/**
 * Panel de inicio de sesión de la aplicación.
 * Permite a los usuarios ingresar su nombre de usuario y contraseña para autenticarse
 * o dirigirse a la pantalla de registro.
 */
public class LoginGUI extends JPanel {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnRegister;
    private GuardarInformacion gestion;
    private App app;

    /**
     * Constructor de la clase LoginGUI.
     * Configura el panel y sus componentes para gestionar el inicio de sesión.
     *
     * @param app     La instancia principal de la aplicación.
     * @param gestion La clase encargada de gestionar la información de usuarios.
     */
    public LoginGUI(App app, GuardarInformacion gestion) {
        this.app = app;
        this.gestion= gestion;
        initComponents();
    }

    /**
     * Inicializa los componentes del panel de inicio de sesión.
     * Configura la disposición, los campos de texto, las etiquetas, y los botones.
     */
    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Imagen de logo
        ImageIcon icon = new ImageIcon("resources/logo.png");

        Image image = icon.getImage().getScaledInstance(131, 153, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);

        // Etiqueta de la imagen
        JLabel lblImagen = new JLabel(icon);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(lblImagen, gbc);

         // Título del panel
        JLabel lblTitulo = new JLabel("Inicio de sesión");
        lblTitulo.setFont(new Font("Impact", Font.CENTER_BASELINE, 30));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(lblTitulo, gbc);

        // Campo de texto para el nombre de usuario
        JLabel lblUsername = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(lblUsername, gbc);

        txtUsername = new JTextField(12);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(txtUsername, gbc);

        // Campo de texto para la contraseña
        JLabel lblPassword = new JLabel("Contraseña:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(lblPassword, gbc);

        txtPassword = new JPasswordField(12);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(txtPassword, gbc);

        // Botón para iniciar sesión
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

        // Botón para registrarse
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

     /**
     * Verifica las credenciales ingresadas por el usuario.
     * Valida el nombre de usuario y contraseña contra los datos almacenados.
     * Si las credenciales son correctas, muestra el panel de pacientes.
     * Si son incorrectas, muestra un mensaje de error.
     */
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
