import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Clase que maneja la gestión de información para usuarios, pacientes y medicamentos.
 * Proporciona funcionalidad para registrar, crear y guardar datos en archivos CSV.
 */
public class GuardarInformacion {
    private List<Usuario> listaUsuarios;
    private List<Paciente> listaPacientes;

    /**
     * Constructor que inicializa las listas de usuarios y pacientes.
     * También carga los usuarios desde un archivo CSV al iniciar.
     */
    public GuardarInformacion() {
        listaUsuarios = new ArrayList<>();
        listaPacientes = new ArrayList<>();
        cargarUsuariosDesdeCSV();  // Nuevo método para cargar los usuarios
    }

    /**
     * Carga los usuarios desde un archivo CSV y los almacena en la lista de usuarios.
     * El archivo debe tener el formato: id, nombre, nombreUsuario, contrasena, edad, sexo, tipoUsuario.
     */
    private void cargarUsuariosDesdeCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Usuarios.csv"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                String id = UUID.randomUUID().toString(); // Generar un nuevo ID
                String nombre = datos[1];
                String nombreUsuario = datos[2];
                // Descifrar la contraseña
                String contrasenaCifrada = datos[3];
                String contrasenaDescifrada = AESUtil.decrypt(contrasenaCifrada);
                int edad = Integer.parseInt(datos[4]);
                String sexo = datos[5];
                String tipoUsuario = datos[6];

                Usuario usuario = new Usuario(id, nombre, nombreUsuario, contrasenaCifrada, edad, sexo, tipoUsuario);
                listaUsuarios.add(usuario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                String idPaciente = UUID.randomUUID().toString(); // Generar nuevo ID para el paciente
                String nombre = datos[1];
                int edad = Integer.parseInt(datos[2]);
                String informacionAdicional = datos[3];
    
                Paciente paciente = new Paciente(idPaciente, nombre, edad, informacionAdicional);
                listaPacientes.add(paciente);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Carga los medicamentos desde un archivo CSV y los almacena en la lista de medicamento.
     * El archivo debe tener el formato: idMedicamento, nombreMedicamento, descripcion, dosis, horarioSuministro, recesatado, inventario
     */
    public void cargarMedicamentosDesdeCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Medicamentos.csv"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                String idMedicamento = UUID.randomUUID().toString(); // Generar nuevo ID para el medicamento
                String idPaciente = datos[0]; // Obtener el ID del paciente
                String nombreMedicamento = datos[1];
                String descripcion = datos[2];
                int dosis = Integer.parseInt(datos[3]);
                float inventario = Float.parseFloat(datos[4]);
    
                // Crear el objeto Medicamento
                Medicamento medicamento = new Medicamento(idMedicamento, nombreMedicamento, descripcion, dosis, inventario);
                
                // Buscar el paciente correspondiente por el idPaciente
                for (Paciente paciente : listaPacientes) {
                    if (paciente.getId().equals(idPaciente)) {
                        // Asocia el medicamento con el paciente
                        paciente.agregarMedicamentos(medicamento);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    

    /**
     * Obtiene la lista de usuarios registrados.
     * 
     * @return Una lista de usuarios.
     */
    public List<Usuario> getUsuarios() {
        return listaUsuarios;
    }

    /**
     * Registra un nuevo usuario en el sistema.
     * 
     * @param id El identificador del usuario.
     * @param nombre El nombre completo del usuario.
     * @param nombreUsuario El nombre de usuario.
     * @param contraseña La contraseña del usuario.
     * @param edad La edad del usuario.
     * @param sexo El sexo del usuario.
     * @param tipoUsuario El tipo de usuario (doctor, administrador, etc.).
     */
    public void registroUsuario(String id, String nombre, String nombreUsuario, String contraseña, int edad, String sexo, String tipoUsuario) {
        Usuario usuario = new Usuario(id, nombre, nombreUsuario, contraseña, edad, sexo, tipoUsuario);
        listaUsuarios.add(usuario);
    }

    /**
     * Crea un nuevo paciente asociado a un usuario y lo agrega a la lista de pacientes.
     * 
     * @param idUsuario El identificador del usuario dueño del paciente.
     * @param nombre El nombre del paciente.
     * @param edad La edad del paciente.
     * @param informacionAdicional Información adicional sobre el paciente.
     */
    public void crearPaciente(String idUsuario, String nombre, int edad, String informacionAdicional) {
        Paciente nuevoPaciente = new Paciente(idUsuario, nombre, edad, informacionAdicional);
        nuevoPaciente.generarId();

        for (Usuario usuario : listaUsuarios) {
            if (usuario.getId().equals(idUsuario)) {
                usuario.agregarPaciente(nuevoPaciente);
                break;
            }
        }

        listaPacientes.add(nuevoPaciente);  
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

        for (Paciente paciente : listaPacientes) {
            if (paciente.getId().equals(idPaciente)) {
                paciente.agregarMedicamentos(medicamento);
                break;
            }
        }
    }

    /**
     * Guarda los usuarios en un archivo CSV. Los datos se guardan en el formato:
     * id, nombre, nombreUsuario, contrasena, edad, sexo, tipoUsuario.
     */
    public void guardarUsuariosCSV() {
        try (FileWriter writer = new FileWriter("Usuarios.csv", true)) {
            for (Usuario usuario : listaUsuarios) {
                writer.append(usuario.getId())
                      .append(",")
                      .append(usuario.getNombre())
                      .append(",")
                      .append(usuario.getNombreUsuario())
                      .append(",")
                      .append(usuario.getContrasena())
                      .append(",")
                      .append(String.valueOf(usuario.getEdad()))
                      .append(",")
                      .append(usuario.getSexo())
                      .append(",")
                      .append(usuario.getTipoUsuario())
                      .append("\n");
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Guarda los pacientes en un archivo CSV. Los datos se guardan en el formato:
     * idUsuario, idPaciente, nombrePaciente, edad, informacionAdicional.
     */
    public void guardarPacientesCSV() {
        try (FileWriter writer = new FileWriter("Pacientes.csv", true)) {
            for (Usuario usuario : listaUsuarios) {
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
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    //Luego lo docuemnto
    public void guardarMedicamentosCSV() {
        try (FileWriter writer = new FileWriter("Medicamentos.csv", true)) {
            for (Paciente paciente : listaPacientes) {
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
    

    /**
     * Guarda los medicamentos en un archivo CSV. Los datos se guardan en el formato:
     * idPaciente, idMedicamento, nombreMedicamento, descripcion, dosis, inventario.
     
    public void guardarMedicamentosCSV() {
        try (FileWriter writer = new FileWriter("Medicamentos.csv", true)) {
            for (Paciente paciente : listaPacientes) {
                System.out.println("Paciente: " + paciente.getNombre());
                for (Medicamento medicamento : paciente.getMedicamentos()) {
                    System.out.println("Medicamento encontrado para el paciente: " + medicamento.getNombre());
                    System.out.println("Descripción: " + medicamento.getDescripcion());
                    System.out.println("Dosis: " + medicamento.getDosis());
                    System.out.println("Inventario: " + medicamento.getInventario());
                    writer.write(paciente.getId() + "," + medicamento.getId() + "," + medicamento.getNombre() + "," + medicamento.getDescripcion() + "," + medicamento.getDosis() + "," + medicamento.getInventario() + "\n");
                }
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    */

}
