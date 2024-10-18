import java.util.ArrayList;

/**
 * Universidad del Valle de Gutemala
 * Programación Orinetada a Objetos 
 * Sección: 10
 * Ing. Kimberly Barrera
 * Proyecto - Pillas
*/

import java.util.Random;

/**
 * Clase GeneradorId se encarga de generar IDs únicos de 6 caracteres para los usuarios.
 * 25!/19! de probabilidad que se repita algun ID.
 */
class GeneradorId {
    private static ArrayList<String> idsGenerados = new ArrayList<>();
    private static final String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * Genera un ID único y se que no se repita.
     * @return El ID generado.
     */
    public String generarId(){
        Random random = new Random();
        String idGenerado;
        boolean idUnico;

        do{
            idGenerado = "";
            for (int i = 0; i < 6; i++) {
                int indiceAleatorio = random.nextInt(letras.length());
                idGenerado += letras.charAt(indiceAleatorio);
            }

            //Comprobacion del ID.
            idUnico = !idsGenerados.contains(idGenerado);
        } while (!idUnico);
        idsGenerados.add(idGenerado);

        return idGenerado; 
    }
}