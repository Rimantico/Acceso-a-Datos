package metodos;

import java.sql.ResultSet;

public class MetodosBaseDatos {

	/**
	 * En este documento vienen los métodos para todo el programa
	 */
	
	public static ResultSet primerPelicula() {
		ResultSet peliculas = null;
		
		String sql = "SELECT film_id , title FROM film where film_id= 1";
		
		return peliculas;
	}
	
}
