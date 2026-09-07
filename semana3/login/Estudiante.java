package login;

public class Estudiante extends Usuario {
    public Estudiante(String nombre, String seccion, String nombreUsuario, String contrasena) {
        super(nombre, seccion, nombreUsuario, contrasena);
    }

    @Override
    public String getTipoUsuario() {
        return "Estudiante";
    }
}
