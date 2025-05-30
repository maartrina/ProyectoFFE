package vista;

import controlador.SerieControlador;
import modelo.Serie;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class PanelConsulta extends JPanel {

    // Campos de texto y componentes de la interfaz
    private JTextField campoTitulo;
    private JTextField campoGenero;
    private JButton botonBuscar;
    private JTable tablaResultados;
    private SerieControlador controlador;

    // Constructor
    public PanelConsulta(SerieControlador controlador) {
        this.controlador = controlador;
        setLayout(new BorderLayout()); // Usamos layout de borde

        // Panel para los filtros (arriba)
        JPanel panelFiltros = new JPanel(new GridBagLayout());
        panelFiltros.setBorder(BorderFactory.createTitledBorder("Buscar series")); // Bordecito con título

        // Usamos GridBag para colocar bien los campos
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Márgenes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Etiqueta y campo de título
        gbc.gridx = 0; gbc.gridy = 0;
        panelFiltros.add(new JLabel("Título:"), gbc);

        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1;
        campoTitulo = new JTextField();
        panelFiltros.add(campoTitulo, gbc);

        // Etiqueta y campo de género
        gbc.gridx = 2; gbc.gridy = 0; gbc.weightx = 0;
        panelFiltros.add(new JLabel("Género:"), gbc);

        gbc.gridx = 3; gbc.gridy = 0; gbc.weightx = 1;
        campoGenero = new JTextField();
        panelFiltros.add(campoGenero, gbc);

        // Botón para buscar (en segunda fila)
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        botonBuscar = new JButton("Buscar");
        panelFiltros.add(botonBuscar, gbc);

        // Añadimos los filtros en la parte de arriba del panel
        add(panelFiltros, BorderLayout.NORTH);

        // Tabla para mostrar los resultados de la búsqueda
        tablaResultados = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaResultados);
        add(scrollPane, BorderLayout.CENTER); // Va en el centro del panel

        // Acción que ocurre al hacer clic en el botón buscar
        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscar(); // Llama al método que hace la consulta
            }
        });
    }

    // Método que hace la búsqueda de series con los filtros
    private void buscar() {
        try {
            // Obtenemos los textos de los campos
            String titulo = campoTitulo.getText().trim();
            String genero = campoGenero.getText().trim();

            // Llamamos al controlador para buscar
            List<Serie> resultados = controlador.buscarSeries(titulo, genero);

            // Creamos el modelo de la tabla
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("Título");
            modelo.addColumn("Género");
            modelo.addColumn("Temporadas");
            modelo.addColumn("Año");
            modelo.addColumn("Plataforma");

            // Añadimos los resultados al modelo
            for (Serie serie : resultados) {
                modelo.addRow(new Object[]{
                        serie.getTitulo(),
                        serie.getGenero(),
                        serie.getTemporadas(),
                        serie.getAnioLanzamiento(),
                        serie.getNombrePlataforma()
                });
            }

            // Mostramos el modelo en la tabla
            tablaResultados.setModel(modelo);

        } catch (SQLException e) {
            // Si algo va mal mostramos un mensaje
            JOptionPane.showMessageDialog(this, "Error al consultar series.");
            e.printStackTrace();
        }
    }
}