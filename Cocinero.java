package Gui;

/*
Versión: 1.2.0
Fecha creación:	03/08/2021. Última modificación: 14/08/2021 
clase Cocineto. Además de los atributos del objeto Cocinero se definen constructores, getters, setters,
y los métodos toString y equals. 
 */

import java.util.Objects;

public class Cocinero extends Trabajador{		
	private String specialty;		// guarda la especialidad del cocinero; respostería, platos tradicionales, platos vanguardia 
	private int workExperience;		// guarda los años de experiencia
	private boolean chef;			// indica si tiene el grado de chef o de pinche de cocina
	
	public Cocinero(String name, String surNames, String dni, double salary, String shift,
			String telephone, String specialty, int workExperience, boolean chef) {
		super(name, surNames, dni, salary, shift, telephone);
		this.specialty = specialty;
		this.workExperience = workExperience;
		this.chef = chef;
	}

	public Cocinero() {
		super();
		specialty = "";
		workExperience = 0;
		chef = false;
	}

	public String getSpecialty() {
		return specialty;
	}

	public int getWorkExperience() {
		return workExperience;
	}

	public boolean isChef() {
		return chef;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public void setWorkExperience(int workExperience) {
		this.workExperience = workExperience;
	}

	public void setChef(boolean chef) {
		this.chef = chef;
	}

	@Override
	public String toString() {
		return "Cocinero: "+ super.toString() + " specialty:" + specialty + ", workExperience:" + workExperience + ", chef" + chef ;
	}

	public boolean equals(Cocinero obj) {		
		return super.equals(obj);
	}
	
}
