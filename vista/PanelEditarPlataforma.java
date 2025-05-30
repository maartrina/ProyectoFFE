package vista;

import controlador.PlataformaControlador;
import modelo.Plataforma;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class PanelEditarPlataforma extends JPanel {

    private PlataformaControlador controlador;

    private JComboBox<Plataforma> comboPlataformas; // Lista desplegable de plataformas
    private JTextField campoNombre; // Campo para nuevo nombre
    private JTextField campoPais;   // Campo para nuevo país
    private JButton botonActualizar; // Botón para guardar los cambios

    public PanelEditarPlataforma(PlataformaControlador controlador) {
        this.controlador = controlador;

        // Diseño con 4 filas y 2 columnas
        setLayout(new GridLayout(4, 2, 10, 10));

        // ComboBox para elegir qué plataforma editar
        add(new JLabel("Selecciona una plataforma:"));
        comboPlataformas = new JComboBox<>();
        cargarPlataformas(); // Se cargan las plataformas de la base de datos
        add(comboPlataformas);

        // Campo para el nuevo nombre
        add(new JLabel("Nuevo nombre:"));
        campoNombre = new JTextField();
        add(campoNombre);

        // Campo para el nuevo país de origen
        add(new JLabel("Nuevo país de origen:"));
        campoPais = new JTextField();
        add(campoPais);

        // Botón que actualiza la plataforma
        botonActualizar = new JButton("Actualizar plataforma");
        botonActualizar.addActionListener(e -> actualizarPlataforma());
        add(botonActualizar);
    }

    // Este método carga todas las plataformas en el ComboBox
    private void cargarPlataformas() {
        try {
            List<Plataforma> plataformas = controlador.obtenerTodas();
            comboPlataformas.removeAllItems(); // Limpiar primero el combo por si ya hay datos
            for (Plataforma p : plataformas) {
                comboPlataformas.addItem(p);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "❌ Error al cargar plataformas.");
            e.printStackTrace();
        }
    }

    // Método para actualizar la plataforma seleccionada
    private void actualizarPlataforma() {
        Plataforma seleccionada = (Plataforma) comboPlataformas.getSelectedItem();
        if (seleccionada == null) {
            JOptionPane.showMessageDialog(this, "⚠️ Debes seleccionar una plataforma.");
            return;
        }

        String nuevoNombre = campoNombre.getText().trim();
        String nuevoPais = campoPais.getText().trim();

        // Verifica que los campos no estén vacíos
        if (nuevoNombre.isEmpty() || nuevoPais.isEmpty()) {
            JOptionPane.showMessageDialog(this, "⚠️ Todos los campos son obligatorios.");
            return;
        }

        // Cambiamos los datos de la plataforma seleccionada
        seleccionada.setNombre(nuevoNombre);
        seleccionada.setPaisOrigen(nuevoPais);

        try {
            controlador.actualizarPlataforma(seleccionada); // Se llama al método que actualiza en BBDD
            JOptionPane.showMessageDialog(this, "✅ Plataforma actualizada correctamente.");
            
            // Limpiamos los campos y recargamos el combo
            campoNombre.setText("");
            campoPais.setText("");
            cargarPlataformas();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "❌ Error al actualizar plataforma.");
            e.printStackTrace();
        }
    }
}