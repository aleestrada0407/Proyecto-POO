public class Ejercicio {

    private String enunciado;
    private String tema;

    public Ejercicio(String enunciado, String tema) {
        this.enunciado = enunciado;
        this.tema = tema;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public void mostrarEjercicio() {
        System.out.println("Tema: " + tema);
        System.out.println("Enunciado: " + enunciado);
    }
}