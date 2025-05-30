package modelo;

public class Plataforma {
    private int id;
    private String nombre;
    private String paisOrigen;

    // Constructor sin ID (usado al registrar una nueva plataforma)
    public Plataforma(String nombre, String paisOrigen) {
        this.nombre = nombre;
        this.paisOrigen = paisOrigen;
    }

    // Constructor con ID (usado cuando ya existe en la base de datos)
    public Plataforma(int id, String nombre, String paisOrigen) {
        this.id = id;
        this.nombre = nombre;
        this.paisOrigen = paisOrigen;
    }

    // Devuelve el ID de la plataforma
    public int getId() {
        return id;
    }

    // Devuelve el nombre de la plataforma
    public String getNombre() {
        return nombre;
    }

    // Devuelve el país de origen de la plataforma
    public String getPaisOrigen() {
        return paisOrigen;
    }

    // Permite cambiar el nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Permite cambiar el país de origen
    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    // Este método se usa para que se muestre el nombre en los ComboBox
    @Override
    public String toString() {
        return nombre;
    }
}