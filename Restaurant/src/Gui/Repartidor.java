package Gui;

/*
Versi�n: 1.2.0
Fecha creaci�n:	03/08/2021. �ltima modificaci�n: 05/12/2021 
clase Repartidor. Adem�s de los atributos del objeto Repartidor se definen constructores, getters, setters,
y los m�todos toString y equals. 
 */

enum Transport {On_foot, Motorcycle, Bicycle, Electric_car, Combustion_car;}

public class Repartidor extends Trabajador{
	private Transport deliveyMode;   //especifica si se desplaza a pie, bicicleta, moto...
	private int age;				
	private boolean motorcycleLicense;
	private boolean ownVehicle;		//especifica si el veh�culo utilizado para el reparto es propio
	
	public Repartidor(String name, String surNames, String dni, double salary, Turno shift,
			String telephone, String clave, Transport deliveyMode, int age, boolean motorcycleLicense, boolean ownVehicle) {
		super(name, surNames, dni, salary, shift, telephone, clave);
		this.deliveyMode = deliveyMode;
		this.age = age;
		this.motorcycleLicense = motorcycleLicense;
		this.ownVehicle = ownVehicle;
	}

	public Repartidor() {
		super();
		deliveyMode = null;
		age = 0;
		motorcycleLicense = false;
		ownVehicle = false;
	}

	public Transport getDeliveyMode() {
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

	public void setDeliveyMode(Transport deliveyMode) {
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
		return 	getName() + " " + getSurNames();
	}
/*	public String toString() {
		return "Repartidor " + super.toString() + " deliveyMode:" + deliveyMode + ", age:" + age + ", motorcycleLicense:" + motorcycleLicense
				+ ", ownVehicle:" + ownVehicle;
	}
*/
	public boolean equals(Repartidor obj) {
		return super.equals(obj);
	}
	
}
