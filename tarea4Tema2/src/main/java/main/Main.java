package main;

import conexion.Conexion;
import metodosJDBC.MetodosJDBC;

public class Main {

	public static void main(String[] args) {
		Conexion.conectarBD();
		MetodosJDBC.insertarBatch();

	}

}
