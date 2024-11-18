import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

/**
 * Clase que representa la interfaz gráfica para registrar un medicamento.
 * Extiende de JPanel y permite a los usuarios ingresar los datos de un medicamento.
 */
public class RegistroMedicamentoPanel extends JPanel {

    private JTextField txtNombre, txtDescripcion, txtDosis, txtInventario; // Campos de texto para datos del medicamento
    private JComboBox<String> opcionHora, opcionMinutos;
    private JButton btnRegistrar, btnRegresar; // Botones para registrar y regresar
    private App app; // Instancia principal de la aplicación
    private Paciente paciente; // Paciente asociado al medicamento
    private GeneradorId generadorId; // Objeto para generar IDs únicos

    /**
     * Constructor de la clase RegistroMedicamentoPanel.
     *
     * @param app      Instancia principal de la aplicación.
     * @param paciente Paciente al que se asociará el medicamento.
     */
    public RegistroMedicamentoPanel(App app, Paciente paciente) {
        this.app = app;
        this.paciente = paciente;
        initComponents(); // Inicializa los componentes de la interfaz gráfica
        generadorId = new GeneradorId(); // Crea una instancia del generador de IDs
    }

    /**
     * Método que inicializa los componentes gráficos de la interfaz de registro de medicamentos.
     */
    private void initComponents() {
        setLayout(new GridBagLayout()); // Configura el layout del panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Márgenes entre los componentes

        JLabel lblTitulo = new JLabel("Registrar Medicamento para " + paciente.getNombre());
        lblTitulo.setFont(new Font("Impact", Font.BOLD, 25)); // Configura la fuente del título
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(lblTitulo, gbc);

        JLabel lblNombre = new JLabel("Nombre:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(lblNombre, gbc);

        txtNombre = new JTextField(15); // Campo de texto para el nombre del medicamento
        gbc.gridx = 1;
        add(txtNombre, gbc);

        JLabel lblDescripcion = new JLabel("Descripción:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblDescripcion, gbc);

        txtDescripcion = new JTextField(15); // Campo de texto para la descripción
        gbc.gridx = 1;
        add(txtDescripcion, gbc);

        JLabel lblDosis = new JLabel("Dosis:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(lblDosis, gbc);

        txtDosis = new JTextField(15); // Campo de texto para la dosis
        gbc.gridx = 1;
        add(txtDosis, gbc);

        JLabel lblInventario = new JLabel("Inventario:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(lblInventario, gbc);

        txtInventario = new JTextField(15); // Campo de texto para el inventario
        gbc.gridx = 1;
        add(txtInventario, gbc);

        JLabel lbHora = new JLabel("Hora de suministro");
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(lbHora, gbc);
        
        opcionHora = new JComboBox<>();
        for(int i = 0; i < 24; i++){
            opcionHora.addItem(String.format("%02d", i));
        }
        gbc.gridx = 1;
        add(opcionHora, gbc);

        JLabel lblMinutos = new JLabel("Minutos: ");
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(lblMinutos, gbc);

        opcionMinutos = new JComboBox<>();
        for(int i = 0; i< 60; i+=15){
            opcionMinutos.addItem(String.format("%2d", i));
        }
        gbc.gridx =1;
        add(opcionMinutos, gbc);

        btnRegistrar = new JButton("Registrar Medicamento");
        btnRegistrar.setBackground(new Color (0, 41, 95, 255));
        btnRegistrar.setForeground(Color.white);
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarMedicamento(); // Registra el medicamento
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 7;
        add(btnRegistrar, gbc);

        btnRegresar = new JButton("Regresar");
        btnRegresar.setBackground(Color.RED);
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.mostrarMedicamentosPanel(paciente); // Regresa al panel de medicamentos
            }
        });
        gbc.gridx = 1;
        add(btnRegresar, gbc);
    }

    /**
     * Método que maneja el registro de un nuevo medicamento.
     */
    private void registrarMedicamento() {
        String nombre = txtNombre.getText();
        String descripcion = txtDescripcion.getText();
        int dosis;
        float inventario;
    
        // Validar los campos de entrada
        try {
            dosis = Integer.parseInt(txtDosis.getText());
            inventario = Float.parseFloat(txtInventario.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Dosis e inventario deben ser valores numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int hora = Integer.parseInt(opcionHora.getSelectedItem().toString().trim());
        int minutos = Integer.parseInt(opcionMinutos.getSelectedItem().toString().trim());
        LocalTime horaSuministro = LocalTime.of(hora, minutos);
    
        // Generar el ID único para el medicamento
        String idMedicamento = generadorId.generarId();
        Medicamento nuevoMedicamento = new Medicamento(idMedicamento, nombre, descripcion, dosis, horaSuministro ,inventario);
    
        // Asociar el medicamento con el paciente actual
        paciente.agregarMedicamentos(nuevoMedicamento);
    
        // Guardar en el archivo CSV a través de la instancia `GuardarInformacion`
        app.getGuardarInformacion().guardarMedicamentosCSV();
    
        // Confirmar al usuario y regresar al panel de medicamentos
        JOptionPane.showMessageDialog(this, "Medicamento registrado exitosamente.");
        app.mostrarMedicamentosPanel(paciente);
    }
    
}
