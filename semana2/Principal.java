import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        boolean inicio = true;

        while (inicio) {
            System.out.println("\n---- BIENVENIDO ----");
            System.out.println("1. Registrar usuario.");
            System.out.println("2. Iniciar sesión.");
            System.out.println("3. Salir.");
            int opcion = 0;

            try {
                opcion = teclado.nextInt();
                teclado.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.println("\n---- NUEVO USUARIO ----");
                        System.out.println("Ingrese su tipo de usuario:");
                        System.out.println("1. Estudiante.");
                        System.out.println("2. Profesor.");
                        int tipousuario = 0;

                        try {
                            tipousuario = teclado.nextInt();
                            teclado.nextLine();

                            switch (tipousuario) {
                                case 1:
                                    System.out.println("\n---- REGISTRAR ESTUDIANTE ----");
                                    System.out.println("Ingrese su nombre:");
                                    String nombre = teclado.nextLine();
                                    System.out.println("Ingrese su sección:");
                                    String seccion = teclado.nextLine();
                                    System.out.println("Ingrese su nombre de usuario:");
                                    String nombreUsuario = teclado.nextLine();
                                    System.out.println("Ingrese su contraseña:");
                                    String contrasena = teclado.nextLine();
                                    break;

                                case 2:
                                    System.out.println("\n---- REGISTRAR PROFESOR ----");
                                    System.out.println("Ingrese su nombre:");
                                    nombre = teclado.nextLine();
                                    System.out.println("Ingrese su sección:");
                                    seccion = teclado.nextLine();
                                    System.out.println("Ingrese su nombre de usuario:");
                                    nombreUsuario = teclado.nextLine();
                                    System.out.println("Ingrese su contraseña:");
                                    contrasena = teclado.nextLine();
                                    break;

                                default:
                                    System.out.println("Entrada no válida. Ingrese 1 o 2.");
                                    break;
                            }
                        } catch (Exception e) {
                            System.out.println("Entrada no válida. Ingrese un número.");
                            teclado.nextLine();
                        }
                        break;

                    case 2:
                        System.out.println("\nIngrese su nombre de usuario:");
                        String user = teclado.nextLine();
                        System.out.println("Ingrese su contraseña:");
                        String pass = teclado.nextLine();
                        break;

                    case 3:
                        System.out.println("\nSaliendo. . .");
                        inicio = false;
                        break;

                    default:
                        System.out.println("Entrada no válida. Ingrese un número entre 1 y 3.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Entrada no válida. Debe ingresar un número entero.");
                teclado.nextLine();
            }
        }
    }
}

            
/* 
switch (opcion) {
                    case 1:
                        System.out.println("\n---- REGISTRO ----");
                        System.out.print("Ingrese su nombre: ");
                        String name = teclado.nextLine(); 
                        
                        System.out.print("Ingrese su carrera: ");
                        String carrera = teclado.nextLine();
                        
                        System.out.print("Ingrese su carné: ");
                        int carne = teclado.nextInt();
                        teclado.nextLine();
                        
                        System.out.print("¿Es su primera vez tomando el curso de Química I? (S/N): ");
                        char si_no = teclado.next().toLowerCase().charAt(0);
                        teclado.nextLine();
                        break;
            }
        
 */


/*         while (ciclo) {
            System.out.println("\n---- REPASO DE QUÍMICA ----");
            System.out.println("1. Registrar estudiante");
            System.out.println("2. Repasar conceptos");
            System.out.println("3. Modificar información del estudiante");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = 0;

            try {
                opcion = teclado.nextInt();
                teclado.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.println("\n---- REGISTRO DE ESTUDIANTE ----");
                        System.out.print("Ingrese su nombre: ");
                        String name = teclado.nextLine(); 
                        
                        System.out.print("Ingrese su carrera: ");
                        String carrera = teclado.nextLine();
                        
                        System.out.print("Ingrese su carné: ");
                        int carne = teclado.nextInt();
                        teclado.nextLine();
                        
                        System.out.print("¿Es su primera vez tomando el curso de Química I? (S/N): ");
                        char si_no = teclado.next().toLowerCase().charAt(0);
                        teclado.nextLine();
                        break;

                    case 2:
                        System.out.println("\n---- REPASAR CONCEPTOS ----");
                        break;

                    case 3:
                        System.out.println("\n---- MODIFICAR INFORMACIÓN DE ESTUDIANTE ----");
                        System.out.print("Ingrese su nombre: ");
                         name = teclado.nextLine();
                        
                        System.out.print("Ingrese su carrera: ");
                         carrera = teclado.nextLine();
                        
                        System.out.print("Ingrese su carné: ");
                         carne = teclado.nextInt();
                        teclado.nextLine();
                        
                        System.out.print("¿Es su primera vez tomando el curso de Química I? (S/N): ");
                         si_no = teclado.next().toLowerCase().charAt(0);
                        teclado.nextLine();
                        break;

                    case 4:
                        System.out.println("Saliendo...");
                        ciclo = false;
                        break;

                    default:
                        System.out.println("Opción no válida. Ingrese un número entre 1 y 4.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor, ingrese el tipo de dato correcto.");
                teclado.nextLine(); 
            }
        }
    }
} */