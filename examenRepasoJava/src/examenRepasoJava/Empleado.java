package examenRepasoJava;

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
	
	
	// Methods

	
	/**
	 * Método que me permite ver toda la información sobre la clase Empleado
	 */
	
	@Override
	public String toString() {
		return "Empleado [departamento=" + departamento + ", sueldo=" + sueldo + ", diasVacacionesAnual="
				+ diasVacacionesAnual + ", tiempoEnEmpresa=" + tiempoEnEmpresa + "]";
	}
	
	
	

}
