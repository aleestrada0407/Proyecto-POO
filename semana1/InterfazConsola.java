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

        System.out.println("Ingrese los coeficientes de "+lado +":");

        for (int i = 0; i < formulas.length; i++) {

            int valor = 0;

            while (valor < 0) {

                System.out.print("  "+formulas[i]+" = ");
                if (scanner.hasNextInt()) {

                    valor = scanner.nextInt();

                    if (valor < 0) {

                        System.out.println("El coeficiente no puede ser negativo.");

                    }
                    
                } else {
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



    
}
