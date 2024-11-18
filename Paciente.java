/**
 * Universidad del Valle de Gutemala
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
 * 
 * @author Universidad del Valle
 * @version 1.0
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

    public String generarId() {
        return UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public ArrayList<String> getCondiciones() {
        return condiciones;
    }

    public void agregarCondiciones(String nuevaCondicion) {
        condiciones.add(nuevaCondicion);
    }

    public ArrayList<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void agregarMedicamentos(Medicamento nuevoMedicamento) {
        this.medicamentos.add(nuevoMedicamento);
    }

    public String getInformacionAdicional() {
        return informacionAdicional;
    }

    public void setInformacionAdicional(String informacionAdicional) {
        this.informacionAdicional = informacionAdicional;
    }
}
