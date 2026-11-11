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

    // Calcula el porcentaje de nomenclatura del estudiante indicado.
    // Lo usa GestorResultados.desempenoNomenclatura().
    // Antes tenía su propia consulta SQL; ahora llama a consultarDesempenoNomenclatura()
    // para que exista un único cálculo y los dos siempre devuelvan el mismo porcentaje.
    public Double calcularDesempeno(String nombreUsuario) throws SQLException {

        // Reutiliza la consulta completa: devuelve null si no hay respuestas
        // y lanza SQLException si la consulta falla.
        return consultarDesempenoNomenclatura(nombreUsuario);
    }

    // Consulta el porcentaje de nomenclatura (flashcards) del estudiante indicado.
    // Double permite devolver null cuando todavía no hay respuestas.
    // SQLException comunica los errores de la consulta a quien llama al método.
    public Double consultarDesempenoNomenclatura(String nombreUsuario)
            throws SQLException {

        // Obtiene cuántas flashcards acertó y cuántas respondió en total.
        // La consulta SQL está en consultarConteoNomenclatura() para no repetirla.
        int[] conteo = consultarConteoNomenclatura(nombreUsuario);

        // Posición 0: respuestas correctas. Posición 1: respuestas registradas.
        int aciertos = conteo[0];
        int total = conteo[1];

        // Comprueba si el estudiante todavía no tiene respuestas.
        if (total == 0) {

            // Devuelve ausencia de resultados y evita dividir entre cero.
            return null;
        }

        // Calcula el porcentaje usando decimales.
        return aciertos * 100.0 / total;
    }

    // Cuenta las flashcards de nomenclatura que el estudiante respondió y cuántas acertó.
    // Devuelve un arreglo de dos números: {aciertos, total}.
    // Si el estudiante no tiene respuestas devuelve {0, 0}.
    // SQLException comunica los errores de la consulta a quien llama al método.
    public int[] consultarConteoNomenclatura(String nombreUsuario)
            throws SQLException {

        // Cuenta las respuestas y suma los aciertos.
        String sql =
                "SELECT COUNT(*) AS total, COALESCE(SUM(n.correcto), 0) AS aciertos "

                // Relaciona los resultados de nomenclatura con el estudiante.
                + "FROM resultados_nomenclatura n JOIN usuarios u ON u.id = n.usuario_id "

                // Selecciona únicamente al usuario recibido.
                + "WHERE u.nombre_usuario = ?";

        // Abre la conexión; se cerrará al salir del bloque try.
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
                            "No se pudo leer el resumen de nomenclatura.");
                }

                // Obtiene la cantidad de respuestas correctas.
                int aciertos = fila.getInt("aciertos");

                // Obtiene la cantidad de respuestas registradas.
                int total = fila.getInt("total");

                // Devuelve las dos cantidades juntas: primero aciertos, luego total.
                return new int[] {aciertos, total};
            }
        }
    }
}
