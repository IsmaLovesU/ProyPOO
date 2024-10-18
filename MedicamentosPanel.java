import javax.swing.*;
import java.awt.*;

/**
 * Panel que muestra la lista de medicamentos de un paciente y permite gestionar opciones
 * relacionadas con los medicamentos. Extiende de JPanel y utiliza un BorderLayout.
 */
public class MedicamentosPanel extends JPanel {

    private App app; // Referencia a la aplicación principal.
    private Paciente paciente; // Paciente cuyos medicamentos se van a mostrar.

    /**
     * Constructor de la clase MedicamentosPanel.
     * Inicializa el panel mostrando los medicamentos del paciente y opciones adicionales.
     *
     * @param app      Instancia principal de la aplicación.
     * @param paciente Paciente cuyos medicamentos se van a gestionar.
     */
    public MedicamentosPanel(App app, Paciente paciente) {
        this.app = app;
        this.paciente = paciente;

        setLayout(new BorderLayout()); // Establece BorderLayout como el diseño del panel.
        setBackground(new Color(248, 240, 255)); // Establece un color de fondo.

        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15)); 
        botonesPanel.setBackground(new Color(248, 240, 255)); // Fondo del panel de botones.

        // Título del panel que indica los medicamentos del paciente.
        JLabel titulo = new JLabel("Medicamentos de " + paciente.getNombre());
        titulo.setFont(new Font("Arial", Font.BOLD, 16)); // Estilo del título.
        titulo.setHorizontalAlignment(SwingConstants.CENTER); // Alineación del título.
        add(titulo, BorderLayout.NORTH); // Agrega el título en la parte superior del panel.

        // Crear un botón para cada medicamento del paciente.
        for (Medicamento medicamento : paciente.getMedicamentos()) {
            JButton botonMedicamento = new JButton(medicamento.getNombre()); // Botón con el nombre del medicamento.
            estilizarBoton(botonMedicamento); // Aplica estilo al botón.
            botonMedicamento.addActionListener(e -> mostrarOpcionesMedicamento(medicamento)); // Evento de clic.
            botonesPanel.add(botonMedicamento); // Agrega el botón al panel.
        }

        // Botón para agregar un nuevo medicamento.
        JButton botonAgregarMedicamento = new JButton("Agregar Medicamento");
        estilizarBoton(botonAgregarMedicamento); // Aplica estilo al botón.
        botonAgregarMedicamento.addActionListener(e -> agregarNuevoMedicamento()); // Evento de clic.
        botonesPanel.add(botonAgregarMedicamento); // Agrega el botón al panel.

        // Botón para volver al panel de pacientes.
        JButton botonVolver = new JButton("Volver a Pacientes");
        estilizarBoton(botonVolver); // Aplica estilo al botón.
        botonVolver.addActionListener(e -> app.mostrarPacientesPanel()); // Evento de clic para volver.
        botonesPanel.add(botonVolver); // Agrega el botón al panel.

        add(botonesPanel, BorderLayout.CENTER); // Agrega el panel de botones al centro del layout.
    }

    /**
     * Muestra un cuadro de diálogo con opciones para el medicamento seleccionado.
     *
     * @param medicamento Medicamento del cual se mostrarán las opciones.
     */
    private void mostrarOpcionesMedicamento(Medicamento medicamento) {
        String[] opciones = {"Ver Información", "Eliminar Medicamento"};
        int seleccion = JOptionPane.showOptionDialog(
                this,
                "Seleccione una opción para: " + medicamento.getNombre(),
                "Opciones del Medicamento",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        // Ejecuta la acción según la opción seleccionada.
        switch (seleccion) {
            case 0 -> JOptionPane.showMessageDialog(this, medicamento.toString()); // Muestra información del medicamento.
            case 1 -> eliminarMedicamento(medicamento); // Elimina el medicamento.
        }
    }

    /**
     * Muestra un mensaje indicando que la función de agregar un nuevo medicamento
     * está en desarrollo.
     */
    private void agregarNuevoMedicamento() {
        JOptionPane.showMessageDialog(this, "Función de agregar medicamento en construcción.");
    }

    /**
     * Elimina el medicamento del paciente y actualiza el panel.
     *
     * @param medicamento Medicamento a eliminar.
     */
    private void eliminarMedicamento(Medicamento medicamento) {
        paciente.getMedicamentos().remove(medicamento); // Elimina el medicamento de la lista del paciente.
        JOptionPane.showMessageDialog(this, "Medicamento eliminado."); // Muestra mensaje de confirmación.
        app.mostrarMedicamentosPanel(paciente); // Actualiza el panel de medicamentos.
    }

    /**
     * Aplica un estilo uniforme a los botones del panel.
     *
     * @param boton El botón al que se le aplicará el estilo.
     */
    private void estilizarBoton(JButton boton) {
        boton.setBackground(new Color(186, 85, 211)); // Color de fondo del botón.
        boton.setForeground(Color.WHITE); // Color del texto del botón.
        boton.setFocusPainted(false); // Desactiva el efecto de foco.
        boton.setFont(new Font("Arial", Font.BOLD, 14)); // Establece la fuente del texto.
        boton.setPreferredSize(new Dimension(200, 40)); // Tamaño fijo del botón.
    }
}
