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

    
        Ejercicio ej1 = new Ejercicio("Balancee la ecuacion de formacion del agua:");
        ej1.llenarEcuacion(
            new String[]{"H2", "O2"},
            new String[]{"H2O"},
            new int[]{2, 1},
            new int[]{2}
        );
        ejerciciosBalanceo.add(ej1);

        Ejercicio ej2 = new Ejercicio("Balancee la ecuacion de sintesis del amoniaco:");
        ej2.llenarEcuacion(
            new String[]{"N2", "H2"},
            new String[]{"NH3"},
            new int[]{1, 3},
            new int[]{2}
        );
        ejerciciosBalanceo.add(ej2);
    }

    public ArrayList<EjerciciosCompletarNomenclatura> getEjerciciosNomenclatura() {
        return ejerciciosNomenclatura;
    }

    public ArrayList<Ejercicio> getEjerciciosBalanceo() {
        return ejerciciosBalanceo;
    }
}