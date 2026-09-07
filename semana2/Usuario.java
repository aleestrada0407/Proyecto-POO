package semana1;

public class Usuario {
    private String nombre;
    private String seccion;

    public Usuario(String nombre, String seccion) {
        this.nombre = nombre;
        this.seccion = seccion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getSeccion() {
        return seccion;
    }

    @Override
    public String toString() {
        return nombre + " - Sección: " + seccion;
    }
}