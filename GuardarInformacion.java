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

        for (Usuario u : listaUsuarios) {
            if (u.getId().equals(idUsuario)) {
                u.agregarPaciente(nuevoPaciente);
                break;
            }
        }

        listaPacientes.add(nuevoPaciente);  
    }

    public void crearMedicamento(String idPaciente, String nombreM, String descripcion, int dosis, float inventario) {
        Medicamento medicamento = new Medicamento(idPaciente, nombreM, descripcion, dosis, inventario);

        for (Paciente p : listaPacientes) {
            if (p.getId().equals(idPaciente)) {
                p.agregarMedicamentos(medicamento);
                break;
            }
        }
    }

    // Guardar usuarios (doctores) en CSV
    public void guardarUsuariosCSV() {
        try (FileWriter writer = new FileWriter("Usuarios.csv", true)) {
            for (Usuario u : listaUsuarios) {
                writer.append(u.getId())
                      .append(",")
                      .append(u.getNombre())
                      .append(",")
                      .append(u.getNombreUsuario())
                      .append(",")
                      .append(u.getContrasena())
                      .append(",")
                      .append(String.valueOf(u.getEdad()))
                      .append(",")
                      .append(u.getSexo())
                      .append(",")
                      .append(u.getTipoUsuario())
                      .append("\n");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Guardar pacientes en CSV
    public void guardarPacientesCSV() {
        try (FileWriter writer = new FileWriter("Pacientes.csv", true)) {
            for (Usuario u : listaUsuarios) {
                for (Paciente p : u.getPacientes()) {
                    writer.append(u.getId())
                          .append(",")
                          .append(p.getId())
                          .append(",")
                          .append(p.getNombre())
                          .append(",")
                          .append(String.valueOf(p.getEdad()))
                          .append(",")
                          .append(p.getInformacionAdicional())
                          .append("\n");
                }
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Guardar medicamentos en CSV
    public void guardarMedicamentosCSV() {
        try (FileWriter writer = new FileWriter("Medicamentos.csv", true)) {
            for (Paciente p : listaPacientes) {
                System.out.println("Paciente: " + p.getNombre());
                for (Medicamento m : p.getMedicamentos()) {
                    System.out.println("Medicamento encontrado para el paciente: " + m.getNombre());
                    System.out.println("Descripción: " + m.getDescripcion());
                    System.out.println("Dosis: " + m.getDosis());
                    System.out.println("Inventario: " + m.getInventario());
                    writer.write(p.getId() + "," + m.getId() + "," + m.getNombre() + "," + m.getDescripcion() + "," + m.getDosis() + "," + m.getInventario() + "\n");
                }
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
