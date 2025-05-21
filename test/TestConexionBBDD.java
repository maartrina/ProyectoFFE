package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConexionBBDD{
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
        String usuario = "martina";
        String clave = "martina";

        try {
            Connection conexion = DriverManager.getConnection(url, usuario, clave);
            System.out.println("Conexión exitosa");
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}