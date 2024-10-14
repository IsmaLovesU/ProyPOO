import java.util.Scanner;

public class MainPrueba {
    public static void main(String[] args) {
        GuardarInformacion sistema = new GuardarInformacion();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Menu:");
            System.out.println("1. Registrar Usuario");
            System.out.println("2. Crear Paciente");
            System.out.println("3. Agregar Medicamento");
            System.out.println("4. Eliminar medicamento");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Ingrese Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese Nombre de Usuario: ");
                    String nombreUsuario = scanner.nextLine();
                    System.out.print("Ingrese Contraseña: ");
                    String contrasena = scanner.nextLine();
                    System.out.print("Ingrese Edad: ");
                    int edad = scanner.nextInt();
                    scanner.nextLine();  // Limpiar el buffer
                    System.out.print("Ingrese Sexo: ");
                    String sexo = scanner.nextLine();
                    System.out.print("Ingrese Tipo de Usuario: ");
                    String tipoUsuario = scanner.nextLine();
                    sistema.registroUsuario(id, nombre, nombreUsuario, contrasena, edad, sexo, tipoUsuario);
                    System.out.println("Usuario registrado exitosamente.");
                    sistema.guardarUsuariosCSV();
                    System.out.println("Usuarios guardados en CSV.");
                    break;

                case 2:
                    System.out.print("Ingrese ID de Usuario: ");
                    String idUsuario = scanner.nextLine();
                    System.out.print("Ingrese Nombre del Paciente: ");
                    String nombrePaciente = scanner.nextLine();
                    System.out.print("Ingrese Edad del Paciente: ");
                    int edadPaciente = scanner.nextInt();
                    scanner.nextLine();  // Limpiar el buffer
                    System.out.print("Ingrese Información Adicional: ");
                    String infoAdicional = scanner.nextLine();
                    sistema.crearPaciente(idUsuario, nombrePaciente, edadPaciente, infoAdicional);
                    System.out.println("Paciente creado exitosamente.");
                    sistema.guardarPacientesCSV();
                    System.out.println("Pacientes guardados en CSV.");
                    break;

                case 3:
                    System.out.print("Ingrese ID de usuario: ");
                    String idPaciente = scanner.nextLine();
                    System.out.print("Ingrese Nombre del Medicamento: ");
                    String nombreMedicamento = scanner.nextLine();
                    System.out.print("Ingrese Descripción del Medicamento: ");
                    String descripcion = scanner.nextLine();
                    System.out.print("Ingrese Dosis: ");
                    int dosis = scanner.nextInt();
                    System.out.print("Ingrese Inventario: ");
                    float inventario = scanner.nextFloat();
                    sistema.crearMedicamento(idPaciente, nombreMedicamento, descripcion, dosis, inventario);
                    System.out.println("Medicamento agregado exitosamente.");
                    sistema.guardarMedicamentosCSV();
                    System.out.println("Medicamentos guardados en CSV.");
                    break;

                case 4:
                    System.out.print("Ingrese el nombre del Medicamento a eliminar: ");
                    String idMedicamentoAEliminar = scanner.nextLine();
                    sistema.eliminarMedicamentoCSV(idMedicamentoAEliminar);
                    System.out.println("Medicamento eliminado del CSV.");
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 0);

        scanner.close();
    }
}
