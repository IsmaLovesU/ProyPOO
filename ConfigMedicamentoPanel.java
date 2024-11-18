/**
 * Universidad del Valle de Guatemala
 * Programación Orientada a Objetos 
 * Sección: 10
 * Ing. Kimberly Barrera
 * Proyecto - Pastillas
 */

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;

/**
 * Panel de configuración para editar la información de un medicamento.
 * Proporciona una interfaz gráfica para modificar atributos como nombre,
 * descripción, dosis, horario de suministro, si es recetado y el inventario.
 */
public class ConfigMedicamentoPanel extends JPanel {
    private JTextField txtNombre;
    private JTextArea txtDescripcion;
    private JTextField txtDosis;
    private JTextField txtHorarioSuministro;
    private JCheckBox chkRecetado;
    private JTextField txtInventario;
    private JButton btnGuardar;
    private JButton btnRegresar;

    private App app;
    private Medicamento medicamento;

    /**
     * Constructor de la clase ConfigMedicamentoPanel.
     * Configura el panel y sus componentes para editar la información de un medicamento.
     *
     * @param app1        La instancia principal de la aplicación.
     * @param medicamento El medicamento cuya información se va a editar.
     */
    public ConfigMedicamentoPanel(App app1, Medicamento medicamento) {
        this.app = app1;
        this.medicamento = medicamento;

        setLayout(new GridBagLayout());
        setBackground(new Color(240, 248, 255)); 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblTitulo = new JLabel("Editar Información del Medicamento");
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

        txtNombre = new JTextField(medicamento.getNombre(), 12);
        gbc.gridx = 1;
        add(txtNombre, gbc);

        // Descripción
        JLabel lblDescripcion = new JLabel("Descripción:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblDescripcion, gbc);

        txtDescripcion = new JTextArea(medicamento.getDescripcion(), 3, 12);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);
        gbc.gridx = 1;
        add(scrollDescripcion, gbc);

        // Dosis
        JLabel lblDosis = new JLabel("Dosis:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(lblDosis, gbc);

        txtDosis = new JTextField(String.valueOf(medicamento.getDosis()), 12);
        gbc.gridx = 1;
        add(txtDosis, gbc);

        // Horario de suministro
        JLabel lblHorario = new JLabel("Horario de Suministro (HH:mm):");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(lblHorario, gbc);

        txtHorarioSuministro = new JTextField(medicamento.getHorarioDeSuministro().toString(), 12);
        gbc.gridx = 1;
        add(txtHorarioSuministro, gbc);

        // Recetado
        JLabel lblRecetado = new JLabel("¿Recetado?");
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(lblRecetado, gbc);

        chkRecetado = new JCheckBox();
        chkRecetado.setSelected(medicamento.getRecetado());
        gbc.gridx = 1;
        add(chkRecetado, gbc);

        // Inventario
        JLabel lblInventario = new JLabel("Inventario:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(lblInventario, gbc);

        txtInventario = new JTextField(String.valueOf(medicamento.getInventario()), 12);
        gbc.gridx = 1;
        add(txtInventario, gbc);

        // Botón Guardar
        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.setBackground(new Color(34, 139, 34)); // Verde para guardar
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.addActionListener(e -> guardarCambios());
        btnGuardar.addActionListener(e -> app.mostrarPacientesPanel());
        gbc.gridx = 1;
        gbc.gridy = 7;
        add(btnGuardar, gbc);

        // Botón Regresar
        btnRegresar = new JButton("Regresar");
        btnRegresar.setBackground(Color.RED);
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.addActionListener(e -> app.mostrarPacientesPanel());
        gbc.gridx = 2;
        add(btnRegresar, gbc);
    }

     /**
     * Guarda los cambios realizados en el medicamento.
     * Valida y actualiza los datos del medicamento con los valores ingresados por el usuario.
     * Muestra un mensaje de éxito si los cambios son guardados correctamente,
     * o un mensaje de error si ocurre una excepción.
     */
    private void guardarCambios() {
        try {
            medicamento.setNombre(txtNombre.getText());
            medicamento.setDescripcion(txtDescripcion.getText());
            medicamento.setDosis(Integer.parseInt(txtDosis.getText()));
            medicamento.setHorarioDeSuministro(LocalTime.parse(txtHorarioSuministro.getText()));
            medicamento.setRecetado(chkRecetado.isSelected());
            medicamento.setInventario(Float.parseFloat(txtInventario.getText()));

            JOptionPane.showMessageDialog(this, "Información del medicamento actualizada exitosamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar los cambios: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
