import java.util.ArrayList;

public class BancoEjercicios {

    private ArrayList<EjerciciosCompletarNomenclatura> ejerciciosNomenclatura;
    private ArrayList<Ejercicio> ejerciciosBalanceo;
    private int indiceActual; 
    private int indiceNomenclatura;

    public BancoEjercicios() {
        this.ejerciciosNomenclatura = new ArrayList<>();
        this.ejerciciosBalanceo = new ArrayList<>();
        this.indiceActual = 0;
        this.indiceNomenclatura = 0;
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



        Ejercicio ej13 = new Ejercicio("Balancee la ecuacion de combustion del metano:");
        ej13.llenarEcuacion(new String[]{"CH4", "O2"}, new String[]{"CO2", "H2O"}, new int[]{1, 2}, new int[]{1, 2});
        ejerciciosBalanceo.add(ej13);

        Ejercicio ej14 = new Ejercicio("Balancee la ecuacion de combustion del propano:");
        ej14.llenarEcuacion(new String[]{"C3H8", "O2"}, new String[]{"CO2", "H2O"}, new int[]{1, 5}, new int[]{3, 4});
        ejerciciosBalanceo.add(ej14);

        Ejercicio ej15 = new Ejercicio("Balancee la ecuacion de descomposicion del clorato de potasio:");
        ej15.llenarEcuacion(new String[]{"KClO3"}, new String[]{"KCl", "O2"}, new int[]{2}, new int[]{2, 3});
        ejerciciosBalanceo.add(ej15);

        Ejercicio ej16 = new Ejercicio("Balancee la ecuacion de neutralizacion del hidroxido de sodio con acido sulfurico:");
        ej16.llenarEcuacion(new String[]{"NaOH", "H2SO4"}, new String[]{"Na2SO4", "H2O"}, new int[]{2, 1}, new int[]{1, 2});
        ejerciciosBalanceo.add(ej16);

        Ejercicio ej17 = new Ejercicio("Balancee la ecuacion de reaccion del zinc con acido clorhidrico:");
        ej17.llenarEcuacion(new String[]{"Zn", "HCl"}, new String[]{"ZnCl2", "H2"}, new int[]{1, 2}, new int[]{1, 1});
        ejerciciosBalanceo.add(ej17);

        Ejercicio ej18 = new Ejercicio("Balancee la ecuacion de formacion del oxido de aluminio:");
        ej18.llenarEcuacion(new String[]{"Al", "O2"}, new String[]{"Al2O3"}, new int[]{4, 3}, new int[]{2});
        ejerciciosBalanceo.add(ej18);

        Ejercicio ej19 = new Ejercicio("Balancee la ecuacion de oxidacion del hierro para formar oxido de hierro (III):");
        ej19.llenarEcuacion(new String[]{"Fe", "O2"}, new String[]{"Fe2O3"}, new int[]{4, 3}, new int[]{2});
        ejerciciosBalanceo.add(ej19);

        Ejercicio ej20 = new Ejercicio("Balancee la ecuacion de descomposicion del peroxido de hidrogeno:");
        ej20.llenarEcuacion(new String[]{"H2O2"}, new String[]{"H2O", "O2"}, new int[]{2}, new int[]{2, 1});
        ejerciciosBalanceo.add(ej20);

        Ejercicio ej21 = new Ejercicio("Balancee la ecuacion de descomposicion del carbonato de calcio:");
        ej21.llenarEcuacion(new String[]{"CaCO3"}, new String[]{"CaO", "CO2"}, new int[]{1}, new int[]{1, 1});
        ejerciciosBalanceo.add(ej21);

        Ejercicio ej22 = new Ejercicio("Balancee la ecuacion entre nitrato de plata y cloruro de sodio:");
        ej22.llenarEcuacion(new String[]{"AgNO3", "NaCl"}, new String[]{"AgCl", "NaNO3"}, new int[]{1, 1}, new int[]{1, 1});
        ejerciciosBalanceo.add(ej22);

        Ejercicio ej23 = new Ejercicio("Balancee la ecuacion de neutralizacion del hidroxido de calcio con acido clorhidrico:");
        ej23.llenarEcuacion(new String[]{"HCl", "Ca(OH)2"}, new String[]{"CaCl2", "H2O"}, new int[]{2, 1}, new int[]{1, 2});
        ejerciciosBalanceo.add(ej23);

        Ejercicio ej24 = new Ejercicio("Balancee la ecuacion redox entre permanganato de potasio y acido clorhidrico:");
        ej24.llenarEcuacion(new String[]{"KMnO4", "HCl"}, new String[]{"KCl", "MnCl2", "H2O", "Cl2"}, new int[]{2, 16}, new int[]{2, 2, 8, 5});
        ejerciciosBalanceo.add(ej24);

        // Tarjetas que muestran la formula y piden el nombre.
        EjerciciosCompletarNomenclatura tarjeta1 =
                new EjerciciosCompletarNomenclatura("MnO4-", "Permanganato");
        ejerciciosNomenclatura.add(tarjeta1);

        EjerciciosCompletarNomenclatura tarjeta2 = new EjerciciosCompletarNomenclatura("NO3-", "Nitrato");
        ejerciciosNomenclatura.add(tarjeta2);

        EjerciciosCompletarNomenclatura tarjeta3 = new EjerciciosCompletarNomenclatura("OH-", "Hidroxido");
        ejerciciosNomenclatura.add(tarjeta3);

        // Tarjetas que muestran el nombre y piden la formula.
        EjerciciosCompletarNomenclatura tarjeta4 =
            new EjerciciosCompletarNomenclatura("MnO4-", "Permanganato");
        tarjeta4.setModo(2);
        ejerciciosNomenclatura.add(tarjeta4);

        EjerciciosCompletarNomenclatura tarjeta5 =
            new EjerciciosCompletarNomenclatura("NO3-", "Nitrato");
            tarjeta5.setModo(2);
        ejerciciosNomenclatura.add(tarjeta5);

        EjerciciosCompletarNomenclatura tarjeta6 =
            new EjerciciosCompletarNomenclatura("OH-", "Hidroxido");
            tarjeta6.setModo(2);
        ejerciciosNomenclatura.add(tarjeta6);


        

        

        
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
    public boolean haySiguienteNomenclatura() {
    return indiceNomenclatura < ejerciciosNomenclatura.size();
}

public EjerciciosCompletarNomenclatura obtenerSiguienteNomenclatura() {
    if (haySiguienteNomenclatura()) {
        EjerciciosCompletarNomenclatura ejercicio =
                ejerciciosNomenclatura.get(indiceNomenclatura);

        indiceNomenclatura++;
        return ejercicio;
    }

    return null;
    }

    public void reiniciarNomenclatura() {
        indiceNomenclatura = 0;
    }

    public int getCantidadTotalNomenclatura() {
        return ejerciciosNomenclatura.size();
    }

    public double obtenerPorcentajeAvanceNomenclatura() {
        if (ejerciciosNomenclatura.isEmpty()) {
            return 0.0;
        }

        return ((double) indiceNomenclatura
                / ejerciciosNomenclatura.size()) * 100;
    }

    public void sembrarBaseDatos(Ejerciciodao dao) {

    int nuevos = 0;

    for (Ejercicio ej : ejerciciosBalanceo) {
        if (!dao.existePorEnunciado(ej.getEnunciado())) {
            if (dao.insertar(ej)) {
                nuevos++;
            }
        }
    }

    if (nuevos > 0) {
        System.out.println("Banco inicial cargado en la base de datos: " + nuevos + " ejercicios.");
        }
    }
}
