import java.util.ArrayList;

public class BancoEjercicios {

    private ArrayList<EjerciciosCompletarNomenclatura> ejerciciosNomenclatura;
    private ArrayList<Ejercicio> ejerciciosBalanceo;

    public BancoEjercicios() {
        this.ejerciciosNomenclatura = new ArrayList<>();
        this.ejerciciosBalanceo = new ArrayList<>();
        inicializarBanco(); 
    }

    private void inicializarBanco() {
        ejerciciosNomenclatura.add(new EjerciciosCompletarNomenclatura("H2O", "Agua"));
        ejerciciosNomenclatura.add(new EjerciciosCompletarNomenclatura("NaCl", "Cloruro de sodio"));
        ejerciciosNomenclatura.add(new EjerciciosCompletarNomenclatura("CO2", "Dióxido de carbono"));
    }

    public ArrayList<EjerciciosCompletarNomenclatura> getEjerciciosNomenclatura() {
        return ejerciciosNomenclatura;
    }

    public ArrayList<Ejercicio> getEjerciciosBalanceo() {
        return ejerciciosBalanceo;
    }
}