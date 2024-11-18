/**
 * Universidad del Valle de Gutemala
 * Programación Orinetada a Objetos 
 * Sección: 10
 * Ing. Kimberly Barrera
 * Proyecto - Pillas
*/

import java.time.LocalTime;
import java.util.UUID;

/**
 * Clase que representa un medicamento.
 * Contiene información sobre el medicamento, como su nombre, descripción, dosis,
 * horario de suministro, si es recetado, y su inventario.
 */
public class Medicamento {
    private String id;
    private String nombre;
    private String descripcion;
    private int dosis;
    private LocalTime horarioSuministro;
    private boolean recetado;
    private float inventario;

    
    /**
     * Constructor de la clase Medicamento.
     * Inicializa los atributos del medicamento.
     *
     * @param id                Identificador único del medicamento.
     * @param nombre            Nombre del medicamento.
     * @param descripcion       Descripción del medicamento.
     * @param dosis             Dosis recomendada del medicamento.
     * @param horarioSuministro Horario de suministro del medicamento.
     * @param inventario        Cantidad disponible en inventario.
     */
    public Medicamento(String id, String nombre, String descripcion, int dosis, LocalTime horarioSuministro, float inventario) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dosis = dosis;
        this.horarioSuministro = horarioSuministro;
        this.inventario = inventario;
    }

    /**
     * Genera un identificador único para el medicamento.
     *
     * @return Un identificador único (UUID).
     */
    public String generarId(){
        return UUID.randomUUID().toString();
    }

    /**
     * Obtiene el identificador único del medicamento.
     *
     * @return El identificador del medicamento.
     */
    public String getId() {
        return id;
    }

    /**
     * Obtiene el nombre del medicamento.
     *
     * @return El nombre del medicamento.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del medicamento.
     *
     * @param nombre El nuevo nombre del medicamento.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción del medicamento.
     *
     * @return La descripción del medicamento.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del medicamento.
     *
     * @param descripcion La nueva descripción del medicamento.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la dosis recomendada del medicamento.
     *
     * @return La dosis recomendada.
     */
    public int getDosis() {
        return dosis;
    }

    /**
     * Establece la dosis recomendada del medicamento.
     *
     * @param dosis La nueva dosis recomendada.
     */
    public void setDosis(int dosis) {
        this.dosis = dosis;
    }

    /**
     * Obtiene el horario de suministro del medicamento.
     *
     * @return El horario de suministro.
     */
    public LocalTime getHorarioDeSuministro() {
        return horarioSuministro;
    }

    /**
     * Establece el horario de suministro del medicamento.
     *
     * @param horarioSuministro El nuevo horario de suministro.
     */
    public void setHorarioDeSuministro(LocalTime horarioSuministro) {
        this.horarioSuministro = horarioSuministro;
    }

    /**
     * Indica si el medicamento es recetado.
     *
     * @return {@code true} si el medicamento es recetado, {@code false} en caso contrario.
     */
    public boolean getRecetado() {
        return recetado;
    }

    /**
     * Establece si el medicamento es recetado.
     *
     * @param recetado {@code true} si el medicamento es recetado, {@code false} en caso contrario.
     */
    public void setRecetado(boolean recetado) {
        this.recetado = recetado;
    }

    /**
     * Obtiene la cantidad disponible en el inventario del medicamento.
     *
     * @return La cantidad disponible en inventario.
     */
    public float getInventario() {
        return inventario;
    }

    /**
     * Establece la cantidad disponible en el inventario del medicamento.
     *
     * @param inventario La nueva cantidad en inventario.
     */
    public void setInventario(float inventario) {
        this.inventario = inventario;
    }

    /**
     * Realiza el suministro del medicamento si hay suficiente inventario.
     * Reduce la cantidad de inventario según la dosis establecida.
     *
     * @return {@code true} si el suministro se realizó correctamente, {@code false} si no hay suficiente inventario.
     */
    public boolean suministro(){
        if(inventario == 0 || inventario < dosis){
            return false;
        }

        inventario = inventario -dosis;
        return true;
    }

    /**
     * Devuelve una representación en cadena del medicamento.
     * Incluye detalles como nombre, descripción, dosis, horario de suministro, si es recetado, y el inventario.
     *
     * @return Una representación en cadena del medicamento.
     */
    @Override
    public String toString() {
        String recetado1= "";
        if (recetado){
            recetado1 = "Si";
        } else {
            recetado1 = "no";
        }
        return "Medicamento" + "\n" +
                "Nombre: " + nombre  + "\n" +
                "Descripcion: " + descripcion + "\n" +
                "Dosis: " + dosis + "\n" +
                "Horario de suministro: " + horarioSuministro + "\n" +
                "Recetado: " + recetado1 + "\n" +
                "Inventario: " + inventario;
    }
}