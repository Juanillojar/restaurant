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
	
	// queda obsoleto tras la validación de usuario
	// Pide los datos para generar pedido por consola y visualiza el ticket
/*	
		public void generarPedido(List<ComidaPizzeria> platos, FicheroLog log) {
		Pedido pedido = new Pedido();
		double precioSinIva = 0.0;
			int seccion = 0;
			String strSeccion ="";  //guarda el texto de la sección seleccionada por usuario
			// Pide la selección de los productos por consola por cada sección seleccionada
			System.out.println("\n*********** NUEVO PEDIDO *************************");
			log.Escritura("Generación de pedido");
			do {
				System.out.println("********** SECCIONES *************************");
				seccion = seleccionaSeccion(strSeccion);
				if (seccion != -1) {
					// busca el texto de la sección correspondiente al enum seleccionado
					Section[] arraySeccion = Section.values();
					strSeccion = arraySeccion[seccion].name();
					seleccionProductos(foods, strSeccion, pedido, log);				
				}
			} while (seccion != -1);
			//pedido.setOrderFoods(platosPedido);
			// Pide la selección del destino del pedido y selecciona el Trabajador para el reparto
			System.out.println("Seleccione destino del pedido:");
			int selectDestinoPedido = seleccionDestino();
			Camarero cam = new Camarero(); // se pasa objeto del tipo solo para comparar
			switch (selectDestinoPedido) {
			case 0:
				//Camarero cam = new Camarero(); // se pasa objeto del tipo solo para comparar
				pedido.setWaiter(seleccionaTrabajadorPedidoSegunHorario(cam));
				cam = null; // no sé si esto libera memoria
				break;
			case 1:
				//Camarero cam = new Camarero(); // se pasa objeto del tipo solo para comparar
				pedido.setWaiter(seleccionaTrabajadorPedidoSegunHorario(cam));
				cam = null; // no sé si esto libera memoria
				break;
			case 2:
				//Camarero cam = new Camarero(); // se pasa objeto del tipo solo para comparar
				pedido.setWaiter(seleccionaTrabajadorPedidoSegunHorario(cam));
				cam = null; // no sé si esto libera memoria
				break;
			case 3:
				Repartidor rep = new Repartidor();
				pedido.setDeliveryMan(seleccionaTrabajadorPedidoSegunHorario(rep));
				break;
			default:
			}
			// asigna el resto de atributos del objeto Pedido
			Date fecha = new Date();
			pedido.setDate(fecha);
			precioSinIva = calculoPrecioPedido(pedido);
			orders.add(pedido);
			generaTicket(pedido, precioSinIva);
			log.Escritura("Pedido generado correctamete");
		}
*/

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

	public void generaTicket(Pedido pedido, double precioSinIva) {
		DateFormat formatoFechaHora = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
		DecimalFormat formatoDecimales = new DecimalFormat("###,###.##");  // formatea un double a String truncando según en formato
		System.out.println("******* Pizzeria " + this.name + " ******** TIQUE ********");
		System.out.println("*******\t Pedido: " + pedido.getOrderId() + "\t" + formatoFechaHora.format(pedido.getDate()) + "  **********");
		VisualizarListaComidasTicket(pedido.getOrderFoods());
		System.out.println("\t\t\tTotal:" + formatoDecimales.format(pedido.getOrderPrice()) + " €");
		System.out.println("\tPrecio sin Iva:" + formatoDecimales.format(precioSinIva) + " € \tDescuentos: " + formatoDecimales.format(pedido.getvalorDescuento()) + " €");
		System.out.print("\tLe atendió: ");
		System.out.println(pedido.getTrabajador().getName());
		System.out.println("*************************************************");
	}

	public List<Integer> VisualizarListaComidas(List<Productos> platos, String seccion) {
		// Visualiza los productos de la sección y devuelve un array de enteros con las
		// selecciones disponibles
		List<Integer> opciones = new ArrayList<Integer>();
		for (Productos p : platos) {
			if (p.getSection().name() == seccion) {
				System.out.print(p.toString());
				opciones.add(p.getFoodId());
			}
		}
		return opciones;
	}
	

	public void seleccionProductos(List<Productos> foods, String strSeccion, Pedido pedido, FicheroLog log) {
		// Visualiza los productos de la sección, acepta la/s selección/es de
		// usuario y añade los productos al pedido
		Scanner ins = new Scanner(System.in);
		List<Integer> platosSeccion = new ArrayList<Integer>();  // lista de enteros correspondiente a los números de orden de productos de la sección en la lista de comidas
		List<Productos> platosPedido = new ArrayList<Productos>(); 
		int selectProducto = 0;
		Integer iselectProducto = 0;
		
		System.out.println("*******SECCION: " + strSeccion + "  ***********");
		platosSeccion = VisualizarListaComidas(foods, strSeccion);
		do {
			System.out.println("******** Inserte un números que corresponda a un producto. 0 para volever al menú Secciones. ********");
			try {
				selectProducto = ins.nextInt();
				iselectProducto = selectProducto; // paso el int a Integer
				if (platosSeccion.contains(iselectProducto)) {
					platosPedido= pedido.getOrderFoods();
					platosPedido.add(foods.get(selectProducto));  //añade el objeto producto a la lista del pedido
					pedido.setOrderFoods(platosPedido);
					System.out.print("Añadido!");
				}else {
					if (selectProducto !=0) {
						System.out.println("Selección no válida. Inserte una de las listadas.");
						log.Escritura("Selección no válida. Inserte una de las listadas.");
					}
				}
			} catch (Exception ex) {
				System.out.println(ex + "Opción no válida. Seleccione un número.");
				log.Escritura("Opción no válida. Seleccione un número.");
			};
		} while (selectProducto != 0);
	}

	public int seleccionaSeccion(String strSeccion) {
		// Visualiza las secciones, acepta selección de usuario y devuelve el int correspondiente
		// además de devolver la cadena del texto asociado en el parámetro por referencia strSeccion
		Scanner ins = new Scanner(System.in);
		int select= 0;
		// este for visualiza el contenido de enum
		for (Section sec : Section.values()) {
			System.out.println(sec.ordinal() + ": " + sec.name());
		}
		System.out.println("Inserte el número asociado a la sección. -1 para acabar de insertar productos.");
		do {
			try {
				select = ins.nextInt(); // lee el valor insertado por el usuario
				if (select < -1 || select >= Section.values().length) {
					System.out.println("Selección no válida. Inserte una de las listadas.");
					select = 0;
				}
			} catch (InputMismatchException ex) {
				System.out.println("Opción no válida. Seleccione un número.");
			};
		} while (select < -1 || select >= Section.values().length);
		// ins.close();
		return select;
	}

	public int seleccionDestino() {
		Scanner ins = new Scanner(System.in);
		int select = 0;
		for (Zone sec : Zone.values()) { // visualiza el contenido de enum
			System.out.println(sec.ordinal() + ": " + sec);
		}
		System.out.println("Seleccione la sección.");
		do {
			try {
				select = ins.nextInt(); // lee el valor insertado por el usuario
				if (select < -1 || select >= Zone.values().length) {
					System.out.println("Selección no válida. Inserte una de las listadas.");
				}
			} catch (InputMismatchException ex) {
				System.out.println("Opción no válida. Seleccione un número.");
			}
		} while (select < -1 || select >= Zone.values().length);
		// ins.close();
		return select;
	}

	
	public void VisualizarListaComidasTicket(List<Productos> lProductos) {
		for (Productos p : lProductos) {
	
					System.out.println("\t" + p.getDenomination() + "\t" + p.getPrice() + " €");
		}
	}

	public Trabajador seleccionaTrabajadorPedidoSegunHorario(Camarero obj) {
		// Selecciona el Trabajador adecuado según el tipo que se pase por parámetros (camarero o repartidor)
		// según su turno y la hora de generación del pedido
		String turnoActual = seleccionaTurno();

		int index = 0;
		do {
			if ((workers.get(index).getShift() == turnoActual) && (workers.get(index).getClass() == obj.getClass())) {
				return workers.get(index);
			};
			index++;
		} while (index < workers.size());
		return null;
	}
	//este mòdulo queda obsoleto en interfaz gráfica tras insertar la validación de usuario
	public Trabajador seleccionaTrabajadorPedidoSegunHorario(Repartidor rep) {
		// Selecciona el Trabajador según el tipo que se pase por parámetros (camarero o
		// repartidor)
		// adecuado según su turno y la hora de generación del pedido
		String turnoActual = seleccionaTurno();

		int index = 0;
		do {
			if ((workers.get(index).getShift() == turnoActual) && (workers.get(index).getClass() == rep.getClass())) {
				// comprueba si el trabajador tiene el turno que se busca y si el trabajadror es
				// un repartidor
				return workers.get(index);
			};
			index++;
		} while (index < workers.size());
		return null;
	}

	public String seleccionaTurno() {
		// Devuelve un String con el turno según la hora del sistema
		Date date = new Date();
		String turnoActual = "";

		if (date.getHours() >= inicioTurnoTarde && date.getHours() < inicioTurnoNoche) { // turno de tarde
			turnoActual = Turno.TARDE.toString();
		} else if (date.getHours() >= inicioTurnoNoche && date.getHours() < finTurnoNoche) { // turno de noche
			turnoActual = Turno.NOCHE.toString();
		} else
			System.out.println("No se pueden generar pedidos fuera de horario " + inicioTurnoTarde + " a " +  finTurnoNoche);
		return turnoActual;
	}

}