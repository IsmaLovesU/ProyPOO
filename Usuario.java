/**
 * Universidad del Valle de Gutemala
 * Programación Orinetada a Objetos 
 * Sección: 10
 * Ing. Kimberly Barrera
 * Proyecto - Pillas
*/

import java.util.UUID;
import java.util.ArrayList;

/**
 * Clase que representa un usuario del sistema.
 * Contiene información del usuario, como su nombre, nombre de usuario, contraseña, edad,
 * sexo, tipo de usuario, y una lista de pacientes asociados.
 */
public class Usuario {
    private String id;
    private String nombre;
    private String nombreUsuario;
    private String contrasena;
    private int edad;
    private String sexo;
    private String tipoUsuario;
    private ArrayList<Paciente> pacientes;

    /**
     * Constructor de la clase Usuario.
     * Inicializa los atributos del usuario con la información proporcionada.
     *
     * @param id            Identificador único del usuario.
     * @param nombre        Nombre completo del usuario.
     * @param nombreUsuario Nombre de usuario para autenticación.
     * @param contrasena    Contraseña del usuario.
     * @param edad          Edad del usuario.
     * @param sexo          Sexo del usuario.
     * @param tipoUsuario   Tipo de usuario (e.g., administrador, médico, paciente).
     */
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

    /**
     * Genera un identificador único para el usuario.
     *
     * @return Un identificador único (UUID).
     */
    public String generarId(){
        return UUID.randomUUID().toString();
    }

    /**
     * Obtiene el identificador único del usuario.
     *
     * @return El identificador único del usuario.
     */
    public String getId(){
        return id;
    }

    /**
     * Obtiene el nombre completo del usuario.
     *
     * @return El nombre completo del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre completo del usuario.
     *
     * @param nombre El nuevo nombre completo del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el nombre de usuario para autenticación.
     *
     * @return El nombre de usuario.
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Establece el nombre de usuario para autenticación.
     *
     * @param nombreUsuario El nuevo nombre de usuario.
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Establece una nueva contraseña para el usuario.
     *
     * @param contrasena La nueva contraseña.
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Obtiene la edad del usuario.
     *
     * @return La edad del usuario.
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Establece la edad del usuario.
     *
     * @param edad La nueva edad.
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Obtiene el sexo del usuario.
     *
     * @return El sexo del usuario.
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Establece el sexo del usuario.
     *
     * @param sexo El nuevo sexo del usuario.
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * Obtiene el tipo de usuario.
     *
     * @return El tipo de usuario.
     */
    public String getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * Establece el tipo de usuario.
     *
     * @param tipoUsuario El nuevo tipo de usuario.
     */
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    /**
     * Agrega un nuevo paciente a la lista de pacientes asociados al usuario.
     *
     * @param paciente El paciente a agregar.
     */
    public void agregarPaciente(Paciente paciente){
        pacientes.add(paciente);
    }

    /**
     * Obtiene la lista de pacientes asociados al usuario.
     *
     * @return La lista de pacientes.
     */
    public ArrayList<Paciente> getPacientes() {
        return pacientes;
    }
}
