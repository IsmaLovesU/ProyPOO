import java.util.List;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class GuardarMedicamento {
    private List<Medicamento> listaMedicamentos;
    GuardarPaciente gp = new GuardarPaciente();

    public GuardarMedicamento() {
        listaMedicamentos = new ArrayList<>();
        cargarMedicamentosDesdeCSV();  // Nuevo método para cargar los medicamentos
    }

    /**
     * Carga los medicamentos desde un archivo CSV y los almacena en la lista de medicamento.
     * El archivo debe tener el formato: idMedicamento, nombreMedicamento, descripcion, dosis, horarioSuministro, recesatado, inventario.
     */
    public void cargarMedicamentosDesdeCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Medicamentos.csv"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                String idMedicamento = datos[0]; // Generar nuevo ID para el medicamento
                String idPaciente = datos[1]; // Obtener el ID del paciente
                String nombreMedicamento = datos[2];
                String descripcion = datos[3];
                int dosis = Integer.parseInt(datos[4]);
                float inventario = Float.parseFloat(datos[5]);

                // Crear el objeto Medicamento
                Medicamento medicamento = new Medicamento(idMedicamento, nombreMedicamento, descripcion, dosis, inventario);

                // Buscar el paciente correspondiente por el idPaciente
                for (Paciente paciente : gp.listaPacientes) {
                    if (paciente.getId().equals(idPaciente)) {
                        // Asocia el medicamento con el paciente
                        paciente.agregarMedicamentos(medicamento);
                        break;
                    }
                }
                // Agregar el medicamento a la lista general
                listaMedicamentos.add(medicamento);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Crea un nuevo medicamento asociado a un paciente y lo agrega a la lista de medicamentos del paciente.
     * 
     * @param idPaciente El identificador del paciente.
     * @param nombreM El nombre del medicamento.
     * @param descripcion La descripción del medicamento.
     * @param dosis La dosis recomendada.
     * @param inventario La cantidad disponible en inventario.
     */
    public void crearMedicamento(String idPaciente, String nombreM, String descripcion, int dosis, float inventario) {
        Medicamento medicamento = new Medicamento(idPaciente, nombreM, descripcion, dosis, inventario);

        for (Paciente paciente : gp.listaPacientes) {
            if (paciente.getId().equals(idPaciente)) {
                paciente.agregarMedicamentos(medicamento);
                break;
            }
        }
        guardarMedicamentosCSV();
    }

    /**
     * Elimina un medicamento del archivo CSV "Medicamentos.csv" basado en el ID proporcionado.
     * 
     * Este método lee todas las líneas del archivo "Medicamentos.csv" y elimina la línea que
     * contiene el ID del medicamento que coincide con el proporcionado como parámetro.
     * Luego, reescribe el archivo con las líneas actualizadas que no contienen el medicamento eliminado.
     * 
     * @param NombreMedicamentoAEliminar El nombre del medicamento que se desea eliminar del archivo CSV.
     * 
     * @throws IOException Si ocurre un error al leer o escribir en el archivo CSV.
     */
    public void eliminarMedicamento(String idPaciente, String nombreMedicamentoAEliminar) {
        for (Paciente paciente : gp.listaPacientes) {
            if (paciente.getId().equals(idPaciente)) {
                // Busca el medicamento a eliminar
                Medicamento medicamentoAEliminar = null;
                for (Medicamento medicamento : paciente.getMedicamentos()) {
                    if (medicamento.getNombre().equals(nombreMedicamentoAEliminar)) {
                        medicamentoAEliminar = medicamento;
                        break;
                    }
                }
    
                // Si se encontró el medicamento, lo elimina
                if (medicamentoAEliminar != null) {
                    paciente.getMedicamentos().remove(medicamentoAEliminar);
                    System.out.println("Medicamento eliminado: " + nombreMedicamentoAEliminar);
                } else {
                    System.out.println("Medicamento no encontrado: " + nombreMedicamentoAEliminar);
                }
    
                // Guarda la lista actualizada en el archivo CSV
                guardarMedicamentosCSV();
                break; // Salimos del bucle una vez que hemos procesado el paciente
            }
        }
    }

    /**
     * Guarda los medicamentos en un archivo CSV. Los datos se guardan en el formato:
     * idPaciente, idMedicamento, nombreMedicamento, descripcion, dosis, inventario.
     */
    public void guardarMedicamentosCSV() {
        try (FileWriter writer = new FileWriter("Medicamentos.csv", false)) {
            for (Paciente paciente : gp.listaPacientes) {
                for (Medicamento medicamento : paciente.getMedicamentos()) {
                    writer.append(paciente.getId())
                          .append(",")
                          .append(medicamento.getId())
                          .append(",")
                          .append(medicamento.getNombre())
                          .append(",")
                          .append(medicamento.getDescripcion())
                          .append(",")
                          .append(String.valueOf(medicamento.getDosis()))
                          .append(",")
                          .append(String.valueOf(medicamento.getInventario()))
                          .append("\n");
                }
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println(e);
        }

    }
}
