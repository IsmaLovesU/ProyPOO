import java.util.List;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class GuardarUsuario {
    public List<Usuario> listaUsuarios;

    public GuardarUsuario() {
        listaUsuarios = new ArrayList<>();
        cargarUsuariosDesdeCSV();  // Nuevo método para cargar los usuarios
    }

        /**
     * Carga los usuarios desde un archivo CSV y los almacena en la lista de usuarios.
     * El archivo debe tener el formato: id, nombre, nombreUsuario, contrasena, edad, sexo, tipoUsuario.
     */
    public void cargarUsuariosDesdeCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Usuarios.csv"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                // Nota: En lugar de generar un nuevo ID, usa el ID que ya está en el archivo CSV, sino luego se duplican los usuarios ayiyiyiyiyi
                String id = datos[0];  // Usar el ID existente del CSV
                String nombre = datos[1];
                String nombreUsuario = datos[2];
        
                // Descifrar la contraseña
                String contrasenaCifrada = datos[3];
                String contrasenaDescifrada = AESUtil.decrypt(contrasenaCifrada);
                
                int edad = Integer.parseInt(datos[4]);
                String sexo = datos[5];
                String tipoUsuario = datos[6];
    
                Usuario usuario = new Usuario(id, nombre, nombreUsuario, contrasenaDescifrada, edad, sexo, tipoUsuario);
                listaUsuarios.add(usuario);  // Agregar el usuario a la lista
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
    /**
     * Obtiene la lista de usuarios registrados.
     * 
     * @return Una lista de usuarios.
     */
    public List<Usuario> getUsuarios() {
        return listaUsuarios;
    }

    /**
     * Verificar que la contraseña cumpla con los requisitos para que sea aeptada
     * 
     * @param contraseña la contraseña que se créa
     * @return true si cumple con todas las condicoines para crear la contraseña
     */
    public boolean verificarContraseña(String contraseña){
        
        if(contraseña.length() < 10){
            System.out.println("Error de tamaño" + contraseña.length());

            return false;
        }

        boolean minuscula= false;
        boolean mayuscula = false;
        boolean numero = false;

        for(char c: contraseña.toCharArray()){

            if (Character.isUpperCase(c)){
                mayuscula = true;
            }

            if (Character.isLowerCase(c)){
                minuscula = true;
            }
            
            if (Character.isDigit(c)){
                numero = true;
            }

            if (!Character.isLetterOrDigit(c)){
                return false;
            }
        }

        if (!mayuscula || !minuscula || !numero){
            return false;
        }

        return true;
    }

    /**
     * Registra un nuevo usuario en el sistema.
     * Cambio de retorno de método a booleano. 
     * 
     * @param id El identificador del usuario.
     * @param nombre El nombre completo del usuario.
     * @param nombreUsuario El nombre de usuario.
     * @param contraseña La contraseña del usuario.
     * @param edad La edad del usuario.
     * @param sexo El sexo del usuario.
     * @param tipoUsuario El tipo de usuario (doctor, administrador, etc.).
     */
    public boolean registroUsuario(String id, String nombre, String nombreUsuario, String contraseña, int edad, String sexo, String tipoUsuario) {
        if (contraseña == null) {
            return false;
        }
    
        try {
            contraseña = AESUtil.encrypt(contraseña); // Cifrar la contraseña
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de excepciones
            return false;
        }

        // Verificar si el usuario ya existe
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getId().equals(id) || usuario.getNombreUsuario().equals(nombreUsuario)) {
                return false; // Salir si el usuario ya existe
            }
        }
        
        // Si no existe, agregarlo a la lista
        Usuario usuario = new Usuario(id, nombre, nombreUsuario, contraseña, edad, sexo, tipoUsuario);
        listaUsuarios.add(usuario);
        return true;
    }

    /**
     * Guarda los usuarios en un archivo CSV. Los datos se guardan en el formato:
     * id, nombre, nombreUsuario, contrasena, edad, sexo, tipoUsuario.
     */
    public void guardarUsuariosCSV() {
        try (FileWriter writer = new FileWriter("Usuarios.csv", false)) { // Sobrescribe el archivo para evitar que se dupliquen los usuarios
            for (Usuario usuario : listaUsuarios) {
                String contrasenaCifrada;
            try {
                // Cifrar la contraseña antes de guardarla
                contrasenaCifrada = AESUtil.encrypt(usuario.getContrasena());
            } catch (Exception e) {
                continue;  // Si hay un error al cifrar, saltar al siguiente usuario
            }
            
            // Guardar los datos del usuario en el archivo CSV
                writer.append(usuario.getId())
                      .append(",")
                      .append(usuario.getNombre())
                      .append(",")
                      .append(usuario.getNombreUsuario())
                      .append(",")
                      .append(contrasenaCifrada)
                      .append(",")
                      .append(String.valueOf(usuario.getEdad()))
                      .append(",")
                      .append(usuario.getSexo())
                      .append(",")
                      .append(usuario.getTipoUsuario())
                      .append("\n");
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Verifica que el usuario y la contraseña existan para el inicio de sesión
     * 
     * @param nombreUsuario nombre de usuario del usuario que ingresa al programa
     * @param contraseña la contraseña del usuario
     * 
     * @return tru si el usuario y la contraseña coniciden con un usuario existente
     */
    public boolean inicioSesion(String nombreUsuario, String contraseña){

        for(Usuario usuario: listaUsuarios){
            if(nombreUsuario.equals(usuario.getNombreUsuario()) && contraseña.equals(usuario.getContrasena())){
                return true;
            }
        }

        return false;
    }

    /**
     * Método que autentica a un usuario verificando su nombre de usuario y contraseña
     * en la lista de usuarios cargados desde el archivo CSV.
     *
     * @param nombreUsuario El nombre de usuario que se desea autenticar.
     * @param contrasena La contraseña correspondiente al usuario.
     * @return true si las credenciales coinciden con las almacenadas en la lista de usuarios, 
     *         false en caso contrario.
     */
    public boolean autenticar(String nombreUsuario, String contrasena) {
        for (Usuario usuario : listaUsuarios) {
            String contrasenaDescifrada;
            
            try {
                contrasenaDescifrada = AESUtil.decrypt(usuario.getContrasena());
            } catch (Exception e) {
                System.out.println("Error al descifrar la contraseña.");
                continue;
            }

            // Verificamos si el nombre de usuario y la contraseña coinciden
            if (usuario.getNombreUsuario().equals(nombreUsuario) && contrasenaDescifrada.equals(contrasena)) {
                System.out.println("Has iniciado sesión");
                return true;
            }
        }

        // Si no se encuentran coincidencias, se indica que las credenciales son incorrectas
        System.out.println("Usuario o contraseña incorrectos.");
        return false;
    }
}
