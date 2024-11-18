/**
 * Universidad del Valle de Guatemala
 * Programación Orinetada a Objetos 
 * Sección: 10
 * Ing. Kimberly Barrera
 * Proyecto - Pillas
*/

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * Clase NotificadorMedicamento.
 * Encargada de gestionar las notificaciones para recordar a los usuarios 
 * sobre los horarios de suministro de medicamentos de los pacientes.
 * Utiliza un scheduler para verificar periódicamente si hay medicamentos
 * que deben tomarse en el momento actual.
 * 
 */
public class NotificadorMedicamento {
    private Usuario usuario;
    private ScheduledExecutorService scheduler;

    /**
     * Constructor de la clase NotificadorMedicamento.
     * 
     * @param usuario El usuario asociado al notificador, que contiene 
     *                la lista de pacientes y sus medicamentos.
     */
    public NotificadorMedicamento(Usuario usuario) {
        this.usuario = usuario;
        this.scheduler = Executors.newScheduledThreadPool(1);
    }

    /**
     * Inicia el proceso de notificación.
     * Configura una tarea programada que verifica los medicamentos
     * cada minuto.
     */
    public void iniciar() {
        scheduler.scheduleAtFixedRate(() -> verificarMedicamentos(), 0, 1, TimeUnit.MINUTES);
    }

    /**
     * Verifica si hay medicamentos que deben tomarse en el momento actual.
     * Recorre la lista de pacientes y sus medicamentos para comparar los horarios
     * de suministro con la hora actual.
     */
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

    /**
     * Envía una notificación al usuario para recordar un medicamento.
     * Muestra un cuadro de diálogo con el nombre del paciente y el medicamento.
     * 
     * @param paciente El paciente asociado al medicamento.
     * @param medicamento El medicamento que debe tomarse.
     */
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

    /**
     * Detiene el proceso de notificación.
     * Finaliza el scheduler para liberar los recursos.
     */
    public void detener() {
        scheduler.shutdown();
    }
}
