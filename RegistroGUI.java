import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que representa la interfaz gráfica para el registro de usuarios.
 * Extiende de JPanel y permite a los usuarios ingresar sus datos para registrarse.
 */
public class RegistroGUI extends JPanel {

    private JTextField txtName, txtUsername, txtedad, txtGenero, txtTipoUsuario; // Campos de texto para datos de usuario
    private JPasswordField txtPassword; // Campo de texto para la contraseña
    private JButton btnRegister, btnRegreso; // Botones para registrar y regresar al login
    private GuardarInformacion gestion; // Objeto para manejar la gestión de la información
    private App app; // Referencia a la instancia principal de la aplicación
    private GeneradorId generadorId; // Objeto para generar identificadores únicos

    /**
     * Constructor de la clase RegistroGUI.
     * 
     * @param app     La instancia principal de la aplicación.
     * @param gestion Objeto para gestionar la información de usuarios.
     */
    public RegistroGUI(App app, GuardarInformacion gestion) {
        this.app = app;
        this.gestion = gestion;
        initComponents(); // Inicializa los componentes de la interfaz
        generadorId = new GeneradorId(); // Crea una instancia del generador de IDs
    }

    /**
     * Método que inicializa los componentes gráficos de la interfaz de registro.
     * Configura los campos de texto, etiquetas, botones y su disposición en el panel.
     */
    private void initComponents() {
        setLayout(new GridBagLayout()); // Establece el layout del panel
        GridBagConstraints gbc = new GridBagConstraints(); // Configuraciones de posicionamiento
        gbc.insets = new Insets(10, 10, 10, 10); // Margen entre los componentes

        JLabel lblTitulo = new JLabel("Registro"); // Título de la pantalla
        lblTitulo.setFont(new Font("Impact", Font.BOLD, 30)); // Establece la fuente del título
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(lblTitulo, gbc); // Agrega el título al panel

        JLabel lblname = new JLabel("Nombre:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(lblname, gbc);

        txtName = new JTextField(12); // Campo para ingresar el nombre
        gbc.gridx = 1;
        add(txtName, gbc);

        JLabel lblUsername = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblUsername, gbc);

        txtUsername = new JTextField(12); // Campo para ingresar el nombre de usuario
        gbc.gridx = 1;
        add(txtUsername, gbc);

        JLabel lblPassword = new JLabel("Contraseña:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(lblPassword, gbc);

        txtPassword = new JPasswordField(12); // Campo para ingresar la contraseña
        gbc.gridx = 1;
        add(txtPassword, gbc);

        JLabel lblEdad = new JLabel("Edad:");
        gbc.gridx = 2;
        gbc.gridy = 1;
        add(lblEdad, gbc);

        txtedad = new JTextField(12); // Campo para ingresar la edad
        gbc.gridx = 3;
        add(txtedad, gbc);

        JLabel lblGenero = new JLabel("Género:");
        gbc.gridx = 2;
        gbc.gridy = 2;
        add(lblGenero, gbc);

        txtGenero = new JTextField(12); // Campo para ingresar el género
        gbc.gridx = 3;
        add(txtGenero, gbc);

        JLabel lblTipoUsuario = new JLabel("Tipo de usuario:");
        gbc.gridx = 2;
        gbc.gridy = 3;
        add(lblTipoUsuario, gbc);

        txtTipoUsuario = new JTextField(12); // Campo para ingresar el tipo de usuario
        gbc.gridx = 3;
        add(txtTipoUsuario, gbc);

        btnRegister = new JButton("Regístrate"); // Botón para registrarse
        btnRegister.setBackground(Color.ORANGE); // Color de fondo del botón
        btnRegister.setForeground(Color.BLACK); // Color del texto del botón
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registroUsuario(); // Llama al método de registro cuando se presiona el botón
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(btnRegister, gbc); // Agrega el botón al panel

        btnRegreso = new JButton("Regresar"); // Botón para regresar al login
        btnRegreso.setBackground(Color.RED); // Color de fondo del botón
        btnRegreso.setForeground(Color.BLACK); // Color del texto del botón
        btnRegreso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Regresando a inicio de sesión.");
                app.mostrarLogIn(); // Muestra la pantalla de inicio de sesión
            }
        });
        gbc.gridx = 2;
        gbc.gridy = 4;
        add(btnRegreso, gbc); // Agrega el botón al panel
    }

    /**
     * Método que maneja el registro de un nuevo usuario.
     * Verifica si la contraseña cumple los requisitos antes de registrarlo.
     */
    private void registroUsuario() {
        String nombre = txtName.getText(); // Obtiene el nombre ingresado
        String nombreUsuario = txtUsername.getText(); // Obtiene el nombre de usuario
        String contraseña = new String(txtPassword.getPassword()); // Obtiene la contraseña ingresada
        String genero = txtGenero.getText(); // Obtiene el género
        String tipo = txtTipoUsuario.getText(); // Obtiene el tipo de usuario

        String id = generadorId.generarId(); // Genera un ID único
        System.out.println(id); // Imprime el ID generado (solo para pruebas)

        // Verifica si la contraseña cumple con los requisitos
        if (!gestion.verificarContraseña(contraseña)) {
            JOptionPane.showMessageDialog(this, 
                "La contraseña no cumple con los requisitos necesarios. Debe tener minúsculas, mayúsculas, números, mínimo 10 caracteres, sin símbolos", 
                "Alerta", JOptionPane.ERROR_MESSAGE);
        } else {
            // Registra al usuario si la contraseña es válida
            gestion.registroUsuario(id, nombre, nombreUsuario, contraseña, 18, genero, tipo);
            app.mostrarPacientesPanel(); // Muestra el panel de pacientes
        }
    }
}

