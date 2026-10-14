package integracionSwing.login;

import java.util.ArrayList;
import java.util.List;

public class SistemaLogin {
    private final List<Usuario> usuarios;

    public SistemaLogin() {
        usuarios = new ArrayList<>();
    }

    public boolean registrarUsuario(Usuario nuevoUsuario) {
        if (buscarUsuario(nuevoUsuario.getNombreUsuario()) != null) {
            return false;
        }
        usuarios.add(nuevoUsuario);
        return true;
    }

    public Usuario iniciarSesion(String nombreUsuario, String contrasena) {
        Usuario usuario = buscarUsuario(nombreUsuario);
        if (usuario != null && usuario.verificarContrasena(contrasena)) {
            return usuario;
        }
        return null;
    }

    private Usuario buscarUsuario(String nombreUsuario) {
        if (nombreUsuario == null) {
            return null;
        }
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equalsIgnoreCase(nombreUsuario.trim())) {
                return usuario;
            }
        }
        return null;
    }
}
