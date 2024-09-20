import java.util.List;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader; //NUEVO
import java.io.FileReader; //NUEVO


public class GuardarInformacion {
    private List<Usuario> listaUsuarios;
    private List<Paciente> listaPacientes;

    public GuardarInformacion() {
        listaUsuarios = new ArrayList<>();
        listaPacientes = new ArrayList<>();
        cargarUsuariosDesdeCSV();  // Nuevo método para cargar los usuarios
    }

    // Nuevo método para cargar los usuarios desde el CSV al iniciar la aplicación
    private void cargarUsuariosDesdeCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Usuarios.csv"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                String id = datos[0];
                String nombre = datos[1];
                String nombreUsuario = datos[2];
                String contrasena = datos[3];
                int edad = Integer.parseInt(datos[4]);
                String sexo = datos[5];
                String tipoUsuario = datos[6];
                
                Usuario usuario = new Usuario(id, nombre, nombreUsuario, contrasena, edad, sexo, tipoUsuario);
                listaUsuarios.add(usuario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener usuarios (para comprobaciones adicionales si es necesario)
    public List<Usuario> getUsuarios() {
        return listaUsuarios;
    }

    public void registroUsuario(String id, String nombre, String nombreUsuario, String contraseña, int edad, String sexo, String tipoUsuario) {
        Usuario usuario = new Usuario(id, nombre, nombreUsuario, contraseña, edad, sexo, tipoUsuario);
        listaUsuarios.add(usuario);
    }

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

    public void crearMedicamento(String idPaciente, String nombreM, String descripcion, int dosis, float inventario) {
        Medicamento medicamento = new Medicamento(idPaciente, nombreM, descripcion, dosis, inventario);

        for (Paciente paciente : listaPacientes) {
            if (paciente.getId().equals(idPaciente)) {
                paciente.agregarMedicamentos(medicamento);
                break;
            }
        }
    }

    // Guardar usuarios (doctores) en CSV
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

    // Guardar pacientes en CSV
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

    // Guardar medicamentos en CSV
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
}
