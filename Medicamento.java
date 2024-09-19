import java.time.LocalTime;

public class Medicamento {
    private String nombre;
    private String descripcion;
    private int dosis;
    private LocalTime horarioSuministro;
    private boolean recetado;
    private float inventario;

    public Medicamento(){

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

    public boolean isRecetado() {
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
}
