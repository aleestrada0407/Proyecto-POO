package login;

import java.util.Scanner;

public class InterfazConsola {
    public void mostrarTexto(String mensaje) {
        System.out.println(mensaje);
    }

    public String leerLinea(Scanner teclado, String mensaje) {
        System.out.print(mensaje);
        return teclado.nextLine().trim();
    }

    public int leerOpcion(Scanner teclado, int minimo, int maximo) {
        while (true) {
            String entrada = leerLinea(teclado, "Selecciona una opcion: ");
            try {
                int opcion = Integer.parseInt(entrada);
                if (opcion >= minimo && opcion <= maximo) {
                    return opcion;
                }
            } catch (NumberFormatException ignored) {
                // Se solicita nuevamente.
            }
            mostrarTexto("Opcion invalida.");
        }
    }
}
