import java.util.ArrayList;


public class GestorResultados {

    private ArrayList<Resultado> resultados;

    public GestorResultados() {
        this.resultados = new ArrayList<>();
    }



    public void registrarResultado(Resultado resultado) {
        resultados.add(resultado);

    }

    public ArrayList<Resultado> obtenerResultadosPorEstudiante(String estudiante) {

        ArrayList<Resultado> resultadosEstudiante = new ArrayList<>();

        for (int i = 0; i < resultados.size(); i++) {
            if (resultados.get(i).getEstudiante().equals(estudiante)) {
                resultadosEstudiante.add(resultados.get(i));
            }
        }

        return resultadosEstudiante;
    }


     
    
}
