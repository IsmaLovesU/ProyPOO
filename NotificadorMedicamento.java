import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class NotificadorMedicamento {
    private Usuario usuario;
    private ScheduledExecutorService scheduler;

    public NotificadorMedicamento(Usuario usuario) {
        this.usuario = usuario;
        this.scheduler = Executors.newScheduledThreadPool(1);
    }

    public void iniciar() {
        scheduler.scheduleAtFixedRate(() -> verificarMedicamentos(), 0, 1, TimeUnit.MINUTES);
    }

    private void verificarMedicamentos() {
        LocalTime horaActual = LocalTime.now();
        for (Paciente paciente : usuario.getPacientes()) {
            for (Medicamento medicamento : paciente.getMedicamentos()) {
                if (medicamento.getHorarioDeSuministro().getHour() == horaActual.getHour() &&
                    medicamento.getHorarioDeSuministro().getMinute() == horaActual.getMinute()) {
                    enviarNotificacion(paciente, medicamento);
                }
            }
        }
    }

private void enviarNotificacion(Paciente paciente, Medicamento medicamento) {
    SwingUtilities.invokeLater(() -> {
        JOptionPane.showMessageDialog(null, 
            "Es hora de que el paciente " + paciente.getNombre() +
            " tome el medicamento: " + medicamento.getNombre() +
            " a las " + medicamento.getHorarioDeSuministro(),
            "Recordatorio de Medicamento",
            JOptionPane.INFORMATION_MESSAGE);
    });
}

    public void detener() {
        scheduler.shutdown();
    }
}
