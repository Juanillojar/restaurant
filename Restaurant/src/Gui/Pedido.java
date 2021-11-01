package Gui;
/*
Autor: Juan Jos� C�rdenas
Versi�n: 1.3.0
Fecha creaci�n:	03/08/2021. �ltima modificaci�n: 175/08/2021 
clase Pedido. Adem�s de los atributos del objeto Pedido se definen constructores, getters, setters,
y los m�todos toString y equals
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
	private static int pedidos =0;    //n�mero de pedidos
	private int orderId;
	private Date date;
	private List<ComidaPizzeria> orderFoods;   //Guarda una lista con los objetos conida del pedido
	private double orderPrice;      	 //total del pedido con impuestos
	private double orderPriceWithoutTaxes;
	// Se podr�a poner solo una variable y evitar comprobaciones
	private Trabajador waiter;		 	 //Si un pedido no tiene camarero por ser para reparto su valor ser� nulo.
	private Trabajador deliveryMan;  	 //Si un pedido no tiene repartidor por no ser para reparto su valor ser� nulo
	private double valorDescuento;		 //Valor de descuento del pedido. Se aplica antes de aplicar impuestos
	private DestinoPedido destination;
	private boolean pedidoCobrado;
	
	//constructor para crear el pedido en test (interfaz consola)
	public Pedido(List<ComidaPizzeria> orderFoods, double orderPrice, Camarero waiter,
			Repartidor deliveryMan, DestinoPedido destino) {
		pedidos ++;
		this.orderId += pedidos;
		this.date = new Date();
		this.orderFoods = orderFoods;
		this.orderPrice = orderPrice;
		this.orderPriceWithoutTaxes = 0.0d;
		this.waiter = waiter;
		this.deliveryMan = deliveryMan;
		this.destination = destino;
		this.pedidoCobrado = false;
	}

	//constructor para la interfaz de consola
	public Pedido() {
		pedidos ++;
		orderId += pedidos;
		date = new Date();
		orderFoods = new ArrayList<ComidaPizzeria>();
		orderPrice = 0.0d;
		orderPriceWithoutTaxes = 0.0d;
		waiter = null;
		deliveryMan = null; 
		destination = null;
		pedidoCobrado= false;
	}
	//constructor para la interfaz gr�fica
	public Pedido(DestinoPedido destino) {
		pedidos ++;
		orderId += pedidos;
		date = new Date();
		orderFoods = new ArrayList<ComidaPizzeria>();
		orderPrice = 0.0d;
		orderPriceWithoutTaxes = 0.0d;
		waiter = null;
		deliveryMan = null;
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

	public List<ComidaPizzeria> getOrderFoods() {
		return orderFoods;
	}

	public double getOrderPrice() {
		return orderPrice;
	}

	public Trabajador getWaiter() {
		return waiter;
	}

	public Trabajador getDeliveryMan() {
		return deliveryMan;
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

	public void setOrderFoods(List<ComidaPizzeria> orderFoods) {
		this.orderFoods = orderFoods;
	}

	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public void setWaiter(Trabajador waiter) {
		this.waiter = waiter;
	}

	public void setDeliveryMan(Trabajador deliveryMan) {
		this.deliveryMan = deliveryMan;
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

	@Override
	public String toString() {
		DateFormat formatoFechaHora = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
		DecimalFormat formatoDecimal = new DecimalFormat("###,###.##");  // formatea un double a String truncando seg�n en formato
		return "Pedido " + orderId + " date:" + formatoFechaHora.format(this.date.getTime())  + 
				" " + orderFoods + " orderPrice:" + formatoDecimal.format(orderPrice) + " � "
				+ (waiter != null ? "waiter=" + waiter.getName() + ", " : "")
				+ (deliveryMan != null ? "deliveryMan=" + deliveryMan.getName() : "")
				+ " valorDescuento: " + valorDescuento + " � \n";
	}

	public boolean equals(Pedido obj) {
		return orderId == obj.orderId ;
	}
	
}