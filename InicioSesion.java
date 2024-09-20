import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase que gestiona el inicio de sesión de usuarios mediante un archivo CSV.
 */
public class InicioSesion {

    /**
     * Atributo que define la ruta del archivo CSV que contiene los datos de los usuarios.
     * El archivo se espera que tenga datos separados por comas en el siguiente formato:
     * nombre,apellido,usuario,contraseña
     */
    private String archivoUsuarios = "Usuarios.csv";

    /**
     * Método que autentica a un usuario verificando su nombre de usuario y contraseña 
     * en el archivo CSV.
     *
     * @param nombreUsuario El nombre de usuario que se desea autenticar.
     * @param contrasena La contraseña correspondiente al usuario.
     * @return true si las credenciales coinciden con las almacenadas en el archivo CSV, 
     *         false en caso contrario.
     */
    public boolean autenticar(String nombreUsuario, String contrasena) {
        // Intentamos leer el archivo de usuarios
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoUsuarios))) {
            String linea;
            
            // Recorremos el archivo línea por línea
            while ((linea = reader.readLine()) != null) {
                // Separamos la línea en partes utilizando la coma como delimitador
                String[] datosUsuario = linea.split(",");
                
                // El nombre de usuario y la contraseña están en las posiciones 2 y 3 del CSV
                String usuarioCSV = datosUsuario[2];
                String contrasenaCSV = datosUsuario[3];

                // Verificamos si el nombre de usuario y la contraseña coinciden
                if (usuarioCSV.equals(nombreUsuario) && contrasenaCSV.equals(contrasena)) {
                    System.out.println("Has iniciado sesión");
                    return true; // Si coinciden, se retorna true y se autentica al usuario
                }
            }
        } catch (IOException e) {
            // Si ocurre un error de entrada/salida (archivo no encontrado, etc.)
            System.out.println("Oh-oh, ocurrió un error desconocido con el archivo.");
        }
        
        // Si no se encuentran coincidencias, se indica que las credenciales son incorrectas
        System.out.println("Usuario o contraseña incorrectos.");
        return false;
    }
}
