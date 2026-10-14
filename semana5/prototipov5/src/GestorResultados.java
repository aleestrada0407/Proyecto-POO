public class GestorResultados {
 
    private Resultadodao resultadodao;
 
    public GestorResultados() {
        this.resultadodao = new Resultadodao();
    }
 
    public void registrarResultado(Resultado resultado) {
        resultadodao.insertar(resultado);
    }
 
    public double calcularDesempenoIndividual(String estudiante) {
        return resultadodao.calcularDesempeno(estudiante);
    }
}