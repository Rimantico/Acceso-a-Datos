package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import models.Cafeteria;

public class MetodosCSV {

	/**
	 * Este método pasa de un fichero CSV a un fichero Binario
	 * 
	 * @param nombre
	 */

	public static void fichero_CSV_To_Binario(String nombre) {
		File archivo = new File(nombre + ".dat");

		try {

			if (archivo.createNewFile()) {
				System.out.println("Archivo creado");
			} else
				System.out.println("Ya estaba creado el archivo");

			BufferedReader lector = new BufferedReader(new FileReader(nombre + ".csv"));
			ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(nombre + ".dat"));

			// Para saltarme el encabezado

			lector.readLine();

			String linea;

			while ((linea = lector.readLine()) != null) {
				String[] campos = linea.split(",");

				String producto = campos[0];
				String categoria = campos[1];
				double precio = Double.parseDouble(campos[2]);

				Cafeteria cafeteria = new Cafeteria(producto, categoria, precio);

				salida.writeObject(cafeteria);

			}
			lector.close();
			salida.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		escribir_Log_Metodos(nombre, nombre + ".csv", nombre + ".dat");
	}

	/**
	 * Función para leer un archivo binario e introducir sus datos dentro de un
	 * archivo csv
	 * 
	 * @param nombre
	 */

	public static void fichero_Binario_To_CSV(String nombre) {

		File archivo = new File(nombre + ".csv");

		try {

			if (archivo.createNewFile()) {
				System.out.println("Se ha creado el archivo");
			} else {
				System.out.println("El archivo ya estaba creado");
			}

			ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(nombre + ".dat"));

			BufferedWriter escritor = new BufferedWriter(new FileWriter(nombre + ".csv"));

			escritor.write("producto,categoria,precio_euros");
			escritor.newLine();

			try {

				while (true) {

					Cafeteria cafeteria = (Cafeteria) entrada.readObject();

					escritor.write(cafeteria.getProducto() + "," + cafeteria.getCategoria() + ","
							+ cafeteria.getPrecio_euros());

					escritor.newLine();
				}

			} catch (EOFException e) {
				// Hemos llegado al final del fichero.
				// No es un error.
			}

			entrada.close();
			escritor.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}

		escribir_Log_Metodos(nombre, nombre + ".dat", nombre + ".csv");
	}

	/**
	 * Método para ordenador un archivo CSV
	 * 
	 * @param nombre
	 */

	public static void ordenar_Archivo_CSV(String nombre) {
		List<String> lineas;
		try {
			lineas = Files.readAllLines(Path.of(nombre));

			lineas.sort(String.CASE_INSENSITIVE_ORDER);

			String nuevoNombre = nombre.substring(0, nombre.length() - 4) + "_ord.csv";

			Files.write(Path.of(nuevoNombre), lineas);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		escribir_Log_Metodos(nombre, nombre + ".csv", nombre + "ord.csv");

	}

	/**
	 * Método para ordenar un archivo Binario
	 * 
	 * @param nombre
	 */

	public static void ordenar_Archivo_Binario(String nombre) {
		List<Cafeteria> cafeterias = new ArrayList<>();

		// Leemos todos los objetos

		try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(nombre))) {

			while (true) {
				try {
					cafeterias.add((Cafeteria) entrada.readObject());
				} catch (EOFException e) {
					break;
				}
			}

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}

		cafeterias.sort(Comparator.comparing(Cafeteria::getProducto, String.CASE_INSENSITIVE_ORDER));

		int posicionExtension = nombre.lastIndexOf('.');
		String nuevoNombre = nombre.substring(0, posicionExtension) + "_ord" + nombre.substring(posicionExtension);

		try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(nuevoNombre))) {

			for (Cafeteria cafeteria : cafeterias) {
				salida.writeObject(cafeteria);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		escribir_Log_Metodos(nombre, nombre + ".dat", nombre + "ord.dat");

	}

	/**
	 * Con este Método pasaremos un archivo Binario a CSV, además lo vamos a ordenar
	 * 
	 * @param nombre
	 */

	public static void fichero_Binario_To_CSV_Ordenado(String nombre) {
		File archivo = new File(nombre + ".csv");

		fichero_Binario_To_CSV(nombre);
		
		ordenar_Archivo_CSV(nombre + ".csv");

		escribir_Log_Metodos(nombre, nombre + ".dat", nombre + "ord.csv");
	}

	/**
	 * Método para ver el Log
	 * 
	 * @param metodo
	 * @param entrada
	 * @param salida
	 */
	public static void escribir_Log_Metodos(String metodo, String entrada, String salida) {
		File archivo = new File("log.txt");

		// Para ver si se ha creado el archivo

		try {
			if (archivo.createNewFile()) {
				System.out.println("Archivo log creado");
			} else
				System.out.println("Ya estaba creado");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LocalDateTime horaActual = LocalDateTime.now();

		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		String fechaHora = horaActual.format(formato);

		try {
			BufferedWriter escritor = new BufferedWriter(new FileWriter("log.txt", true));

			escritor.write(
					fechaHora + " Método: " + metodo + " Fichero Entrada: " + entrada + " Fichero Salida: " + salida);

			escritor.newLine();
			
			escritor.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
