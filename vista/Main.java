package vista;

import bd.GestorBaseDatos;
import controlador.PlataformaControlador;
import controlador.SerieControlador;
import dao.PlataformaDAO;
import dao.SerieDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class Main extends JFrame {

    // Constructor de la ventana principal
    public Main() {
        setTitle("Gestión de Series y Plataformas"); // Título de la ventana
        setSize(800, 500); // Tamaño de la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Cierra la app al cerrar la ventana
        setLocationRelativeTo(null); // Centra la ventana

        try {
            // Conectamos con la base de datos
            Connection conn = GestorBaseDatos.obtenerConexion();

            // Creamos los objetos DAO (acceso a base de datos)
            PlataformaDAO plataformaDAO = new PlataformaDAO(conn);
            SerieDAO serieDAO = new SerieDAO(conn);

            // Creamos los controladores, que usan los DAO
            PlataformaControlador plataformaCtrl = new PlataformaControlador(plataformaDAO);
            SerieControlador serieCtrl = new SerieControlador(serieDAO, plataformaDAO);

            // Creamos el panel de pestañas general
            JTabbedPane pestañasPrincipales = new JTabbedPane();

            // Subpestañas para las plataformas
            JTabbedPane pestañasPlataformas = new JTabbedPane();
            pestañasPlataformas.addTab("Registrar", new PanelRegistroPlataforma(plataformaCtrl));
            pestañasPlataformas.addTab("Editar", new PanelEditarPlataforma(plataformaCtrl));
            pestañasPlataformas.addTab("Eliminar", new PanelEliminarPlataforma(plataformaCtrl));
            pestañasPrincipales.addTab("Plataformas", pestañasPlataformas);

            // Subpestañas para las series
            JTabbedPane pestañasSeries = new JTabbedPane();
            pestañasSeries.addTab("Registrar", new PanelRegistroSerie(serieCtrl, plataformaCtrl));
            pestañasSeries.addTab("Editar", new PanelEditarSerie(serieCtrl));
            pestañasSeries.addTab("Eliminar", new PanelEliminarSerie(serieCtrl));
            pestañasSeries.addTab("Consultar", new PanelConsulta(serieCtrl));
            pestañasPrincipales.addTab("Series", pestañasSeries);

            // Añadimos todo al panel principal
            add(pestañasPrincipales, BorderLayout.CENTER);

        } catch (SQLException e) {
            // Si hay error de conexión, mostramos mensaje
            JOptionPane.showMessageDialog(this, "❌ Error de conexión con la base de datos.");
            e.printStackTrace();
        }
    }

    // Método para aplicar estilo a toda la interfaz
    public static void aplicarEstiloGlobal() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            UIManager.put("Button.font", new Font("SansSerif", Font.BOLD, 14));
            UIManager.put("Button.background", new Color(66, 133, 244));
            UIManager.put("Button.foreground", Color.WHITE);
            UIManager.put("Button.focus", new Color(0, 0, 0, 0));
            UIManager.put("Button.border", BorderFactory.createEmptyBorder(10, 20, 10, 20));
            UIManager.put("Label.font", new Font("SansSerif", Font.PLAIN, 14));
            UIManager.put("TextField.font", new Font("SansSerif", Font.PLAIN, 14));
            UIManager.put("TextField.border", BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            UIManager.put("TabbedPane.font", new Font("SansSerif", Font.BOLD, 13));
            UIManager.put("TabbedPane.selected", new Color(230, 230, 250));
            UIManager.put("Panel.background", Color.WHITE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método principal que lanza la aplicación
    public static void main(String[] args) {
        aplicarEstiloGlobal(); // Aplicamos el diseño
        SwingUtilities.invokeLater(() -> new Main().setVisible(true)); // Mostramos la ventana
    }
}