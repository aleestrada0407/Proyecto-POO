import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
public class Usuariodao {

    public boolean insertar(Usuario usuario) {
 
        String sql = "INSERT INTO usuarios (nombre, seccion, nombre_usuario, contrasena_hash, tipo) " + "VALUES (?, ?, ?, ?, ?)";
 
        try (Connection conexion = BaseDatos.conectar();
             PreparedStatement consulta = conexion.prepareStatement(sql)) {
 
            consulta.setString(1, usuario.getNombre());
            consulta.setString(2, usuario.getSeccion());
            consulta.setString(3, usuario.getNombreUsuario());
            consulta.setString(4, usuario.getContrasenaHash());
            consulta.setString(5, usuario.getTipoUsuario());
 
            consulta.executeUpdate();
            return true;
 
        } catch (SQLException e) {
            System.out.println("Error al guardar el usuario: " + e.getMessage());
            return false;
        }
    }
 
    public Usuario buscarPorNombreUsuario(String nombreUsuario) {
 
        String sql = "SELECT nombre, seccion, nombre_usuario, contrasena_hash, tipo " + "FROM usuarios WHERE nombre_usuario = ?";
 
        try (Connection conexion = BaseDatos.conectar();
             PreparedStatement consulta = conexion.prepareStatement(sql)) {
 
            consulta.setString(1, nombreUsuario);
 
            try (ResultSet fila = consulta.executeQuery()) {
                if (fila.next()) {
                    return construirUsuario(fila);
                }
            }
 
        } catch (SQLException e) {
            System.out.println("Error al buscar el usuario: " + e.getMessage());
        }
 
        return null;
    }
 

    public boolean existe(String nombreUsuario) {
 
        String sql = "SELECT 1 FROM usuarios WHERE nombre_usuario = ?";
 
        try (Connection conexion = BaseDatos.conectar();
             PreparedStatement consulta = conexion.prepareStatement(sql)) {
 
            consulta.setString(1, nombreUsuario);
 
            try (ResultSet fila = consulta.executeQuery()) {
                return fila.next();
            }
 
        } catch (SQLException e) {
            System.out.println("Error al consultar el usuario: " + e.getMessage());
            return false;
        }
    }
 
    private Usuario construirUsuario(ResultSet fila) throws SQLException {
 
        String nombre = fila.getString("nombre");
        String seccion = fila.getString("seccion");
        String nombreUsuario = fila.getString("nombre_usuario");
        String hash = fila.getString("contrasena_hash");
        String tipo = fila.getString("tipo");
 
        if (tipo.equals("Profesor")) {
            return new Profesor(nombre, seccion, nombreUsuario, hash, true);
        }
 
        return new Estudiante(nombre, seccion, nombreUsuario, hash, true);
    }


}