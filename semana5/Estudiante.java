public class Estudiante extends Usuario {
    public Estudiante(String nombre, String seccion, String nombreUsuario, String contrasena) {
        super(nombre, seccion, nombreUsuario, contrasena);
    }

    public Estudiante(String nombre, String seccion, String nombreUsuario, String contrasenaOHash, boolean esHash) {
        super(nombre, seccion, nombreUsuario, contrasenaOHash, esHash);
    }

    @Override
    public String getTipoUsuario() {
        return "Estudiante";
    }
}
