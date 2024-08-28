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
}