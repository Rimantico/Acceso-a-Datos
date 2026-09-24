package examenRepasoJava;

import examenRepasoJava.Empleado.Departamento;

public class PruebaEmpresa {

	public static void main(String[] args) {
	Empleado empleado = new Empleado("26791055B","Juan","Gomez",Departamento.Contabilidad,1235,4,3);
	
	System.out.println(empleado.toString());
	
	Empleado empleadosAleatorios[] = Empleado.generar_empleados(6);
	
	for(int i = 0 ; i<6 ; i++) {
		System.out.println(empleadosAleatorios[i]);
	}
	
	
	
	
	
	}

}
