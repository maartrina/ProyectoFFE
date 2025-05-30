package modelo;

public class Serie {
    private int id;
    private String titulo;
    private String genero;
    private int temporadas;
    private int anioLanzamiento;
    private int idPlataforma; // ID de la plataforma (clave foránea)

    // Este campo se usa solo para mostrar el nombre de la plataforma en consultas
    private String nombrePlataforma;

    // Constructor sin ID (cuando se va a insertar una nueva serie)
    public Serie(String titulo, String genero, int temporadas, int anioLanzamiento, int idPlataforma) {
        this.titulo = titulo;
        this.genero = genero;
        this.temporadas = temporadas;
        this.anioLanzamiento = anioLanzamiento;
        this.idPlataforma = idPlataforma;
    }

    // Constructor con ID (cuando se obtiene desde la base de datos)
    public Serie(int id, String titulo, String genero, int temporadas, int anioLanzamiento, int idPlataforma) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.temporadas = temporadas;
        this.anioLanzamiento = anioLanzamiento;
        this.idPlataforma = idPlataforma;
    }

    // Métodos para obtener los datos (getters)
    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }

    public int getTemporadas() {
        return temporadas;
    }

    public int getAnioLanzamiento() {
        return anioLanzamiento;
    }

    public int getIdPlataforma() {
        return idPlataforma;
    }

    public String getNombrePlataforma() {
        return nombrePlataforma;
    }

    // Método para establecer el nombre de la plataforma (solo para mostrar)
    public void setNombrePlataforma(String nombrePlataforma) {
        this.nombrePlataforma = nombrePlataforma;
    }

    // Este método hace que al mostrar el objeto en un JComboBox salga el título
    @Override
    public String toString() {
        return titulo;
    }
}