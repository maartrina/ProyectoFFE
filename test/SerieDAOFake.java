package test;

import dao.SerieDAO;
import modelo.Serie;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SerieDAOFake extends SerieDAO {

    public List<Serie> series = new ArrayList<>();

    public SerieDAOFake() {
        // no llama al super() de SerieDAO, porque no queremos conexión real
    }

    @Override
    public void insertar(Serie serie) throws SQLException {
        series.add(serie);
    }

    @Override
    public void actualizar(Serie serie) throws SQLException {
        for (int i = 0; i < series.size(); i++) {
            if (series.get(i).getId() == serie.getId()) {
                series.set(i, serie);
                return;
            }
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        series.removeIf(s -> s.getId() == id);
    }

    @Override
    public List<Serie> buscarPorFiltros(String titulo, String genero) throws SQLException {
        List<Serie> resultado = new ArrayList<>();
        for (Serie s : series) {
            if ((titulo == null || s.getTitulo().equalsIgnoreCase(titulo)) &&
                (genero == null || s.getGenero().equalsIgnoreCase(genero))) {
                resultado.add(s);
            }
        }
        return resultado;
    }
}
