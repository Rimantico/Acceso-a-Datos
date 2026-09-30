package models;

public class Empleado {

	// Attributes

	private String empresa;
	private int edad;
	private int numEmpleados;

	// Constructor

	public Empleado(String empresa, int edad, int numEmpleados) {
		this.empresa = empresa;
		this.edad = edad;
		this.numEmpleados = numEmpleados;
	}

	// Getters and Setters

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getNumEmpleados() {
		return numEmpleados;
	}

	public void setNumEmpleados(int numEmpleados) {
		this.numEmpleados = numEmpleados;
	}

	@Override
	public String toString() {

		return "Empresa: " + empresa + "\n" + "Edad: " + edad + "\n" + "Número empleados: " + numEmpleados;
	}

}
