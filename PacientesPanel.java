import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Panel que muestra una lista de pacientes y permite gestionar sus opciones.
 * Extiende de JPanel y utiliza una disposición gráfica basada en BorderLayout.
 */
public class PacientesPanel extends JPanel {

    private App app; // Referencia a la aplicación principal
    private ArrayList<Paciente> listaPacientes; // Lista de pacientes disponibles

    /**
     * Constructor de la clase PacientesPanel.
     * Inicializa el panel con los botones correspondientes a los pacientes y 
     * un botón para agregar nuevos pacientes.
     *
     * @param app       Instancia principal de la aplicación.
     * @param pacientes Lista de pacientes a mostrar en el panel.
     */
    public PacientesPanel(App app, ArrayList<Paciente> pacientes) {
        this.app = app;
        this.listaPacientes = pacientes;

        setLayout(new BorderLayout()); // Establece el layout como BorderLayout
        setBackground(new Color(240, 248, 255)); // Establece el color de fondo

        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15)); 
        botonesPanel.setBackground(new Color(240, 248, 255)); // Fondo del panel de botones

        // Crear un botón para cada paciente en la lista
        for (Paciente paciente : listaPacientes) {
            JButton botonPaciente = new JButton(paciente.getNombre()); // Botón con el nombre del paciente
            estilizarBoton(botonPaciente); // Aplica estilo al botón
            botonPaciente.addActionListener(e -> mostrarOpcionesPaciente(paciente)); // Evento al hacer clic en el botón
            botonesPanel.add(botonPaciente); // Agrega el botón al panel
        }

        // Botón para agregar un nuevo paciente
        JButton botonAgregarPaciente = new JButton("Agregar Paciente");
        estilizarBoton(botonAgregarPaciente); // Aplica estilo al botón
        botonAgregarPaciente.addActionListener(e -> agregarNuevoPaciente()); // Evento al hacer clic en el botón
        botonesPanel.add(botonAgregarPaciente); // Agrega el botón al panel

        add(botonesPanel, BorderLayout.CENTER); // Agrega el panel de botones al panel principal
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

        // Ejecuta la acción según la opción seleccionada
        switch (seleccion) {
            case 0 -> app.mostrarMedicamentosPanel(paciente); // Muestra los medicamentos del paciente
            case 1 -> JOptionPane.showMessageDialog(this, "Función de edición en construcción."); // Mensaje de función pendiente
            case 2 -> eliminarPaciente(paciente); // Elimina al paciente seleccionado
        }
    }

    /**
     * Muestra un mensaje indicando que la función de agregar un nuevo paciente 
     * está en desarrollo.
     */
    private void agregarNuevoPaciente() {
        JOptionPane.showMessageDialog(this, "Función de agregar paciente en construcción.");
    }

    /**
     * Elimina un paciente de la lista y actualiza el panel.
     *
     * @param paciente El paciente que se desea eliminar.
     */
    private void eliminarPaciente(Paciente paciente) {
        listaPacientes.remove(paciente); // Elimina al paciente de la lista
        JOptionPane.showMessageDialog(this, "Paciente eliminado."); // Muestra un mensaje de confirmación
        app.mostrarPacientesPanel(); // Refresca el panel de pacientes
    }

    /**
     * Aplica un estilo uniforme a los botones del panel.
     *
     * @param boton El botón al que se le aplicará el estilo.
     */
    private void estilizarBoton(JButton boton) {
        boton.setBackground(Color.BLUE); // Color de fondo del botón
        boton.setForeground(Color.WHITE); // Color del texto del botón
        boton.setFocusPainted(false); // Desactiva el efecto de foco al seleccionar
        boton.setFont(new Font("Arial", Font.BOLD, 14)); // Fuente del texto del botón
        boton.setPreferredSize(new Dimension(200, 40)); // Tamaño fijo del botón
    }
}
