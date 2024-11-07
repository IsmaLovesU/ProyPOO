<<<<<<< HEAD
/**
 * Universidad del Valle de Gutemala
 * Programación Orinetada a Objetos 
 * Sección: 10
 * Ing. Kimberly Barrera
 * Proyecto - Pillas
*/

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean pregunta = false;
        String respuesta= "";
        boolean inicio = false;
        GuardarInformacion gestor = new GuardarInformacion();
        Scanner scanner = new Scanner(System.in);

        
        System.out.println("--Programa sin nombre(pi-las)--\n");
        while (!pregunta) {
            System.out.print("¿Posees una cuenta? (si/no): ");
            respuesta = scanner.nextLine();

            if (respuesta.equals("si") || respuesta.equals("no")) {
                pregunta = true;
            } else {
                System.out.println("Opción inválida.");
            }
        }

        // Proceso de inicio de sesión
        if (respuesta.equals("si")) {
            System.out.println("----- Inicio de Sesión -----");
            boolean sesionIniciada = false;

            while (!sesionIniciada) {
                System.out.print("Nombre de usuario: ");
                String nombreUsuario = scanner.next();
                System.out.print("Contraseña: ");
                String contrasena = scanner.next();
                
                // Llamar al método autenticar del gestor de información
                sesionIniciada = gestor.autenticar(nombreUsuario, contrasena); // Cambiar 'inicioSesion' por 'gestor'
                
                if (sesionIniciada) {
                    System.out.println("Sesión iniciada correctamente.");
                    inicio = true;
                } else {
                    System.out.println("Credenciales incorrectas. Intenta de nuevo.");
                }
            }
        }

        else if(respuesta.equals("no")){
            System.out.println("----- Registro de usuario-----");
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





=======
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
>>>>>>> df5b90e48d5269b6cfa4b8269a23999a4eed445c
