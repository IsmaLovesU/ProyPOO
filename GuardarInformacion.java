import java.util.List;
import java.util.ArrayList;
import java.time.LocalTime;

public class GuardarInformacion {
    private Usuario usuario;
    private Paciente paciente;
    private Medicamento medicamento;
    private List<Usuario> usuarios;

    public GuardarInformacion(){
        usuario= new Usuario();
        paciente= new Paciente();
        medicamento = new Medicamento();
        usuarios = new ArrayList<>();
    }

    public void registroUsuario(String nombre, String nombreUsuario, String contraseña, int edad, String tipoUsuario){
        usuario.setNombre(nombre);
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setContrasena(contraseña);
        usuario.setEdad(edad);
        usuario.setTipoUsuario(tipoUsuario);
    }

    public void crearPaciente(String nombre, int edad, String informacionAdicional, Usuario usuario1){
        paciente.setNombre(nombre);
        paciente.setEdad(edad);
        paciente.setInformacionAdicional(informacionAdicional);
        
        usuario1.agregarPaciente(paciente);

        paciente = new Paciente();
    }

    public List<Paciente> mostrarPacientes(Usuario usuario1) {
        return usuario1.getPacientes();
    }

    public void crearMedicamento(String nombreM, String descripcion, int dosis, LocalTime horaSuministro, boolean recetado, float inventario){
        medicamento.setNombre(nombreM);
        medicamento.setDescripcion(descripcion);
        medicamento.setDosis(dosis);
        medicamento.setHorarioDeSuministro(horaSuministro);
        medicamento.setRecetado(recetado);
        medicamento.setInventario(inventario);
    }

    public void agregarMedicamentos(Paciente paciente, Medicamento medicamento) {
        paciente.getMedicamentos().add(medicamento);
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

    // public List<Medicamento> mostrarMedicamentos(Usuario usuario1) {
    //     return usuario1.getPacientes().
    // }

    public boolean inicioSesion(String nombreUsuario, String contrasena) {
        return usuario.getNombreUsuario().equals(nombreUsuario) && usuario.getContrasena().equals(contrasena);
    }

    

}
