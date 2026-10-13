public class Profesor extends Usuario {
    public Profesor(String nombre, String seccion, String nombreUsuario, String contrasena) {
        super(nombre, seccion, nombreUsuario, contrasena);
    }

    public Profesor(String nombre, String seccion, String nombreUsuario, String contrasenaOHash, boolean esHash) {
        super(nombre, seccion, nombreUsuario, contrasenaOHash, esHash);
    }

    @Override
    public String getTipoUsuario() {
        return "Profesor";
    }
}
