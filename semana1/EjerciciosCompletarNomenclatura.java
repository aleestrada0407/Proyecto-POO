public class EjercicioCompletarNomenclatura {

    private String formulaQuimica;
    private String respuestaEsperada;

    public EjercicioCompletarNomenclatura(
            String formulaQuimica,
            String respuestaEsperada) {

        this.formulaQuimica = formulaQuimica;
        this.respuestaEsperada = respuestaEsperada;
    }

    public String getFormulaQuimica() {
        return formulaQuimica;
    }

    public void setFormulaQuimica(String formulaQuimica) {
        this.formulaQuimica = formulaQuimica;
    }

    public String getRespuestaEsperada() {
        return respuestaEsperada;
    }

    public void setRespuestaEsperada(String respuestaEsperada) {
        this.respuestaEsperada = respuestaEsperada;
    }

    public boolean verificarRespuesta(String respuesta) {
        return respuestaEsperada.equalsIgnoreCase(respuesta.trim());
    }

    public void mostrarEjercicio() {
        System.out.println(
                "Escriba el nombre del compuesto: " + formulaQuimica
        );
    }
}