import java.util.Scanner;

public class InterfazConsola {


    

    private Ejercicio ejercicio;
    private Scanner scanner;

    public InterfazConsola(Ejercicio ejercicio) {
        this.ejercicio = ejercicio;
        this.scanner = new Scanner(System.in);

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

                    if (valor < 1) {
                        System.out.println("El coeficiente debe ser mayor o igual a 1.");
                    }

                } catch (Exception e) {
                    System.out.println("Ingrese un número entero válido.");
                    scanner.next();
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

    public String toString() {
        return "InterfazConsola para: " + ejercicio.getEnunciado();
    }
}

