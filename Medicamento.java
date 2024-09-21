import java.time.LocalTime;
import java.util.UUID;

public class Medicamento {
    private String id;
    private String nombre;
    private String descripcion;
    private int dosis;
    private LocalTime horarioSuministro;
    private boolean recetado;
    private float inventario;

    

    public Medicamento(String id, String nombre, String descripcion, int dosis, float inventario) {
        this.id = id; 
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dosis = dosis;
        this.inventario = inventario;
    }

    public String generarId(){
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDosis() {
        return dosis;
    }

    public void setDosis(int dosis) {
        this.dosis = dosis;
    }

    public LocalTime getHorarioDeSuministro() {
        return horarioSuministro;
    }

    public void setHorarioDeSuministro(LocalTime horarioSuministro) {
        this.horarioSuministro = horarioSuministro;
    }

    public boolean getRecetado() {
        return recetado;
    }

    public void setRecetado(boolean recetado) {
        this.recetado = recetado;
    }

    public float getInventario() {
        return inventario;
    }

    public void setInventario(float inventario) {
        this.inventario = inventario;
    }

    @Override
    public String toString() {
        return "Medicamento{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", dosis='" + dosis + '\'' +
                ", horarioSuministro='" + horarioSuministro + '\'' +
                ", recetado='" + recetado + '\'' +
                ", inventario='" + inventario + '\'' +
                '}';
    }
}