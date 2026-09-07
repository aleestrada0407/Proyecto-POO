public class EjerciciosOpcionMultiple {

    private String enunciado;
    private String[] opciones;
    private int opcionCorrecta;

    public EjerciciosOpcionMultiple(
            String enunciado,
            String[] opciones,
            int opcionCorrecta) {

        this.enunciado = enunciado;
        this.opciones = opciones;
        this.opcionCorrecta = opcionCorrecta;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String[] getOpciones() {
        return opciones;
    }

    public void setOpciones(String[] opciones) {
        this.opciones = opciones;
    }

    public int getOpcionCorrecta() {
        return opcionCorrecta;
    }

    public void setOpcionCorrecta(int opcionCorrecta) {
        this.opcionCorrecta = opcionCorrecta;
    }

    public boolean verificarRespuesta(int respuesta) {
        return respuesta == opcionCorrecta;
    }

    public void mostrarOpciones() {
        System.out.println(enunciado);

        for (int i = 0; i < opciones.length; i++) {
            System.out.println((i + 1) + ". " + opciones[i]);
        }
    }
}