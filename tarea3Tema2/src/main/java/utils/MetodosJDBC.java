package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import conexion.Conexion;

public class MetodosJDBC {
	
	/**
	 * Con este método leo el archivo que introduzca
	 * @return
	 */

	public static ArrayList<String> leerArchivo() {
		ArrayList<String> lineas = new ArrayList<String>();

		try (BufferedReader leer = new BufferedReader(new FileReader("AsignaturasProfesores.txt"))) {
			String linea;

			while ((linea = leer.readLine()) != null) {
				lineas.add(linea);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lineas;

	}
	/**
	 * Con este método inserto los Datos que he visto previamente
	 * @param lista
	 */

	public static void insertarDatos(ArrayList<String> lista) {

		int idProfesor = -1;
		boolean transaccionActiva = false;

		try {

			Conexion.CONEXION.setAutoCommit(false);

			for (String linea : lista) {

				String[] datos = linea.split(";");

				if (datos[0].equals("Profesor")) {

					// Confirmamos el profesor anterior
					if (transaccionActiva) {
						Conexion.CONEXION.commit();
					}

					idProfesor = -1;

					String sql = "INSERT INTO profesor(NIF_P,Nombre,Especialidad,Tlfn) VALUES(?,?,?,?)";

					try (PreparedStatement sentencia = Conexion.CONEXION.prepareStatement(sql,
							Statement.RETURN_GENERATED_KEYS)) {

						sentencia.setString(1, datos[1]);
						sentencia.setString(2, datos[2]);
						sentencia.setString(3, datos[3]);
						sentencia.setString(4, datos[4]);

						sentencia.executeUpdate();

						try (ResultSet rs = sentencia.getGeneratedKeys()) {
							if (rs.next()) {
								idProfesor = rs.getInt(1);
							} else {
								throw new SQLException("No se ha generado IdProfesor");
							}
						}
					}

					transaccionActiva = true;

				} else if (datos[0].equals("Asignatura")) {

					if (!transaccionActiva || idProfesor == -1) {
						throw new SQLException("Asignatura sin profesor válido");
					}

					String sql = "INSERT INTO asignatura VALUES(?,?,?)";

					try (PreparedStatement sentencia = Conexion.CONEXION.prepareStatement(sql)) {

						sentencia.setString(1, datos[1]);
						sentencia.setString(2, datos[2]);
						sentencia.setInt(3, idProfesor);

						sentencia.executeUpdate();
					}
				}
			}

			if (transaccionActiva) {
				Conexion.CONEXION.commit();
			}

		} catch (SQLException e) {

			try {
				Conexion.CONEXION.rollback();
				System.out.println("Se ha realizado rollback");
			} catch (SQLException ex) {
				ex.printStackTrace();
			}

			e.printStackTrace();

		} finally {

			try {
				Conexion.CONEXION.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
