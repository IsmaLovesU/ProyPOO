import java.time.LocalTime;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        GuardarInformacion sistema = new GuardarInformacion();
        Scanner scanner = new Scanner(System.in);

        int opcion = 0;

        // Menú básico para interactuar con el sistema
        while (opcion != 8) {
            System.out.println("\n----- Menú Principal -----");
            System.out.println("1. Registrar Usuario");
            System.out.println("2. Crear Paciente");
            System.out.println("3. Crear Medicamento");
            System.out.println("4. Agregar Medicamento a Paciente");
            System.out.println("5. Mostrar Pacientes");
            System.out.println("6. Mostrar Medicamentos de Pacientes");
            System.out.println("7. Iniciar Sesión");
            System.out.println("8. Salir");
            opcion = scanner.nextInt();


            if (opcion == 1) {
                System.out.println("---- Registro de Usuario ----");
                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();
                System.out.print("Nombre de Usuario: ");
                String nombreUsuario = scanner.nextLine();
                System.out.print("Contraseña: ");
                String contraseña = scanner.nextLine();
                System.out.print("Edad: ");
                int edad = scanner.nextInt();
                scanner.nextLine(); // Consumir nueva línea
                System.out.print("Tipo de Usuario: ");
                String tipoUsuario = scanner.nextLine();
                sistema.registroUsuario(nombre, nombreUsuario, contraseña, edad, tipoUsuario);
                System.out.println("Usuario registrado correctamente.");

            } else if (opcion == 2) {
                System.out.println("---- Creación de Paciente ----");
                System.out.print("Nombre del paciente: ");
                String nombrePaciente = scanner.nextLine();
                System.out.print("Edad del paciente: ");
                int edadPaciente = scanner.nextInt();
                scanner.nextLine(); // Consumir nueva línea
                System.out.print("Información adicional: ");
                String informacionAdicional = scanner.nextLine();
                sistema.crearPaciente(nombrePaciente, edadPaciente, informacionAdicional);
                System.out.println("Paciente creado correctamente.");


            } else if (opcion == 3) {
                System.out.println("---- Creación de Medicamento ----");
                System.out.print("Nombre del medicamento: ");
                String nombreMedicamento = scanner.nextLine();
                System.out.print("Descripción del medicamento: ");
                String descripcion = scanner.nextLine();
                System.out.print("Dosis: ");
                int dosis = scanner.nextInt();
                scanner.nextLine(); // Consumir nueva línea
                System.out.print("Hora de suministro (HH:MM): ");
                String horaSuministro = scanner.nextLine();
                System.out.print("¿Es recetado? (true/false): ");
                boolean recetado = scanner.nextBoolean();
                System.out.print("Inventario: ");
                float inventario = scanner.nextFloat();
                LocalTime hora = LocalTime.parse(horaSuministro);
                sistema.crearMedicamento(nombreMedicamento, descripcion, dosis, hora, recetado, inventario);
                System.out.println("Medicamento creado correctamente.");

            } else if (opcion == 4) {
                System.out.println("---- Agregar Medicamento a Paciente ----");
                System.out.print("Nombre del paciente: ");
                String pacienteParaMedicamento = scanner.nextLine();
                List<Paciente> pacientes = sistema.mostrarPacientes();
                Paciente pacienteSeleccionado = null;
                for (Paciente p : pacientes) {
                    if (p.getNombre().equals(pacienteParaMedicamento)) {
                        pacienteSeleccionado = p;
                    }
                }
                if (pacienteSeleccionado != null) {
                    System.out.print("Nombre del medicamento a agregar: ");
                    String nombreMed = scanner.nextLine();
                    List<Medicamento> medicamentos = sistema.mostrarMedicamentos();
                    Medicamento medicamentoSeleccionado = null;
                    for (Medicamento m : medicamentos) {
                        if (m.getNombre().equals(nombreMed)) {
                            medicamentoSeleccionado = m;
                        }
                    }
                    if (medicamentoSeleccionado != null) {
                        sistema.agregarMedicamentos(pacienteSeleccionado, medicamentoSeleccionado);
                        System.out.println("Medicamento agregado correctamente.");
                    } else {
                        System.out.println("Medicamento no encontrado.");
                    }
                } else {
                    System.out.println("Paciente no encontrado.");
                }

            } else if (opcion == 5) {
                System.out.println("---- Listado de Pacientes ----");
                List<Paciente> listaPacientes = sistema.mostrarPacientes();
                if (!listaPacientes.isEmpty()) {
                    for (Paciente p : listaPacientes) {
                        System.out.println("Nombre: " + p.getNombre() + ", Edad: " + p.getEdad());
                    }
                } else {
                    System.out.println("No hay pacientes registrados.");
                }

            } else if (opcion == 6) {
                System.out.println("---- Medicamentos de los Pacientes ----");
                List<Medicamento> listaMedicamentos = sistema.mostrarMedicamentos();
                if (!listaMedicamentos.isEmpty()) {
                    for (Medicamento m : listaMedicamentos) {
                        System.out.println(m);
                    }
                } else {
                    System.out.println("No hay medicamentos registrados.");
                }
                

            } else if (opcion == 7) {
                System.out.println("---- Inicio de Sesión ----");
                System.out.print("Nombre de Usuario: ");
                String nombreDeUsuario = scanner.nextLine();
                System.out.print("Contraseña: ");
                String pass = scanner.nextLine();
                if (sistema.inicioSesion(nombreDeUsuario, pass)) {
                    System.out.println("Inicio de sesión exitoso.");
                } else {
                    System.out.println("Nombre de usuario o contraseña incorrectos.");
                }

            } else if (opcion == 8) {
                System.out.println("Saliendo del sistema...");

            } else {
                System.out.println("Opción no válida, elija otra opcicion...");
            }
        }
        scanner.close();
    }
}
