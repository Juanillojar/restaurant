/*
Autor: Juan José Cárdenas
Versión: 1.2.0
Fecha creación:	03/08/2021. Última modificación: 23/08/2021 
clase pizzeria. Además de los atributos de la pizzeria se definen listas para guardar objetos tipo 
Trabajador (workers), Comida (foods) y Pedidos (orders).
También se definen algunos métodos para visualizar el contenido de las listas y para la generación 
y visualizacion de un pedido
 */

package Gui;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

enum Zone {
	Bar, IntTable, ExtTable, Delivery;
}
public class Restaurant {
	private static int impuestos = 10;
	private static int descuento = 5;
	private static int inicioTurnoTarde = 11;
	private static int inicioTurnoNoche= 17;
	private static int finTurnoNoche=24;
	private String name;
	private String address;
	private int barZones;    // especifica el número de zonas de barra
	private int deliveryZones;// especifica el número de zonas de reparto
	private int inTables;	 // especifica el número de mesas en interior
	private int outTables;   // especifica el número de mesas en interior
	private List<Trabajador> workers;
	public List<Productos> foods;
	private List<Pedido> orders;

	public Restaurant(String noum, String address, List<Trabajador> workers, List<Productos> foods, List<Pedido> orders) {
		this.name = noum;
		this.address = address;
		this.workers = workers;
		this.foods = foods;
		this.orders = orders;
	}

	public Restaurant(String noum, String address) {
		this.name = noum;
		this.address = address;
		workers = null;
		foods = null;
		orders = null;
	}

	public Restaurant() {
		name = "";
		address = "";
		workers = null;
		foods = null;
		orders = null;
	}

	public static int getImpuestos() {
		return impuestos;
	}

	public static int getDescuento() {
		return descuento;
	}
	public static int getIncioTurnoTarde() {
		return inicioTurnoTarde;
	}
	public static int getIncioTurnoNoche() {
		return inicioTurnoNoche;
	}
	public String getName() {
		return name;
	}
	public static int getFinTurnoNoche() {
		return finTurnoNoche;
	}
	public String getAddress() {
		return address;
	}

	public List<Trabajador> getWorkers() {
		return workers;
	}

	public List<Productos> getFoods() {
		return foods;
	}

	public List<Pedido> getOrders() {
		return orders;
	}
	public int getBarZones() {
		return barZones;
	}

	public int getInTables() {
		return inTables;
	}

	public int getOutTables() {
		return outTables;
	}

	public void setBarZones(int barZones) {
		this.barZones = barZones;
	}

	public void setInTables(int inTables) {
		this.inTables = inTables;
	}

	public void setOutTables(int outTables) {
		this.outTables = outTables;
	}
	
	public int getDeliveryZones() {
		return deliveryZones;
	}

	public void setDeliveryZones(int deliveryZones) {
		this.deliveryZones = deliveryZones;
	}

	public static void setImpuestos(int impuestos) {
		Restaurant.impuestos = impuestos;
	}

	public static void setDescuento(int descuento) {
		Restaurant.descuento = descuento;
	}
	public static void setInicioTurnoTarde(int incioTurnoTarde) {
		Restaurant.inicioTurnoTarde = incioTurnoTarde;
	}
	public static void setInicioTurnoNoche(int incioTurnoNoche) {
		Restaurant.inicioTurnoNoche = incioTurnoNoche;
	}
	public static void setFinTurnoTarde(int finTurnoNoche) {
		Restaurant.finTurnoNoche = finTurnoNoche;
	}

	public void setName(String noum) {
		this.name = noum;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setWorkers(List<Trabajador> workers) {
		this.workers = workers;
	}

	public void setFoods(List<Productos> foods) {
		this.foods = foods;
	}

	public void setOrders(List<Pedido> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Pizzeria " + name + ", address:" + address + (workers != null ? "\nworkers=" + workers + ", " : "")
				+ (foods != null ? "\nfoods=" + foods + ", " : "") + (orders != null ? "\norders=" + orders : "");
	}

	public boolean equals(Restaurant obj) {
		return address.equals(obj.address) && name.equals(obj.name);
	}
	
	public void VisualizarListaTrabajadores(List<Trabajador> workers) {
		for (Trabajador t : workers) {
			System.out.println(t.toString());
		}
	}

	public void VisualizarListaComidas(List<Productos> platos) {
		for (Productos p : platos) {
			System.out.println(p.toString());
		}
	}

	public double calculoPrecioPedido(Pedido pedido) {
		// Calcula el precio total de los productos del pedido sin impuestos
		// si algún producto está promocionado descuenta antes de aplicar impuestos
		// guarda en el objeto pedido los valores de descuento y valor total del pedido
		double precioSinImpuestos = 0.0d;
		double valorDescuento = 0.0d;
		for (Productos p : pedido.getOrderFoods()) { // el for recorre la lista de comidas del pedido
			if (p.isLowPrice()) {
				valorDescuento += p.getPrice() * descuento / 100;
				precioSinImpuestos += (p.getPrice()- (p.getPrice() * descuento / 100));
			} else
				precioSinImpuestos += p.getPrice();
		}
		pedido.setValorDescuento(valorDescuento);
		pedido.setOrderPrice(precioSinImpuestos + precioSinImpuestos * impuestos / 100);
		return precioSinImpuestos;
	}

}