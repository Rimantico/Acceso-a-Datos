package modelos;

public class Pelicula {
    private final int id;
    private final String nombre;
    private final Integer aa;

    public Pelicula(int id, String nombre, Integer aa) {
        this.id = id;
        this.nombre = nombre;
        this.aa = aa;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public Integer getAA() { return aa; }

    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre + " | AA: "
                + (aa == null ? "" : String.format(java.util.Locale.ROOT, "%04d", aa));
    }
}
