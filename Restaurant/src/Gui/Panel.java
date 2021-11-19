package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.sun.jarsigner.ContentSignerParameters;

/**
 * @author Juan José Cárdenas
 * Se definen los constructores de gran parte de los paneles que se utilizan
 */
/**
 * @author Operador
 *
 */
public class Panel extends JPanel {
	private GestorBotones gestorBotones = new GestorBotones();
	private GestorRaton gestorRaton = new GestorRaton();
	private GestorEventosFoco gestorEventosFoco = new GestorEventosFoco();
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
	/**
	 * @param productos Lista de productos
	 * @param botonMesaPMesas Botón de mesa, zona de barra o reparto elegida por el usuario
	 * Constructor for productos panel. Se genera lista de productos ordenados en pestañas por la
	 * categoría del producto. El producto/s elegido/s se asocian a la mesa, zona de barra o reparto elegido
	 */
	public Panel(List<Productos> productos, BotonPizzeriaMesas botonMesaPMesas) {
		botonMesa = botonMesaPMesas;  //se carga la variable estática con los datos de la mesa en curso
		setLayout(new BorderLayout());
		lDestinoPedidoEnCurso = new Label("Order " + botonMesaPMesas.getDestinoBoton().getDestinationDenomination(),fuenteTitulo, "CENTER");
		add(lDestinoPedidoEnCurso, BorderLayout.NORTH);
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
					buttonProduct.addMouseListener(gestorRaton);
				}
			}
		}
		add(produ, BorderLayout.CENTER);
		JPanel botones = new JPanel();
		lsubtotalOrder = new Label("Subtotal Order:" + formatoDecimales.format(subtotal), fuenteTitulo);
		botones.add(lsubtotalOrder);
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

	/**
	 * @param i no se utiliza
	 * Constructor for reports panel
	 */
	public Panel(int i) {
		setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		ImageIcon iconProductsReport = new ImageIcon("src/gui/images/Report.png", "Products Report");
		ImageIcon iconOrderReport = new ImageIcon("src/gui/images/Report.png", "Order Report");
		ImageIcon iconWorkerReport = new ImageIcon("src/gui/images/Report.png", "Worker Report");
		Boton bProductosReport = new Boton(iconProductsReport);
		Boton bOrderReport = new Boton(iconOrderReport);
		Boton bWorkerReport = new Boton(iconWorkerReport);
		//bWorkerReport.setEnabled(false);
		add(bProductosReport);
		add(bOrderReport);
		add(bWorkerReport);
		bProductosReport.setActionCommand("OpenProductsReport");
		bProductosReport.addActionListener(gestorBotones);

		bOrderReport.setActionCommand("OpenOrderReport");
		bOrderReport.addActionListener(gestorBotones);
		bWorkerReport.setActionCommand("OpenWorkersReport");
		bWorkerReport.addActionListener(gestorBotones);
		Boton buttonSalirReports = new Boton(iconBack);
		buttonSalirReports.setActionCommand("SalirPanelReports");
		buttonSalirReports.addActionListener(gestorBotones);
		add(buttonSalirReports);
		setVisible(false);
	}
	
	/**
	 * @param productos Lista de productos
	 * Constructor for products report panel using a JTable
	 */
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
 
	/**
	 * @param pedidos Lista de pedidos 
	 * @param str no se utiliza
	 * Constructor for order report panel using JTable
	 */
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
	
	/**
	 * @param workers Lista de trabajadores
	 * @param str1 No se utiliza
	 * @param str2 No se utiliza
	 * Genera un panel que contiene una tabla con la lista de trabajadores
	 */
	public Panel(List<Trabajador> workers, String str1, String str2) {
		setLayout(new BorderLayout());
		String [] nombresColumnas = {"id", "Name", "Surnames", "Telephone", "Dni", "salary", "Password"};
		Class [] tipoColumnas = {java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class};
		ModeloTablaTrabajadores tModelTrabajadores = new ModeloTablaTrabajadores(workers, nombresColumnas, tipoColumnas);
		JTable tableTrabajadores = new JTable(tModelTrabajadores);
		tableTrabajadores.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane panelScrollPedidosReport = new JScrollPane(tableTrabajadores,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(panelScrollPedidosReport, BorderLayout.NORTH);
		Boton buttonBackPedidosReport = new Boton(iconBack);
		buttonBackPedidosReport.setActionCommand("BackReportsFromWorkersReport");
		buttonBackPedidosReport.addActionListener(gestorBotones);
		add(buttonBackPedidosReport, BorderLayout.SOUTH);	
	}
	
	//
	/**
	 * @param totalTique el total del tique a cobrar
	 * Constructor panel for change and collect money ticket)
	 * Este panel muestra el total del tique y proporciona al usuario el dinero a devolver
	 */
	public Panel(double totalTique) {
		JTextField txfEntregaDato;
		Label lblCambioDato = new Label("",fuenteDatos,"LEFT");
		FocusListener focusListener = new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(((JTextField)e.getSource()).getName().equals("txfEntrega")) {
					System.out.println("canbidad entregada pierde foco");
					calculaCambio(totalTique,((JTextField)e.getSource()).getText(),lblCambioDato);
					
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
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
							calculaCambio(totalTique,((JTextField)e.getSource()).getText(),lblCambioDato);
							
						/*	double cambio = Double.parseDouble(((JTextField)e.getSource()).getText()) - totalTique;
							cambio = (double)Math.round(cambio *100) /100d; //redondeo
							if(cambio < 0) {
								JOptionPane.showMessageDialog(null, "No es suficiente","Paid out" , JOptionPane.INFORMATION_MESSAGE);
							}else {
								lblCambioDato.setText(cambio + "");
							}*/		
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
		txfEntregaDato.setText("0.00");
		txfEntregaDato.setName("txfEntrega");
		txfEntregaDato.addKeyListener(listener);
		txfEntregaDato.addFocusListener(focusListener);
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
	 * Crea o visualiza el panel de Productos asociándolo al botón de la mesa, zona de barra o reparto seleccionado
	 * Si hay un pedido pendiente en el botón asociado carga los datos para visualizarlos  
	 */
	public void abrirPanelProductos() {
		if (Frame.InstanceFPizzerie.panelProductos == null) {
			System.out.println("nuevo panel productos");
			Frame.InstanceFPizzerie.panelProductos = new Panel(Frame.InstanceFPizzerie.myPizzerie.foods,Panel.getBotonMesa());
		} else { // ya existe el panel de productos
			if (Frame.InstanceFPizzerie.panelMesas.checkForOpenOrder(Panel.getBotonMesa().getPedidoBoton())) { 
				// hay un pedido pendiente de la mesa, zona de barra o reparto asociado al botón
				// carga total pedido en curso en variable estática
				Panel.setSubtotal(Panel.getBotonMesa().getPedidoBoton().getOrderPriceWithoutTaxes());
				// Actualiza los valores de total pedido en curso y muestra el boton tique
				Frame.InstanceFPizzerie.getPanelProductos().getLsubtotalOrder().setText(
						"Subtotal Order: " + Panel.getBotonMesa().getPedidoBoton().getOrderPriceWithoutTaxes());
				Frame.InstanceFPizzerie.getPanelProductos().getButtonTique().setVisible(true);
			}
		}
		// actualiza el texto del label que indica la mesa, zona de barra o reparto que se está tratando
		Frame.InstanceFPizzerie.getPanelProductos().getlDestinoPedidoEnCurso().setText(Panel.getBotonMesa().getDestinoBoton().getDestinationDenomination());
		Frame.InstanceFPizzerie.getPanelProductos().getlDestinoPedidoEnCurso().repaint();
		// Visualiza panel productos y oculta panel mesas
		Frame.InstanceFPizzerie.cambiaPanel(Frame.InstanceFPizzerie.panelProductos,	Frame.InstanceFPizzerie.panelMesas);
	}

	/**
	 * @param e evento que genera el boton Back de panel productos. Contiene el contenido del pedido
	 * en curso si el usuario no lo ha cerrado
	 * Si ha quedado pedido sin cerrar cambia el aspecto del botón asociado a la mesa, zona de barra o reparto 
	 * Resetea variables que contienen datos del pedido en curso 
	 */
	public void cerrarPanelProductos(ActionEvent e) {
		if (Panel.getBotonMesa().getPedidoBoton() != null) { // Se ha insertado producto/s al pedido
			// rellena datos del pedido (el operador añade productos sin ver el tique)
			Frame.InstanceFPizzerie.panelProductos.upgradeOrderData(Panel.getBotonMesa().getPedidoBoton());
			Panel.getBotonMesa().setBackground(Color.MAGENTA);
			// guarda los datos en el botón del panel de mesas
			((BotonPizzeriaMesas) e.getSource()).setPedidoBoton(Panel.getBotonMesa().getPedidoBoton());
		}
		reseteaVariablesEstaticas(); 
		// back to bar and tables panel
		Frame.InstanceFPizzerie.cambiaPanel(Frame.InstanceFPizzerie.panelMesas,	Frame.InstanceFPizzerie.panelProductos);
	}

	/**
	 * Añade el pedido a la lista y lo marca como pagado Muestra panelcobro para
	 * insertar el dinero entregado y ofrecer el cambio muestra botón para imprimir
	 * el tique
	 */
	public void pagarPedido() {
		// store to pizzeria order list and mark as paid
		Frame.InstanceFPizzerie.getPanelProductos().paidOut(Panel.getBotonMesa().getPedidoBoton());
		Frame.InstanceFPizzerie.myPizzerie.getOrders().add(Panel.getBotonMesa().getPedidoBoton());
		Panel.getBotonMesa().setBackground(Color.LIGHT_GRAY);

		// crea panel cobro y lo visualiza
		Frame.InstanceFPizzerie.panelCobro = new Panel(Panel.getBotonMesa().getPedidoBoton().getOrderPrice());
		Frame.InstanceFPizzerie.panelticket.setVisible(false);
		Frame.InstanceFPizzerie.add(Frame.InstanceFPizzerie.panelCobro, BorderLayout.CENTER);
		Frame.InstanceFPizzerie.panelCobro.setVisible(true);
		// reset de variables estáticas
		Panel.getBotonMesa().setPedidoBoton(null);// resetea el pedido guardado
		reseteaVariablesEstaticas();
	}
	/**
	 * Resetea varibles estática botonMesa para cambiar de mesa...
	 * Resetea valor de subtotal en panel productos
	 */
	public void reseteaVariablesEstaticas() {
		Panel.setBotonMesa(null);
		Frame.InstanceFPizzerie.getPanelProductos().IncrementaSutotal(0.0);
		Frame.InstanceFPizzerie.panelProductos.getButtonTique().setVisible(false);
	}

	public void anyadeProducto(ActionEvent e) {
		if (Panel.getBotonMesa().getPedidoBoton() == null) {// crear pedido y añadir el producto
			Panel.getBotonMesa().setPedidoBoton(new Pedido(Panel.getBotonMesa().getDestinoBoton()));
		}
		// Add product to the order object list
		Panel.getBotonMesa().getPedidoBoton().getOrderFoods().add(((Boton) e.getSource()).getProducto());
		// incrementa el precio del pedido, lo guarda en el botón y actualiza el label
		Panel.getBotonMesa().getPedidoBoton().setOrderPriceWithoutTaxes(Frame.InstanceFPizzerie.panelProductos
				.IncrementaSutotal(((Boton) e.getSource()).getProducto().getPrice()));
		// make visible Tique button
		Frame.InstanceFPizzerie.getPanelProductos().getButtonTique().setVisible(true);
		System.out.println("Añade producto al pedido.");
	}

	/**
	 * @param precio del producto añadido al pedido
	 * @return la suma de productos añadidos al pedido
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
	 * @param boton Boton correspondiente a la mesa, zona de barra o reparto
	 * @param pedido Pedido en curso correspondiente a la mesa, zona de barra o reparto elegido por el usuario
	 * se asina al botón de la mesa, zona de barra o reparto el pedido modificado
	 */
	public void upgradePedidoEnCurso(BotonPizzeriaMesas boton, Pedido pedido) {
		boton.setPedidoBoton(pedido);
	}
	
	/**
	 * @param pedidoEnCurso Pedido asociado al botón de la mesa, zona de barra o reparto elegido por el usuario
	 * @return Devuelve false si no hay un pedido en curso para el botón de la mesa, zona de barra o reparo elegido por el usuario
	 * Se comprueba si hay un pedido asociado al botón de mesa, zona de barra o reparto
	 */
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
	
	/**
	 * @param totalTique total del pedido en curso
	 * @param entrega cantidad que entrega el cliente para pagar
	 * @param jlCambio cantidad que hay que entregar al cliente
	 */
	public void calculaCambio(double totalTique, String entrega, JLabel jlCambio) {
		double cambio = Double.parseDouble(entrega) - totalTique;
		cambio = (double)Math.round(cambio *100) /100d; //redondeo
		if(cambio < 0) {
			JOptionPane.showMessageDialog(null, "No es suficiente","Paid out" , JOptionPane.INFORMATION_MESSAGE);
		}else {
			jlCambio.setText(cambio + "");
		}		
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