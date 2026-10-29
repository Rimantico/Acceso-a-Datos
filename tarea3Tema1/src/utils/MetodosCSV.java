package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MetodosCSV {

	/**
	 * Método para crear el archivo de películas
	 */
	public static void crearArchivoCSV() {
		File archivo = new File("mispeliculas.csv");
		try {
			if (archivo.createNewFile()) {
				System.out.println("Archivo creado");
				try (FileWriter escribirCabecera = new FileWriter(archivo)) {
					escribirCabecera.write("titulo_pelicula,año,calidad,formato\n");
				}
			} else
				System.out.println("El archivo ya estaba creado");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void escribirArchivo(File carpeta) {
		FileWriter escribir;

		

		File[] archivos = carpeta.listFiles();

		try {
			escribir = new FileWriter("mispeliculas.csv", true);

			if (carpeta != null) {
				for (File archivo : archivos) {
					if (archivo.isDirectory()) {
						escribirArchivo(archivo);
					} else {
						String nombre = archivo.getName();

						String[] extension = nombre.split("\\.");

						String[] nombreArchivo = extension[0].split("[()]");

						String fichero = nombreArchivo[0] + "," + nombreArchivo[1] + "," + nombreArchivo[2] + ","
								+ extension[1];
						
						System.out.println(fichero);

						escribir.write(fichero + "\n");
					
					}
				}
			}
			escribir.close();
		} catch (IOException e) {
			e.printStackTrace();
			
		}
	}

}
