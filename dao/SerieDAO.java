package dao;

import modelo.Serie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SerieDAO {

    private Connection conn;

    // Constructor que recibe la conexión de la base de datos
    public SerieDAO(Connection conn) {
        this.conn = conn;
    }

    // Constructor vacío, útil para hacer pruebas
    public SerieDAO() {
    }

    // Método para insertar una nueva serie en la base de datos
    public void insertar(Serie serie) throws SQLException {
        String sql = "INSERT INTO series (titulo, genero, temporadas, anio_lanzamiento, id_plataforma) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Establecemos los valores en la consulta
            stmt.setString(1, serie.getTitulo());
            stmt.setString(2, serie.getGenero());
            stmt.setInt(3, serie.getTemporadas());
            stmt.setInt(4, serie.getAnioLanzamiento());
            stmt.setInt(5, serie.getIdPlataforma());
            stmt.executeUpdate(); // Ejecutamos la consulta
        }
    }

    // Método para buscar series por filtros (título y género)
    public List<Serie> buscarPorFiltros(String titulo, String genero) throws SQLException {
        List<Serie> lista = new ArrayList<>();

        // Creamos la consulta base
        StringBuilder sql = new StringBuilder(
            "SELECT s.*, p.nombre AS nombre_plataforma, p.pais_origen " +
            "FROM series s JOIN plataformas p ON s.id_plataforma = p.id WHERE 1=1"
        );

        // Si se escribe título, lo añadimos como filtro
        if (!titulo.isEmpty()) sql.append(" AND LOWER(s.titulo) LIKE LOWER(?)");
        // Si se escribe género, también lo añadimos
        if (!genero.isEmpty()) sql.append(" AND LOWER(s.genero) LIKE LOWER(?)");

        try (PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            int index = 1;
            // Añadimos los parámetros en orden según existan
            if (!titulo.isEmpty()) stmt.setString(index++, "%" + titulo + "%");
            if (!genero.isEmpty()) stmt.setString(index++, "%" + genero + "%");

            ResultSet rs = stmt.executeQuery();

            // Recorremos el resultado y lo añadimos a la lista
            while (rs.next()) {
                Serie serie = new Serie(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("genero"),
                    rs.getInt("temporadas"),
                    rs.getInt("anio_lanzamiento"),
                    rs.getInt("id_plataforma")
                );
                // Guardamos el nombre de la plataforma para mostrarlo luego
                String nombre = rs.getString("nombre_plataforma");
                String pais = rs.getString("pais_origen");
                serie.setNombrePlataforma(nombre + " (" + pais + ")");
                lista.add(serie);
            }
        }

        return lista; // Devolvemos la lista de series filtradas
    }

    // Método para actualizar los datos de una serie existente
    public void actualizar(Serie serie) throws SQLException {
        String sql = "UPDATE series SET titulo = ?, genero = ?, temporadas = ?, anio_lanzamiento = ?, id_plataforma = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, serie.getTitulo());
            stmt.setString(2, serie.getGenero());
            stmt.setInt(3, serie.getTemporadas());
            stmt.setInt(4, serie.getAnioLanzamiento());
            stmt.setInt(5, serie.getIdPlataforma());
            stmt.setInt(6, serie.getId()); // Especificamos qué serie se actualiza
            stmt.executeUpdate();
        }
    }

    // Método para eliminar una serie por su ID
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM series WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id); // ID de la serie a eliminar
            stmt.executeUpdate();
        }
    }
}