package metodosJDBC;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import conexion.Conexion;

public class MetodosJDBC {

	public static void insertarBatch() {
		Random random = new Random();
		int contador = 0;
		String[] nombres = { "Alejandro", "Beatriz", "Carlos", "Daniela", "Eduardo", "Fernanda", "Gabriel", "Helena",
				"Ignacio", "Julia", "Kevin", "Laura", "Manuel", "Natalia", "Óscar", "Paula", "Rafael", "Sofía", "Tomás",
				"Valeria" };
		Statement stm;

		try {
			stm = Conexion.CONEXION.createStatement();
			while (contador < 20) {
				int numero = random.nextInt(nombres.length);
				String sql = String.format(
						"INSERT INTO alumno(Nombre,FechaNacimiento,Tlfn) VALUES('%s','2026-11-18',688721365)",
						nombres[numero]);

				stm.addBatch(sql);
				contador++;

				if (contador == 5) {
					int[] update = stm.executeBatch();
					System.out.println("5 Insert realizados");
				} else if (contador == 10) {
					int[] update = stm.executeBatch();
					System.out.println("10 Insert realizados");
				} else if (contador == 15) {
					int[] update = stm.executeBatch();
					System.out.println("15 Insert realizados");
				} else if (contador == 20) {
					int[] update = stm.executeBatch();
					System.out.println("20 Insert realizados");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
