package controlador;

import dao.PlataformaDAO;
import modelo.Plataforma;

import java.sql.SQLException;

public class PlataformaControlador {

    // Atributo para acceder a la base de datos de plataformas
    private PlataformaDAO dao;

    // Constructor: se le pasa el DAO que permite acceder a los datos
    public PlataformaControlador(PlataformaDAO dao) {
        this.dao = dao;
    }

    // Método para registrar una nueva plataforma
    public boolean registrarPlataforma(String nombre, String pais) {
        try {
            // Creamos un objeto Plataforma con los datos que llegan de la vista
            Plataforma plataforma = new Plataforma(nombre, pais);

            // Llamamos al DAO para guardarla en la base de datos
            dao.insertar(plataforma);
            return true; // Si todo va bien, devolvemos true
        } catch (SQLException e) {
            e.printStackTrace(); // Mostramos el error por consola si hay fallo
            return false;
        }
    }

    // Método para eliminar una plataforma (solo si no tiene series asociadas)
    public boolean eliminarPlataforma(int id) {
        try {
            // Si tiene series asociadas, no se puede eliminar
            if (dao.tieneSeriesAsociadas(id)) {
                return false;
            }

            // Si no tiene, la eliminamos
            dao.eliminar(id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace(); // Mostramos el error si falla
            return false;
        }
    }

    // Método para obtener todas las plataformas registradas
    public java.util.List<Plataforma> obtenerTodas() throws SQLException {
        return dao.obtenerTodas();
    }

    // Método para actualizar los datos de una plataforma existente
    public void actualizarPlataforma(Plataforma plataforma) throws SQLException {
        dao.actualizar(plataforma);
    }
}