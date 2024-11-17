/**
 * Universidad del Valle de Gutemala
 * Programación Orinetada a Objetos 
 * Sección: 10
 * Ing. Kimberly Barrera
 * Proyecto - Pillas
*/

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Panel que muestra una lista de pacientes mediante un JComboBox y permite gestionar sus opciones.
 * Extiende de JPanel y utiliza una disposición gráfica basada en BorderLayout.
 */
public class PacientesPanel extends JPanel {

    private App app; // Referencia a la aplicación principal
    private ArrayList<Paciente> listaPacientes; // Lista de pacientes disponibles
    private JComboBox<String> comboPacientes; // JComboBox para mostrar los nombres de los pacientes
    private GuardarInformacion gestion;

    /**
     * Constructor de la clase PacientesPanel.
     * Inicializa el panel con un JComboBox para pacientes, un botón para agregar pacientes 
     * y un botón para configuraciones.
     *
     * @param app       Instancia principal de la aplicación.
     * @param pacientes Lista de pacientes a mostrar en el JComboBox.
     */
    public PacientesPanel(App app, ArrayList<Paciente> pacientes, GuardarInformacion gestion1) {
        this.app = app;
        this.listaPacientes = pacientes;
        this.gestion = gestion1;

        setLayout(new BorderLayout()); // Establece el layout como BorderLayout
        setBackground(new Color(240, 248, 255)); // Establece el color de fondo

        JPanel panelTitulo = new JPanel(new BorderLayout());
        panelTitulo.setBackground(new Color(240, 248,255));


        JLabel lblTitulo = new JLabel("Pacientes de " + gestion.devolverUsuario().getNombre());
        lblTitulo.setFont(new Font("Impact", Font.CENTER_BASELINE, 30));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelTitulo.add(lblTitulo, BorderLayout.NORTH);

        // Panel superior para el JComboBox y opciones
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        panelSuperior.setBackground(new Color(240, 248, 255));

        // ComboBox para pacientes
        comboPacientes = new JComboBox<>();
        actualizarComboBox(); // Llena el JComboBox con los nombres de los pacientes
        comboPacientes.setPreferredSize(new Dimension(250, 30));
        panelSuperior.add(comboPacientes);

        // Botón para gestionar el paciente seleccionado
        JButton botonGestionarPaciente = new JButton("Gestionar Paciente");
        estilizarBoton(botonGestionarPaciente);
        botonGestionarPaciente.addActionListener(e -> gestionarPacienteSeleccionado());
        panelSuperior.add(botonGestionarPaciente);

        panelTitulo.add(panelSuperior, BorderLayout.CENTER);
        add(panelTitulo, BorderLayout.NORTH);

        // Panel inferior para los botones adicionales
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        panelInferior.setBackground(new Color(240, 248, 255));

        // Botón para agregar un nuevo paciente
        JButton botonAgregarPaciente = new JButton("Agregar Paciente");
        estilizarBoton(botonAgregarPaciente);
        botonAgregarPaciente.addActionListener(e -> app.mostrarRegistroPacientePanel());
        panelInferior.add(botonAgregarPaciente);

        // Botón para configuraciones
        JButton botonConfiguraciones = new JButton("Configuraciones");
        estilizarBoton(botonConfiguraciones);
        botonConfiguraciones.addActionListener(e -> mostrarConfiguraciones());
        panelInferior.add(botonConfiguraciones);

        add(panelInferior, BorderLayout.SOUTH);
    }

    /**
     * Llena el JComboBox con los nombres de los pacientes.
     */
    private void actualizarComboBox() {
        comboPacientes.removeAllItems();
        for (Paciente paciente : listaPacientes) {
            comboPacientes.addItem(paciente.getNombre());
        }
    }

    /**
     * Gestiona las opciones del paciente seleccionado en el JComboBox.
     */
    private void gestionarPacienteSeleccionado() {
        int index = comboPacientes.getSelectedIndex();
        if (index != -1) {
            Paciente pacienteSeleccionado = listaPacientes.get(index);
            mostrarOpcionesPaciente(pacienteSeleccionado);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un paciente.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Muestra un cuadro de diálogo con opciones para un paciente seleccionado.
     *
     * @param paciente El paciente para el que se muestran las opciones.
     */
    private void mostrarOpcionesPaciente(Paciente paciente) {
        String[] opciones = {"Mostrar Medicamentos", "Editar Información", "Eliminar Paciente"};
        int seleccion = JOptionPane.showOptionDialog(
                this,
                "Seleccione una opción para: " + paciente.getNombre(),
                "Opciones del Paciente",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        switch (seleccion) {
            case 0 -> app.mostrarMedicamentosPanel(paciente);
            case 1 -> app.mostrarConfigPacientePanel(paciente);
            case 2 -> eliminarPaciente(paciente);
        }
    }

    /**
     * Elimina un paciente de la lista y actualiza el JComboBox.
     *
     * @param paciente El paciente que se desea eliminar.
     */
    private void eliminarPaciente(Paciente paciente) {
        listaPacientes.remove(paciente);
        JOptionPane.showMessageDialog(this, "Paciente eliminado.");
        actualizarComboBox();
    }

    /**
     * Muestra el panel de configuraciones.
     */
    private void mostrarConfiguraciones() {
        app.mostrarConfigUsuarioPanel();
    }

    /**
     * Aplica un estilo uniforme a los botones del panel.
     *
     * @param boton El botón al que se le aplicará el estilo.
     */
    private void estilizarBoton(JButton boton) {
        boton.setBackground(new Color(70, 130, 180)); // Color de fondo
        boton.setForeground(Color.WHITE); // Color del texto
        boton.setFocusPainted(false);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setPreferredSize(new Dimension(200, 40));
    }
}
