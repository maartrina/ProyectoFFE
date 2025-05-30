package test;

import static org.junit.jupiter.api.Assertions.*;

import controlador.SerieControlador;
import dao.PlataformaDAO;
import dao.SerieDAO;
import modelo.Serie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SerieControladorTest {

    private SerieControlador controlador;

    @BeforeEach
    public void setUp() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XEPDB1", "martina", "martina");
        SerieDAO serieDAO = new SerieDAO(conn);
        PlataformaDAO plataformaDAO = new PlataformaDAO(conn);
        controlador = new SerieControlador(serieDAO, plataformaDAO);
    }

    @Test
    public void testRegistrarSerie() {
        boolean resultado = controlador.registrarSerie("Test Series", "Drama", 3, 2020, 1); // Asegúrate de que el ID plataforma 1 exista
        assertTrue(resultado, "Debe registrar correctamente una serie");
    }
}