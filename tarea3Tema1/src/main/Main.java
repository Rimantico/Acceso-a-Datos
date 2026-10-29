package main;
import java.io.File;

import utils.MetodosCSV;

public class Main {

	public static void main(String[] args) {
		File Peliculas = new File("Peliculas");
		MetodosCSV.crearArchivoCSV();
		
		MetodosCSV.escribirArchivo(Peliculas);
	}

}
