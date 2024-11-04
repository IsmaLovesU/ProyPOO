import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
        // Aquí puedes utilizar SystemTray o alguna otra forma de notificación
        System.out.println("Es hora de que el paciente " + paciente.getNombre() + 
                           " tome el medicamento: " + medicamento.getNombre() +
                           " a las " + medicamento.getHorarioDeSuministro());
    }

    public void detener() {
        scheduler.shutdown();
    }
}
