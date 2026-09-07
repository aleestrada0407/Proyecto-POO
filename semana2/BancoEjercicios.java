import java.util.ArrayList;

public class BancoEjercicios {

    // Listas para almacenar los ejercicios
    private ArrayList<EjerciciosCompletarNomenclatura> ejerciciosNomenclatura;
    private ArrayList<Ejercicio> ejerciciosBalanceo;

    // Constructor que inicializa las listas vacías
    public BancoEjercicios() {
        this.ejerciciosNomenclatura = new ArrayList<>();
        this.ejerciciosBalanceo = new ArrayList<>();
    }

    // Métodos de acceso
    public ArrayList<EjerciciosCompletarNomenclatura> getEjerciciosNomenclatura() {
        return ejerciciosNomenclatura;
    }

    public ArrayList<Ejercicio> getEjerciciosBalanceo() {
        return ejerciciosBalanceo;
    }
}