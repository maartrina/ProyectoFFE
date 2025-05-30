package vista;

import controlador.SerieControlador;
import modelo.Plataforma;
import modelo.Serie;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class PanelEditarSerie extends JPanel {

    // Componentes que vamos a usar
    private JComboBox<Serie> comboSeries; // Para seleccionar la serie a editar
    private JTextField campoTitulo, campoGenero, campoTemporadas, campoAnio; // Para editar datos
    private JComboBox<Plataforma> comboPlataformas; // Para elegir plataforma
    private JButton botonActualizar; // Botón para guardar los cambios

    public PanelEditarSerie(SerieControlador controlador) {
        // Diseño del panel con 7 filas y 2 columnas
        setLayout(new GridLayout(7, 2, 10, 10));

        // Creamos todos los campos
        comboSeries = new JComboBox<>();
        campoTitulo = new JTextField();
        campoGenero = new JTextField();
        campoTemporadas = new JTextField();
        campoAnio = new JTextField();
        comboPlataformas = new JComboBox<>();
        botonActualizar = new JButton("Actualizar");

        // Añadimos los componentes al panel
        add(new JLabel("Selecciona serie:"));
        add(comboSeries);
        add(new JLabel("Título:"));
        add(campoTitulo);
        add(new JLabel("Género:"));
        add(campoGenero);
        add(new JLabel("Temporadas:"));
        add(campoTemporadas);
        add(new JLabel("Año de lanzamiento:"));
        add(campoAnio);
        add(new JLabel("Plataforma:"));
        add(comboPlataformas);
        add(new JLabel()); // espacio vacío
        add(botonActualizar);

        // Cargamos las series y plataformas al iniciar
        cargarSeries(controlador);
        cargarPlataformas(controlador);

        // Cuando seleccionamos una serie, se rellenan los campos
        comboSeries.addActionListener(e -> {
            Serie seleccionada = (Serie) comboSeries.getSelectedItem();
            if (seleccionada != null) {
                campoTitulo.setText(seleccionada.getTitulo());
                campoGenero.setText(seleccionada.getGenero());
                campoTemporadas.setText(String.valueOf(seleccionada.getTemporadas()));
                campoAnio.setText(String.valueOf(seleccionada.getAnioLanzamiento()));

                // Seleccionamos en el combo la plataforma correcta
                for (int i = 0; i < comboPlataformas.getItemCount(); i++) {
                    Plataforma p = comboPlataformas.getItemAt(i);
                    if (p.getId() == seleccionada.getIdPlataforma()) {
                        comboPlataformas.setSelectedIndex(i);
                        break;
                    }
                }
            }
        });

        // Al pulsar el botón, actualizamos la serie con los nuevos datos
        botonActualizar.addActionListener(e -> {
            Serie seleccionada = (Serie) comboSeries.getSelectedItem();
            Plataforma plataforma = (Plataforma) comboPlataformas.getSelectedItem();

            if (seleccionada == null || plataforma == null) return;

            // Cogemos los valores de los campos
            String titulo = campoTitulo.getText().trim();
            String genero = campoGenero.getText().trim();
            int temporadas = Integer.parseInt(campoTemporadas.getText().trim());
            int anio = Integer.parseInt(campoAnio.getText().trim());

            // Llamamos al controlador para editar la serie
            boolean exito = controlador.editarSerie(
                    seleccionada.getId(), titulo, genero, temporadas, anio, plataforma.getId());

            // Mostramos mensaje según el resultado
            if (exito) {
                JOptionPane.showMessageDialog(this, "✅ Serie actualizada.");
                cargarSeries(controlador); // Recargamos la lista
            } else {
                JOptionPane.showMessageDialog(this, "❌ Error al actualizar la serie.");
            }
        });
    }

    // Método para cargar todas las series al ComboBox
    private void cargarSeries(SerieControlador controlador) {
        comboSeries.removeAllItems();
        try {
            List<Serie> series = controlador.buscarSeries("", "");
            for (Serie s : series) {
                comboSeries.addItem(s);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar series.");
        }
    }

    // Método para cargar todas las plataformas al ComboBox
    private void cargarPlataformas(SerieControlador controlador) {
        comboPlataformas.removeAllItems();
        try {
            List<Plataforma> plataformas = controlador.obtenerTodasLasPlataformas();
            for (Plataforma p : plataformas) {
                comboPlataformas.addItem(p);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar plataformas.");
        }
    }
}