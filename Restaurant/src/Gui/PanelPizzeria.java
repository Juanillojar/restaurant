package Gui;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.sun.jarsigner.ContentSignerParameters;

public class PanelPizzeria extends JPanel{
	private GestorBotonesPizzeria gestorBotones = new GestorBotonesPizzeria();
	private static BotonPizzeriaMesas botonMesa;  //Identifica el botón de la mesa pulsado 
	private static Double subtotal = 0.0;
	private LabelPizzeria lsubtotalOrder, lDestinoPedidoEnCurso;
	private BotonPizzeria buttonTique;  //botón para mostrar el tique de pedido. No siempre visible

	ImageIcon iconOperate = new ImageIcon("src/Gui/images/operar.jpg", "Operar");
	ImageIcon iconConf = new ImageIcon("src/Gui/images/conf.png", "Configuración");
	ImageIcon iconReports = new ImageIcon("src/Gui/images/informes.png", "Informes");
	ImageIcon iconBar = new ImageIcon("src/Gui/images/barra.jpg", "Bar");
	ImageIcon iconDelivery = new ImageIcon("src/gui/images/Delivery.jpg", "Delivery");
	ImageIcon iconIntTable = new ImageIcon("src/gui/images/ExtTable.png", "Int. Table");
	ImageIcon iconExtTable = new ImageIcon("src/gui/images/intTable.jpg", "Ext. Table");
	ImageIcon iconBack = new ImageIcon("src/gui/images/Back.png", "Back");
	ImageIcon iconTique = new ImageIcon("src/gui/images/tique.png", "Tique");
	ImageIcon iconCaja = new ImageIcon("src/gui/images/caja.png", "Caja");
	Font fuenteTitulo = new Font("arial", Font.BOLD, 20);
	Font fuenteDatos = new Font("arial", Font.PLAIN, 12);
	DateFormat formatoFechaHora = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
	DecimalFormat formatoDecimales = new DecimalFormat("###,###.##"); // formatea un double a String truncando según en
																		// formato

	// Constructor of main panel principal
	public PanelPizzeria() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 25));
		//setLayout(new GridLayout());
		BotonPizzeria bOperate = new BotonPizzeria(iconOperate);
		bOperate.setActionCommand("AbrirPanelMesas");
		BotonPizzeria bConf = new BotonPizzeria(iconConf);
		bConf.setActionCommand("AbrirPanelConfiguracion");
		bConf.setEnabled(false);
		BotonPizzeria bReport = new BotonPizzeria(iconReports);
		bReport.setActionCommand("AbrirPanelReports");
		add(bOperate);
		add(bConf);
		add(bReport);
		bOperate.addActionListener(gestorBotones);
		bConf.addActionListener(gestorBotones);
		bReport.addActionListener(gestorBotones);
		setVisible(true);
	}

	// Contructor for bar, tables panel (Operar)
	public PanelPizzeria(int barzones, int deliverys, int intTables, int extTables) {
//		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 25));
		setLayout(new BorderLayout());
		LabelPizzeria titulo = new LabelPizzeria("Seleccione una opción para crear pedido", fuenteTitulo, "CENTER");
		add(titulo, BorderLayout.NORTH);
		JPanel panelMesas = new JPanel();
		// creación de botones de zonas barra
		for (int i = 0; i < barzones; i++) {
			BotonPizzeria buttonBar = new BotonPizzeriaMesas(iconBar, i+1, Zone.BAR);
			panelMesas.add(buttonBar);
			buttonBar.addActionListener(gestorBotones);
		}
		// Creación de botones Reparto
		for (int i = 0; i < deliverys; i++) {
			BotonPizzeria buttonDelivery = new BotonPizzeriaMesas(iconDelivery, i+1, Zone.Delivery);
			panelMesas.add(buttonDelivery);
			buttonDelivery.addActionListener(gestorBotones);
		}
		// Creación de botones de zona mesas interior
		for (int i = 0; i < intTables; i++) {
			BotonPizzeria buttonIntTables = new BotonPizzeriaMesas(iconIntTable, i+1, Zone.IntTable);
			panelMesas.add(buttonIntTables);
			buttonIntTables.addActionListener(gestorBotones);
		}
		// Creación de botones de zona mesas exterior
		for (int i = 0; i < extTables; i++) {
			BotonPizzeria buttonExtTables = new BotonPizzeriaMesas(iconExtTable, i+1, Zone.ExtTable);
			panelMesas.add(buttonExtTables);
			buttonExtTables.addActionListener(gestorBotones);
		}
		add(panelMesas, BorderLayout.CENTER);
		BotonPizzeria buttonBack = new BotonPizzeria(iconBack);
		add(buttonBack, BorderLayout.SOUTH);
		buttonBack.setActionCommand("SalirPanelMesas");
		buttonBack.addActionListener(gestorBotones);
		setVisible(false);
	}

	// Constructor for products panel
	public PanelPizzeria(List<ComidaPizzeria> productos, BotonPizzeriaMesas botonMesaPMesas) {
		botonMesa = botonMesaPMesas;  //se carga la variable estática con los datos de la mesa en curso
		setLayout(new BorderLayout());
		lDestinoPedidoEnCurso = new LabelPizzeria("Order " + botonMesaPMesas.getDestinoBoton().getDestinationDenomination(),fuenteTitulo, "CENTER");
		add(lDestinoPedidoEnCurso, BorderLayout.NORTH);
		//JPanel produ = new JPanel();
		JTabbedPane produ = new JTabbedPane();
		// se crean los botones en el panel de productos en pestañas creadas de acuerdo
		// a la sección del producto (contenido del enum Section)
		for(Section sec: Section.values()) {
			JPanel panelProductos = new JPanel(new FlowLayout(FlowLayout.CENTER));
			produ.addTab(sec.name(), panelProductos);
			for(ComidaPizzeria producto :productos) {
				if (sec == producto.getSection()) {
					BotonPizzeria buttonProduct = new BotonPizzeria(producto);
					panelProductos.add(buttonProduct);
					buttonProduct.addActionListener(gestorBotones);
				}
			}
		}
		
		add(produ, BorderLayout.CENTER);
		JPanel botones = new JPanel();
		lsubtotalOrder = new LabelPizzeria("Subtotal Order:" + formatoDecimales.format(subtotal), fuenteTitulo);
		LabelPizzeria lInfoDescuento = new LabelPizzeria("Los productos sombreados tienen un descuento", fuenteDatos, "CENTER");
		botones.add(lsubtotalOrder);
		botones.add(lInfoDescuento);
		buttonTique = new BotonPizzeria(iconTique);
		buttonTique.addActionListener(gestorBotones);
		buttonTique.setActionCommand("OpenTiquePanel");
		buttonTique.setVisible(false);
		botones.add(buttonTique);
		//el botón back devuelve el pedido a la variable pedido del boton de mesas
		BotonPizzeria buttonSalirProductos = new BotonPizzeriaMesas(iconBack, 1, botonMesa.getPedidoBoton());
		buttonSalirProductos.addActionListener(gestorBotones);
		buttonSalirProductos.setActionCommand("SalirPanelProductos");
		botones.add(buttonSalirProductos);
		add(botones, BorderLayout.SOUTH);
		setVisible(false);
	}

	// Constructor for ticket panel
	public PanelPizzeria(Pedido order) {
		setLayout(new BorderLayout(50, 0));
		LabelPizzeria lTitulopizzeria = new LabelPizzeria(FramePizzeria.InstanceFPizzerie.myPizzerie.getName() + "  "
				+ FramePizzeria.InstanceFPizzerie.myPizzerie.getAddress(), fuenteTitulo, "CENTER");
		add(lTitulopizzeria, BorderLayout.NORTH);
		JPanel tique = new JPanel(new GridLayout(0, 2, 25,0));
		LabelPizzeria lTituloFecha = new LabelPizzeria("Fecha: ", fuenteTitulo, "RIGHT");
		LabelPizzeria lFecha = new LabelPizzeria(formatoFechaHora.format(order.getDate()), fuenteDatos);
		tique.add(lTituloFecha);
		tique.add(lFecha);
		LabelPizzeria lTituloProductos = new LabelPizzeria("Producto", fuenteTitulo, "RIGHT");
		LabelPizzeria lTituloImporte = new LabelPizzeria("Importe", fuenteTitulo);
		tique.add(lTituloProductos);
		tique.add(lTituloImporte);
		for (ComidaPizzeria producto : order.getOrderFoods()) {
			LabelPizzeria prod = new LabelPizzeria(producto.getDenomination(), fuenteDatos, "RIGHT");
			tique.add(prod);
			LabelPizzeria importe = new LabelPizzeria(" " + producto.getPrice(), fuenteDatos);
			tique.add(importe);
		}
		LabelPizzeria lTituloTotal = new LabelPizzeria("Total: ", fuenteTitulo, "RIGHT");
		LabelPizzeria lTotal = new LabelPizzeria(formatoDecimales.format(order.getOrderPrice()) + " €", fuenteTitulo);
		tique.add(lTituloTotal);
		tique.add(lTotal);
		LabelPizzeria lTituloPrecioSinIva = new LabelPizzeria("Precio sin IVA: ", fuenteTitulo, "RIGHT");
		FramePizzeria.InstanceFPizzerie.getPanelProductos().calculoPrecioPedido(order);
		LabelPizzeria lPrecioSinIva = new LabelPizzeria(formatoDecimales.format(order.getOrderPriceWithoutTaxes()) + " €",	fuenteDatos);
		tique.add(lTituloPrecioSinIva);
		tique.add(lPrecioSinIva);
		LabelPizzeria lTituloDescuento = new LabelPizzeria("Descuento: ", fuenteTitulo, "RIGHT");
		LabelPizzeria lDescuento = new LabelPizzeria(formatoDecimales.format(order.getvalorDescuento()) + " €",	fuenteDatos);
		tique.add(lTituloDescuento);
		tique.add(lDescuento);
		LabelPizzeria lTituloAtendido = new LabelPizzeria("Le atendió: ", fuenteTitulo, "RIGHT");
		String atiende = "";
		if (order.getDestination().getDestinationZone().equals(Zone.Delivery)) { // add delivery man
			atiende = order.getDeliveryMan().getName();
		} else { // add waiter
			atiende = order.getWaiter().getName();
		};
		LabelPizzeria lAtendido = new LabelPizzeria(atiende, fuenteTitulo);
		tique.add(lTituloAtendido);
		tique.add(lAtendido);
		add(tique, BorderLayout.CENTER);
		JPanel panelbotones = new JPanel(new FlowLayout(FlowLayout.CENTER,30,0));
		BotonPizzeria buttonPaidOut = new BotonPizzeria(iconCaja);
		buttonPaidOut.setActionCommand("PaidOut");
		buttonPaidOut.addActionListener(gestorBotones);
		panelbotones.add(buttonPaidOut);
		BotonPizzeria buttonSalirTique = new BotonPizzeria(iconBack);
		buttonSalirTique.setActionCommand("SalirPanelTique");
		buttonSalirTique.addActionListener(gestorBotones);
		panelbotones.add(buttonSalirTique);
		add(panelbotones, BorderLayout.SOUTH);
		setVisible(false);
	}

	// Constructor for reports panel
	public PanelPizzeria(int i) {
		setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		ImageIcon iconProductsReport = new ImageIcon("src/gui/images/Report.png", "Products Report");
		ImageIcon iconOrderReport = new ImageIcon("src/gui/images/Report.png", "Order Report");
		ImageIcon iconWorkerReport = new ImageIcon("src/gui/images/Report.png", "Worker Report");
		BotonPizzeria bProductosReport = new BotonPizzeria(iconProductsReport);
		//BotonPizzeria bProductosReport2 = new BotonPizzeria(iconProductsReport);
		BotonPizzeria bOrderReport = new BotonPizzeria(iconOrderReport);
		BotonPizzeria bWorkerReport = new BotonPizzeria(iconWorkerReport);
		bWorkerReport.setEnabled(false);
		// no lo muestro por que no se ve el botón BACK y provoca que todo falle
		add(bProductosReport);
//		add(bProductosReport2);
		add(bOrderReport);
		add(bWorkerReport);
		bProductosReport.setActionCommand("OpenProductsReport");
		bProductosReport.addActionListener(gestorBotones);
	//	bProductosReport2.setActionCommand("OpenProductsReport2");
		//bProductosReport2.addActionListener(gestorBotones);
		bOrderReport.setActionCommand("OpenOrderReport");
		bOrderReport.addActionListener(gestorBotones);
		bWorkerReport.addActionListener(gestorBotones);
		BotonPizzeria buttonSalirReports = new BotonPizzeria(iconBack);
		buttonSalirReports.setActionCommand("SalirPanelReports");
		buttonSalirReports.addActionListener(gestorBotones);
		add(buttonSalirReports);
		setVisible(false);
	}
	
	
	//Constructor for products report panel using a JTable
	public PanelPizzeria(List<ComidaPizzeria> productos) {
		String [] nombresColumnas = {"id", "Denomination", "Section", "Ingredients", "Price", "LowPrice"};
		Class [] tipoColumnas = {java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,java.lang.Boolean.class};
		ModeloTablaPizzeriaProductos tModelProductos = new ModeloTablaPizzeriaProductos(productos, nombresColumnas, tipoColumnas);
		JTable tableProductos = new JTable(tModelProductos);
		tableProductos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane panelScrollProductosReport = new JScrollPane(tableProductos,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(panelScrollProductosReport);
		BotonPizzeria buttonBackProductReport = new BotonPizzeria(iconBack);
		buttonBackProductReport.setActionCommand("BackReportsFromProductsReport");
		buttonBackProductReport.addActionListener(gestorBotones);
		add(buttonBackProductReport, BorderLayout.SOUTH);	
	}
	
	//Constructor for products report panel
/*	public PanelPizzeria(int i, int j) {
		setLayout(new BorderLayout());
		JPanel pProductos = new JPanel(new GridLayout(0, 5, 5, 5));
		JScrollPane panelScrollProductosReport = new JScrollPane(pProductos,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelScrollProductosReport.setAlignmentY(0.0f);
		panelScrollProductosReport.setAlignmentX(0.0f);
		LabelPizzeria jlTituloDenomination = new LabelPizzeria("Denomination", fuenteTitulo,"LEFT");
		LabelPizzeria jlTituloSection = new LabelPizzeria("Section", fuenteTitulo);
		LabelPizzeria jlTituloPrice = new LabelPizzeria("Price", fuenteTitulo);
		LabelPizzeria jlTituloLowPrice = new LabelPizzeria("Low Price", fuenteTitulo);
		LabelPizzeria jlTituloIngredients = new LabelPizzeria("Ingredients", fuenteTitulo);
		pProductos.add(jlTituloDenomination);
		pProductos.add(jlTituloSection);
		pProductos.add(jlTituloPrice);
		pProductos.add(jlTituloLowPrice);
		pProductos.add(jlTituloIngredients);
		for (ComidaPizzeria plato : FramePizzeria.InstanceFPizzerie.myPizzerie.getFoods()) {
			LabelPizzeria jlDenomination = new LabelPizzeria(plato.getDenomination(), fuenteDatos);
			LabelPizzeria jlSection = new LabelPizzeria(plato.getSection(), fuenteDatos);
			LabelPizzeria jlPrice = new LabelPizzeria(((Double) plato.getPrice()).toString(), fuenteDatos);
			JCheckBox jcLowPrice = new JCheckBox(" ", (plato.isLowPrice()) ? true : false);
			jcLowPrice.setEnabled(false);
			LabelPizzeria jlIngredients = new LabelPizzeria((plato.getIngredients()), fuenteDatos);
			pProductos.add(jlDenomination);
			pProductos.add(jlSection);
			pProductos.add(jlPrice);
			pProductos.add(jcLowPrice);
			pProductos.add(jlIngredients);
		}
		add(panelScrollProductosReport, BorderLayout.CENTER);
		BotonPizzeria buttonBackProductReport = new BotonPizzeria(iconBack);
		buttonBackProductReport.setActionCommand("BackReportsFromProductsReport");
		buttonBackProductReport.addActionListener(gestorBotones);
		add(buttonBackProductReport, BorderLayout.SOUTH);	
	}
*/
	//Constructor for order report panel
/*	public PanelPizzeria(String str) {
		setLayout(new BorderLayout());
		JPanel pPedidos = new JPanel();
		JScrollPane panelScrollPedidosReport = new JScrollPane(pPedidos,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelScrollPedidosReport.setAlignmentY(0.0f);
		pPedidos.setLayout(new GridLayout(0, 5, 5,5));
		LabelPizzeria jTituloOrderId = new LabelPizzeria("Id", fuenteTitulo);
		LabelPizzeria jlTituloOrderDate = new LabelPizzeria("Date", fuenteTitulo);
		LabelPizzeria jlTituloPrice = new LabelPizzeria("Price", fuenteTitulo);
		LabelPizzeria jlTituloDestino = new LabelPizzeria("Destino", fuenteTitulo);
		LabelPizzeria jlTituloCobrado = new LabelPizzeria("Cobrado", fuenteTitulo);
		pPedidos.add(jTituloOrderId);
		pPedidos.add(jlTituloOrderDate);
		pPedidos.add(jlTituloPrice);
		pPedidos.add(jlTituloDestino);
		pPedidos.add(jlTituloCobrado);
		for (Pedido order : FramePizzeria.InstanceFPizzerie.myPizzerie.getOrders()) {
			LabelPizzeria jlOrderId = new LabelPizzeria(((Integer)order.getOrderId()).toString(), fuenteDatos);
			LabelPizzeria jlOrderDate = new LabelPizzeria(formatoFechaHora.format(order.getDate()), fuenteDatos);
			LabelPizzeria jlPrice = new LabelPizzeria(((Double) order.getOrderPrice()).toString(), fuenteDatos);
			LabelPizzeria jlDestino = new LabelPizzeria(order.getDestination().getDestinationDenomination(), fuenteDatos);			
			JCheckBox jcCobrado = new JCheckBox(" ", (order.isPedidoCobrado()) ? true : false);
			jcCobrado.setEnabled(false);
			pPedidos.add(jlOrderId);
			pPedidos.add(jlOrderDate);
			pPedidos.add(jlPrice);
			pPedidos.add(jlDestino);
			pPedidos.add(jcCobrado);
		}
		add(panelScrollPedidosReport, BorderLayout.CENTER);
		BotonPizzeria buttonBackOrderReport = new BotonPizzeria(iconBack);
		buttonBackOrderReport.setActionCommand("BackReportsFromOrderReport");
		buttonBackOrderReport.addActionListener(gestorBotones);
		add(buttonBackOrderReport, BorderLayout.SOUTH);	
	}
*/
	
	//Constructor for order report panel using JTable
	public PanelPizzeria(List<Pedido> pedidos, String str) {
		String [] nombresColumnas = {"id", "Date", "Price", "PriceWhitoutTaxes", "worker", "discount", "Destiny"};
		Class [] tipoColumnas = {java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class};
		ModeloTablaPizzeriaPedidos tModelPedidos = new ModeloTablaPizzeriaPedidos(pedidos, nombresColumnas, tipoColumnas);
		JTable tablePedidos = new JTable(tModelPedidos);
		tablePedidos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane panelScrollPedidosReport = new JScrollPane(tablePedidos,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(panelScrollPedidosReport);
		BotonPizzeria buttonBackPedidosReport = new BotonPizzeria(iconBack);
		buttonBackPedidosReport.setActionCommand("BackReportsFromOrderReport");
		buttonBackPedidosReport.addActionListener(gestorBotones);
		add(buttonBackPedidosReport, BorderLayout.SOUTH);	
	}
	
	
	//Actualiza el valor de variable subtotal o la resetea 0.0, lo visualiza en el label y devuelve el valor 
	public double IncrementaSutotal(Double precio) {
		if (precio != 0.0) {
			subtotal += precio;
		} else
			subtotal = precio;
		lsubtotalOrder.setText("Subtotal Order:" + formatoDecimales.format(subtotal));
		lsubtotalOrder.repaint();
		return subtotal;
	}
	
	//rellena los datos del pedido con el precio con impuestos y descuentos, el camarero o repartidor
	public void upgradeOrderData(Pedido pedidoEnCurso) {
		pedidoEnCurso.setOrderPriceWithoutTaxes(subtotal); // add total price whitout taxes
		if (pedidoEnCurso.getDestination().getDestinationZone().equals(Zone.Delivery)) { // add delivery man
			Repartidor rep = new Repartidor();
			pedidoEnCurso.setDeliveryMan(
					FramePizzeria.InstanceFPizzerie.myPizzerie.seleccionaTrabajadorPedidoSegunHorario(rep));
			rep = null; // no sé si esto libera memoria
		} else { // add waiter
			Camarero cam = new Camarero(); // se pasa objeto del tipo solo para comparar
			pedidoEnCurso.setWaiter(FramePizzeria.InstanceFPizzerie.myPizzerie.seleccionaTrabajadorPedidoSegunHorario(cam));
			cam = null; // no sé si esto libera memoria
		};
		// calc total price and discounts
		FramePizzeria.InstanceFPizzerie.myPizzerie.calculoPrecioPedido(pedidoEnCurso);
		System.out.println("Rellenado pedido " + pedidoEnCurso);
	}
	
	public void upgradePedidoEnCurso(BotonPizzeriaMesas boton, Pedido pedido) {
	// se devuelve al botón de la mesa, barra...???? el pedido modificado
	// tendría que pasar el pedido al objeto
		boton.setPedidoBoton(pedido);
	}
	
	public Boolean checkForOpenOrder(Pedido pedidoEnCurso) {
		if (pedidoEnCurso != null  && pedidoEnCurso.getOrderPrice() != 0.0f && pedidoEnCurso.isPedidoCobrado() != true) {
			System.out.println("Boton mesa pulsado. Ya tiene datos");
			return true;
		}else {
			System.out.println("Boton mesa pulsado. No tiene datos");
			return false;
		}
	}
	

	public void calculoPrecioPedido(Pedido pedido) {
		// Calcula el precio total de los productos del pedido sin impuestos
		// si algún producto está promocionado descuenta antes de aplicar impuestos
		// guarda en el objeto pedido los valores de descuento, valor sin impuestos y valor total del pedido
		double precioSinImpuestos = 0.0d;
		double valorDescuento = 0.0d;
		for (ComidaPizzeria p : pedido.getOrderFoods()) { // el for recorre la lista de comidas del pedido
			if (p.isLowPrice()) {
				valorDescuento += p.getPrice() * Pizzeria.getDescuento() / 100;
				precioSinImpuestos += (p.getPrice()- (p.getPrice() * Pizzeria.getDescuento() / 100));
			} else
				precioSinImpuestos += p.getPrice();
		}
		pedido.setValorDescuento(valorDescuento);
		pedido.setOrderPriceWithoutTaxes(precioSinImpuestos);
		pedido.setOrderPrice(precioSinImpuestos + precioSinImpuestos * Pizzeria.getImpuestos() / 100);
		
	}

	public static void setSubtotal(Double subtotal) {
		PanelPizzeria.subtotal = subtotal;
	}

	public LabelPizzeria getLsubtotalOrder() {
		return lsubtotalOrder;
	}

	public BotonPizzeria getButtonTique() {
		return buttonTique;
	}

	public void setButtonTique(BotonPizzeria buttonTique) {
		this.buttonTique = buttonTique;
	}

	public static BotonPizzeriaMesas getBotonMesa() {
		return botonMesa;
	}

	public static void setBotonMesa(BotonPizzeriaMesas botonMesa) {
		PanelPizzeria.botonMesa = botonMesa;
	};
	public void setLsubtotalOrder(LabelPizzeria lsubtotalOrder) {
		this.lsubtotalOrder = lsubtotalOrder;
	}

	public void paidOut(Pedido pedidoEnCurso) {
		pedidoEnCurso.setPedidoCobrado(true);
	}

	public LabelPizzeria getlDestinoPedidoEnCurso() {
		return lDestinoPedidoEnCurso;
	}

	public void setlDestinoPedidoEnCurso(LabelPizzeria lDestinoPedidoEnCurso) {
		this.lDestinoPedidoEnCurso = lDestinoPedidoEnCurso;
	}
	
}