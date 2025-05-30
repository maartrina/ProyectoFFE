package vista;

import javax.swing.*;
import java.awt.*;
import controlador.PlataformaControlador;

// Panel para registrar una nueva plataforma
public class PanelRegistroPlataforma extends JPanel {

    // Campos de texto donde el usuario escribe los datos
    private JTextField campoNombre, campoPais;
    // Botón para guardar la plataforma
    private JButton botonGuardar;
    // Controlador que se encarga de la lógica
    private PlataformaControlador controlador;

    // Método privado que registra una plataforma cuando se hace clic en el botón
    private void registrarPlataforma() {
        // Obtener los datos que ha escrito el usuario
        String nombre = campoNombre.getText().trim();
        String pais = campoPais.getText().trim();

        // Comprobar si los campos están vacíos
        if (nombre.isEmpty() || pais.isEmpty()) {
            JOptionPane.showMessageDialog(this, "⚠️ Todos los campos son obligatorios.");
            return;
        }

        // Llamar al controlador para registrar la plataforma
        boolean exito = controlador.registrarPlataforma(nombre, pais);
        if (exito) {
            JOptionPane.showMessageDialog(this, "✅ Plataforma registrada correctamente.");
            // Limpiar los campos
            campoNombre.setText("");
            campoPais.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "❌ Error al registrar plataforma.");
        }
    }

    // Constructor del panel
    public PanelRegistroPlataforma(PlataformaControlador controlador) {
        this.controlador = controlador;

        // Diseño del panel con 3 filas y 2 columnas
        setLayout(new GridLayout(3, 2, 10, 10));

        // Etiqueta y campo de texto para el nombre
        add(new JLabel("Nombre de la plataforma:"));
        campoNombre = new JTextField();
        add(campoNombre);

        // Etiqueta y campo de texto para el país
        add(new JLabel("País de origen:"));
        campoPais = new JTextField();
        add(campoPais);

        // Botón para registrar
        botonGuardar = new JButton("Registrar plataforma");
        botonGuardar.addActionListener(e -> registrarPlataforma());
        add(botonGuardar);
    }
}