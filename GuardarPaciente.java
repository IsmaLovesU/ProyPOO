import java.util.List;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class GuardarPaciente {
    public List<Paciente> listaPacientes;
    GuardarUsuario gu = new GuardarUsuario();

    public GuardarPaciente() {
        listaPacientes = new ArrayList<>();
        cargarPacientesDesdeCSV();  // Nuevo método para cargar los pacientes
    }

    /**
     * Carga los pacientes desde un archivo CSV y los almacena en la lista de pacientes.
     * El archivo debe tener el formato: idPaciente, nombre, edad, informacionAdicional.
     */
    public void cargarPacientesDesdeCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Pacientes.csv"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                String idPaciente = datos[0];
                String idUsuario = datos[1];
                String nombre = datos[2];
                int edad = Integer.parseInt(datos[3]);
                String informacionAdicional = datos[4];
    
                Paciente paciente = new Paciente(idPaciente, nombre, edad, informacionAdicional);
    
                for (Usuario usuario : gu.listaUsuarios){
                    if (usuario.getId().equals(idUsuario)){
                        usuario.agregarPaciente(paciente);
                    }
                }
                listaPacientes.add(paciente);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Crea un nuevo paciente asociado a un usuario y lo agrega a la lista de pacientes.
     * @param idUsuario El identificador del usuario dueño del paciente.
     * @param nombre El nombre del paciente.
     * @param edad La edad del paciente.
     * @param informacionAdicional Información adicional sobre el paciente.
     */
    public void crearPaciente(String idUsuario, String nombre, int edad, String informacionAdicional) {
        Paciente nuevoPaciente = new Paciente(idUsuario, nombre, edad, informacionAdicional);
    
        for (Usuario usuario : gu.listaUsuarios) {
            if (usuario.getId().equals(idUsuario)) {
                usuario.agregarPaciente(nuevoPaciente); // Agregar paciente al usuario
                break;
            }
        }
    
        listaPacientes.add(nuevoPaciente); // Agregar paciente a la lista general
        //guardarPacientesCSV(); // Guardar el paciente en el archivo CSV
    }

    /**
     * Método para eliminar un paciente de un usuario especifico
     * @param idUsuario
     * @param nombrePacienteAEliminar
     */
    public void eliminarPaciente(String idUsuario, String nombrePacienteAEliminar) {
        for (Usuario usuario : gu.listaUsuarios) {
            if (usuario.getId().equals(idUsuario)) {
                // Busca el paciente a eliminar
                Paciente pacienteAEliminar = null;
                for (Paciente paciente : usuario.getPacientes()) {
                    if (paciente.getNombre().equals(nombrePacienteAEliminar)) {
                        pacienteAEliminar = paciente;
                        break;
                    }
                }
    
                // Si se encontró el paciente, lo elimina
                if (pacienteAEliminar != null) {
                    usuario.getPacientes().remove(pacienteAEliminar);
                } else {
    
                }
    
                // Guarda la lista actualizada en el archivo CSV
                guardarPacientesCSV();
                break; // Salimos del bucle una vez que hemos procesado el paciente
            }
        }
    }

    /**
     * Guarda los pacientes en un archivo CSV. Los datos se guardan en el formato:
     * idUsuario, idPaciente, nombrePaciente, edad, informacionAdicional.
     */
    public void guardarPacientesCSV() {
        try (FileWriter writer = new FileWriter("Pacientes.csv", false)) {
            for (Usuario usuario : gu.listaUsuarios)
                for (Paciente paciente : usuario.getPacientes()) {
                    writer.append(usuario.getId())
                          .append(",")
                          .append(paciente.getId())
                          .append(",")
                          .append(paciente.getNombre())
                          .append(",")
                          .append(String.valueOf(paciente.getEdad()))
                          .append(",")
                          .append(paciente.getInformacionAdicional())
                          .append("\n");
                }
            
            writer.flush();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Muestra los pacientes cuyo ID coincide con el ID proporcionado.
     * @param idPaciente
     * @return
     */
    public String mostrarPacientesPorId(String idPaciente) {
        for (Paciente paciente : listaPacientes) {
            if (paciente.getId().equals(idPaciente)) {
                return "ID: " + paciente.getId() + ", Nombre: " + paciente.getNombre();
            }
        }
        return "Paciente no encontrado";
    }

    /**
     * Muestra la lista de medicamentos asociados a un paciente especificado por su ID y nombre.
     * @param idPaciente
     * @param nombrePaciente
     * @return
     */
    public String obtenerMedicamentosPaciente(String idPaciente, String nombrePaciente) {
        StringBuilder resultado = new StringBuilder();
        
        for (Paciente paciente : listaPacientes) {
            if (paciente.getId().equals(idPaciente) && paciente.getNombre().equalsIgnoreCase(nombrePaciente)) {
                resultado.append("Medicamentos de ").append(nombrePaciente).append(":\n");
                for (Medicamento medicamento : paciente.getMedicamentos()) {
                    resultado.append("- ").append(medicamento).append("\n");
                }
                return resultado.toString(); // Retornamos la cadena cuando encontramos al paciente
            }
        }
    
        // Si no se encontró el paciente
        return "Paciente no encontrado. Verifique el ID y nombre.";
    }

    /**
     * Muestra los pacientes cuyo ID coincide con el ID proporcionado y muestra información más especifica
     * @param idPaciente
     * @param nombrePaciente
     * @return
     */
    public String buscarPacientePorIDyNombre(String idPaciente, String nombrePaciente) {
        for (Paciente paciente : listaPacientes) {
            if (paciente.getId().equals(idPaciente) && paciente.getNombre().equalsIgnoreCase(nombrePaciente)) {
                StringBuilder resultado = new StringBuilder();
                resultado.append("Datos del Paciente:\n");
                resultado.append("ID: ").append(paciente.getId()).append("\n");
                resultado.append("Nombre: ").append(paciente.getNombre()).append("\n");
                resultado.append("Edad: ").append(paciente.getEdad()).append("\n");
                resultado.append("Información Adicional: ").append(paciente.getInformacionAdicional()).append("\n");
                return resultado.toString();
            }
        }
    
        return "Paciente no encontrado. Verifique que el ID y el nombre.";
    }
    
}
