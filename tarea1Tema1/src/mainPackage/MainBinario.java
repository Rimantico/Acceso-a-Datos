package mainPackage;

import java.util.ArrayList;

import models.Empleado;
import utils.UtilsFicheroBinario;

public class MainBinario {

	public static void main(String[] args) {
		UtilsFicheroBinario.crearArchivoBinario("binario");
		UtilsFicheroBinario.escribirArchivoBinario("binario");
		ArrayList<Empleado> empleados =
		        UtilsFicheroBinario.leerArchivoBinario("binario");		
		
		
		for (Empleado empleado : empleados) {
		    System.out.println(empleado);
		}

	}

}
