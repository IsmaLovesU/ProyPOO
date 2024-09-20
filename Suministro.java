import java.time.LocalDate;
import java.time.LocalTime;

public class Suministro {
    //Los atributos baby
    private int id;
    private String medicamento;
    private int cantidad;
    private LocalDate fechaSuministro;
    private LocalTime horaSuministro;
    private String estado;


    //El bob constructor
    public Suministro(int id, String medicamento, int cantidad, LocalDate fechaSuministro, LocalTime horaSuministro, String estado){
        this.id = id;
        this.medicamento = medicamento;
        this.cantidad = cantidad;
        this.fechaSuministro = fechaSuministro;
        this.horaSuministro = horaSuministro;
        this.estado = estado;
    }

    public Suministro(){

    }

    //Gettes y setters (Me quede sin imaginacion)
    public int getid() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getFechaSuministro() {
        return fechaSuministro;
    }

    public void setFechaSuministro(LocalDate fechaSuministro) {
        this.fechaSuministro = fechaSuministro;
    }

    public LocalTime getHoraSuministro() {
        return horaSuministro;
    }

    public void setHoraSuministro(LocalTime horaSuministro) {
        this.horaSuministro = horaSuministro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}