import java.util.ArrayList;

public class BancoEjercicios {

    private ArrayList<EjerciciosCompletarNomenclatura> ejerciciosNomenclatura;
    private ArrayList<Ejercicio> ejerciciosBalanceo;
    private int indiceActual; 

    public BancoEjercicios() {
        this.ejerciciosNomenclatura = new ArrayList<>();
        this.ejerciciosBalanceo = new ArrayList<>();
        this.indiceActual = 0;
        inicializarBanco();
    }

    private void inicializarBanco() {
      
        Ejercicio ej1 = new Ejercicio("Balancee la ecuacion de formacion del agua:");
        ej1.llenarEcuacion(new String[]{"H2", "O2"}, new String[]{"H2O"}, new int[]{2, 1}, new int[]{2});
        ejerciciosBalanceo.add(ej1);

        Ejercicio ej2 = new Ejercicio("Balancee la ecuacion de sintesis del amoniaco:");
        ej2.llenarEcuacion(new String[]{"N2", "H2"}, new String[]{"NH3"}, new int[]{1, 3}, new int[]{2});
        ejerciciosBalanceo.add(ej2);
    }


    public boolean haySiguiente() {
        return indiceActual < ejerciciosBalanceo.size();
    }

    public Ejercicio obtenerSiguiente() {
        if (haySiguiente()) {
            Ejercicio ej = ejerciciosBalanceo.get(indiceActual);
            indiceActual++;
            return ej;
        }
        return null;
    }

    public void reiniciar() {
        this.indiceActual = 0;
    }

    public ArrayList<EjerciciosCompletarNomenclatura> getEjerciciosNomenclatura() {
        return ejerciciosNomenclatura;
    }

    public ArrayList<Ejercicio> getEjerciciosBalanceo() {
        return ejerciciosBalanceo;
    }

    public int getIndiceActual() {
        return indiceActual;
    }

    public int getCantidadTotalEjercicios() {
        return ejerciciosBalanceo.size();
    }

    public double obtenerPorcentajeAvance() {
        if (ejerciciosBalanceo.isEmpty()) return 0.0;
        return ((double) indiceActual / ejerciciosBalanceo.size()) * 100;
    }
}