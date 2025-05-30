package test;

import dao.PlataformaDAO;
import modelo.Plataforma;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class PlataformaDAOFake extends PlataformaDAO {

    @Override
    public List<Plataforma> obtenerTodas() throws SQLException {
        return Arrays.asList(
            new Plataforma(1, "Netflix", "EEUU"),
            new Plataforma(2, "Amazon", "EEUU")
        );
    }
}