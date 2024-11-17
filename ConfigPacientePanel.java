/**
 * Universidad del Valle de Guatemala
 * Programación Orientada a Objetos 
 * Sección: 10
 * Ing. Kimberly Barrera
 * Proyecto - Pastillas
 */

import javax.swing.*;
import java.awt.*;

public class ConfigPacientePanel extends JPanel {
    private JTextField txtNombre;
    private JTextField txtEdad;
    private JTextArea txtCondiciones;
    private JTextArea txtMedicamentos;
    private JTextArea txtInformacionAdicional;
    private JButton btnGuardar;
    private JButton btnRegresar;

    private App app;
    private Paciente paciente;
    private GuardarInformacion gestion;

    public ConfigPacientePanel(App app, GuardarInformacion gestion, Paciente paciente) {
        this.app = app;
        this.gestion = gestion;
        this.paciente = paciente;

        setLayout(new GridBagLayout());
        setBackground(new Color(240, 248, 255)); 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblTitulo = new JLabel("Editar Información del Paciente");
        lblTitulo.setFont(new Font("Impact", Font.CENTER_BASELINE, 20));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(lblTitulo, gbc);

        // Nombre
        JLabel lblNombre = new JLabel("Nombre:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(lblNombre, gbc);

        txtNombre = new JTextField(paciente.getNombre(), 12);
        gbc.gridx = 1;
        add(txtNombre, gbc);

        // Edad
        JLabel lblEdad = new JLabel("Edad:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblEdad, gbc);

        txtEdad = new JTextField(String.valueOf(paciente.getEdad()), 12);
        gbc.gridx = 1;
        add(txtEdad, gbc);

        // Condiciones
        JLabel lblCondiciones = new JLabel("Condiciones:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(lblCondiciones, gbc);

        txtCondiciones = new JTextArea(String.join(", ", paciente.getCondiciones()), 3, 12);
        txtCondiciones.setLineWrap(true);
        txtCondiciones.setWrapStyleWord(true);
        JScrollPane scrollCondiciones = new JScrollPane(txtCondiciones);
        gbc.gridx = 1;
        add(scrollCondiciones, gbc);

        // Medicamentos
        JLabel lblMedicamentos = new JLabel("Medicamentos:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(lblMedicamentos, gbc);

        txtMedicamentos = new JTextArea(String.join(", ", 
            paciente.getMedicamentos().stream()
                    .map(Medicamento::toString) // Si Medicamento tiene un método `toString`
                    .toList()), 3, 12);
        txtMedicamentos.setLineWrap(true);
        txtMedicamentos.setWrapStyleWord(true);
        JScrollPane scrollMedicamentos = new JScrollPane(txtMedicamentos);
        gbc.gridx = 1;
        add(scrollMedicamentos, gbc);

        // Información adicional
        JLabel lblInformacionAdicional = new JLabel("Información Adicional:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(lblInformacionAdicional, gbc);

        txtInformacionAdicional = new JTextArea(paciente.getInformacionAdicional(), 3, 12);
        txtInformacionAdicional.setLineWrap(true);
        txtInformacionAdicional.setWrapStyleWord(true);
        JScrollPane scrollInformacionAdicional = new JScrollPane(txtInformacionAdicional);
        gbc.gridx = 1;
        add(scrollInformacionAdicional, gbc);

        // Botón Guardar
        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.setBackground(new Color(34, 139, 34)); // Verde para guardar
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.addActionListener(e -> guardarCambios());
        btnGuardar.addActionListener(e-> app.mostrarPacientesPanel());
        gbc.gridx = 1;
        gbc.gridy = 6;
        add(btnGuardar, gbc);

        // Botón Regresar
        btnRegresar = new JButton("Regresar");
        btnRegresar.setBackground(Color.RED);
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.addActionListener(e -> app.mostrarPacientesPanel());
        gbc.gridx = 2;
        add(btnRegresar, gbc);
    }

private void guardarCambios() {
        try {
            paciente.setNombre(txtNombre.getText());
            paciente.setEdad(Integer.parseInt(txtEdad.getText()));
            paciente.getCondiciones().clear();
            for (String condicion : txtCondiciones.getText().split(",")) {
                paciente.agregarCondiciones(condicion.trim());
            }

            // Medicamentos no se modifican directamente aquí; depende del diseño.
            paciente.setInformacionAdicional(txtInformacionAdicional.getText());

            JOptionPane.showMessageDialog(this, "Información del paciente actualizada exitosamente.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Edad debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
 
