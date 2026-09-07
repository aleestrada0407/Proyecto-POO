public class Resultado {
    private Ejercicio ejercicio;
    private String estudiante;
    private RespuestaEjercicio respuesta;
    private boolean correcto;

    public Resultado(Ejercicio ejercicio, String estudiante, RespuestaEjercicio respuesta, boolean correcto) {
        this.ejercicio = ejercicio;
        this.estudiante = estudiante;
        this.respuesta = respuesta;
        this.correcto = correcto;
    }

    public Ejercicio getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Ejercicio ejercicio) {
        this.ejercicio = ejercicio;
    }

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }

    public RespuestaEjercicio getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(RespuestaEjercicio respuesta) {
        this.respuesta = respuesta;
    }

    public boolean isCorrecto() {
        return correcto;
    }

    public void setCorrecto(boolean correcto) {
        this.correcto = correcto;
    }

    public String toString() {

        String estado = "";

        if (correcto) {
            estado = "Correcto";
        } else {

            estado = "Incorrecto";
        }

        return estudiante+" - "+estado+"\n"+ejercicio.armarEcuacion();
    }
    
}
