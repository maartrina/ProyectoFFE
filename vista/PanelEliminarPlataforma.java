package vista;

import controlador.PlataformaControlador;
import modelo.Plataforma;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class PanelEliminarPlataforma extends JPanel {

    // Combo para seleccionar la plataforma a eliminar
    private JComboBox<Plataforma> comboPlataformas;
    // Botón para hacer la eliminación
    private JButton botonEliminar;
    // Referencia al controlador
    private PlataformaControlador controlador;

    // Constructor
    public PanelEliminarPlataforma(PlataformaControlador controlador) {
        this.controlador = controlador;

        // Diseño del panel con 3 filas y 1 columna
        setLayout(new GridLayout(3, 1, 10, 10));

        // Crear combo y botón
        comboPlataformas = new JComboBox<>();
        botonEliminar = new JButton("Eliminar Plataforma");

        // Añadir componentes
        add(new JLabel("Selecciona la plataforma a eliminar:"));
        add(comboPlataformas);
        add(botonEliminar);

        // Cargamos las plataformas al combo
        cargarPlataformas();

        // Acción del botón eliminar
        botonEliminar.addActionListener(e -> {
            // Obtenemos la plataforma seleccionada
            Plataforma seleccionada = (Plataforma) comboPlataformas.getSelectedItem();
            if (seleccionada == null) return;

            // Confirmamos con el usuario
            int confirm = JOptionPane.showConfirmDialog(this,
                    "¿Seguro que deseas eliminar \"" + seleccionada.getNombre() + "\"?",
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

            // Si confirma, intentamos eliminar
            if (confirm == JOptionPane.YES_OPTION) {
                boolean exito = controlador.eliminarPlataforma(seleccionada.getId());
                if (exito) {
                    JOptionPane.showMessageDialog(this, "✅ Plataforma eliminada.");
                    cargarPlataformas(); // Recargamos el combo
                } else {
                    JOptionPane.showMessageDialog(this, "❌ No se puede eliminar. Hay series asociadas.");
                }
            }
        });
    }

    // Método que carga todas las plataformas en el combo
    private void cargarPlataformas() {
        comboPlataformas.removeAllItems();
        try {
            List<Plataforma> plataformas = controlador.obtenerTodas();
            for (Plataforma p : plataformas) {
                comboPlataformas.addItem(p);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar plataformas.");
        }
    }
}