import java.util.Scanner;

public class PrincipalPrueba {
    private final SistemaLogin sistemaLogin;
    private final InterfazLogin interfaz;
    private final GestorResultados gestorResultados;

    public PrincipalPrueba() {
        sistemaLogin = new SistemaLogin();
        interfaz = new InterfazLogin();
        gestorResultados = new GestorResultados();
    }

    public static void main(String[] args) {
        new PrincipalPrueba().ejecutar();
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

        if (usuario instanceof Estudiante estudiante) {
            menuEstudiante(estudiante, teclado);
        } else if (usuario instanceof Profesor profesor) {
            menuProfesor(profesor, teclado);
        }
    }

    private void menuEstudiante(Estudiante estudiante, Scanner teclado) {
        boolean continuar = true;
        while (continuar) {
            interfaz.mostrarTexto("\n=== MENU ESTUDIANTE ===");
            interfaz.mostrarTexto("1. Resolver ejercicio");
            interfaz.mostrarTexto("2. Ver mi desempeno");
            interfaz.mostrarTexto("3. Volver al menu principal");

            int opcion = interfaz.leerOpcion(teclado, 1, 3);
            switch (opcion) {
                case 1 -> resolverEjercicio(estudiante, teclado);
                case 2 -> mostrarDesempeno(estudiante.getNombreUsuario(), estudiante.getNombre());
                case 3 -> continuar = false;
                default -> throw new IllegalStateException("Opcion inesperada");
            }
        }
    }

    private void resolverEjercicio(Estudiante estudiante, Scanner teclado) {
        
        Ejercicio ejercicio = crearEjercicioDemo();

        InterfazEjercicio interfazEjercicio = new InterfazEjercicio(ejercicio, teclado);
        interfazEjercicio.mostrarEnunciado();

        RespuestaEjercicio respuesta = interfazEjercicio.solicitarRespuesta();
        boolean correcto = interfazEjercicio.evaluarRespuesta(respuesta);
        interfazEjercicio.mostrarRetroalimentacion(correcto);

        Resultado resultado = new Resultado(ejercicio, estudiante.getNombreUsuario(), respuesta, correcto);
        gestorResultados.registrarResultado(resultado);
    }

    private Ejercicio crearEjercicioDemo() {
        Ejercicio ejercicio = new Ejercicio("Combustion del metano");
        ejercicio.llenarEcuacion(
                new String[]{"CH4", "O2"},
                new String[]{"CO2", "H2O"},
                new int[]{1, 2},
                new int[]{1, 2}
        );
        return ejercicio;
    }

    
}
