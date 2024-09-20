import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

public class Usuario {
    private String id;
    private String nombre;
    private String nombreUsuario;
    private String contrasena;
    private int edad;
    private String sexo;
    private String tipoUsuario;
    private List<Paciente> pacientes;

    public Usuario(String id, String nombre, String nombreUsuario, String contrasena, int edad, String sexo, String tipoUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.edad = edad;
        this.sexo = sexo;
        this.tipoUsuario = tipoUsuario;
        this.pacientes = new ArrayList<>();
    }

    public String generarId(){
        return UUID.randomUUID().toString();
    }

    public String getId(){
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public void agregarPaciente(Paciente paciente){
        this.pacientes.add(paciente);
    }


}
