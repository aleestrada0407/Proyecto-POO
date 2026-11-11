package login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class ResultadoBalanceodao {
 
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

    public Double calcularDesempeno(String nombreUsuario) throws SQLException {

    String sql = "SELECT COUNT(*) AS total, COALESCE(SUM(r.correcto), 0) AS aciertos " + "FROM resultados r JOIN usuarios u ON u.id = r.usuario_id " + "WHERE u.nombre_usuario = ?";

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
        // Consulta el porcentaje de balanceo del estudiante indicado.
    // Double permite devolver null cuando todavía no hay respuestas.
    // SQLException comunica los errores de la consulta a quien llama al método.
    public Double consultarDesempenoBalanceo(String nombreUsuario)
            throws SQLException {

        // Cuenta las respuestas y suma los aciertos.
        String sql =
                "SELECT COUNT(*) AS total, COALESCE(SUM(r.correcto), 0) AS aciertos "

                // Relaciona los resultados con el estudiante al que pertenecen.
                + "FROM resultados r JOIN usuarios u ON u.id = r.usuario_id "

                // Incluye resultados asociados a ejercicios de balanceo.
                + "JOIN ejercicios_balanceo b ON b.id = r.ejercicio_id "

                // Selecciona únicamente al usuario recibido.
                + "WHERE u.nombre_usuario = ?";

        // Abre la conexión existente; se cerrará al salir del bloque try.
        try (Connection conexion = BaseDatos.conectar();

            // Prepara la consulta; también se cerrará automáticamente.
            PreparedStatement consulta = conexion.prepareStatement(sql)) {

            // Sustituye el signo ? por el nombre de usuario.
            consulta.setString(1, nombreUsuario);

            // Ejecuta la consulta y obtiene sus resultados.
            try (ResultSet fila = consulta.executeQuery()) {

                // Comprueba que se pueda leer la fila con las cantidades.
                if (!fila.next()) {

                    // Informa de un resultado inesperado de la consulta.
                    throw new SQLException(
                            "No se pudo leer el resumen de balanceo.");
                }

                // Obtiene la cantidad de respuestas registradas.
                int total = fila.getInt("total");

                // Comprueba si el estudiante todavía no tiene respuestas.
                if (total == 0) {

                    // Devuelve ausencia de resultados y evita dividir entre cero.
                    return null;
                }

                // Obtiene la cantidad de respuestas correctas.
                int aciertos = fila.getInt("aciertos");

                // Calcula el porcentaje usando decimales.
                return aciertos * 100.0 / total;
            }
        }
    }
}
 