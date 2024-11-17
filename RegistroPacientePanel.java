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
import java.util.ArrayList;

/**
 * Clase que representa la interfaz gráfica para el registro de pacientes.
 * Extiende de JPanel y permite a los usuarios ingresar los datos de un paciente.
 */
public class RegistroPacientePanel extends JPanel {

    private JTextField txtNombre, txtEdad, txtCondiciones, txtInformacionAdicional; // Campos de texto para datos del paciente
    private JButton btnRegistrarPaciente, btnRegresar; // Botones para registrar y regresar
    private App app; // Instancia principal de la aplicacion.
    private GeneradorId generadorId; // Objeto para generar identificadores únicos

    /**
     * Constructor de la clase RegistroPacientePanel.
     *
     * @param app El usuario al que se le agregará el paciente.
     */
    public RegistroPacientePanel(App app) {
        this.app = app;
        initComponents(); // Inicializa los componentes de la interfaz
        generadorId = new GeneradorId(); // Crea una instancia del generador de IDs
    }

    /**
     * Método que inicializa los componentes gráficos de la interfaz de registro de pacientes.
     */
    private void initComponents() {
        setLayout(new GridBagLayout()); // Establece el layout del panel
        GridBagConstraints gbc = new GridBagConstraints(); // Configuraciones de posicionamiento
        gbc.insets = new Insets(10, 10, 10, 10); // Margen entre los componentes

        JLabel lblTitulo = new JLabel("Registro de Paciente"); // Título de la pantalla
        lblTitulo.setFont(new Font("Impact", Font.BOLD, 30)); // Establece la fuente del título
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(lblTitulo, gbc); // Agrega el título al panel

        JLabel lblNombre = new JLabel("Nombre:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(lblNombre, gbc);

        txtNombre = new JTextField(12); // Campo para ingresar el nombre del paciente
        gbc.gridx = 1;
        add(txtNombre, gbc);

        JLabel lblEdad = new JLabel("Edad:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblEdad, gbc);

        txtEdad = new JTextField(12); // Campo para ingresar la edad del paciente
        gbc.gridx = 1;
        add(txtEdad, gbc);

        JLabel lblCondiciones = new JLabel("Condiciones:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(lblCondiciones, gbc);

        txtCondiciones = new JTextField(12); // Campo para ingresar condiciones médicas del paciente
        gbc.gridx = 1;
        add(txtCondiciones, gbc);

        JLabel lblInformacionAdicional = new JLabel("Información adicional:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(lblInformacionAdicional, gbc);

        txtInformacionAdicional = new JTextField(12); // Campo para ingresar información adicional
        gbc.gridx = 1;
        add(txtInformacionAdicional, gbc);

        btnRegistrarPaciente = new JButton("Registrar Paciente"); // Botón para registrar el paciente
        btnRegistrarPaciente.setBackground(Color.GREEN); // Color de fondo del botón
        btnRegistrarPaciente.setForeground(Color.BLACK); // Color del texto del botón
        btnRegistrarPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarPaciente(); // Llama al método de registro cuando se presiona el botón
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(btnRegistrarPaciente, gbc); // Agrega el botón al panel

        btnRegresar = new JButton("Regresar"); // Botón para regresar
        btnRegresar.setBackground(Color.RED); // Color de fondo del botón
        btnRegresar.setForeground(Color.BLACK); // Color del texto del botón
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Regresando a inicio.");

                app.mostrarPacientesPanel();
            }
        });
        gbc.gridx = 2;
        gbc.gridy = 5;
        add(btnRegresar, gbc); // Agrega el botón al panel
    }

    /**
     * Método que maneja el registro de un nuevo paciente.
     */
    private void registrarPaciente() {
        String nombre = txtNombre.getText(); // Obtiene el nombre ingresado
        int edad = 0;
        try {
            edad = Integer.parseInt(txtEdad.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un número válido para la edad.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String condicionesText = txtCondiciones.getText(); // Obtiene las condiciones ingresadas
        String informacionAdicional = txtInformacionAdicional.getText(); // Obtiene la información adicional ingresada

        String id = generadorId.generarId(); // Genera un ID único para el paciente
        ArrayList<String> condiciones = new ArrayList<>();
        if (!condicionesText.isEmpty()) {
            for (String condicion : condicionesText.split(",")) {
                condiciones.add(condicion.trim());
            }
        }

        Paciente paciente = new Paciente(id, nombre, edad, informacionAdicional);
        for (String condicion : condiciones) {
            paciente.agregarCondiciones(condicion);
        }

        app.agregarPaciente(paciente); // Agrega el paciente al usuario actual
        JOptionPane.showMessageDialog(this, "Paciente registrado exitosamente.");
        // Aquí podrías limpiar los campos o regresar al panel anterior si es necesario
    }
}

