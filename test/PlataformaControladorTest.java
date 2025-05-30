package test;

import controlador.PlataformaControlador;
import dao.PlataformaDAO;
import modelo.Plataforma;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlataformaControladorTest {

    private PlataformaControlador controlador;
    private PlataformaDAO daoFalso;

    @BeforeEach
    public void setUp() {
        daoFalso = new PlataformaDAO() {
            List<Plataforma> lista = new ArrayList<>();

            @Override
            public void insertar(Plataforma plataforma) {
                lista.add(plataforma);
            }

            @Override
            public void eliminar(int id) {
                lista.removeIf(p -> p.getId() == id);
            }

            @Override
            public boolean tieneSeriesAsociadas(int id) {
                return false;
            }

            @Override
            public List<Plataforma> obtenerTodas() {
                return lista;
            }

            @Override
            public void actualizar(Plataforma plataforma) {
                // Actualizar no hace nada aquí, solo prueba que no da error
            }
        };

        controlador = new PlataformaControlador(daoFalso);
    }

    @Test
    public void testRegistrarPlataforma() {
        boolean resultado = controlador.registrarPlataforma("Netflix", "Estados Unidos");
        assertTrue(resultado);
    }

    @Test
    public void testEliminarPlataformaSinSeries() {
        Plataforma p = new Plataforma(1, "Disney+", "EE.UU");
        try {
            daoFalso.insertar(p);
        } catch (SQLException e) {
            fail("Error al insertar");
        }

        boolean resultado = controlador.eliminarPlataforma(1);
        assertTrue(resultado);
    }

    @Test
    public void testObtenerTodas() throws SQLException {
        controlador.registrarPlataforma("HBO", "EEUU");
        List<Plataforma> plataformas = controlador.obtenerTodas();
        assertEquals(1, plataformas.size());
    }
}