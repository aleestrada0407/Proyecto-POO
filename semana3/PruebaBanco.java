public class PruebaBanco {
    public static void main(String[] args) {
        BancoEjercicios banco = new BancoEjercicios();

        System.out.println("--- PRUEBA DE NAVEGACIÓN EN BANCO DE EJERCICIOS ---");

        while (banco.haySiguiente()) {
            Ejercicio ej = banco.obtenerSiguiente();
            System.out.println("Ejercicio cargado: " + ej.getEnunciado());
            System.out.println("Progreso: " + banco.obtenerPorcentajeAvance() + "%\n");
        }

        System.out.println("¿Hay más ejercicios disponibles?: " + banco.haySiguiente());

        System.out.println("\nEjecutando reinicio...");
        banco.reiniciar();
        System.out.println("¿Hay ejercicios tras reiniciar?: " + banco.haySiguiente());
        System.out.println("Índice actual restablecido a: " + banco.getIndiceActual());
    }
}