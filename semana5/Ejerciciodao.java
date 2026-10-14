import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    public boolean existePorEnunciado(String enunciado) {

    String sql = "SELECT 1 FROM ejercicios_balanceo WHERE enunciado = ?";

    try (Connection conexion = BaseDatos.conectar();
         PreparedStatement consulta = conexion.prepareStatement(sql)) {

        consulta.setString(1, enunciado);

        try (ResultSet fila = consulta.executeQuery()) {
            return fila.next();
        }

    } catch (SQLException e) {
        System.out.println("Error al consultar el ejercicio: " + e.getMessage());
        return true;
    }
}

public ArrayList<Ejercicio> listarTodos() {

    String sql = "SELECT e.id, e.enunciado, c.lado, c.formula, c.coef_sugerido "+ "FROM ejercicios_balanceo e " + "JOIN compuestos c ON c.ejercicio_id = e.id " + "ORDER BY e.id, c.lado DESC, c.posicion";

    ArrayList<Ejercicio> ejercicios = new ArrayList<>();

    try (Connection conexion = BaseDatos.conectar();
         Statement sentencia = conexion.createStatement();
         ResultSet fila = sentencia.executeQuery(sql)) {

        int idActual = -1;
        String enunciado = "";
        List<String> formulasR = new ArrayList<>();
        List<String> formulasP = new ArrayList<>();
        List<Integer> coefR = new ArrayList<>();
        List<Integer> coefP = new ArrayList<>();

        while (fila.next()) {

            int id = fila.getInt("id");

            if (id != idActual) {
                if (idActual != -1) { ejercicios.add(armarEjercicio(idActual, enunciado, formulasR, formulasP, coefR, coefP));
                }
                idActual = id;
                enunciado = fila.getString("enunciado");
                formulasR = new ArrayList<>();
                formulasP = new ArrayList<>();
                coefR = new ArrayList<>();
                coefP = new ArrayList<>();
            }

            if (fila.getString("lado").equals("R")) {
                formulasR.add(fila.getString("formula"));
                coefR.add(fila.getInt("coef_sugerido"));
            } else {
                formulasP.add(fila.getString("formula"));
                coefP.add(fila.getInt("coef_sugerido"));
            }
        }

        if (idActual != -1) {
            ejercicios.add(armarEjercicio(idActual, enunciado, formulasR, formulasP, coefR, coefP));
        }

    } catch (SQLException e) {
        System.out.println("Error al leer los ejercicios: " + e.getMessage());
        return new ArrayList<>();
    }

    return ejercicios;
}

private Ejercicio armarEjercicio(int id, String enunciado,List<String> formulasR, List<String> formulasP,List<Integer> coefR, List<Integer> coefP) {

    Ejercicio ejercicio = new Ejercicio(enunciado);
    ejercicio.setId(id);
    ejercicio.llenarEcuacion(formulasR.toArray(new String[0]),formulasP.toArray(new String[0]), aArreglo(coefR),aArreglo(coefP));
    return ejercicio;
}

private int[] aArreglo(List<Integer> lista) {

    int[] arreglo = new int[lista.size()];

    for (int i = 0; i < lista.size(); i++) {
        arreglo[i] = lista.get(i);
    }

    return arreglo;
}



}
