import javax.swing.*;
public class App {

    private JFrame frame;
    private JTabbedPane tabbedPane;
    private PacientesPanel pacientesPanel;
    private GuardarInformacion gestion;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App app = new App();
            app.mostrarLogIn();
        });
    }

    public App() {
        gestion = new GuardarInformacion();

        gestion.prueba();
        
        frame = new JFrame("Gestión de Medicamentos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null);

        pacientesPanel = new PacientesPanel();
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Pacientes", pacientesPanel);

    }
    public void mostrarLogIn(){
        // Añadir la interfaz de Login al frame
        LoginGUI loginPanel = new LoginGUI(this, gestion);
        frame.setContentPane(loginPanel);
        frame.setVisible(true);

    }

    public void mostrarMenu(){
        frame.setContentPane(tabbedPane);
        frame.revalidate();
        frame.repaint();
    }

    public void mostrarRegistro(){

    }
}

