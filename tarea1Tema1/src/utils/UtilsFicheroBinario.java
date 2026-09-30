package utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.EOFException;
import models.Empleado;

public class UtilsFicheroBinario {
	
	
	/**
	 * Creo el archivo Binario
	 * @param nombre
	 */

	public static void crearArchivoBinario(String nombre) {
		File archivo = new File(nombre + ".bin");

		try {
			if (archivo.createNewFile()) {
				System.out.println("Se ha creado el archivo binario correctamente");

			} else
				System.out.println("El archivo ya se ha creado");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Escribo dentro del archivo Binario
	 * @param nombreArchivo
	 */

	public static void escribirArchivoBinario(String nombreArchivo) {
		Scanner sc = new Scanner(System.in);

		ArrayList<Empleado> empleados = new ArrayList<>();

		System.out.println("¿Cuantos empleados quieres introducir?");
		int numEmpleados = Integer.parseInt(sc.nextLine());

		for (int i = 0; i < numEmpleados; i++) {
			
			// Pedir datos de los empleados

			System.out.println("Introduzca la empresa del empleado " + (i + 1));
			String nombreEmpresa = sc.nextLine();
			System.out.println("Introduzca la edad del empleado" + (i + 1));
			int edadEmpleado = Integer.parseInt(sc.nextLine());
			System.out.println("Introduzca el número de empleados" + (i + 1));
			int numEmpleado = Integer.parseInt(sc.nextLine());
			
			// Los meto dentro de un arraylist para que sea mas sencillo su escritura

			Empleado empleado = new Empleado(nombreEmpresa, edadEmpleado, numEmpleado);

			empleados.add(empleado);

		}
		
		/*
		 * Este try catch me sirve para introducir los datos de cada empleado en el archivo binario
		 */

		try (DataOutputStream salida = new DataOutputStream(new FileOutputStream(nombreArchivo + ".bin"))) {

			for (Empleado empleado : empleados) {
				salida.writeUTF(empleado.getEmpresa());
				salida.writeInt(empleado.getEdad());
				salida.writeInt(empleado.getNumEmpleados());
			}

		} 
		
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sc.close();
	}

	
	/**
	 * Leo el archivo binario y meto sus datos en un Array List
	 * @param nombreArchivo
	 * @return
	 */
	public static ArrayList<Empleado> leerArchivoBinario(String nombreArchivo) {

		ArrayList<Empleado> empleados = new ArrayList<>();

		try (DataInputStream entrada = new DataInputStream(new FileInputStream(nombreArchivo + ".bin"))) {

			// Este bucle es clave para la lectura de un archivo binario, siempre va a ser así
			
			// Hay que respetar el orden, es decir, que tengo que seguir el mismo orden por el cual se escribió.
			
			while (true) {
				
				String empresa = entrada.readUTF();
				int edad = entrada.readInt();
				int numeroEmpleado = entrada.readInt();

				Empleado empleado = new Empleado(empresa, edad, numeroEmpleado);

				empleados.add(empleado);
			}

		} catch(EOFException e) {
			
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return empleados;

	}
}
