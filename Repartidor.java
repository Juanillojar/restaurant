package Gui;

/*
Versión: 1.2.0
Fecha creación:	03/08/2021. Última modificación: 14/08/2021 
clase Repartidor. Además de los atributos del objeto Repartidor se definen constructores, getters, setters,
y los métodos toString y equals. 
 */

enum Transport {Pie, Moto, Bicicleta, CocheElectrico, CocheCombustion;}

public class Repartidor extends Trabajador{
	private String deliveyMode;   //especifica si se desplaza a pie, bicicleta, moto...
	private int age;				
	private boolean motorcycleLicense;
	private boolean ownVehicle;		//especifica si el vehículo utilizado para el reparto es propio
	
	public Repartidor(String name, String surNames, String dni, double salary, String shift,
			String telephone, String deliveyMode, int age, boolean motorcycleLicense, boolean ownVehicle) {
		super(name, surNames, dni, salary, shift, telephone);
		this.deliveyMode = deliveyMode;
		this.age = age;
		this.motorcycleLicense = motorcycleLicense;
		this.ownVehicle = ownVehicle;
	}

	public Repartidor() {
		super();
		deliveyMode = "";
		age = 0;
		motorcycleLicense = false;
		ownVehicle = false;
	}

	public String getDeliveyMode() {
		return deliveyMode;
	}

	public int getAge() {
		return age;
	}

	public boolean isMotorcycleLicense() {
		return motorcycleLicense;
	}

	public boolean isOwnVehicle() {
		return ownVehicle;
	}

	public void setDeliveyMode(String deliveyMode) {
		this.deliveyMode = deliveyMode;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setMotorcycleLicense(boolean motorcycleLicense) {
		this.motorcycleLicense = motorcycleLicense;
	}

	public void setOwnVehicle(boolean ownVehicle) {
		this.ownVehicle = ownVehicle;
	}

	@Override
	public String toString() {
		return "Repartidor " + super.toString() + " deliveyMode:" + deliveyMode + ", age:" + age + ", motorcycleLicense:" + motorcycleLicense
				+ ", ownVehicle:" + ownVehicle;
	}

	public boolean equals(Repartidor obj) {
		return super.equals(obj);
	}
	
}

