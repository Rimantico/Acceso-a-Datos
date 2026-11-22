package main;

import java.util.ArrayList;

import conexion.Conexion;
import utils.MetodosJDBC;

public class Main {

	public static void main(String[] args) {
		Conexion.conectarBD();
		ArrayList<String> lista =MetodosJDBC.leerArchivo();
		MetodosJDBC.insertarDatos(lista);
	

	}

}
