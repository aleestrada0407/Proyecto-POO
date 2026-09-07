package login;

public class Profesor extends Usuario {
    public Profesor(String nombre, String seccion, String nombreUsuario, String contrasena) {
        super(nombre, seccion, nombreUsuario, contrasena);
    }

    @Override
    public String getTipoUsuario() {
        return "Profesor";
    }
}
