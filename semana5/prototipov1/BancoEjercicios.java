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

        Ejercicio ej3 = new Ejercicio("Balancee la ecuacion de reaccion del hierro con acido clorhidrico:");
        ej3.llenarEcuacion(new String[]{"Fe", "HCl"}, new String[]{"FeCl3", "H2"}, new int[]{2, 6}, new int[]{2, 3});
        ejerciciosBalanceo.add(ej3);

        Ejercicio ej4 = new Ejercicio("Balancee la ecuacion de reaccion del cobre con acido fosforico:");
        ej4.llenarEcuacion(new String[]{"Cu", "H3PO4"}, new String[]{"Cu3(PO4)2", "P", "H2O"}, new int[]{15, 16}, new int[]{5, 6, 24});
        ejerciciosBalanceo.add(ej4);
        
        Ejercicio ej5 = new Ejercicio("Balancee la ecuacion de reaccion del cadmio con acido nitrico:");
        ej5.llenarEcuacion(new String[]{"Cd", "HNO3"}, new String[]{"Cd(NO3)2", "H2O", "NO2"}, new int[]{1, 4}, new int[]{1, 2, 2});
        ejerciciosBalanceo.add(ej5);

        Ejercicio ej6 = new Ejercicio("Balancee la ecuacion de descomposicion del permanganato de potasio con acido sulfurico:");
        ej6.llenarEcuacion(new String[]{"KMnO4", "H2SO4"}, new String[]{"O2", "K2SO4", "MnSO4", "H2O"}, new int[]{4, 6}, new int[]{5, 2, 4, 6});
        ejerciciosBalanceo.add(ej6);


        Ejercicio ej7 = new Ejercicio("Balancee la ecuacion de combustion del indol:");
        ej7.llenarEcuacion(new String[]{"C8H7N", "O2"}, new String[]{"CO2", "H2O", "N2"}, new int[]{4, 39}, new int[]{32, 14, 2});
        ejerciciosBalanceo.add(ej7);

        Ejercicio ej8 = new Ejercicio("Balancee la ecuacion de reacción del cromato de plomo (II) con yoduro de potasio y ácido clorhídrico:");
        ej8.llenarEcuacion(new String[]{"PbCrO4", "KI", "HCl"}, new String[]{"PbCl2", "CrI3", "KCl", "I2", "H2O"}, new int[]{2, 12, 16}, new int[]{2, 2, 12, 3, 8});
        ejerciciosBalanceo.add(ej8);

        Ejercicio ej9 = new Ejercicio("Balancee la ecuacion de reacción del permanganato de potasio con el sulfuro ferroso y el ácido sulfúrico:");
        ej9.llenarEcuacion(new String[]{"KMnO4", "FeSO4", "H2SO4"}, new String[]{"MnSO4", "Fe2(SO4)3", "K2SO4", "H2O"}, new int[]{2, 10, 8}, new int[]{2, 5, 1, 8});
        ejerciciosBalanceo.add(ej9);

        Ejercicio ej10 = new Ejercicio("Balancee la ecuacion de reacción del cloro con el hidróxido de potasio:");
        ej10.llenarEcuacion(new String[]{"Cl2", "KOH"}, new String[]{"KClO4", "KCl", "H2O"}, new int[]{4, 8}, new int[]{1, 7, 4});
        ejerciciosBalanceo.add(ej10);

        Ejercicio ej11 = new Ejercicio("Balancee la ecuación de reacción del ácido fosfórico con molibdato de amonio:");
        ej11.llenarEcuacion(new String[]{"H3PO4", "(NH4)2MoO4"}, new String[]{"(NH4)3PO4", "MoO3", "H2O"}, new int[]{2, 3}, new int[]{2, 3, 3});
        ejerciciosBalanceo.add(ej11);

        Ejercicio ej12 = new Ejercicio("Balancee la ecuacion de reacción del bromuro de yodo con el amoníaco:");
        ej12.llenarEcuacion(new String[]{"IBr", "NH3"}, new String[]{"NI3", "NH4Br"}, new int[]{3, 4}, new int[]{1, 3});
        ejerciciosBalanceo.add(ej12);
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