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
                            if (u.getNombreUsuario().equals(user) && u.getContrasena().equals(pass)) {
                                usuarioEncontrado = u;
                                break;
                            }
                        }

                        if (usuarioEncontrado == null) {
                            System.out.println("Usuario o contraseña incorrectos.");
                        } else {
                            System.out.println("Inicio de sesión correcto.");
                            System.out.println("Bienvenido: " + usuarioEncontrado.getNombre());
                            System.out.println("Tipo de usuario: " + usuarioEncontrado.getTipoUsuario());
                            System.out.println("Sección: " + usuarioEncontrado.getSeccion());
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