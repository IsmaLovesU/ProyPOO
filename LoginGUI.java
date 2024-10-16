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

        JLabel lblTitulo = new JLabel("Inicio de sesión");
        lblTitulo.setFont(new Font("Impact", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(lblTitulo, gbc);

        JLabel lblUsername = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(lblUsername, gbc);

        txtUsername = new JTextField(15);
        gbc.gridx = 1;
        add(txtUsername, gbc);

        JLabel lblPassword = new JLabel("Contraseña:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblPassword, gbc);

        txtPassword = new JPasswordField(15);
        gbc.gridx = 1;
        add(txtPassword, gbc);

        btnLogin = new JButton("Iniciar sesión");
        btnLogin.setBackground(Color.BLUE);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarInicioSesion();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(btnLogin, gbc);

        btnRegister = new JButton("Registrate");
        btnRegister.setBackground(Color.ORANGE);
        btnRegister.setForeground(Color.BLACK);
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.mostrarRegistro();
            }
        });
        gbc.gridx = 1;
        add(btnRegister, gbc);
    }

    private void verificarInicioSesion() {
        String nombreUsuario = txtUsername.getText();
        String contraseña = new String(txtPassword.getPassword());

        if (gestion.autenticar(nombreUsuario, contraseña)) {
            JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso.");
            app.mostrarMenu();
            // Aquí podrías abrir el menú principal o la siguiente pantalla
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
