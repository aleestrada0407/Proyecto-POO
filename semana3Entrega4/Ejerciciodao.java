import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejerciciodao {

public boolean insertar(Ejercicio ejercicio) {
 
        try (Connection conexion = BaseDatos.conectar()) {
 
            conexion.setAutoCommit(false);
 
            try {
                int id = guardarEnunciado(conexion, ejercicio.getEnunciado());
 
                guardarLado(conexion, id, "R",
                        ejercicio.getFormulasReactivos(),
                        ejercicio.getCoefSugeridosReactivos());
 
                guardarLado(conexion, id, "P",
                        ejercicio.getFormulasProductos(),
                        ejercicio.getCoefSugeridosProductos());
 
                conexion.commit();
                ejercicio.setId(id);
                return true;
 
            } catch (SQLException e) {
                conexion.rollback();
                System.out.println("Error al guardar el ejercicio: " + e.getMessage());
                return false;
            }
 
        } catch (SQLException e) {
            System.out.println("Error de conexion con la base de datos: " + e.getMessage());
            return false;
        }
    }
 
    private int guardarEnunciado(Connection conexion, String enunciado) throws SQLException {
 
        String sql = "INSERT INTO ejercicios_balanceo (enunciado) VALUES (?)";
 
        try (PreparedStatement consulta = conexion.prepareStatement(sql)) {
            consulta.setString(1, enunciado);
            consulta.executeUpdate();
        }
 
        try (Statement sentencia = conexion.createStatement();
             ResultSet fila = sentencia.executeQuery("SELECT last_insert_rowid()")) {
            fila.next();
            return fila.getInt(1);
        }
    }
 
  
    private void guardarLado(Connection conexion, int ejercicioId, String lado, String[] formulas, int[] coeficientes) throws SQLException {
 
        String sql = "INSERT INTO compuestos (ejercicio_id, lado, posicion, formula, coef_sugerido) "
                   + "VALUES (?, ?, ?, ?, ?)";
 
        try (PreparedStatement consulta = conexion.prepareStatement(sql)) {
 
            for (int i = 0; i < formulas.length; i++) {
                consulta.setInt(1, ejercicioId);
                consulta.setString(2, lado);
                consulta.setInt(3, i);
                consulta.setString(4, formulas[i]);
                consulta.setInt(5, coeficientes[i]);
                consulta.executeUpdate();
            }
        }
    }

}
