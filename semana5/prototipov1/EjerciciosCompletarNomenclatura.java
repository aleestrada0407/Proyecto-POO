public class EjerciciosCompletarNomenclatura{

    private String formulaQuimica;
    private String respuestaEsperada;
    private int modo;
    private String[] elementos;

      public EjerciciosCompletarNomenclatura(
            String formulaQuimica,
            String respuestaEsperada) {this(formulaQuimica, respuestaEsperada, new String[0]);
    }
      public EjerciciosCompletarNomenclatura(
            String formulaQuimica,
            String respuestaEsperada,
            String[] elementos) {

        this.formulaQuimica = formulaQuimica;
        this.respuestaEsperada = respuestaEsperada;
        this.modo = 1;
        this.elementos = elementos;
    }

    public String getFormulaQuimica() {
        return formulaQuimica;
    }

    public void setFormulaQuimica(String formulaQuimica) {
        this.formulaQuimica = formulaQuimica;
    }

    public String getRespuestaEsperada() {
          if (modo == 2) {
        return formulaQuimica;
    }

    return respuestaEsperada;
    }

    public void setRespuestaEsperada(String respuestaEsperada) {
        this.respuestaEsperada = respuestaEsperada;
    }

    public boolean verificarRespuesta(String respuesta) {
         if (respuesta == null || respuesta.trim().isEmpty()) {
        return false;
    }

    String respuestaLimpia = respuesta.trim();

    if (modo == 2) {
        return formulaQuimica.equals(respuestaLimpia);
    }

    return respuestaEsperada.equalsIgnoreCase(respuestaLimpia);
    }

    public void mostrarEjercicio() {
            if (modo == 1) {
                System.out.println(
                        "Escriba el nombre del compuesto: " + formulaQuimica
                );
            } else if (modo == 2) {
                System.out.println(
                        "Escriba la fórmula del compuesto: " + respuestaEsperada
                );
            }
        
    }
    public int getModo() {
        return modo;
    }

    public void setModo(int modo) {
          if (modo != 1 && modo != 2) {
        throw new IllegalArgumentException(
                "El modo debe ser 1 o 2."
        );
    }

    this.modo = modo;
    }
        public String[] getElementos() {
        return elementos;
    }

    public void setElementos(String[] elementos) {
        this.elementos = elementos;
    }

}