package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import models.Empleado;

public class UtilsFicheroArray {

	public static void crearArchivo(String nombreArchivo) {
		File archivo = new File(nombreArchivo);

		try {
			if (archivo.createNewFile()) {
				System.out.println("Archivo creado Correctamente");
			} else
				System.out.println("El fichero ya existe");
		} catch (IOException e) {
			System.out.println("Error");
			e.printStackTrace();
		}
	}

	public static void leerArchivo(String nombreArchivo) {
		try {
			BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo));

			String linea;
			while ((linea = lector.readLine()) != null) {
				System.out.println(linea);
			}

			lector.close();
		} catch (IOException e) {
		}
	}

	// TODO Auto-generated catch block

	public static void pasarArchivoArray(String nombreArchivo) {
		try {
			BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo));

			lector.readLine();

			ArrayList<Empleado> empleados = new ArrayList<>();

			String linea;

			while ((linea = lector.readLine()) != null) {

				String[] campos = linea.split(",");

				for (int i = 0; i < campos.length; i++) {
					campos[i] = campos[i].replace("\"", "");
				}

				String empresa = campos[0];
				int edad = Integer.parseInt(campos[1]);
				int numEmpleado = Integer.parseInt(campos[2]);

				Empleado empleado = new Empleado(empresa, edad, numEmpleado);

				empleados.add(empleado);

			}

			for (Empleado empleado : empleados) {
				System.out.println(empleado);
			}
			lector.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
