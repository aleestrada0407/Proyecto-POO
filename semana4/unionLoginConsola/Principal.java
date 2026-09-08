import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    
    private static List<Usuario> usuarios = new ArrayList<>();

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

                                    boolean existeEstudiante = false;
                                    for (Usuario u : usuarios) {
                                        if (u.getNombreUsuario().equals(nombreUsuario)) {
                                            existeEstudiante = true;
                                            break;
                                        }
                                    }

                                    if (existeEstudiante) {
                                        System.out.println("Ese nombre de usuario ya existe.");
                                    } else {
                                        usuarios.add(new Estudiante(nombre, seccion, nombreUsuario, contrasena));
                                        System.out.println("Usuario registrado como Estudiante.");
                                    }
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

                                    boolean existeProfesor = false;
                                    for (Usuario u : usuarios) {
                                        if (u.getNombreUsuario().equals(nombreUsuario)) {
                                            existeProfesor = true;
                                            break;
                                        }
                                    }

                                    if (existeProfesor) {
                                        System.out.println("Ese nombre de usuario ya existe.");
                                    } else {
                                        usuarios.add(new Profesor(nombre, seccion, nombreUsuario, contrasena));
                                        System.out.println("Usuario registrado como Profesor.");
                                    }
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

                        Usuario usuarioEncontrado = null;
                        for (Usuario u : usuarios) {
                            if (u.getNombreUsuario().equals(user) && u.verificarContrasena(pass)) {
                                usuarioEncontrado = u;
                                break;
                            }
                        }

                        if (usuarioEncontrado == null) {
                            System.out.println("Usuario o contraseña incorrectos.");
                        } else {
                            System.out.println("Inicio de sesión correcto.");
                            System.out.println("Bienvenido: " + usuarioEncontrado.getNombre());


                            if (usuarioEncontrado.getTipoUsuario().equalsIgnoreCase("Estudiante")) {
                                boolean enMenuEstudiante = true;
                                while (enMenuEstudiante) {
                                    System.out.println("\n---- MENU ESTUDIANTE ----");
                                    System.out.println("1. Resolver ejercicio");
                                    System.out.println("2. Ver mi desempeno");
                                    System.out.println("3. Volver al menu principal");

                                    try {
                                        int opEstudiante = teclado.nextInt();
                                        teclado.nextLine();

                                        switch(opEstudiante){
                                            case 1:
                                                System.out.println("\n---- RESOLVER EJERCICIO ----");

                                                break;
                                            case 2: 
                                                System.out.println("\n---- DESEMPEÑO ----");
                                                System.out.println("\nEl desempeño para "+usuarioEncontrado.getNombre()+" es:");
                                                break;
                                            case 3:
                                                System.out.println("\nRegresando. . .");
                                                enMenuEstudiante = false;
                                                break;
                                            default:
                                                System.out.println("Opción no válida.");
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Opción no válida.");
                                        teclado.nextLine();
                                    }
                                }
                            } 
                            else if (usuarioEncontrado.getTipoUsuario().equalsIgnoreCase("Profesor")) {
                                boolean enMenuProfesor = true;
                                while (enMenuProfesor) {
                                    System.out.println("\n---- MENU PROFESOR ----");
                                    System.out.println("1. Consultar desempeno de un estudiante");
                                    System.out.println("2. Volver al menu principal");

                                    try {
                                        int opProfesor = teclado.nextInt();
                                        teclado.nextLine();

                                        switch(opProfesor){
                                            case 1:
                                                System.out.println("\n---- DESEMPEÑO ----");
                                                System.out.println("\nIngrese el nombre de usuario del estudiante");
                                                String revisarusuario = teclado.nextLine();
                                                
                                                boolean existe = false;
                                                String nombreEncontrado = "";
                                                for (Usuario u : usuarios) {
                                                    if (u.getNombreUsuario().equals(revisarusuario)) {
                                                        existe = true;
                                                        nombreEncontrado = u.getNombre();
                                                        break;
                                                    }
                                                }
                                                
                                                if (existe){
                                                    System.out.println("El desempeño para "+nombreEncontrado+" es: ");
                                                } else {
                                                    System.out.println("No se ha podido encontrar al usuario");
                                                }
                                                
                                                break;
                                            case 2: 
                                                System.out.println("\nRegresando. . .");
                                                enMenuProfesor = false;
                                                break;
                                            default:
                                                System.out.println("Opción no válida.");
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Opción no válida.");
                                        teclado.nextLine();
                                    }
                                }
                            }
                        }
                        break;

                    case 3:
                        System.out.println("\nSaliendo. . .");
                        inicio = false;
                        break;

                    default:
                        System.out.println("Entrada no válida. Debe ingresar un número entre 1 y 3.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Entrada no válida. Debe ingresar un número entre 1 y 3.");
                teclado.nextLine();
            }
        }
    }
}