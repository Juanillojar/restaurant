package Gui;

enum kitchenCategory{CHEF, ASSISTANT};

/**
 * Versi�n: 1.2.0
 * Fecha creaci�n:	03/08/2021. �ltima modificaci�n: 05/12/2021
 * clase Cocineto. Adem�s de los atributos del objeto Cocinero se definen constructores, getters, setters,
 * y los m�todos toString y equals.
 * @author Operador
 *
 */
public class Cocinero extends Trabajador{		
	private String speciality;		// guarda la especialidad del cocinero; resposter�a, platos tradicionales, platos vanguardia 
	private int workExperience;		// guarda los a�os de experiencia
	private kitchenCategory category;	// specifies the category of a worker in the kitchen
	
	public Cocinero(String name, String surNames, String dni, double salary, Turno shift,
			String telephone, String clave, String specialty, int workExperience, kitchenCategory category) {
		super(name, surNames, dni, salary, shift, telephone, clave);
		this.speciality = specialty;
		this.workExperience = workExperience;
		this.category = category;
	}

	public Cocinero() {
		super();
		speciality = "";
		workExperience = 0;
		category = null;
	}

	public String getSpeciality() {
		return speciality;
	}

	public int getWorkExperience() {
		return workExperience;
	}

	public void setSpeciality(String specialty) {
		this.speciality = specialty;
	}

	public void setWorkExperience(int workExperience) {
		this.workExperience = workExperience;
	}

	
	public kitchenCategory getCategory() {
		return category;
	}

	public void setCategory(kitchenCategory category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Cocinero: "+ super.toString() + " specialty:" + speciality + ", workExperience:" + workExperience + ", chef: " + category ;
	}

	public boolean equals(Cocinero obj) {		
		return super.equals(obj);
	}
	
}