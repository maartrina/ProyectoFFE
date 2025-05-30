package controlador;

import dao.SerieDAO;
import dao.PlataformaDAO;
import modelo.Plataforma;
import modelo.Serie;

import java.sql.SQLException;
import java.util.List;

public class SerieControlador {

    // Atributos para acceder a las clases DAO
    private SerieDAO serieDAO;
    private PlataformaDAO plataformaDAO;

    // Constructor que recibe los DAOs necesarios
    public SerieControlador(SerieDAO serieDAO, PlataformaDAO plataformaDAO) {
        this.serieDAO = serieDAO;
        this.plataformaDAO = plataformaDAO;
    }

    // Método para registrar una nueva serie
    public boolean registrarSerie(String titulo, String genero, int temporadas, int anio, int idPlataforma) {
        try {
            // Creamos una nueva serie con los datos recibidos
            Serie serie = new Serie(titulo, genero, temporadas, anio, idPlataforma);
            // Insertamos la serie usando el DAO
            serieDAO.insertar(serie);
            return true; // Si todo sale bien, devolvemos true
        } catch (SQLException e) {
            e.printStackTrace(); // Mostramos el error si falla
            return false;
        }
    }

    // Método para editar una serie existente
    public boolean editarSerie(int id, String titulo, String genero, int temporadas, int anio, int idPlataforma) {
        try {
            // Creamos una serie con todos los datos (incluido el ID)
            Serie serie = new Serie(id, titulo, genero, temporadas, anio, idPlataforma);
            // Llamamos al método de actualizar del DAO
            serieDAO.actualizar(serie);
            return true;
        } catch (SQLException e) {
            e.printStackTrace(); // Mostramos el error si falla
            return false;
        }
    }

    // Método para eliminar una serie según su ID
    public boolean eliminarSerie(int id) {
        try {
            serieDAO.eliminar(id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Este método sirve para cargar las plataformas (por ejemplo, en un JComboBox)
    public List<Plataforma> obtenerTodasLasPlataformas() throws SQLException {
        return plataformaDAO.obtenerTodas();
    }

    // Método para buscar series según título y género (consulta con filtros)
    public List<Serie> buscarSeries(String titulo, String genero) throws SQLException {
        return serieDAO.buscarPorFiltros(titulo, genero);
    }
}
