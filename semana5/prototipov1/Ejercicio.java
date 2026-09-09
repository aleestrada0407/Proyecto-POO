import java.util.HashMap;

public class Ejercicio{


    private String enunciado;
    private String[] formulasReactivos;
    private String[] formulasProductos;
    private int[] coefSugeridosReactivos;
    private int[] coefSugeridosProductos;


    public Ejercicio(){


        enunciado = "";
        formulasReactivos = new String[0];
        formulasProductos = new String[0];
        coefSugeridosReactivos = new int[0];
        coefSugeridosProductos = new int[0];

    }

    public Ejercicio(String enunciado){

        this.enunciado = enunciado;
        this.formulasReactivos = new String[0];
        this.formulasProductos = new String[0];

        this.coefSugeridosReactivos = new int[0];
        this.coefSugeridosProductos = new int[0];
    }

    public void llenarEcuacion(String[] formulasReactivos, String[] formulasProductos, int[] coefSugeridosReactivos, int[] coefSugeridosProductos){
 
        this.formulasReactivos = formulasReactivos;
        this.formulasProductos = formulasProductos;
        this.coefSugeridosReactivos = coefSugeridosReactivos;
        this.coefSugeridosProductos = coefSugeridosProductos;

        
    }

    
    public String getEnunciado(){

        return enunciado;
    }


    public String[] getFormulasReactivos(){

        return formulasReactivos;
    }

    public String[] getFormulasProductos(){

        return formulasProductos;
    }

    public int getCantReactivos(){

        return formulasReactivos.length;
    }

    public int getCantProductos(){

        return formulasProductos.length;
    }

    private String armarLado(String[] formulas, int[] coeficientes){

        String cadena = "";

        for(int i = 0; i < formulas.length; i++){



            String coefTexto = (coeficientes == null) ? "_" : ("" + coeficientes[i]);


            cadena = cadena + coefTexto + " " + formulas[i];

            if(i < formulas.length - 1){
                cadena = cadena + " + ";
            }


        }

        return cadena;

    }

    public String armarEcuacion(){


        return armarLado(formulasReactivos, null)+"→"+ armarLado(formulasProductos, null);
    }

    public String armarEcuacionSugerida(){


        return armarLado(formulasReactivos, coefSugeridosReactivos)+"→"+armarLado(formulasProductos, coefSugeridosProductos);
    }



    private HashMap<String, Integer> contarAtomos(String formula, int coeficiente){

        
        HashMap<String, Integer> atomos = new HashMap<>();

        int i = 0;

        while(i < formula.length()){


            String elemento = "" + formula.charAt(i);

            i++;

            if(i < formula.length() && Character.isLowerCase(formula.charAt(i))){

                elemento = elemento + formula.charAt(i);
                i++;
            }

            String numeroTexto = "";


            while(i < formula.length() && Character.isDigit(formula.charAt(i))){

                numeroTexto = numeroTexto + formula.charAt(i);
                i++;
            }

            int cantidad = numeroTexto.isEmpty() ? 1 : Integer.parseInt(numeroTexto);
            cantidad = cantidad * coeficiente;

            if(atomos.containsKey(elemento)){

                atomos.put(elemento, atomos.get(elemento) + cantidad);

            } else {

                atomos.put(elemento, cantidad);
            }
        }

        return atomos;
    }


    private void acumularAtomos(HashMap<String, Integer> total, HashMap<String, Integer> nuevo){


        for(String elemento : nuevo.keySet()){

            int cantidad = nuevo.get(elemento);

            if(total.containsKey(elemento)){

                total.put(elemento, total.get(elemento) + cantidad);

            } else {

                total.put(elemento, cantidad);
            }
        }


    }

    public boolean esBalanceada(int[] coefReactivos, int[] coefProductos){

        HashMap<String, Integer> totalReactivos = new HashMap<>();
        HashMap<String, Integer> totalProductos = new HashMap<>();

        for(int i = 0; i < formulasReactivos.length; i++){

            acumularAtomos(totalReactivos, contarAtomos(formulasReactivos[i], coefReactivos[i]));
        }

        for(int i =0; i < formulasProductos.length; i++){
            acumularAtomos(totalProductos, contarAtomos(formulasProductos[i], coefProductos[i]));
        }

        if(totalReactivos.size()!=totalProductos.size()){
            return false;
        }

        for(String elemento : totalReactivos.keySet()){
            if(!totalProductos.containsKey(elemento)){
                return false;
            }
            if(!totalReactivos.get(elemento).equals(totalProductos.get(elemento))){
                return false;
            }
        }

        return true;



    }

    public String toString(){
        return enunciado + "\n" + armarEcuacion();
    }



}
