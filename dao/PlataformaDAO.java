package dao;

import modelo.Plataforma;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class PlataformaDAO {

    private Connection conn;

    // Constructor principal que recibe la conexión con la base de datos
    public PlataformaDAO(Connection conn) {
        this.conn = conn;
    }

    // Constructor vacío (se puede usar para pruebas)
    public PlataformaDAO() {
    }

    // Método para insertar una nueva plataforma en la base de datos
    public void insertar(Plataforma plataforma) throws SQLException {
        String sql = "INSERT INTO plataformas (nombre, pais_origen) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, plataforma.getNombre());      // Primer valor: nombre
            stmt.setString(2, plataforma.getPaisOrigen());  // Segundo valor: país
            stmt.executeUpdate(); // Ejecuta el INSERT
        }
    }

    // Método para actualizar los datos de una plataforma
    public void actualizar(Plataforma plataforma) throws SQLException {
        String sql = "UPDATE plataformas SET nombre = ?, pais_origen = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, plataforma.getNombre());      // Nuevo nombre
            stmt.setString(2, plataforma.getPaisOrigen());  // Nuevo país
            stmt.setInt(3, plataforma.getId());             // ID a actualizar
            stmt.executeUpdate(); // Ejecuta el UPDATE
        }
    }

    // Método que comprueba si una plataforma tiene series asociadas
    public boolean tieneSeriesAsociadas(int idPlataforma) throws SQLException {
        String sql = "SELECT COUNT(*) FROM series WHERE id_plataforma = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPlataforma); // ID de la plataforma
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Si hay alguna serie asociada
            }
        }
        return false; // Si no hay resultados
    }

    // Método para eliminar una plataforma por su ID
    public void eliminar(int idPlataforma) throws SQLException {
        String sql = "DELETE FROM plataformas WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPlataforma); // ID que queremos borrar
            stmt.executeUpdate(); // Ejecuta el DELETE
        }
    }

    // Método que devuelve todas las plataformas guardadas
    public List<Plataforma> obtenerTodas() throws SQLException {
        List<Plataforma> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, pais_origen FROM plataformas";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // Creamos una nueva Plataforma con los datos del ResultSet
                Plataforma p = new Plataforma(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("pais_origen")
                );
                lista.add(p); // La añadimos a la lista
            }
        }

        return lista; // Devolvemos la lista con todas las plataformas
    }
}