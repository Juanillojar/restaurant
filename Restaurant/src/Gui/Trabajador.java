package Gui;

/*
Autor: Juan José Cárdenas
Versión: 1.2.0
Fecha creación:	03/08/2021. Última modificación: 14/08/2021 
clase Camarero. Además de los atributos del objeto Camarero se definen constructores, getters, setters,
y los métodos toString y equals. 
Se define un métodoa para visualización de lenguajes que domina el camarero
 */


import java.util.Objects;

enum Turno {TARDE, NOCHE;}  //turno de tarde 10:00 a 17:00. Turno de noche 17:00 a 00:00

public class Trabajador {
	private static int workers = 0; //guarda el número de trabajadores. Se utiliza para calcular workerId
	private int workerId;
	private String name;
	private String surNames;
	private String dni;
	private double salary;
	private String shift;		//especifica el turno de trabajo de tarde o noche
	private String telephone;
	
	
	
	public Trabajador(String name, String surNames, String dni, double salary, String shift,
			String telephone) {
		workers ++;					
		this.workerId += workers;	//Se utiliza la variable estática workers para asignar workerId
		this.name = name;
		this.surNames = surNames;
		this.dni = dni;
		this.salary = salary;
		this.shift = shift;
		this.telephone = telephone;
	}


	public Trabajador() {
		workers ++;
		workerId += workers;
		name = "";
		surNames = "";
		dni = "";
		salary = 0.0;
		shift = "";
		telephone = "";
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


	public String getShift() {
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


	public void setShift(String shift) {
		this.shift = shift;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
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