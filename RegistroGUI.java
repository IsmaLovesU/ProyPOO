import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroGUI extends JPanel {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnRegister;
    private GuardarInformacion gestion;


    public RegistroGUI() {
        gestion = new GuardarInformacion();
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblTitulo = new JLabel("Registro");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
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
                // Aquí se manejaría el cambio a la pantalla de registro
                JOptionPane.showMessageDialog(null, "Registro no implementado aún.");
            }
        });
        gbc.gridx = 1;
        add(btnRegister, gbc);
    }

    private void verificarInicioSesion() {
        String nombreUsuario = txtUsername.getText();
        String contraseña = new String(txtPassword.getPassword());

        if (gestion.inicioSesion(nombreUsuario, contraseña)) {
            JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso.");
            // Aquí podrías abrir el menú principal o la siguiente pantalla
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
