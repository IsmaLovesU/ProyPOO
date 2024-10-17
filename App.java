import javax.swing.*;
public class App {

    private JFrame frame;
    private JFrame programaFrame;
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
        
        frame = new JFrame("LOG In");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null);

        pacientesPanel = new PacientesPanel();
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Pacientes", pacientesPanel);

        inicializarProgramaframe();

    }
    public void mostrarLogIn(){
        LoginGUI loginPanel = new LoginGUI(this, gestion);
        frame.setContentPane(loginPanel);
        frame.setVisible(true);

    }

    public void mostrarMenu(){
        frame.dispose();
        programaFrame.setVisible(true);
    }

    public void mostrarRegistro(){
        RegistroGUI registroPanel= new RegistroGUI(this, gestion);
        frame.setContentPane(registroPanel);
        frame.setVisible(true);
    }

    private void inicializarProgramaframe(){
        programaFrame = new JFrame("Gestion de Medicamentos");
        programaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        programaFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        programaFrame.setLocationRelativeTo(null);
        programaFrame.setContentPane(tabbedPane);
    }
}

