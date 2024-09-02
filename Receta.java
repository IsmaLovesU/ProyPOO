import java.util.ArrayList;
import java.util.List;

public class Receta {
    private String paciente;
    private String nombreMedico;
    private String centroMedico;
    private String fecha;
    private List<Medicamento> medicamentos;

    public Receta(String paciente, String fecha, String nombreMedico, String centroMedico) {
        this.paciente = paciente;
        this.fecha = fecha;
        this.nombreMedico = nombreMedico;
        this.centroMedico= centroMedico;
        this.medicamentos = new ArrayList<>();
    }
    public Receta(){
        
    }

    //Getters y setters
    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public String getCentroMedico() {
        return centroMedico;
    }

    public void setCentroMedico(String centroMedico) {
        this.centroMedico = centroMedico;
    }

    // Método para agregar un medicamento a la receta
    public void agregarMedicamento(Medicamento medicamento) {
        this.medicamentos.add(medicamento);
    }

    // Método para eliminar un medicamento de la receta
    public void eliminarMedicamento(Medicamento medicamento) {
        this.medicamentos.remove(medicamento);
    }

    // Método para listar todos los medicamentos en la receta
    public List<Medicamento> listarMedicamentos() {
        return new ArrayList<>(this.medicamentos);
    }

    public void editarReceta (String nuevoPaciente, String nuevaFecha, String nuevoNombreMedico, String nuevoCentroMedico){
        this.paciente = nuevoPaciente;
        this.nombreMedico = nuevoNombreMedico;
        this.centroMedico = nuevoCentroMedico;
        this.fecha = nuevaFecha;
    }
     
}

