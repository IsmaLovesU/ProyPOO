import java.time.LocalDate;
import java.time.LocalTime;

public class Suministro {
    //Los atributos baby
    private int id;
    private String medicamentoMaloshPiumPium;
    private int cantidad;
    private LocalDate fechaSuministroUWU;
    private LocalTime horaSuministroAÑA;
    private String estado;


    //El bob constructor
    public Suministro(int id, String medicamentoMaloshPiumPium, int cantidad, LocalDate fechaSuministroUWU, LocalTime horaSuministroAÑA, String estado){
        this.id = id;
        this.medicamentoMaloshPiumPium = medicamentoMaloshPiumPium;
        this.cantidad = cantidad;
        this.fechaSuministroUWU = fechaSuministroUWU;
        this.horaSuministroAÑA = horaSuministroAÑA;
        this.estado = estado;
    }

    //Gettes y setters (Me quede sin imaginacion)
    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public String getMedicamento() {
        return medicamentoMaloshPiumPium;
    }

    public void setMedicamento(String medicamentoMaloshPiumPium) {
        this.medicamentoMaloshPiumPium = medicamentoMaloshPiumPium;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getFechaSuministro() {
        return fechaSuministroUWU;
    }

    public void setFechaSuministro(LocalDate fechaSuministroUWU) {
        this.fechaSuministroUWU = fechaSuministroUWU;
    }

    public LocalTime getHoraSuministro() {
        return horaSuministroAÑA;
    }

    public void setHoraSuministro(LocalTime horaSuministroAÑA) {
        this.horaSuministroAÑA = horaSuministroAÑA;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}