/**
 * Universidad del Valle de Guatemala
 * Programación Orinetada a Objetos 
 * Sección: 10
 * Ing. Kimberly Barrera
 * Proyecto - Pillas
*/

import java.util.ArrayList;
import java.util.UUID;

/**
 * Clase Paciente.
 * Representa a un paciente que incluye información como su nombre, edad, condiciones médicas, 
 * medicamentos asociados y datos adicionales relevantes. Proporciona métodos para gestionar 
 * esta información, incluyendo la generación de un identificador único.
 */
public class Paciente {
    
    private String id;
    private String nombre;
    private int edad;
    private ArrayList<String> condiciones;
    private ArrayList<Medicamento> medicamentos;
    private String informacionAdicional;

    /**
     * Constructor de la clase Paciente.
     * 
     * @param id Identificador único del paciente.
     * @param nombre Nombre del paciente.
     * @param edad Edad del paciente.
     * @param informacionAdicional Información adicional relevante sobre el paciente.
     */
    public Paciente(String id, String nombre, int edad, String informacionAdicional) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.condiciones = new ArrayList<>();
        this.medicamentos = new ArrayList<>();
        this.informacionAdicional = informacionAdicional;
    }

    /**
     * Genera un identificador único para el paciente.
     * 
     * @return Un identificador único (UUID).
     */
    public String generarId() {
        return UUID.randomUUID().toString();
    }

    /**
     * Obtiene el identificador único del paciente.
     * 
     * @return El identificador del paciente.
     */
    public String getId() {
        return id;
    }

    /**
     * Obtiene el nombre del paciente.
     * 
     * @return El nombre del paciente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del paciente.
     * 
     * @param nombre El nuevo nombre del paciente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la edad del paciente.
     * 
     * @return La edad del paciente.
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Establece la edad del paciente.
     * 
     * @param edad La nueva edad del paciente.
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Obtiene la lista de condiciones médicas del paciente.
     * 
     * @return Una lista con las condiciones médicas del paciente.
     */
    public ArrayList<String> getCondiciones() {
        return condiciones;
    }

    /**
     * Agrega una nueva condición médica al paciente.
     * 
     * @param nuevaCondicion La nueva condición médica a agregar.
     */
    public void agregarCondiciones(String nuevaCondicion) {
        condiciones.add(nuevaCondicion);
    }

    /**
     * Obtiene la lista de medicamentos asociados al paciente.
     * 
     * @return Una lista con los medicamentos del paciente.
     */
    public ArrayList<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    /**
     * Agrega un nuevo medicamento asociado al paciente.
     * 
     * @param nuevoMedicamento El nuevo medicamento a agregar.
     */
    public void agregarMedicamentos(Medicamento nuevoMedicamento) {
        this.medicamentos.add(nuevoMedicamento);
    }

    /**
     * Obtiene la información adicional asociada al paciente.
     * 
     * @return La información adicional del paciente.
     */
    public String getInformacionAdicional() {
        return informacionAdicional;
    }

    /**
     * Establece la información adicional asociada al paciente.
     * 
     * @param informacionAdicional La nueva información adicional del paciente.
     */
    public void setInformacionAdicional(String informacionAdicional) {
        this.informacionAdicional = informacionAdicional;
    }
}
