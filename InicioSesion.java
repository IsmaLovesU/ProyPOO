import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InicioSesion {
    // Atributo con la ruta del archivo
    private String archivoUsuarios = "Usuarios.csv";

    public boolean autenticar(String nombreUsuario, String contrasena) {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoUsuarios))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datosUsuario = linea.split(",");
                String usuarioCSV = datosUsuario[2];
                String contrasenaCSV = datosUsuario[3];

                if (usuarioCSV.equals(nombreUsuario) && contrasenaCSV.equals(contrasena)) {
                    System.out.println("Has iniciado sesión");
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Oh-oh, ocurrió un error desconocido con el archivo.");
        }
        System.out.println("Usuario o contraseña incorrectos.");
        return false;
    }
}
