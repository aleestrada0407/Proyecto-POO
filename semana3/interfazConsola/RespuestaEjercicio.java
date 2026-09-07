public class RespuestaEjercicio {
    private int[] coefReactivos;
    private int[] coefProductos;

    public RespuestaEjercicio(int[] coefReactivos, int[] coefProductos) {
        this.coefReactivos = coefReactivos;
        this.coefProductos = coefProductos;
    }

    public int[] getCoefReactivos() {
        return coefReactivos;
    }

    public int[] getCoefProductos() {
        return coefProductos;
    }
    
}
