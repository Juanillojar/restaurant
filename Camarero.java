package Gui;

/*
Autor: Juan José Cárdenas
Versión: 1.2.0
Fecha creación:	03/08/2021. Última modificación: 14/08/2021 
clase Camarero. Además de los atributos del objeto Camarero se definen constructores, getters, setters,
y los métodos toString y equals. 
Se define un métodoa para visualización de lenguajes que domina el camarero
 */

import java.util.Arrays;
import java.util.Objects;

public class Camarero extends Trabajador{
	private String workZone;  		//especifica si sirve en terraza, interior o barra.
	private String[] languages;		//especifíca los idiomas que domina
	private boolean cocktail;		//especifica si el camarero sabe preparar cocteles
	
	public Camarero(String name, String surNames, String dni, double salary, String shift,
			String telephone, String workZone, String[] languages, boolean cocktail) {
		super(name, surNames, dni, salary, shift, telephone);
		this.workZone = workZone;
		this.languages = languages;
		this.cocktail = cocktail;
	}
	
	public Camarero() {
		super();
		workZone= "";
		languages = new String[5];
		cocktail= false;
	}

	public String getWorkZone() {
		return workZone;
	}

	public String[] getLanguages() {
		return languages;
	}

	public boolean isCocktail() {
		return cocktail;
	}

	public void setWorkZone(String workZone) {
		this.workZone = workZone;
	}

	public void setLanguages(String[] languages) {
		this.languages = languages;
	}

	public void setCocktail(boolean cocktail) {
		this.cocktail = cocktail;
	}
	
	public String toString() {
		return "Camarero " + super.toString() + " workZone:" + workZone + ", languages:(" + visualizaLanguages(languages) + "), cocktail:" + cocktail;
	}

	public boolean equals(Camarero obj) {
		return super.equals(obj);	
	}
	

	public String visualizaLanguages(String[] array) {
	//genera una cadena con los elementos de un array de String separados por comas
		String cadena = ""; 
		for(String st: array) {
			cadena += st +", ";
		}
		return (cadena.substring(0, cadena.length()-2));  //elimina los dos últimos caractéres ", "
	}
}
