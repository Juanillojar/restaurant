package Gui;

/*
Autor: Juan Jos� C�rdenas
Versi�n: 1.2.0
Fecha creaci�n:	03/08/2021. �ltima modificaci�n: 14/08/2021 
clase Camarero. Adem�s de los atributos del objeto Camarero se definen constructores, getters, setters,
y los m�todos toString y equals. 
Se define un m�todoa para visualizaci�n de lenguajes que domina el camarero
 */

enum Turno {Morning, Afternoon, Evening};  //turno de tarde 10:00 a 17:00. Turno de noche 17:00 a 00:00

public class Trabajador {
	private static int workers = 0; //guarda el n�mero de trabajadores. Se utiliza para calcular workerId
	private int workerId;
	private String name;
	private String surNames;
	private String dni;
	private double salary;
	private Turno shift;		//especify the shift (Morning, Afternoon or Evening)
	private String telephone;
	private String clave;
	
	public Trabajador(String name, String surNames, String dni, double salary, Turno shift,
			String telephone, String clave) {
		workers ++;					
		this.workerId += workers;	//Se utiliza la variable est�tica workers para asignar workerId
		this.name = name;
		this.surNames = surNames;
		this.dni = dni;
		this.salary = salary;
		this.shift = shift;
		this.telephone = telephone;
		this.clave = clave;
	}

	public Trabajador() {
		workers ++;
		workerId += workers;
		name = "";
		surNames = "";
		dni = "";
		salary = 0.0;
		shift = null;
		telephone = "";
		clave = "";
	}

	public static int getWorkers() {
		return workers;
	}

	public int getWorkerId() {
		return workerId;
	}

	public String getName() {
		return name;
	}

	public String getSurNames() {
		return surNames;
	}

	public String getDni() {
		return dni;
	}

	public double getSalary() {
		return salary;
	}

	public Turno getShift() {
		return shift;
	}

	public String getTelephone() {
		return telephone;
	}

	public static void setWorkers(int workers) {
		Trabajador.workers = workers;
	}

	public void setWorkerId(int workerId) {
		this.workerId = workerId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurNames(String surNames) {
		this.surNames = surNames;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public void setShift(Turno shift) {
		this.shift = shift;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	@Override
	public String toString() {
		return name + " " + surNames + ", WorkerId:" + workerId + ", dni:" + dni
				+ ", salary:" + salary + ", shift:" + shift + ", tlf:" + telephone + " ";
	}

	public boolean equals(Trabajador obj) {
		return (dni.equals(obj.dni));
	}
}