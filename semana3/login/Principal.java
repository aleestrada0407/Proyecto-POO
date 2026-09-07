package login;

import java.util.Scanner;

public class Principal {
    private final SistemaLogin sistemaLogin;
    private final InterfazConsola interfaz;

    public Principal() {
        sistemaLogin = new SistemaLogin();
        interfaz = new InterfazConsola();
    }

    public static void main(String[] args) {
        new Principal().ejecutar();
    }

    public void ejecutar() {
        try (Scanner teclado = new Scanner(System.in)) {
            boolean continuar = true;
            while (continuar) {
                interfaz.mostrarTexto("\n=== SISTEMA DE LOGIN ===");
                interfaz.mostrarTexto("1. Registrar usuario");
                interfaz.mostrarTexto("2. Iniciar sesion");
                interfaz.mostrarTexto("3. Salir");

                int opcion = interfaz.leerOpcion(teclado, 1, 3);
                switch (opcion) {
                    case 1 -> registrar(teclado);
                    case 2 -> iniciarSesion(teclado);
                    case 3 -> continuar = false;
                    default -> throw new IllegalStateException("Opcion inesperada");
                }
            }
        }
        interfaz.mostrarTexto("Programa finalizado.");
    }

    private void registrar(Scanner teclado) {
        interfaz.mostrarTexto("\n1. Estudiante");
        interfaz.mostrarTexto("2. Profesor");
        int tipo = interfaz.leerOpcion(teclado, 1, 2);

        String nombre = interfaz.leerLinea(teclado, "Nombre completo: ");
        String seccion = interfaz.leerLinea(teclado, "Seccion: ");
        String nombreUsuario = interfaz.leerLinea(teclado, "Nombre de usuario: ");
        String contrasena = interfaz.leerLinea(teclado, "Contrasena: ");

        try {
            Usuario usuario = tipo == 1
                    ? new Estudiante(nombre, seccion, nombreUsuario, contrasena)
                    : new Profesor(nombre, seccion, nombreUsuario, contrasena);

            if (sistemaLogin.registrarUsuario(usuario)) {
                interfaz.mostrarTexto("Usuario registrado como " + usuario.getTipoUsuario() + ".");
            } else {
                interfaz.mostrarTexto("Ese nombre de usuario ya existe.");
            }
        } catch (IllegalArgumentException e) {
            interfaz.mostrarTexto("No se pudo registrar: " + e.getMessage());
        }
    }

    private void iniciarSesion(Scanner teclado) {
        String nombreUsuario = interfaz.leerLinea(teclado, "Nombre de usuario: ");
        String contrasena = interfaz.leerLinea(teclado, "Contrasena: ");

        Usuario usuario = sistemaLogin.iniciarSesion(nombreUsuario, contrasena);
        if (usuario == null) {
            interfaz.mostrarTexto("Usuario o contrasena incorrectos.");
            return;
        }

        interfaz.mostrarTexto("Inicio de sesion correcto.");
        interfaz.mostrarTexto("Bienvenido: " + usuario.getNombre());
        interfaz.mostrarTexto("Tipo de usuario: " + usuario.getTipoUsuario());
        interfaz.mostrarTexto("Seccion: " + usuario.getSeccion());

        if (usuario instanceof Estudiante estudiante){
            menuEstudiante(Estudiante, teclado);
        }
        else if (usuario instanceof Profesor profesor){
            menuProfesor(profesor, teclado);
        }
    }
    
}
