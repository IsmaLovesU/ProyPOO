import java.util.List;
import java.util.ArrayList;
import java.time.LocalTime;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class GuardarInformacion {
    private Usuario usuario;
    private Paciente paciente;
    private Medicamento medicamento;
    private List<Paciente> listaPacientes;
    private List<Usuario> listaUsuarios;

    public GuardarInformacion(){
        listaUsuarios = new ArrayList<>();
        listaPacientes = new ArrayList<>();
    }

    public void registroUsuario(String nombre, String nombreUsuario, String contraseña, int edad, String tipoUsuario){
        usuario.setNombre(nombre);
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setContrasena(contraseña);
        usuario.setEdad(edad);
        usuario.setTipoUsuario(tipoUsuario);
    }

    public void crearPaciente(String nombre, int edad, String informacionAdicional){
        paciente.generarId();
        paciente.setNombre(nombre);
        paciente.setEdad(edad);
        paciente.setInformacionAdicional(informacionAdicional);
        paciente.agregarCondiciones(informacionAdicional);
    }

    public List<Paciente> mostrarPacientes() {
        return listaPacientes;
    }

    public void crearMedicamento(String nombreM, String descripcion, int dosis, LocalTime horaSuministro, boolean recetado, float inventario){
        medicamento.setNombre(nombreM);
        medicamento.setDescripcion(descripcion);
        medicamento.setDosis(dosis);
        medicamento.setHorarioDeSuministro(horaSuministro);
        medicamento.setRecetado(recetado);
        medicamento.setInventario(inventario);
        paciente.agregarMedicamentos(medicamento);
    }

    

    public void editarMedicamentos(Paciente paciente, Medicamento medicamentoEditado) {
        List<Medicamento> medicamentos = paciente.getMedicamentos();
        for (int i = 0; i < medicamentos.size(); i++) {
            if (medicamentos.get(i).getNombre().equals(medicamentoEditado.getNombre())) {
                medicamentos.set(i, medicamentoEditado);
                break;
            }
        }
    }

    public List<Medicamento> mostrarMedicamentos() {
        List<Medicamento> medicamentos = new ArrayList<>();
        for (Paciente paciente : listaPacientes) {
            medicamentos.addAll(paciente.getMedicamentos());
        }
        return medicamentos;
    }

    public boolean inicioSesion(String nombreUsuario, String contrasena) {
        return usuario.getNombreUsuario().equals(nombreUsuario) && usuario.getContrasena().equals(contrasena);
    }

    public void guardarUsuarioCSV() {
        try (FileWriter escribir = new FileWriter("Usuarios.csv", true)){
            escribir.append(usuario.getNombre())
                    .append(",")
                    .append(usuario.getNombreUsuario())
                    .append(",")
                    .append(usuario.getContrasena())
                    .append(",")
                    .append(String.valueOf(usuario.getEdad()))
                    .append(",")
                    .append(usuario.getTipoUsuario())
                    .append("\n");
            escribir.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void guardarPacientesCSV() {
        try (FileWriter escribir = new FileWriter("Pacientes.csv", true)){
            for (Paciente paciente : listaPacientes){
                escribir.append(paciente.getNombre())
                        .append(",")
                        .append(String.valueOf(paciente.getEdad()))
                        .append(",")
                        .append(paciente.getInformacionAdicional())
                        .append("\n");
            }
            escribir.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    
}
