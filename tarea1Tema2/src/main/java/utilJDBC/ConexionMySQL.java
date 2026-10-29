package utilJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionMySQL {

	static final String USUARIO = "root";
	static final String CONTRASEÑA = "root";
	static final String URL = "jdbc:mysql://localhost:3306/sakila";
	static  Connection CONEXION = null;

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
	
	public static ResultSet selectSakila(String sql) {
		Statement sentencia;
		ResultSet resultados = null;
		try {
			sentencia = CONEXION.createStatement();
			 resultados = sentencia.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return resultados;
		
	}

}
