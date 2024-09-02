
import java.time.LocalTime;

public class GuardarInformacacion {
    private Usuario usuario;
    private Paciente paciente;
    private Medicamento medicamento;

    public GuardarInformacacion(){
        usuario= new Usuario();
        paciente= new Paciente();
        medicamento = new Medicamento();
    }

    public void registroUsuario(String nombre, String nombreUsuario, String contraseña, int edad, String tipoUsuario){
        usuario.setNombre(nombre);
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setContrasena(contraseña);
        usuario.setEdad(edad);
        usuario.setTipoUsuario(tipoUsuario);
    }

    public void crearPaciente(String nombre, int edad, String informacionAdicional){
        paciente.setNombre(nombre);
        paciente.setEdad(edad);
        paciente.setInformacionAdicional(informacionAdicional);
    }

    public void crearMedicamento(String nombreM, String descripcion, int dosis, LocalTime horaSuministro, boolean recetado, float inventario){
        medicamento.setNombre(nombreM);
        medicamento.setDescripcion(descripcion);
        medicamento.setDosis(dosis);
        medicamento.setHorarioDeSuministro(horaSuministro);
        medicamento.setRecetado(recetado);
        medicamento.setInventario(inventario);
    }

}
