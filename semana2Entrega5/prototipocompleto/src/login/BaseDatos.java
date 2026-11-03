package login;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
 
public class BaseDatos {

    private static final String archivo_bd = "quimica.db";
    private static final String URL = "jdbc:sqlite:" + archivo_bd;
    private static final String archivo_esquema = "esquema.sql";
 
    public static Connection conectar() throws SQLException {
 
        Connection conexion = DriverManager.getConnection(URL);
 
        try (Statement sentencia = conexion.createStatement()) {
            sentencia.execute("PRAGMA foreign_keys = ON");
        }
 
        return conexion;
    }
 
    public static void inicializar() {

        if (!Files.exists(Path.of(archivo_esquema)) || !Files.exists(Path.of(archivo_bd))) {
            System.out.println("No se encontró " + archivo_esquema + " o " + archivo_bd + " en la carpeta actual: " + Path.of("").toAbsolutePath());
            System.out.println("Ejecuta el programa desde la carpeta raíz del proyecto.");
            System.exit(1);
        }   
 
        try (Connection conexion = conectar();
             Statement sentencia = conexion.createStatement()) {
 
            String esquema = Files.readString(Path.of(archivo_esquema));
 
            for (String instruccion : esquema.split(";")) {
                if (!instruccion.isBlank()) {
                    sentencia.execute(instruccion);
                }
            }
 
            System.out.println("Base de datos lista.");
 
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo " + archivo_esquema + ".");
        } catch (SQLException e) {
            System.out.println("Error al crear las tablas: " + e.getMessage());
        }
    }
}