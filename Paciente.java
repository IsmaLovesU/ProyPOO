import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Paciente {
    
    private String id;
    private String nombre;
    private int edad;
    private ArrayList<String> condiciones;
    private ArrayList<Medicamento> medicamentos;
    private String informacionAdicional;

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

    public List<String> getCondiciones() {
        return condiciones;
    }

    public void agregarCondiciones(String nuevaCondicion) {
        condiciones.add(nuevaCondicion);
    }

    public List<Medicamento> getMedicamentos() {
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
