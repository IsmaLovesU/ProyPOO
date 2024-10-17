import java.util.List;
import java.util.ArrayList;
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
    private List<Medicamento> listaMedicamentos;

    /**
     * Constructor que inicializa las listas de usuarios y pacientes.
     * También carga los usuarios desde un archivo CSV al iniciar.
     */
    public GuardarInformacion() {
        listaUsuarios = new ArrayList<>();
        listaPacientes = new ArrayList<>();
        listaMedicamentos = new ArrayList<>();
        cargarUsuariosDesdeCSV();  // Nuevo método para cargar los usuarios
        cargarPacientesDesdeCSV();  // Nuevo método para cargar los pacientes
        cargarMedicamentosDesdeCSV();  // Nuevo método para cargar los medicamentos
    }

    // Este método de prueba solo es para crear un usuario y que lo pruebe en la GUI, luego lo borro
    public void prueba(){
        Usuario usuario= new Usuario("123", "Diego", "Diego", "123", 17, "Siempre", "JJS");
        listaUsuarios.add(usuario);
    }

    /**
     * Carga los usuarios desde un archivo CSV y los almacena en la lista de usuarios.
     * El archivo debe tener el formato: id, nombre, nombreUsuario, contrasena, edad, sexo, tipoUsuario.
     */
    public void cargarUsuariosDesdeCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Usuarios.csv"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                // Nota: En lugar de generar un nuevo ID, usa el ID que ya está en el archivo CSV, sino luego se duplican los usuarios ayiyiyiyiyi
                String id = datos[0];  // Usar el ID existente del CSV
                String nombre = datos[1];
                String nombreUsuario = datos[2];
        
                // Descifrar la contraseña
                String contrasenaCifrada = datos[3];
                String contrasenaDescifrada = AESUtil.decrypt(contrasenaCifrada);
                
                int edad = Integer.parseInt(datos[4]);
                String sexo = datos[5];
                String tipoUsuario = datos[6];
    
                Usuario usuario = new Usuario(id, nombre, nombreUsuario, contrasenaDescifrada, edad, sexo, tipoUsuario);
                listaUsuarios.add(usuario);  // Agregar el usuario a la lista
            }
        } catch (Exception e) {
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
                String idPaciente = datos[1]; 
                String nombre = datos[0];
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
                for (Paciente paciente : listaPacientes) {
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
     public String registroUsuario(String id, String nombre, String nombreUsuario, String contraseña, int edad, String sexo, String tipoUsuario) {
        if (contraseña == null) {
            return "Error: La contraseña no puede ser nula.";
        }
    
        try {
            contraseña = AESUtil.encrypt(contraseña); // Cifrar la contraseña
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de excepciones
            return "Ocurrió un error al guardar la información"; // Salir del método si hay un error
        }

        // Verificar si el usuario ya existe
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getId().equals(id) || usuario.getNombreUsuario().equals(nombreUsuario)) {
                return "El usuario ya existe. Ingrese nuevamente los datos"; // Salir si el usuario ya existe
            }
        }
        
        // Si no existe, agregarlo a la lista
        Usuario usuario = new Usuario(id, nombre, nombreUsuario, contraseña, edad, sexo, tipoUsuario);
        listaUsuarios.add(usuario);
        return "Registro de usuario exitoso";
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
    
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getId().equals(idUsuario)) {
                usuario.agregarPaciente(nuevoPaciente); // Agregar paciente al usuario
                break;
            }
        }
    
        listaPacientes.add(nuevoPaciente); // Agregar paciente a la lista general
        //guardarPacientesCSV(); // Guardar el paciente en el archivo CSV
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
        for (Paciente paciente : listaPacientes) {
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
     * Guarda los usuarios en un archivo CSV. Los datos se guardan en el formato:
     * id, nombre, nombreUsuario, contrasena, edad, sexo, tipoUsuario.
     */
    public void guardarUsuariosCSV() {
        try (FileWriter writer = new FileWriter("Usuarios.csv", false)) { // Sobrescribe el archivo para evitar que se dupliquen los usuarios
            for (Usuario usuario : listaUsuarios) {
                String contrasenaCifrada;
            try {
                // Cifrar la contraseña antes de guardarla
                contrasenaCifrada = AESUtil.encrypt(usuario.getContrasena());
            } catch (Exception e) {
                continue;  // Si hay un error al cifrar, saltar al siguiente usuario
            }
            
            // Guardar los datos del usuario en el archivo CSV
                writer.append(usuario.getId())
                      .append(",")
                      .append(usuario.getNombre())
                      .append(",")
                      .append(usuario.getNombreUsuario())
                      .append(",")
                      .append(contrasenaCifrada)
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
        try (FileWriter writer = new FileWriter("Pacientes.csv", false)) {
                for (Paciente paciente : listaPacientes) {
                    writer.append(paciente.getId())
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
     * Guarda los medicamentos en un archivo CSV. Los datos se guardan en el formato:
     * idPaciente, idMedicamento, nombreMedicamento, descripcion, dosis, inventario.
     */
    public void guardarMedicamentosCSV() {
        try (FileWriter writer = new FileWriter("Medicamentos.csv", false)) {
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
     * Verifica que el usuario y la contraseña existan para el inicio de sesión
     * 
     * @param nombreUsuario nombre de usuario del usuario que ingresa al programa
     * @param contraseña la contraseña del usuario
     * 
     * @return tru si el usuario y la contraseña coniciden con un usuario existente
     */
    public boolean inicioSesion(String nombreUsuario, String contraseña){

        for(Usuario usuario: listaUsuarios){
            if(nombreUsuario.equals(usuario.getNombreUsuario()) && contraseña.equals(usuario.getContrasena())){
                return true;
            }
        }

        return false;
    }

    /**
     * Método que autentica a un usuario verificando su nombre de usuario y contraseña
     * en la lista de usuarios cargados desde el archivo CSV.
     *
     * @param nombreUsuario El nombre de usuario que se desea autenticar.
     * @param contrasena La contraseña correspondiente al usuario.
     * @return true si las credenciales coinciden con las almacenadas en la lista de usuarios, 
     *         false en caso contrario.
     */
    public boolean autenticar(String nombreUsuario, String contrasena) {
        for (Usuario usuario : listaUsuarios) {
            String contrasenaDescifrada;
            
            try {
                contrasenaDescifrada = AESUtil.decrypt(usuario.getContrasena());
            } catch (Exception e) {
                System.out.println("Error al descifrar la contraseña.");
                continue;
            }

            // Verificamos si el nombre de usuario y la contraseña coinciden
            if (usuario.getNombreUsuario().equals(nombreUsuario) && contrasenaDescifrada.equals(contrasena)) {
                System.out.println("Has iniciado sesión");
                return true;
            }
        }

        // Si no se encuentran coincidencias, se indica que las credenciales son incorrectas
        System.out.println("Usuario o contraseña incorrectos.");
        return false;
    }

    

}