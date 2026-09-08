import java.util.Scanner;

public class InterfazEjercicio {


    

    private Ejercicio ejercicio;
    private Scanner scanner;

    public InterfazEjercicio(Ejercicio ejercicio, Scanner scanner) {
        this.ejercicio = ejercicio;
        this.scanner = scanner;

    }

    public void mostrarEnunciado() {
        System.out.println("Ejercicio");
        System.out.println(ejercicio.getEnunciado());
        System.out.println();
        System.out.println(ejercicio.armarEcuacion());
        System.out.println();
    }

    public int[] solicitarCoeficientes(String[] formulas, String lado) {

        int[] coeficientes = new int[formulas.length];

        System.out.println("Ingrese los coeficientes de "+lado+":");

        for (int i = 0; i < formulas.length; i++) {

            int valor = 0;

            while (valor < 1) {

                System.out.print("  " + formulas[i] + " = ");

                try {
                    valor = scanner.nextInt();
                    scanner.nextLine(); // limpia el salto de línea pendiente tras nextInt()

                    if (valor < 1) {
                        System.out.println("El coeficiente debe ser mayor o igual a 1.");
                    }

                } catch (Exception e) {
                    System.out.println("Ingrese un número entero válido.");
                    scanner.nextLine(); // descarta toda la línea inválida, no solo un token
                }
            }
            coeficientes[i] = valor;
        }

        return coeficientes;
    }

    public RespuestaEjercicio solicitarRespuesta() {
        int[] coefReactivos = solicitarCoeficientes(ejercicio.getFormulasReactivos(), "reactivos");
        int[] coefProductos = solicitarCoeficientes(ejercicio.getFormulasProductos(), "productos");

        return new RespuestaEjercicio(coefReactivos, coefProductos);
    }

    public boolean evaluarRespuesta(RespuestaEjercicio respuesta) {
        return ejercicio.esBalanceada(respuesta.getCoefReactivos(), respuesta.getCoefProductos());
    }

    public void mostrarRetroalimentacion(boolean correcto) {

        if (correcto) {
            System.out.println("La ecuación quedó balanceada.");
        } else {
            System.out.println("Incorrecto. La ecuación todavía no está balanceada.");
            System.out.println("Ecuación sugerida: "+ejercicio.armarEcuacionSugerida());
        }

        System.out.println();
    }

    public String toString() {
        return "Interfaz de Ejercicio para: " + ejercicio.getEnunciado();
    }




}
