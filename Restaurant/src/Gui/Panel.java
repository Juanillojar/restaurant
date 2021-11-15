package Gui;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.sun.jarsigner.ContentSignerParameters;

public class Panel extends JPanel {
	private GestorBotones gestorBotones = new GestorBotones();
	private static BotonPizzeriaMesas botonMesa;  //Identifica el botón de la mesa pulsado 
	private static Double subtotal = 0.0;
	private Label lsubtotalOrder, lDestinoPedidoEnCurso;
	private Boton buttonTique;  //botón para mostrar el tique de pedido. No siempre visible

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
	ImageIcon iconImprimir = new ImageIcon("src/gui/images/imprimir.png", "Imprimir");
	ImageIcon iconAceptar = new ImageIcon("src/gui/images/aceptar2.png", "Aceptar");
	Font fuenteTitulo = new Font("arial", Font.BOLD, 20);
	Font fuenteDatos = new Font("arial", Font.PLAIN, 12);
	DateFormat formatoFechaHora = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
	DecimalFormat formatoDecimales = new DecimalFormat("###,###.##"); // formatea un double a String truncando según en
																		// formato

	// Constructor of main panel principal
	public Panel() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 25));
		Boton bOperate = new Boton(iconOperate);
		bOperate.setActionCommand("AbrirPanelValida");
		Boton bConf = new Boton(iconConf);
		bConf.setActionCommand("AbrirPanelConfiguracion");
		bConf.setEnabled(false);
		Boton bReport = new Boton(iconReports);
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
	public Panel(int barzones, int deliverys, int intTables, int extTables) {
		setLayout(new BorderLayout());
		Label titulo = new Label("Seleccione una opción para crear pedido", fuenteTitulo, "CENTER");
		add(titulo, BorderLayout.NORTH);
		JPanel panelMesas = new JPanel();
		// creación de botones de zonas barra
		for (int i = 0; i < barzones; i++) {
			Boton buttonBar = new BotonPizzeriaMesas(iconBar, i+1, Zone.Bar);
			panelMesas.add(buttonBar);
			buttonBar.addActionListener(gestorBotones);
		}
		// Creación de botones Reparto
		for (int i = 0; i < deliverys; i++) {
			Boton buttonDelivery = new BotonPizzeriaMesas(iconDelivery, i+1, Zone.Delivery);
			panelMesas.add(buttonDelivery);
			buttonDelivery.addActionListener(gestorBotones);
		}
		// Creación de botones de zona mesas interior
		for (int i = 0; i < intTables; i++) {
			Boton buttonIntTables = new BotonPizzeriaMesas(iconIntTable, i+1, Zone.IntTable);
			panelMesas.add(buttonIntTables);
			buttonIntTables.addActionListener(gestorBotones);
		}
		// Creación de botones de zona mesas exterior
		for (int i = 0; i < extTables; i++) {
			Boton buttonExtTables = new BotonPizzeriaMesas(iconExtTable, i+1, Zone.ExtTable);
			panelMesas.add(buttonExtTables);
			buttonExtTables.addActionListener(gestorBotones);
		}
		add(panelMesas, BorderLayout.CENTER);
		Boton buttonBack = new Boton(iconBack);
		add(buttonBack, BorderLayout.SOUTH);
		buttonBack.setActionCommand("SalirPanelMesas");
		buttonBack.addActionListener(gestorBotones);
		setVisible(false);
	}

	// Constructor for products panel
	public Panel(List<Productos> productos, BotonPizzeriaMesas botonMesaPMesas) {
		botonMesa = botonMesaPMesas;  //se carga la variable estática con los datos de la mesa en curso
		setLayout(new BorderLayout());
		lDestinoPedidoEnCurso = new Label("Order " + botonMesaPMesas.getDestinoBoton().getDestinationDenomination(),fuenteTitulo, "CENTER");
		add(lDestinoPedidoEnCurso, BorderLayout.NORTH);
		//JPanel produ = new JPanel();
		JTabbedPane produ = new JTabbedPane();
		// se crean los botones en el panel de productos en pestañas creadas de acuerdo
		// a la sección del producto (contenido del enum Section)
		for(Section sec: Section.values()) {
			JPanel panelProductos = new JPanel(new FlowLayout(FlowLayout.CENTER));
			produ.addTab(sec.name(), panelProductos);
			for(Productos producto :productos) {
				if (sec == producto.getSection()) {
					Boton buttonProduct = new Boton(producto);
					panelProductos.add(buttonProduct);
					buttonProduct.addActionListener(gestorBotones);
				}
			}
		}
		
		add(produ, BorderLayout.CENTER);
		JPanel botones = new JPanel();
		lsubtotalOrder = new Label("Subtotal Order:" + formatoDecimales.format(subtotal), fuenteTitulo);
		Label lInfoDescuento = new Label("Los productos sombreados tienen un descuento", fuenteDatos, "CENTER");
		botones.add(lsubtotalOrder);
		botones.add(lInfoDescuento);
		buttonTique = new Boton(iconTique);
		buttonTique.addActionListener(gestorBotones);
		buttonTique.setActionCommand("OpenTiquePanel");
		buttonTique.setVisible(false);
		botones.add(buttonTique);
		//el botón back devuelve el pedido a la variable pedido del boton de mesas
		Boton buttonSalirProductos = new BotonPizzeriaMesas(iconBack, 1, botonMesa.getPedidoBoton());
		buttonSalirProductos.addActionListener(gestorBotones);
		buttonSalirProductos.setActionCommand("SalirPanelProductos");
		botones.add(buttonSalirProductos);
		add(botones, BorderLayout.SOUTH);
		setVisible(false);
	}

	// Constructor for ticket panel
/*	public Panel(Pedido order) {
		setLayout(new BorderLayout(50, 0));
		Label lTitulopizzeria = new Label(Frame.InstanceFPizzerie.myPizzerie.getName() + "  "
				+ Frame.InstanceFPizzerie.myPizzerie.getAddress(), fuenteTitulo, "CENTER");
		add(lTitulopizzeria, BorderLayout.NORTH);
		JPanel tique = new JPanel(new GridLayout(0, 2, 25,0));
		Label lTituloFecha = new Label("Fecha: ", fuenteTitulo, "RIGHT");
		Label lFecha = new Label(formatoFechaHora.format(order.getDate()), fuenteDatos);
		tique.add(lTituloFecha);
		tique.add(lFecha);
		Label lTituloProductos = new Label("Producto", fuenteTitulo, "RIGHT");
		Label lTituloImporte = new Label("Importe", fuenteTitulo);
		tique.add(lTituloProductos);
		tique.add(lTituloImporte);
		for (Productos producto : order.getOrderFoods()) {
			Label prod = new Label(producto.getDenomination(), fuenteDatos, "RIGHT");
			tique.add(prod);
			Label importe = new Label(" " + producto.getPrice(), fuenteDatos);
			tique.add(importe);
		}
		Label lTituloTotal = new Label("Total: ", fuenteTitulo, "RIGHT");
		Label lTotal = new Label(formatoDecimales.format(order.getOrderPrice()) + " €", fuenteTitulo);
		tique.add(lTituloTotal);
		tique.add(lTotal);
		Label lTituloPrecioSinIva = new Label("Precio sin IVA: ", fuenteTitulo, "RIGHT");
		Frame.InstanceFPizzerie.getPanelProductos().calculoPrecioPedido(order);
		Label lPrecioSinIva = new Label(formatoDecimales.format(order.getOrderPriceWithoutTaxes()) + " €",	fuenteDatos);
		tique.add(lTituloPrecioSinIva);
		tique.add(lPrecioSinIva);
		Label lTituloDescuento = new Label("Descuento: ", fuenteTitulo, "RIGHT");
		Label lDescuento = new Label(formatoDecimales.format(order.getvalorDescuento()) + " €",	fuenteDatos);
		tique.add(lTituloDescuento);
		tique.add(lDescuento);
		Label lTituloAtendido = new Label("Le atendió: ", fuenteTitulo, "RIGHT");
		Label lAtendido = new Label(order.getTrabajador().getName(), fuenteTitulo);
		tique.add(lTituloAtendido);
		tique.add(lAtendido);
		add(tique, BorderLayout.CENTER);
		JPanel panelbotones = new JPanel(new FlowLayout(FlowLayout.CENTER,30,0));
		Boton buttonPaidOut = new Boton(iconCaja);
		buttonPaidOut.setActionCommand("PaidOut");
		buttonPaidOut.addActionListener(gestorBotones);
		panelbotones.add(buttonPaidOut);
		Boton buttonSalirTique = new Boton(iconBack);
		buttonSalirTique.setActionCommand("SalirPanelTique");
		buttonSalirTique.addActionListener(gestorBotones);
		panelbotones.add(buttonSalirTique);
		add(panelbotones, BorderLayout.SOUTH);
		setVisible(false);
	}
*/
	// Constructor for reports panel
	public Panel(int i) {
		setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		ImageIcon iconProductsReport = new ImageIcon("src/gui/images/Report.png", "Products Report");
		ImageIcon iconOrderReport = new ImageIcon("src/gui/images/Report.png", "Order Report");
		ImageIcon iconWorkerReport = new ImageIcon("src/gui/images/Report.png", "Worker Report");
		Boton bProductosReport = new Boton(iconProductsReport);
		Boton bOrderReport = new Boton(iconOrderReport);
		Boton bWorkerReport = new Boton(iconWorkerReport);
		bWorkerReport.setEnabled(false);
		// no lo muestro por que no se ve el botón BACK y provoca que todo falle
		add(bProductosReport);
		add(bOrderReport);
		add(bWorkerReport);
		bProductosReport.setActionCommand("OpenProductsReport");
		bProductosReport.addActionListener(gestorBotones);

		bOrderReport.setActionCommand("OpenOrderReport");
		bOrderReport.addActionListener(gestorBotones);
		bWorkerReport.addActionListener(gestorBotones);
		Boton buttonSalirReports = new Boton(iconBack);
		buttonSalirReports.setActionCommand("SalirPanelReports");
		buttonSalirReports.addActionListener(gestorBotones);
		add(buttonSalirReports);
		setVisible(false);
	}
	
	
	//Constructor for products report panel using a JTable
	public Panel(List<Productos> productos) {
		setLayout(new BorderLayout());
		String [] nombresColumnas = {"id", "Denomination", "Section", "Ingredients", "Price", "LowPrice"};
		Class [] tipoColumnas = {java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,java.lang.Boolean.class};
		ModeloTablaProductos tModelProductos = new ModeloTablaProductos(productos, nombresColumnas, tipoColumnas);
		JTable tableProductos = new JTable(tModelProductos);
		tableProductos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane panelScrollProductosReport = new JScrollPane(tableProductos,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(panelScrollProductosReport,BorderLayout.NORTH);
		Boton buttonBackProductReport = new Boton(iconBack);
		buttonBackProductReport.setActionCommand("BackReportsFromProductsReport");
		buttonBackProductReport.addActionListener(gestorBotones);
		add(buttonBackProductReport, BorderLayout.SOUTH);	
	}
	
	//Constructor for order report panel using JTable
	public Panel(List<Pedido> pedidos, String str) {
		setLayout(new BorderLayout());
		String [] nombresColumnas = {"id", "Date", "Price", "PriceWhitoutTaxes", "worker", "discount", "Destiny"};
		Class [] tipoColumnas = {java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class};
		ModeloTablaPedidos tModelPedidos = new ModeloTablaPedidos(pedidos, nombresColumnas, tipoColumnas);
		JTable tablePedidos = new JTable(tModelPedidos);
		tablePedidos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane panelScrollPedidosReport = new JScrollPane(tablePedidos,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(panelScrollPedidosReport, BorderLayout.NORTH);
		Boton buttonBackPedidosReport = new Boton(iconBack);
		buttonBackPedidosReport.setActionCommand("BackReportsFromOrderReport");
		buttonBackPedidosReport.addActionListener(gestorBotones);
		add(buttonBackPedidosReport, BorderLayout.SOUTH);	
	}
	
	//Constructor panel for change pago ticket
	public Panel(double totalTique) {
		JTextField txfEntregaDato;
		Label lblCambioDato = new Label("",fuenteDatos,"LEFT");
		KeyListener listener = new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if (!Character.isDigit(e.getKeyChar()) && e.getKeyChar()!='.' && e.getKeyCode() !=10  && e.getKeyCode() !=8) {
					getToolkit().beep();
					JTextField txfOrigen = (JTextField)e.getSource();
					try{
						txfOrigen.setText(txfOrigen.getText(0,txfOrigen.getText().length()-1));
					}
					catch(Exception e2) {
						System.out.println(e2.toString());
					};
					e.consume();
				}else {
						if (e.getKeyCode() == 10 || e.getKeyChar() == KeyEvent.VK_TAB) {  //si pulsa enter o tabulador
							//Calcular cambio
							double cambio = Double.parseDouble(((JTextField)e.getSource()).getText()) - totalTique;
							cambio = (double)Math.round(cambio *100) /100d; //redondeo
							
							if(cambio < 0) {
								JOptionPane.showMessageDialog(null, "No es suficiente","Paid out" , JOptionPane.INFORMATION_MESSAGE);
							}else {
								lblCambioDato.setText(cambio + "");
							}		
						}
				}
			}
			
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getKeyCode());
				System.out.println(e.getKeyChar());
			}
		};
		
		GridBagConstraints gbc_MiConstraint= new GridBagConstraints();
		setLayout(new GridBagLayout());
		Label lblCobrar = new Label("Cobrar",fuenteTitulo,"LEFT");
		gbc_MiConstraint.gridx = 0;		 //columna inicial que ocupa
		gbc_MiConstraint.gridy = 0;		 //fila inicial que ocupa
		gbc_MiConstraint.gridwidth = 1;  //Columnas que ocupa
		gbc_MiConstraint.gridheight = 1; //Filas que ocupa
		gbc_MiConstraint.fill = GridBagConstraints.HORIZONTAL; //ajusta el componente a la celda
		gbc_MiConstraint.insets = new Insets(0, 10, 10, 0); //Espacio hasta los bordes del componente en la celda
														  //top, left, botton, right
		add(lblCobrar, gbc_MiConstraint);
		Label lblTotal = new Label("Total:",fuenteDatos,"LEFT");		 
		gbc_MiConstraint.gridy = 1;		  
		add(lblTotal, gbc_MiConstraint);
		Label lblEntrega = new Label("Entrega:",fuenteDatos,"LEFT");		
		lblEntrega.addKeyListener(listener);
		gbc_MiConstraint.gridy = 2;		  
		add(lblEntrega, gbc_MiConstraint);
		Label lblCambio = new Label("Cambio:",fuenteDatos,"LEFT");		 
		gbc_MiConstraint.gridy = 3;		  
		add(lblCambio, gbc_MiConstraint);
		Label lblTotalDato = new Label(totalTique + "",fuenteDatos,"LEFT");		 
		gbc_MiConstraint.gridx = 1;		  
		gbc_MiConstraint.gridy = 1;
		add(lblTotalDato, gbc_MiConstraint);
		txfEntregaDato = new JTextField(50);		 		  
		gbc_MiConstraint.gridy = 2;
		txfEntregaDato.addKeyListener(listener);
		add(txfEntregaDato, gbc_MiConstraint);
		gbc_MiConstraint.gridy = 3;
		add(lblCambioDato, gbc_MiConstraint);
		Boton btnAceptar = new Boton(iconAceptar);
		gbc_MiConstraint.gridx = 0;
		gbc_MiConstraint.gridy = 4;
		add(btnAceptar, gbc_MiConstraint);
		btnAceptar.addActionListener(gestorBotones);
		btnAceptar.setActionCommand("Acepta cobro");
		
	}
	
	/**
	 * @param precio
	 * @return
	 * Actualiza el valor de variable subtotal o la resetea 0.0, lo visualiza en el Jlabel y devuelve el valor 
	 */
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
		pedidoEnCurso.setTrabajador(Frame.InstanceFPizzerie.getTrabajadorValidado());
		// calc total price and discounts
		Frame.InstanceFPizzerie.myPizzerie.calculoPrecioPedido(pedidoEnCurso);
		System.out.println("Rellenado pedido " + pedidoEnCurso);
	}
	
	/**
	 * @param boton
	 * @param pedido
	 * se devuelve al botón de la mesa, barra...???? el pedido modificado
	 * tendría que pasar el pedido al objeto
	 */
	public void upgradePedidoEnCurso(BotonPizzeriaMesas boton, Pedido pedido) {

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
	

	/**
	 * @param pedido
	 * Calcula el precio total de los productos del pedido sin impuestos
	 * si algún producto está promocionado descuenta antes de aplicar impuestos
	 * guarda en el objeto pedido los valores de descuento, valor sin impuestos 
	 * y valor total del pedido redondeados a dos decimales
	 */
	public void calculoPrecioPedido(Pedido pedido) {
		double precioSinImpuestos = 0.0d;
		double valorDescuento = 0.0d;
		for (Productos p : pedido.getOrderFoods()) { // el for recorre la lista de comidas del pedido
			if (p.isLowPrice()) {
				valorDescuento += p.getPrice() * Restaurant.getDescuento() / 100;
				precioSinImpuestos += (p.getPrice()- (p.getPrice() * Restaurant.getDescuento() / 100));
			} else
				precioSinImpuestos += p.getPrice();
		}
		pedido.setValorDescuento((double)Math.round(valorDescuento*100) / 100d);
		pedido.setOrderPriceWithoutTaxes((double)Math.round(precioSinImpuestos*100) /100d);
		pedido.setOrderPrice((double)Math.round((precioSinImpuestos + precioSinImpuestos * Restaurant.getImpuestos() / 100)*100) / 100);
	}
	
	public void imprimirPanelTicket() {
		
	}
	public static void setSubtotal(Double subtotal) {
		Panel.subtotal = subtotal;
	}

	public Label getLsubtotalOrder() {
		return lsubtotalOrder;
	}

	public Boton getButtonTique() {
		return buttonTique;
	}

	public void setButtonTique(Boton buttonTique) {
		this.buttonTique = buttonTique;
	}

	public static BotonPizzeriaMesas getBotonMesa() {
		return botonMesa;
	}

	public static void setBotonMesa(BotonPizzeriaMesas botonMesa) {
		Panel.botonMesa = botonMesa;
	};
	public void setLsubtotalOrder(Label lsubtotalOrder) {
		this.lsubtotalOrder = lsubtotalOrder;
	}

	public void paidOut(Pedido pedidoEnCurso) {
		pedidoEnCurso.setPedidoCobrado(true);
	}

	public Label getlDestinoPedidoEnCurso() {
		return lDestinoPedidoEnCurso;
	}

	public void setlDestinoPedidoEnCurso(Label lDestinoPedidoEnCurso) {
		this.lDestinoPedidoEnCurso = lDestinoPedidoEnCurso;
	}
	
}