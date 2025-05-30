package vista;

import controlador.SerieControlador;
import controlador.PlataformaControlador;
import modelo.Plataforma;

import javax.swing.*;
import java.awt.*;
import java.util.List;

// Panel para registrar nuevas series
public class PanelRegistroSerie extends JPanel {

    // Campos para rellenar los datos
    private JTextField campoTitulo, campoGenero, campoTemporadas, campoAnio;
    // ComboBox para elegir la plataforma
    private JComboBox<Plataforma> comboPlataformas;
    // Botón para guardar la serie
    private JButton botonGuardar;

    // Controladores que se encargan de la lógica
    private SerieControlador serieControlador;
    private PlataformaControlador plataformaControlador;

    // Constructor del panel
    public PanelRegistroSerie(SerieControlador serieCtrl, PlataformaControlador plataformaCtrl) {
        this.serieControlador = serieCtrl;
        this.plataformaControlador = plataformaCtrl;

        // Diseño en forma de tabla: 6 filas, 2 columnas
        setLayout(new GridLayout(6, 2, 10, 10));

        // Campo para el título
        add(new JLabel("Título de la serie:"));
        campoTitulo = new JTextField();
        add(campoTitulo);

        // Campo para el género
        add(new JLabel("Género:"));
        campoGenero = new JTextField();
        add(campoGenero);

        // Campo para temporadas
        add(new JLabel("Número de temporadas:"));
        campoTemporadas = new JTextField();
        add(campoTemporadas);

        // Campo para año
        add(new JLabel("Año de lanzamiento:"));
        campoAnio = new JTextField();
        add(campoAnio);

        // Combo para seleccionar plataforma
        add(new JLabel("Plataforma:"));
        comboPlataformas = new JComboBox<>();
        cargarPlataformas(); // Cargar plataformas desde la base de datos
        add(comboPlataformas);

        // Botón para registrar
        botonGuardar = new JButton("Registrar serie");
        botonGuardar.addActionListener(e -> registrarSerie());
        add(new JLabel()); // Celda vacía para que se vea mejor
        add(botonGuardar);
    }

    // Método para cargar las plataformas desde el controlador
    private void cargarPlataformas() {
        try {
            List<Plataforma> plataformas = plataformaControlador.obtenerTodas();
            for (Plataforma p : plataformas) {
                comboPlataformas.addItem(p);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar plataformas");
            e.printStackTrace();
        }
    }

    // Método que registra la serie cuando se pulsa el botón
    private void registrarSerie() {
        // Obtener datos del formulario
        String titulo = campoTitulo.getText().trim();
        String genero = campoGenero.getText().trim();
        String temporadasStr = campoTemporadas.getText().trim();
        String anioStr = campoAnio.getText().trim();
        Plataforma seleccionada = (Plataforma) comboPlataformas.getSelectedItem();

        // Validar que no estén vacíos
        if (titulo.isEmpty() || genero.isEmpty() || temporadasStr.isEmpty() || anioStr.isEmpty() || seleccionada == null) {
            JOptionPane.showMessageDialog(this, "⚠️ Todos los campos son obligatorios.");
            return;
        }

        try {
            // Convertir los campos de texto a número
            int temporadas = Integer.parseInt(temporadasStr);
            int anio = Integer.parseInt(anioStr);
            // Llamar al controlador para guardar la serie
            boolean exito = serieControlador.registrarSerie(titulo, genero, temporadas, anio, seleccionada.getId());

            if (exito) {
                JOptionPane.showMessageDialog(this, "✅ Serie registrada correctamente.");
                // Limpiar los campos del formulario
                campoTitulo.setText("");
                campoGenero.setText("");
                campoTemporadas.setText("");
                campoAnio.setText("");
                comboPlataformas.setSelectedIndex(0);
            } else {
                JOptionPane.showMessageDialog(this, "❌ Error al registrar la serie.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "⚠️ Temporadas y Año deben ser números.");
        }
    }
}