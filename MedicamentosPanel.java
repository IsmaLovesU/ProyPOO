import javax.swing.*;
import java.awt.*;

public class MedicamentosPanel extends JPanel {

    private App app;
    private Paciente paciente;

    public MedicamentosPanel(App app, Paciente paciente) {
        this.app = app;
        this.paciente = paciente;

        setLayout(new BorderLayout());
        setBackground(new Color(248, 240, 255));

        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        botonesPanel.setBackground(new Color(248, 240, 255));

        JLabel titulo = new JLabel("Medicamentos de " + paciente.getNombre());
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(titulo, BorderLayout.NORTH);

        // Crear un botón para cada medicamento
        for (Medicamento medicamento : paciente.getMedicamentos()) {
            JButton botonMedicamento = new JButton(medicamento.getNombre());
            estilizarBoton(botonMedicamento);
            botonMedicamento.addActionListener(e -> mostrarOpcionesMedicamento(medicamento));
            botonesPanel.add(botonMedicamento);
        }

        JButton botonAgregarMedicamento = new JButton("Agregar Medicamento");
        estilizarBoton(botonAgregarMedicamento);
        botonAgregarMedicamento.addActionListener(e -> agregarNuevoMedicamento());
        botonesPanel.add(botonAgregarMedicamento);

        JButton botonVolver = new JButton("Volver a Pacientes");
        estilizarBoton(botonVolver);
        botonVolver.addActionListener(e -> app.mostrarPacientesPanel());
        botonesPanel.add(botonVolver);

        add(botonesPanel, BorderLayout.CENTER);
    }

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

        switch (seleccion) {
            case 0 -> JOptionPane.showMessageDialog(this, medicamento.toString());
            case 1 -> eliminarMedicamento(medicamento);
        }
    }

    private void agregarNuevoMedicamento() {
        JOptionPane.showMessageDialog(this, "Función de agregar medicamento en construcción.");
    }

    private void eliminarMedicamento(Medicamento medicamento) {
        paciente.getMedicamentos().remove(medicamento);
        JOptionPane.showMessageDialog(this, "Medicamento eliminado.");
        app.mostrarMedicamentosPanel(paciente);
    }

    private void estilizarBoton(JButton boton) {
        boton.setBackground(new Color(186, 85, 211));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setPreferredSize(new Dimension(200, 40));  // Tamaño fijo del botón
    }
}
