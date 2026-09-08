import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Usuario {
    private final String nombre;
    private final String seccion;
    private final String nombreUsuario;
    private final String contrasenaHash;

    public Usuario(String nombre, String seccion, String nombreUsuario, String contrasena) {
        this.nombre = validar(nombre, "nombre");
        this.seccion = validar(seccion, "seccion");
        this.nombreUsuario = validar(nombreUsuario, "nombre de usuario");
        this.contrasenaHash = calcularHash(validar(contrasena, "contrasena"));
    }

    public String getNombre() {
        return nombre;
    }

    public String getSeccion() {
        return seccion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public boolean verificarContrasena(String contrasena) {
        return contrasena != null && contrasenaHash.equals(calcularHash(contrasena));
    }

    public String getTipoUsuario() {
        return "Usuario";
    }

    private static String validar(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo " + campo + " es obligatorio");
        }
        return valor.trim();
    }

    private static String calcularHash(String texto) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = digest.digest(texto.getBytes(StandardCharsets.UTF_8));
            StringBuilder hash = new StringBuilder();
            for (byte valor : bytes) {
                hash.append(String.format("%02x", valor));
            }
            return hash.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No se pudo proteger la contrasena", e);
        }
    }

    @Override
    public String toString() {
        return nombre + " - Seccion: " + seccion + " - Tipo: " + getTipoUsuario();
    }
}
