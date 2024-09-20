import java.util.ArrayList;
import java.util.List;

public class Paciente {
    private String nombre;
    private int edad;
    private ArrayList<String> condiciones;
    private ArrayList<Medicamento> medicamentos;
    private String informacionAdicional;

    public Paciente(){

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
        medicamentos.add(nuevoMedicamento);
    }

    public String getInformacionAdicional() {
        return informacionAdicional;
    }

    public void setInformacionAdicional(String informacionAdicional) {
        this.informacionAdicional = informacionAdicional;
    }
}
