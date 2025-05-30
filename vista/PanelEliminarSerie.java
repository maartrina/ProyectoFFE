package vista;

import controlador.SerieControlador;
import modelo.Serie;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class PanelEliminarSerie extends JPanel {

    // ComboBox donde se muestran las series disponibles
    private JComboBox<Serie> comboSeries;
    // Botón para eliminar la serie seleccionada
    private JButton botonEliminar;
    // Controlador que se encarga de las acciones de series
    private SerieControlador controlador;

    // Constructor del panel
    public PanelEliminarSerie(SerieControlador controlador) {
        this.controlador = controlador;

        // Diseño con 3 filas, 1 columna y separación de 10px
        setLayout(new GridLayout(3, 1, 10, 10));

        // Inicializamos componentes
        comboSeries = new JComboBox<>();
        botonEliminar = new JButton("Eliminar Serie");

        // Añadimos los componentes al panel
        add(new JLabel("Selecciona la serie a eliminar:"));
        add(comboSeries);
        add(botonEliminar);

        // Cargamos las series desde la base de datos
        cargarSeries();

        // Acción del botón
        botonEliminar.addActionListener(e -> {
            // Obtener la serie que el usuario seleccionó
            Serie seleccionada = (Serie) comboSeries.getSelectedItem();
            if (seleccionada == null) return;

            // Preguntar al usuario si realmente quiere eliminar
            int confirm = JOptionPane.showConfirmDialog(this,
                    "¿Seguro que deseas eliminar la serie \"" + seleccionada.getTitulo() + "\"?",
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

            // Si dice que sí, se elimina
            if (confirm == JOptionPane.YES_OPTION) {
                boolean exito = controlador.eliminarSerie(seleccionada.getId());
                if (exito) {
                    JOptionPane.showMessageDialog(this, "✅ Serie eliminada.");
                    cargarSeries(); // Recargar combo después de eliminar
                } else {
                    JOptionPane.showMessageDialog(this, "❌ Error al eliminar la serie.");
                }
            }
        });
    }

    // Método que carga las series al ComboBox
    private void cargarSeries() {
        comboSeries.removeAllItems(); // Limpiar por si ya había datos antes
        try {
            // Buscar todas las series sin aplicar filtros
            List<Serie> series = controlador.buscarSeries("", "");
            for (Serie s : series) {
                comboSeries.addItem(s);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "❌ Error al cargar series.");
        }
    }
}