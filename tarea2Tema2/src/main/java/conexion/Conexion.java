package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    static final String USUARIO = "root";
    static final String CONTRASEÑA = "root";
    static final String URL = "jdbc:mysql://localhost:3306/sakila";

    private Conexion() { }

    // Cada llamada entrega una conexion nueva; quien la usa debe cerrarla.
    public static Connection conectarBD() throws SQLException {
        String url = System.getenv("SAKILA_URL");
        String usuario = System.getenv("SAKILA_USER");
        String clave = System.getenv("SAKILA_PASSWORD");
        if (url == null || url.trim().isEmpty()) {
            url = URL + "?connectTimeout=5000&socketTimeout=10000";
        }
        if (usuario == null || usuario.trim().isEmpty()) usuario = USUARIO;
        if (clave == null) clave = CONTRASEÑA;
        return DriverManager.getConnection(url, usuario, clave);
    }
}
