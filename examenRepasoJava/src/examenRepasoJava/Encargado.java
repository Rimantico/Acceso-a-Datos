package examenRepasoJava;

public final class Encargado extends Empleado {
	
	

	// Enumerators

	public enum CargoEmpresa {
		ResponsableDeArea, AdjuntoADireccion;
	}

	// Attributes

	private CargoEmpresa cargoEmpresa;
	private double sueldo;

	// Constructor

	public Encargado(String dni, String nombre, String apellidos, Departamento departamento, CargoEmpresa cargoEmpresa) {
		super(dni, nombre, apellidos, departamento);
		this.cargoEmpresa = cargoEmpresa;
		numEmpleados++;
		
	}
	
	public Encargado(String dni, String nombre, String apellidos, Departamento departamento, CargoEmpresa cargoEmpresa, double sueldo) {
		super(dni, nombre, apellidos, departamento);
		this.cargoEmpresa = cargoEmpresa;
		this.sueldo = sueldo;
		numEmpleados++;
		
	}
	
	// Getters and Setters

	public CargoEmpresa getCargoEmpresa() {
		return cargoEmpresa;
	}

	public void setCargoEmpresa(CargoEmpresa cargoEmpresa) {
		this.cargoEmpresa = cargoEmpresa;
	}

	public double getSueldo() {
		return sueldo;
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}


	// Methods
	
	/**
	 * Método para ver toda la informacion de la clase Encargado
	 */
	
	@Override
	public String toString() {
		return "Encargado [cargoEmpresa=" + cargoEmpresa + ", sueldo=" + sueldo + "]";
	}

	
	
	
}
