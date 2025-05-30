package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GestorBaseDatos {

    private static final String URL = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
    private static final String USUARIO = "martina";
    private static final String CLAVE = "martina";

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }
}