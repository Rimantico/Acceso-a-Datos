package metodos;

import conexion.Conexion;
import modelos.Pelicula;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MetodosBaseDatos {
    private MetodosBaseDatos() { }

    public static List<Pelicula> listaEntera() throws SQLException {
        List<Pelicula> peliculas = new ArrayList<>();
        String sql = "SELECT film_id, title, CAST(release_year AS CHAR) AS release_year FROM film ORDER BY film_id";
        try (Connection con = Conexion.conectarBD();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String valor = rs.getString("release_year");
                Integer aa = valor == null ? null : Integer.valueOf(valor);
                peliculas.add(new Pelicula(rs.getInt("film_id"), rs.getString("title"), aa));
            }
        }
        return peliculas;
    }

    public static Integer validarAA(String texto) {
        texto = texto.trim();
        if (texto.isEmpty()) return null;
        if (!texto.matches("[0-9]{4}")) {
            throw new IllegalArgumentException("El año debe tener cuatro cifras o estar vacío.");
        }
        int aa = Integer.parseInt(texto);
        if (aa != 0 && (aa < 1901 || aa > 2155)) {
            throw new IllegalArgumentException("MySQL YEAR admite 0000 o años entre 1901 y 2155.");
        }
        return aa;
    }

    public static Pelicula guardarPelicula(String nombre, Integer aa) throws SQLException {
        nombre = nombre.trim();
        if (nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre debe tener entre 1 y 255 caracteres.");
        }
        if (aa != null && aa != 0 && (aa < 1901 || aa > 2155)) {
            throw new IllegalArgumentException("Año fuera del intervalo permitido.");
        }
        try (Connection con = Conexion.conectarBD()) {
            int idioma;
            try (PreparedStatement ps = con.prepareStatement(
                    "SELECT language_id FROM language WHERE name = ? ORDER BY language_id LIMIT 1")) {
                ps.setString(1, "English");
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) throw new SQLException("No existe el idioma English en language.");
                    idioma = rs.getInt(1);
                }
            }
            String sql = "INSERT INTO film (title, release_year, language_id) VALUES (?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, nombre);
                if (aa == null) ps.setNull(2, Types.DATE);
                else ps.setString(2, String.format(java.util.Locale.ROOT, "%04d", aa));
                ps.setInt(3, idioma);
                if (ps.executeUpdate() != 1) throw new SQLException("No se insertó la película.");
                try (ResultSet claves = ps.getGeneratedKeys()) {
                    if (!claves.next()) throw new SQLException("No se recibió el ID generado.");
                    return new Pelicula(claves.getInt(1), nombre, aa);
                }
            }
        }
    }
}
