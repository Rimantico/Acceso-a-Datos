package mainPackage;

import utils.UtilsFicheroArray;

public class Main {

	public static void main(String[] args) {

		UtilsFicheroArray.crearArchivo("Empresa.txt");

		UtilsFicheroArray.leerArchivo("Empresa.txt");
		
		UtilsFicheroArray.pasarArchivoArray("Empresa.txt");

	}

}
