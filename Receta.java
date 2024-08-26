import java.util.ArrayList;
import java.util.List;

public class Receta {
    private String nombrePaciente;
    private String fecha;
    private List<Medicamento> medicamentos;

    public Receta(String nombrePaciente, String fecha) {
        this.nombrePaciente = nombrePaciente;
        this.fecha = fecha;
        this.medicamentos = new ArrayList<>();
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

}

