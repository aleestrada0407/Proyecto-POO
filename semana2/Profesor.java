package semana2;

import java.util.ArrayList;
import java.util.List;

public class Profesor extends Usuario {
    private final List<Estudiante> listaEstudiantes;

    public Profesor(String nombre, String seccion) {
        super(nombre, seccion);
        listaEstudiantes = new ArrayList<>();
    }

    public void agregarEstudiante(Estudiante estudiante) {
        if (estudiante != null) {
            listaEstudiantes.add(estudiante);
        }
    }

    public List<Estudiante> getListaEstudiantes() {
        return new ArrayList<>(listaEstudiantes);
    }
}