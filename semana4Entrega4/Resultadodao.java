import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class Resultadodao {
 
    public boolean insertar(Resultado resultado) {
 
        if (resultado.getEjercicio().getId() == 0) {
            System.out.println("Error al guardar el resultado: el ejercicio no esta guardado en la base de datos.");
            return false;
        }
 
        try (Connection conexion = BaseDatos.conectar()) {
 
            conexion.setAutoCommit(false);
 
            try {
                int id = guardarResultado(conexion, resultado);
 
                RespuestaEjercicio respuesta = resultado.getRespuesta();
                guardarCoeficientes(conexion, id, "R", respuesta.getCoefReactivos());
                guardarCoeficientes(conexion, id, "P", respuesta.getCoefProductos());
 
                conexion.commit();
                return true;
 
            } catch (SQLException e) {
                conexion.rollback();
                System.out.println("Error al guardar el resultado: " + e.getMessage());
                return false;
            }
 
        } catch (SQLException e) {
            System.out.println("Error de conexion con la base de datos: " + e.getMessage());
            return false;
        }
    }
 
    private int guardarResultado(Connection conexion, Resultado resultado) throws SQLException {
 
        String sql = "INSERT INTO resultados (usuario_id, ejercicio_id, correcto) " + "VALUES ((SELECT id FROM usuarios WHERE nombre_usuario = ?), ?, ?)";
 
        try (PreparedStatement consulta = conexion.prepareStatement(sql)) {
 
            consulta.setString(1, resultado.getEstudiante());
            consulta.setInt(2, resultado.getEjercicio().getId());
 
            if (resultado.isCorrecto()) {
                consulta.setInt(3, 1);
            } else {
                consulta.setInt(3, 0);
            }
 
            consulta.executeUpdate();
        }
 
        try (Statement sentencia = conexion.createStatement();
             ResultSet fila = sentencia.executeQuery("SELECT last_insert_rowid()")) {
            fila.next();
            return fila.getInt(1);
        }
    }
 
    
    private void guardarCoeficientes(Connection conexion, int resultadoId, String lado, int[] coeficientes) throws SQLException {
 
        String sql = "INSERT INTO respuestas_coef (resultado_id, lado, posicion, coeficiente) " + "VALUES (?, ?, ?, ?)";
 
        try (PreparedStatement consulta = conexion.prepareStatement(sql)) {
 
            for (int i = 0; i < coeficientes.length; i++) {
                consulta.setInt(1, resultadoId);
                consulta.setString(2, lado);
                consulta.setInt(3, i);
                consulta.setInt(4, coeficientes[i]);
                consulta.executeUpdate();
            }
        }
    }

    public double calcularDesempeno(String nombreUsuario) {

        String sql = "SELECT COUNT(*) AS total, SUM(r.correcto) AS aciertos "+ "FROM resultados r JOIN usuarios u ON u.id = r.usuario_id "+ "WHERE u.nombre_usuario = ?";

        try (Connection conexion = BaseDatos.conectar();
             PreparedStatement consulta = conexion.prepareStatement(sql)) {

            consulta.setString(1, nombreUsuario);

            try (ResultSet fila = consulta.executeQuery()) {

                if (fila.next() && fila.getInt("total") > 0) {
                    return (double) fila.getInt("aciertos") / fila.getInt("total") * 100;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al calcular el desempeno: " + e.getMessage());
        }

        return 0.0;
    }
}
 