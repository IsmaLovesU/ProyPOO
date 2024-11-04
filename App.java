/**
 * Universidad del Valle de Gutemala
 * Programación Orinetada a Objetos 
 * Sección: 10
 * Ing. Kimberly Barrera
 * Proyecto - Pillas
*/

import java.util.ArrayList;
import javax.swing.*;

/**
 * Clase principal de la aplicación de gestión de medicamentos.
 * Gestiona los distintos paneles y ventanas de la interfaz gráfica, así como 
 * la lógica principal de la aplicación.
 */
public class App {

    private JFrame frame; // Ventana principal para el login y registro
    private JFrame programaFrame; // Ventana secundaria para el panel de gestión de medicamentos
    private GuardarInformacion gestion; // Objeto encargado de guardar la información
    private ArrayList<Paciente> listaPacientes; // Lista de pacientes de la aplicación

    /**
     * Método principal de la aplicación que inicia el flujo en un hilo de la interfaz gráfica.
     * 
     * @param args Argumentos pasados por línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App app = new App(); // Crea una instancia de la aplicación
            app.mostrarLogIn(); // Muestra el panel de inicio de sesión
        });
    }

    /**
     * Constructor de la clase `App`. 
     * Inicializa la lista de pacientes, el objeto de gestión de información y la ventana principal.
     */
    public App() {
        gestion = new GuardarInformacion(); // Inicializa el gestor de información
        listaPacientes = new ArrayList<>(); // Inicializa la lista de pacientes
        
        frame = new JFrame("Log In"); // Configura la ventana del login
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra la aplicación al cerrar la ventana
        frame.setSize(600, 500); // Establece el tamaño de la ventana
        frame.setLocationRelativeTo(null); // Centra la ventana en la pantalla

        inicializarProgramaframe(); // Inicializa la ventana del programa principal
    }

    /**
     * Muestra el panel de inicio de sesión en la ventana principal.
     */
    public void mostrarLogIn() {
        LoginGUI loginPanel = new LoginGUI(this, gestion); // Crea el panel de login
        frame.setContentPane(loginPanel); // Establece el contenido del frame
        frame.setVisible(true); // Muestra la ventana
    }

    /**
     * Muestra el panel de registro en la ventana principal.
     */
    public void mostrarRegistro() {
        RegistroGUI registroPanel = new RegistroGUI(this, gestion); // Crea el panel de registro
        frame.setContentPane(registroPanel); // Establece el contenido del frame
        frame.setVisible(true); // Muestra la ventana
    }

    /**
     * Inicializa la ventana principal del programa para la gestión de medicamentos.
     * Configura la ventana en modo maximizado.
     */
    private void inicializarProgramaframe() {
        programaFrame = new JFrame("Gestión de Medicamentos"); // Crea la ventana del programa
        programaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Configura la operación al cerrar la ventana
        programaFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximiza la ventana
        programaFrame.setLocationRelativeTo(null); // Centra la ventana en la pantalla
    }

    /**
     * Muestra el panel de pacientes en la ventana principal.
     * Permite la visualización de los pacientes disponibles.
     */
    public void mostrarPacientesPanel() {
        frame.setContentPane(new PacientesPanel(this, listaPacientes)); // Crea y establece el panel de pacientes
        frame.revalidate(); // Vuelve a validar el contenido del frame
        frame.repaint(); // Redibuja el frame
        frame.setVisible(true); // Asegura que la ventana esté visible
    }

    /**
     * Muestra el panel de registro de pacientes en la ventana principal.
     */
    public void mostrarRegistroPacientePanel() {
        RegistroPacientePanel registroPacientePanel = new RegistroPacientePanel(this);
        frame.setContentPane(registroPacientePanel);
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }

    /**
     * Añade un paciente a la lista y actualiza el panel de pacientes.
     *
     * @param paciente El paciente que se desea agregar.
     */
    public void agregarPaciente(Paciente paciente) {
        listaPacientes.add(paciente);
        mostrarPacientesPanel(); // Refresca el panel de pacientes
    }


    /**
     * Muestra el panel de medicamentos de un paciente específico.
     * 
     * @param paciente El paciente cuyos medicamentos se mostrarán.
     */
    public void mostrarMedicamentosPanel(Paciente paciente) {
        frame.setContentPane(new MedicamentosPanel(this, paciente)); // Crea y establece el panel de medicamentos
        frame.revalidate(); // Vuelve a validar el contenido del frame
        frame.repaint(); // Redibuja el frame
    }
}
