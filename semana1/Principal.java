import java.util.Scanner;
public class Principal {
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);

        System.out.println("\n---- REPASO DE QUÍMICA ----");
        System.out.println("1. Registrar estudiante");
        System.out.println("2. Repasar conceptos");
        System.out.println("3. Modificar información del estudiante");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
        int opcion = teclado.nextInt();
        teclado.nextLine();

        switch (opcion){
            case 1:
        }
            System.out.println("\n---- REGISTRO DE ESTUDIANTE ----");
            System.out.println("\nIngrese su nombre: ");
            String name = teclado.nextLine(); 
            System.out.println("\nIngrese su carrera: ");
            String carrera = teclado.nextLine();
            System.out.println("\nIngrese su carné: ");
            int carne = teclado.nextInt();
            System.out.println("\n¿Es su primera vez tomando el curso de Química I? (S/N): ");
            char si_no = teclado.next().toLowerCase().charAt(0);

         switch (opcion){
            case 2:
        }

         switch (opcion){
            case 3:
            System.out.println("\n---- MODIFICAR INFORMACIÓN DE ESTUDIANTE ----");
            System.out.println("\nIngrese su nombre: ");
            System.out.println("\nIngrese su carrera: ");
            System.out.println("\nIngrese su carné: ");
            System.out.println("\n¿Es su primera vez tomando el curso de Química I? (S/N): ");
        }
    }
}