import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean pregunta = false;
        String respuesta= "";
        boolean inicio= false;
        GuardarInformacion gestor = new GuardarInformacion();
        Scanner scanner = new Scanner(System.in);

        System.out.println("--Programa sin nombre( pi-las)--\n");

        while (!pregunta) {
            System.out.print("¿Posees una cuenta? (si/no): ");
            respuesta= scanner.nextLine();

            if(respuesta.equals("si") || respuesta.equals("no")){
                pregunta = true;
            } else {
                System.out.println("Opción inválida.");
            }

        }
        // Proceso de inicio de sesión
        if(respuesta.equals("si")){
            System.out.println("----- Inicio de Sesión -----");
            boolean sesionIniciada = false;
            while (!sesionIniciada) {
                System.out.print("Nombre de usuario: ");
                String nombreUsuario = scanner.nextLine();
                System.out.print("Contraseña: ");
                String contrasena = scanner.nextLine();
                
                sesionIniciada = inicioSesion.autenticar(nombreUsuario, contrasena);

                inicio= true;
            }
        } 
        else if(respuesta.equals("no")){
            System.out.println("----- Registro de usuario-----");
            System.out.println("Registro de usuario:");
            System.out.print("ID: ");
            String id = scanner.nextLine();
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Nombre de usuario: ");
            String nombreUsuario = scanner.nextLine();
            System.out.print("Contraseña: ");
            String contrasena = scanner.nextLine();
            System.out.print("Edad: ");
            int edad = scanner.nextInt();
            scanner.nextLine(); 
            System.out.print("Sexo (M/F): ");
            String sexo = scanner.nextLine();
            System.out.print("Tipo de usuario: ");
            String tipoUsuario = scanner.nextLine();

            gestor.registroUsuario(id, nombre, nombreUsuario, contrasena, edad, sexo, tipoUsuario);
            System.out.println("Guardando usuarios en CSV...");
            gestor.guardarUsuariosCSV();


            inicio= true;
        }

        if (!inicio){
            int opcion = 0;

            while (opcion != 6) {
                System.out.println("----- Menú -----");
                System.out.println("1. Crear paciente");
                System.out.println("2. Crear medicamento");
                System.out.println("3. Guardar pacientes en CSV");
                System.out.println("4. Guardar medicamentos en CSV");
                System.out.println("5. Mostrar información");
                System.out.println("6. Salir");
                System.out.print("Elige una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine();  // Consumir la nueva línea

                
                
                if (opcion == 1) {
                    System.out.println("Crear paciente:");
                    System.out.print("ID del usuario (doctor): ");
                    String idUsuario = scanner.nextLine();
                    System.out.print("Nombre del paciente: ");
                    String nombrePaciente = scanner.nextLine();
                    System.out.print("Edad del paciente: ");
                    int edadPaciente = scanner.nextInt();
                    scanner.nextLine(); // Consumir nueva línea
                    System.out.print("Información adicional: ");
                    String infoAdicional = scanner.nextLine();

                    gestor.crearPaciente(idUsuario, nombrePaciente, edadPaciente, infoAdicional);
                    System.out.println("Guardando usuarios en CSV...");
                    gestor.guardarUsuariosCSV();


                } else if (opcion == 2) {
                    System.out.println("Crear medicamento:");
                    System.out.print("ID del paciente: ");
                    String idPaciente = scanner.nextLine();
                    System.out.print("Nombre del medicamento: ");
                    String nombreMedicamento = scanner.nextLine();
                    System.out.print("Descripción: ");
                    String descripcion = scanner.nextLine();
                    System.out.print("Dosis: ");
                    int dosis = scanner.nextInt();
                    System.out.print("Inventario: ");
                    float inventario = scanner.nextFloat();

                    gestor.crearMedicamento(idPaciente, nombreMedicamento, descripcion, dosis, inventario);

                } else if (opcion == 3) {
                    System.out.println("Guardando pacientes en CSV...");
                    gestor.guardarPacientesCSV();

                } else if (opcion == 4) {
                    System.out.println("Guardando medicamentos en CSV...");
                    gestor.guardarMedicamentosCSV();

                } else if (opcion == 5) {
                    System.out.println("Mostrando información...");

                } else if (opcion == 6) {
                    System.out.println("Saliendo del sistema...");

                } else {
                    System.out.println("Opción no válida, por favor intenta nuevamente.");
                }
            }
            
        }
        scanner.close();

   }
}





