package examenRepasoJava;

import utils.Biblioteca_dni;

public class Empleado extends Persona{
	
	public static int numEmpleados = 0;
	public static double mediaSueldo= 0;
	
	// Enumerator
	
	public enum Departamento{
		Contabilidad,Logistica,Soporte_al_usuario;
	}
	
	// Attributes
	
	private Departamento departamento;
	private double sueldo;
	private int diasVacacionesAnual;
	private int tiempoEnEmpresa;
	
	// Constructor
	
	public Empleado(String dni, String nombre, String apellidos, Departamento departamento) {
		super(dni,nombre,apellidos);
		this.departamento = departamento;
		numEmpleados++;
		mediaSueldo += sueldo;
		mediaSueldo = mediaSueldo / numEmpleados;
	}
	
	public Empleado(String dni, String nombre, String apellidos, Departamento departamento, double sueldo,int diasVacacionesAnual, int tiempoEnEmpresa) {
		super(dni,nombre,apellidos);
		this.departamento = departamento;
		this.sueldo = sueldo;
		this.diasVacacionesAnual = diasVacacionesAnual;
		this.tiempoEnEmpresa = tiempoEnEmpresa;
		numEmpleados++;
		mediaSueldo += sueldo;
		mediaSueldo = mediaSueldo / numEmpleados;
		
	}
	
	// Getters and Setters

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public double getSueldo() {
		return sueldo;
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
		mediaSueldo += sueldo;
		mediaSueldo = mediaSueldo / numEmpleados;
	}

	public int getDiasVacacionesAnual() {
		return diasVacacionesAnual;
	}

	public void setDiasVacacionesAnual(int diasVacacionesAnual) {
		this.diasVacacionesAnual = diasVacacionesAnual;
	}

	public int getTiempoEnEmpresa() {
		return tiempoEnEmpresa;
	}

	public void setTiempoEnEmpresa(int tiempoEnEmpresa) {
		this.tiempoEnEmpresa = tiempoEnEmpresa;
	}
	
	public static int getNumEmpleados() {
		return numEmpleados;
	}

	public static void setNumEmpleados(int numEmpleados) {
		Empleado.numEmpleados = numEmpleados;
	}
	
	public static double getMediaSueldo() {
		return mediaSueldo;
	}

	public static void setMediaSueldo(double mediaSueldo) {
		Empleado.mediaSueldo = mediaSueldo;
	}
	
	
	
	// Methods

	
	

	/**
	 * Método que me permite ver toda la información sobre la clase Empleado
	 */
	
	@Override
	public String toString() {
		return "Empleado [departamento=" + departamento + ", sueldo=" + sueldo + ", diasVacacionesAnual="
				+ diasVacacionesAnual + ", tiempoEnEmpresa=" + tiempoEnEmpresa + "]";
	}
	
	/**
	 * 
	 * @param num_empleados
	 * @return Genera el numero de empleados que le pidas
	 */
	
	
	public static Empleado[] generar_empleados(int num_empleados) {
		Empleado[] empleado = new Empleado[num_empleados];
		
		for(int i = 0 ; i<num_empleados ; i++) {
			String dni = Biblioteca_dni.crea_dni_con_letra();
			
			String nombre = ("nombre"+ i);
			String apellido = ("apellido" + i);
			
			Departamento departamento = null;
			
			int numero = (int) (Math.random() * 3) + 1;
			
			if(numero == 1) {
				departamento = departamento.Contabilidad;
			}else if(numero == 2) {
				departamento = departamento.Logistica;
			}else
				departamento = departamento.Soporte_al_usuario;
			
			Empleado empleadoNuevo = new Empleado(dni,nombre,apellido,departamento);
			
			empleado[i] = empleadoNuevo;
			
			
			
		}
		
		
		
		return empleado;
		
	}
	
	

}
