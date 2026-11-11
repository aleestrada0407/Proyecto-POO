package login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ResultadoNomenclaturadao {

    public boolean insertar(String nombreUsuario, String formula, int modo, String respuesta, boolean correcto) {

        String sql = "INSERT INTO resultados_nomenclatura " + "(usuario_id, formula, modo, respuesta, correcto) " + "VALUES ((SELECT id FROM usuarios WHERE nombre_usuario = ?), ?, ?, ?, ?)";

        try (Connection conexion = BaseDatos.conectar(); PreparedStatement consulta = conexion.prepareStatement(sql)) {

            consulta.setString(1, nombreUsuario);
            consulta.setString(2, formula);
            consulta.setInt(3, modo);
            consulta.setString(4, respuesta);
            consulta.setInt(5, correcto ? 1 : 0);
            consulta.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al guardar el resultado de nomenclatura: " + e.getMessage());
            return false;
        }
    }

    
    public Double calcularDesempeno(String nombreUsuario) throws SQLException {

        String sql = "SELECT COUNT(*) AS total, COALESCE(SUM(n.correcto), 0) AS aciertos " + "FROM resultados_nomenclatura n JOIN usuarios u ON u.id = n.usuario_id " + "WHERE u.nombre_usuario = ?";

        try (Connection conexion = BaseDatos.conectar(); PreparedStatement consulta = conexion.prepareStatement(sql)) {

            consulta.setString(1, nombreUsuario);

            try (ResultSet fila = consulta.executeQuery()) {
                fila.next();
                int total = fila.getInt("total");
                if (total == 0) {
                    return null;
                }
                return fila.getInt("aciertos") * 100.0 / total;
            }
        }
    }
}