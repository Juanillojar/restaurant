package Gui;
/*
Autor: Juan José Cárdenas
Versión: 1.3.0
Fecha creación:	03/08/2021. Última modificación: 175/08/2021 
clase Pedido. Además de los atributos del objeto Pedido se definen constructores, getters, setters,
y los métodos toString y equals
 */

import java.util.Date;
import java.util.GregorianCalendar;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class Pedido {
	private static int pedidos =0; 		    //número de pedidos
	private int orderId;
	private Date date;
	private List<Productos> orderFoods;   	//Guarda una lista con los objetos conida del pedido
	private double orderPrice;      	 	//total del pedido con impuestos
	private double orderPriceWithoutTaxes;
	private Trabajador trabajador; 			// trabajador que atiende la comanda (camarero o repartidor)
	private double valorDescuento;		 	//Valor de descuento del pedido. Se aplica antes de aplicar impuestos
	private DestinoPedido destination;
	private boolean pedidoCobrado;
	
	//constructor para crear el pedido en test (interfaz consola)
	public Pedido(List<Productos> orderFoods, double orderPrice, Trabajador trabajador,
			 DestinoPedido destino) {
		pedidos ++;
		this.orderId += pedidos;
		this.date = new Date();
		this.orderFoods = orderFoods;
		this.orderPrice = orderPrice;
		this.orderPriceWithoutTaxes = 0.0d;
		this.trabajador = trabajador;
		this.destination = destino;
		this.pedidoCobrado = false;
	}

	//constructor para la interfaz de consola
	public Pedido() {
		pedidos ++;
		orderId += pedidos;
		date = new Date();
		orderFoods = new ArrayList<Productos>();
		orderPrice = 0.0d;
		orderPriceWithoutTaxes = 0.0d;
		trabajador = null;
		destination = null;
		pedidoCobrado= false;
	}
	//constructor para la interfaz gráfica
	public Pedido(DestinoPedido destino) {
		pedidos ++;
		orderId += pedidos;
		date = new Date();
		orderFoods = new ArrayList<Productos>();
		orderPrice = 0.0d;
		orderPriceWithoutTaxes = 0.0d;
		trabajador = null;
		this.destination = destino;
	}

	public static int getPedidos() {
		return pedidos;
	}

	public int getOrderId() {
		return orderId;
	}

	public Date getDate() {
		
		return date;
	}

	public List<Productos> getOrderFoods() {
		return orderFoods;
	}

	public double getOrderPrice() {
		return orderPrice;
	}

	public double getvalorDescuento() {
		return valorDescuento;
	}
	public static void setPedidos(int pedidos) {
		Pedido.pedidos = pedidos;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setOrderFoods(List<Productos> orderFoods) {
		this.orderFoods = orderFoods;
	}

	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public void setValorDescuento(double valorDescuento) {
		this.valorDescuento = valorDescuento;
	}
	
	public DestinoPedido getDestination() {
		return destination;
	}

	public void setDestination(DestinoPedido destination) {
		this.destination = destination;
	}

	public boolean isPedidoCobrado() {
		return pedidoCobrado;
	}

	public void setPedidoCobrado(boolean pedidoCobrado) {
		this.pedidoCobrado = pedidoCobrado;
	}

	public double getOrderPriceWithoutTaxes() {
		return orderPriceWithoutTaxes;
	}

	public void setOrderPriceWithoutTaxes(double orderPriceWithoutTaxes) {
		this.orderPriceWithoutTaxes = orderPriceWithoutTaxes;
	}

	
	public Trabajador getTrabajador() {
		return trabajador;
	}

	public void setTrabajador(Trabajador trabajador) {
		this.trabajador = trabajador;
	}

	@Override
	public String toString() {
		DateFormat formatoFechaHora = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
		DecimalFormat formatoDecimal = new DecimalFormat("###,###.##");  // formatea un double a String truncando según en formato
		return "Pedido " + orderId + " date:" + formatoFechaHora.format(this.date.getTime())  + 
				" " + orderFoods + " orderPrice:" + formatoDecimal.format(orderPrice) + " € "
				+  trabajador.getName()	+ " valorDescuento: " + valorDescuento + " € \n";
	}

	public boolean equals(Pedido obj) {
		return orderId == obj.orderId ;
	}
	
}