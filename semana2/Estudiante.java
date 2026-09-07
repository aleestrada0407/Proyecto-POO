package semana2;

import java.util.ArrayList;
import java.util.List;

public class Estudiante extends Usuario {
    private final List<Resultado> historialResultados;

    public Estudiante(String nombre, String seccion) {
        super(nombre, seccion);
        historialResultados = new ArrayList<>();
    }

    public void registrarResultado(Resultado resultado) {
        if (resultado != null) {
            historialResultados.add(resultado);
        }
    }

    public List<Resultado> getHistorialResultados() {
        return new ArrayList<>(historialResultados);
    }
}