import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PacientesPanel extends JPanel {

    private App app;
    private ArrayList<Paciente> listaPacientes;

    public PacientesPanel(App app, ArrayList<Paciente> pacientes) {
        this.app = app;
        this.listaPacientes = pacientes;

        setLayout(new BorderLayout());
        setBackground(new Color(240, 248, 255));

        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        botonesPanel.setBackground(new Color(240, 248, 255));

        // Crear un botón para cada paciente
        for (Paciente paciente : listaPacientes) {
            JButton botonPaciente = new JButton(paciente.getNombre());
            estilizarBoton(botonPaciente);
            botonPaciente.addActionListener(e -> mostrarOpcionesPaciente(paciente));
            botonesPanel.add(botonPaciente);
        }

        JButton botonAgregarPaciente = new JButton("Agregar Paciente");
        estilizarBoton(botonAgregarPaciente);
        botonAgregarPaciente.addActionListener(e -> agregarNuevoPaciente());
        botonesPanel.add(botonAgregarPaciente);

        add(botonesPanel, BorderLayout.CENTER);
    }

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
            case 1 -> JOptionPane.showMessageDialog(this, "Función de edición en construcción.");
            case 2 -> eliminarPaciente(paciente);
        }
    }

    private void agregarNuevoPaciente() {
        JOptionPane.showMessageDialog(this, "Función de agregar paciente en construcción.");
    }

    private void eliminarPaciente(Paciente paciente) {
        listaPacientes.remove(paciente);
        JOptionPane.showMessageDialog(this, "Paciente eliminado.");
        app.mostrarPacientesPanel();
    }

    private void estilizarBoton(JButton boton) {
        boton.setBackground(Color.BLUE);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setPreferredSize(new Dimension(200, 40));  // Tamaño fijo del botón
    }
    
}
