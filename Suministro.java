import java.util.List;

public class Suministro {
    private Paciente paciente;

    //Metodo Constructor
    public Suministro(Paciente paciente) {
        this.paciente = paciente;
    }

    //Metodo para obtener la cantidad de medicamentos que el paciente debe tomar
    public int inventarioSuministros() {
        List<Medicamento> medicamentos = paciente.getMedicamentos();
        return medicamentos.size();
    }

    //Metodo para obtener los recordatorios de los medicamentos y la dosis que tiene que tomar el
    public String medicamentoRecordatorio() {
        StringBuilder recordatorio = new StringBuilder();
        List<Medicamento> medicamentos = paciente.getMedicamentos();
        
        for (Medicamento medicamento : medicamentos) {
            recordatorio.append("Medicamento: ").append(medicamento.getNombre())
                        .append("\nDosis: ").append(medicamento.getDosis())
                        .append("\nHorario de suministro: ").append(medicamento.getHorarioDeSuministro());
        }
        
        return recordatorio.toString();
    }

    // Getters y Setters
    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
