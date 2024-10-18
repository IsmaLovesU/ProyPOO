import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroGUI extends JPanel {
    private JTextField txtName, txtUsername, txtedad, txtGenero, txtTipoUsuario;    
    private JPasswordField txtPassword;
    private JButton btnRegister, btnRegreso;
    private GuardarInformacion gestion;
    private App app;
    private GeneradorId generadorId;


    public RegistroGUI(App app, GuardarInformacion gestion) {
        this.app= app;
        this.gestion= gestion;
        initComponents();
        generadorId = new GeneradorId();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblTitulo = new JLabel("Registro");
        lblTitulo.setFont(new Font("Impact", Font.BOLD, 30));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(lblTitulo, gbc);

        JLabel lblname= new JLabel("Nombre:");
        gbc.gridx= 0;
        gbc.gridy= 1;
        gbc.gridwidth = 1;
        add(lblname, gbc);

        txtName = new JTextField(12);
        gbc.gridx = 1;
        add(txtName, gbc);

        JLabel lblUsername = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(lblUsername, gbc);

        txtUsername = new JTextField(12);
        gbc.gridx = 1;
        add(txtUsername, gbc);

        JLabel lblPassword = new JLabel("Contraseña:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(lblPassword, gbc);

        txtPassword = new JPasswordField(12);
        gbc.gridx = 1;
        add(txtPassword, gbc);

        JLabel lblEdad  = new JLabel("Edad:");
        gbc.gridx = 2; 
        gbc.gridy = 1;
        add(lblEdad, gbc);

        txtedad = new JTextField(12);
        gbc.gridx = 3;
        add(txtedad, gbc);

        JLabel lblGenero = new JLabel("Género:");
        gbc.gridx = 2;
        gbc.gridy = 2;
        add(lblGenero, gbc);

        txtGenero = new JTextField(12);
        gbc.gridx = 3;
        add(txtGenero , gbc);

        JLabel lblTipoUsuario = new JLabel("TIpo de usuario:");
        gbc.gridx = 2;
        gbc.gridy = 3;
        add(lblTipoUsuario, gbc);

        txtTipoUsuario = new JTextField(12);
        gbc.gridx = 3;
        add(txtTipoUsuario, gbc);

        btnRegister = new JButton("Registrate");
        btnRegister.setBackground(Color.ORANGE);
        btnRegister.setForeground(Color.BLACK);
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí se manejaría el cambio a la pantalla de registro
                registroUsuario();
            }
        });
        gbc.gridx = 1;
        gbc.gridy= 4;
        add(btnRegister, gbc);

        btnRegreso = new JButton("Regresar");
        btnRegreso.setBackground(Color.RED);
        btnRegreso.setForeground(Color.BLACK);

        btnRegreso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null, "Regresando a inicio de sesión.");
                app.mostrarLogIn();
            }
        });

        gbc.gridx= 2;
        gbc.gridy= 4;
        add(btnRegreso, gbc);

    }

    private void registroUsuario(){
        String nombre = txtName.getText();
        String nombreUsuario= txtUsername.getText();
        String contraseña= new String(txtPassword.getPassword());
        String genero = txtGenero.getText();
        String tipo = txtTipoUsuario.getText();
        
        
        String id = generadorId.generarId();
        System.out.println(id);

        if(!gestion.verificarContraseña(contraseña)){
            JOptionPane.showMessageDialog(this, "La contraseña no cumple con los requisitos necesarios. Debe tenrer minúsculas, mayúsculas, números, mínimo 10 caracteres, sin símbolos", "Alerta", JOptionPane.ERROR_MESSAGE);
        } else{
            gestion.registroUsuario(id, nombre, nombreUsuario, contraseña, 18,genero, tipo);
            app.mostrarPacientesPanel();
    
        }


    }

}
