import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    
    private static Usuariodao usuariodao = new Usuariodao();
    private static GestorResultados gestorResultados = new GestorResultados();
    private static BancoEjercicios banco = new BancoEjercicios();
    

    public static void main(String[] args) {

        BaseDatos.inicializar();
        Ejerciciodao ejerciciodao = new Ejerciciodao();
        banco.sembrarBaseDatos(ejerciciodao);
        banco.cargarDesdeBD(ejerciciodao);
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

                                    Estudiante nuevoEstudiante = new Estudiante(nombre, seccion, nombreUsuario, contrasena);
 
                                    if (usuariodao.existe(nuevoEstudiante.getNombreUsuario())) {
                                        System.out.println("Ese nombre de usuario ya existe.");
                                    } else if (usuariodao.insertar(nuevoEstudiante)) {
                                        System.out.println("Usuario registrado como Estudiante.");
                                    } else {
                                        System.out.println("No se pudo registrar el usuario.");
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

                                    Profesor nuevoProfesor = new Profesor(nombre, seccion, nombreUsuario, contrasena);
 
                                    if (usuariodao.existe(nuevoProfesor.getNombreUsuario())) {
                                        System.out.println("Ese nombre de usuario ya existe.");
                                    } else if (usuariodao.insertar(nuevoProfesor)) {
                                        System.out.println("Usuario registrado como Profesor.");
                                    } else {
                                        System.out.println("No se pudo registrar el usuario.");
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
 
                        Usuario usuarioEncontrado = usuariodao.buscarPorNombreUsuario(user);
 
                        if (usuarioEncontrado == null || !usuarioEncontrado.verificarContrasena(pass)) {
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
                                                if (banco.haySiguiente()) {
                                                Ejercicio ejercicioActual = banco.obtenerSiguiente();
                                                InterfazEjercicio interfazEj = new InterfazEjercicio(ejercicioActual, teclado);
 
                                                interfazEj.mostrarEnunciado();
                                                RespuestaEjercicio respuesta = interfazEj.solicitarRespuesta();
                                                boolean correcto = interfazEj.evaluarRespuesta(respuesta);
                                                interfazEj.mostrarRetroalimentacion(correcto);
 
                                                Resultado resultado = new Resultado(ejercicioActual, usuarioEncontrado.getNombreUsuario(), respuesta, correcto);
                                                gestorResultados.registrarResultado(resultado);
 
                                            System.out.println("Progreso del banco: " + banco.obtenerPorcentajeAvance() + "%");
                                        } else {
                                                System.out.println("Ya no hay más ejercicios disponibles.");
                                                System.out.println("¿Desea reiniciar el banco de ejercicios? (s/n)");
                                                String resp = teclado.nextLine();
                                            if (resp.equalsIgnoreCase("s")) {
                                                banco.reiniciar();
                                                System.out.println("Banco reiniciado.");
                                            }
                                            }
                                            
 
                                                break;
                                            case 2: 
                                                System.out.println("\n---- DESEMPEÑO ----");
                                                double desempeno = gestorResultados.calcularDesempenoIndividual(usuarioEncontrado.getNombreUsuario());
                                                System.out.println("\nEl desempeño para "+usuarioEncontrado.getNombre()+" es: " + desempeno + "%");
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
                                                
                                                Usuario revisado = usuariodao.buscarPorNombreUsuario(revisarusuario);
 
                                                if (revisado != null){
                                                    double desempenoProfesor = gestorResultados.calcularDesempenoIndividual(revisarusuario);
                                                    System.out.println("El desempeño para "+revisado.getNombre()+" es: " + desempenoProfesor + "%");
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