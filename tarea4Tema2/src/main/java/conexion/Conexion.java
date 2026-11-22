package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	static final String USUARIO = "root";
	static final String CONTRASEÑA = "root";
	static final String URL = "jdbc:mysql://localhost:3306/bd_Tarea3TransaccionesFicheroTXT";
	public static Connection CONEXION = null;
	
	
	public static Connection conectarBD() {
		
		try {
			CONEXION = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
			System.out.println("Todo OK");
			return CONEXION;
		} catch (SQLException e) {
			System.out.println("Error en SQL");
			e.printStackTrace();
		}
		
		
		return CONEXION;
	}
}
