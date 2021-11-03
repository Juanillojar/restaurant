package Gui;
/*
Versión: 1.2.0
Fecha creación:	03/08/2021. Última modificación: 14/08/2021 
clase Comida. Además de los atributos del objeto Comida se definen constructores, getters, setters,
y los métodos toString y equals.
Especifica los distintos platos que se sirven así como bebidas y cualquier otro producto
 */

import java.util.List;

enum Section {PIZZAS, STARTERS, PASTAS, COMBINADOS, SALADS, DESSERTS, DRINKS, BREAD, OTHERS;}

public class ComidaPizzeria {
	private static int foods = 0;
	private int foodId;
	private String denomination;    //Descripción del plato
	private Section section;			//Indica a que seccion de la carta pertenece. Pizza, plato combinado, postre, ensalada, bebidas...
	private String ingredients;
	private double price;			
	private boolean lowPrice;		//Si es true indica que el plato está promocionado
	// como metemos el tamaño de la pizza??
	
	public ComidaPizzeria(String denomination, Section section, String ingredients, double prize, boolean lowPrice) {
		foods ++;
		this.foodId += foods;				//Se utiliza la variable estática foods para asignar foodId
		this.denomination = denomination;
		this.section = section;
		this.ingredients = ingredients;
		this.price = prize;
		this.lowPrice = lowPrice;
	};
	
	public ComidaPizzeria() {
		foods ++;
		foodId += foods;
		denomination = "";
		section = null;
		ingredients = "";
		price = 0.0d;
		lowPrice = false;
	}

	public static int getFoods() {
		return foods;
	}

	public int getFoodId() {
		return foodId;
	}

	public String getDenomination() {
		return denomination;
	}

	public Section getSection() {
		return section;
	}

	public String getIngredients() {
		return ingredients;
	}

	public double getPrice() {
		return price;
	}

	public boolean isLowPrice() {
		return lowPrice;
	}

	public static void setFoods(int foods) {
		ComidaPizzeria.foods = foods;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public void setPrice(double prize) {
		this.price = prize;
	}

	public void setLowPrice(boolean lowPrice) {
		this.lowPrice = lowPrice;
	}

	@Override
	public String toString() {
		return " " + foodId + "\t" + denomination + ", section:" + section + ", ingredients:"
				+ ingredients + ", prize:" + price + " €, lowPrice:" + lowPrice + "\n";
	};
	
}