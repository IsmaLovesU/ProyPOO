import java.util.ArrayList;

import javax.swing.*;
public class App {

    private JFrame frame;
    private JFrame programaFrame;
    private GuardarInformacion gestion;
    private ArrayList<Paciente> listaPacientes;

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App app = new App();
            app.mostrarLogIn();
        });
    }

    public App() {
        gestion = new GuardarInformacion();
        listaPacientes = new ArrayList<>();
        Paciente paciente = new Paciente("FNEON221", "Jesus", 10, "Es asmatico");
        listaPacientes.add(paciente);
        Paciente paciente2 = new Paciente("fadf3", "María", 12, "Nada");
        listaPacientes.add(paciente2);
        Paciente paciente3 = new Paciente("adfa23", "Pedro", 23, "Algo más");
        listaPacientes.add(paciente3);
    

        gestion.prueba();
        
        frame = new JFrame("LOG In");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null);

        inicializarProgramaframe();

    }
    public void mostrarLogIn(){
        LoginGUI loginPanel = new LoginGUI(this, gestion);
        frame.setContentPane(loginPanel);
        frame.setVisible(true);

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
    }
    public void mostrarPacientesPanel() {
        frame.setContentPane(new PacientesPanel(this, listaPacientes));
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }

    public void mostrarMedicamentosPanel(Paciente paciente) {
        frame.setContentPane(new MedicamentosPanel(this, paciente));
        frame.revalidate();
        frame.repaint();
    }


}

