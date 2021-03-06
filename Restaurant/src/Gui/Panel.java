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
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;
import com.mysql.cj.x.protobuf.MysqlxPrepare;
import com.sun.jarsigner.ContentSignerParameters;

/**
 * @author Juan Jos? C?rdenas
 * Se definen los constructores de gran parte de los paneles que se utilizan
 */

public class Panel extends JPanel {
	private GestorBotones gestorBotones = new GestorBotones();
	private GestorRaton gestorRaton = new GestorRaton();
	private GestorEventosFoco gestorEventosFoco = new GestorEventosFoco();
	private static BotonRestauranteMesas botonMesa;  //Identifica el bot?n de la mesa pulsado 
	private static Double subtotal = 0.0;
	private Label lsubtotalOrder, lDestinoPedidoEnCurso;
	private Boton buttonTique;  //bot?n para mostrar el tique de pedido. No siempre visible
	//private XmlDoc myXmlDoc;	//objeto XmlDoc relativo al documento config.xml
	

	ImageIcon iconOperate = new ImageIcon("src/Gui/images/operar.jpg", "Operate");
	ImageIcon iconConf = new ImageIcon("src/Gui/images/conf.png", "Configuration");
	ImageIcon iconReports = new ImageIcon("src/Gui/images/informes.png", "Reports");
	ImageIcon iconBar = new ImageIcon("src/Gui/images/barra.jpg", "Bar");
	ImageIcon iconDelivery = new ImageIcon("src/gui/images/Delivery.jpg", "Delivery");
	ImageIcon iconIntTable = new ImageIcon("src/gui/images/ExtTable.png", "Int. Table");
	ImageIcon iconExtTable = new ImageIcon("src/gui/images/intTable.jpg", "Ext. Table");
	ImageIcon iconBack = new ImageIcon("src/gui/images/Back.png", "Back");
	ImageIcon iconTique = new ImageIcon("src/gui/images/tique.png", "Tique");
	ImageIcon iconCaja = new ImageIcon("src/gui/images/caja.png", "Cash");
	ImageIcon iconImprimir = new ImageIcon("src/gui/images/imprimir.png", "Print");
	ImageIcon iconAceptar = new ImageIcon("src/gui/images/aceptar2.png", "Apply");
	ImageIcon iconInsert = new ImageIcon("src/gui/images/insert2.png", "Insertion");
	Font fuenteTitulo = new Font("arial", Font.BOLD, 20);
	Font fuenteDatos = new Font("arial", Font.PLAIN, 12);
	DateFormat formatoFechaHora = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
	DecimalFormat formatoDecimales = new DecimalFormat("###,###.##"); // formatea un double a String truncando seg?n el formato

	// Constructor of main panel principal
	public Panel() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 25));
		Boton bOperate = new Boton(iconOperate);
		bOperate.setActionCommand("AbrirPanelValida");
		Boton bConf = new Boton(iconConf);
		bConf.setActionCommand("AbrirPanelConfiguracion");
		Boton bReport = new Boton(iconReports);
		bReport.setActionCommand("AbrirPanelReports");
		Boton bInsert = new Boton(iconInsert);
		bInsert.setActionCommand("OpenPanelInsert");
		add(bOperate);
		add(bConf);
		add(bReport);
		add(bInsert);
		bOperate.addActionListener(gestorBotones);
		bConf.addActionListener(gestorBotones);
		bReport.addActionListener(gestorBotones);
		bInsert.addActionListener(gestorBotones);
		setVisible(true);
	}
	

	// Constructor for bar zones, tables and deliveries panel
	public Panel(int barzones, int deliverys, int intTables, int extTables) {
		setLayout(new BorderLayout());
		Label titulo = new Label("Seleccione una opci?n para crear pedido", fuenteTitulo, "CENTER");
		add(titulo, BorderLayout.NORTH);
		JPanel panelMesas = new JPanel();
		// creaci?n de botones de zonas barra
		for (int i = 0; i < barzones; i++) {
			Boton buttonBar = new BotonRestauranteMesas(iconBar, i+1, Zone.Bar);
			panelMesas.add(buttonBar);
			buttonBar.addActionListener(gestorBotones);
			buttonBar.addMouseListener(gestorRaton);
		}
		// Creaci?n de botones mesas interiores
		for (int i = 0; i < intTables; i++) {
			Boton buttonIntTables = new BotonRestauranteMesas(iconIntTable, i+1, Zone.IntTable);
			panelMesas.add(buttonIntTables);
			buttonIntTables.addActionListener(gestorBotones);
			buttonIntTables.addMouseListener(gestorRaton);
		}
		// Creaci?n de botones de mesas exteriores
		for (int i = 0; i < extTables; i++) {
			Boton buttonExtTables = new BotonRestauranteMesas(iconExtTable, i+1, Zone.ExtTable);
			panelMesas.add(buttonExtTables);
			buttonExtTables.addActionListener(gestorBotones);
			buttonExtTables.addMouseListener(gestorRaton);
		}
		// Creaci?n de botones Reparto
		for (int i = 0; i < deliverys; i++) {
			Boton buttonDelivery = new BotonRestauranteMesas(iconDelivery, i+1, Zone.Delivery);
			panelMesas.add(buttonDelivery);
			buttonDelivery.addActionListener(gestorBotones);
			buttonDelivery.addMouseListener(gestorRaton);
		}
		add(panelMesas, BorderLayout.CENTER);
		Boton buttonBack = new Boton(iconBack);
		add(buttonBack, BorderLayout.SOUTH);
		buttonBack.setActionCommand("SalirPanelMesas");
		buttonBack.addActionListener(gestorBotones);
		setVisible(false);
	}

	/**
	 * Constructor for products panel. Create list of Productos order in Tabs orders by the Enum Section
	 * Selected product is asociated to the table in or out, bar zone or delivery selected in tables... panel
	 * @param productos List of Productos
	 * @param botonMesaPMesas Bot?n de mesa, zona de barra o reparto elegida por el usuario
	 */
	public Panel(List<Productos> productos, BotonRestauranteMesas botonMesaPMesas) {
		botonMesa = botonMesaPMesas;  //se carga la variable est?tica con los datos de la mesa en curso
		setLayout(new BorderLayout());
		lDestinoPedidoEnCurso = new Label("Order " + botonMesaPMesas.getDestinoBoton().getDestinationDenomination(),fuenteTitulo, "CENTER");
		add(lDestinoPedidoEnCurso, BorderLayout.NORTH);
		JTabbedPane produ = new JTabbedPane();
		// se crean los botones en el panel de productos en pesta?as creadas de acuerdo
		// a la secci?n del producto (contenido del enum Section)
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
		lsubtotalOrder = new Label("Subtotal Order:" + formatoDecimales.format(subtotal) + " ?", fuenteTitulo);
		botones.add(lsubtotalOrder);
		buttonTique = new Boton(iconTique);
		buttonTique.addActionListener(gestorBotones);
		buttonTique.setActionCommand("OpenTiquePanel");
		buttonTique.setVisible(false);
		botones.add(buttonTique);
		//el bot?n back devuelve el pedido a la variable pedido del boton de mesas
		Boton buttonSalirProductos = new BotonRestauranteMesas(iconBack, 1, botonMesa.getPedidoBoton());
		buttonSalirProductos.addActionListener(gestorBotones);
		buttonSalirProductos.setActionCommand("SalirPanelProductos");
		botones.add(buttonSalirProductos);
		add(botones, BorderLayout.SOUTH);
		setVisible(false);
	}

	/**
	 * Constructor for reports panel
	 * @param i not used
	 */
	public Panel(int i) {
		setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		ImageIcon iconProductsReport = new ImageIcon("src/gui/images/Report.png", "Products Report");
		ImageIcon iconOrderReport = new ImageIcon("src/gui/images/Report.png", "Order Report");
		ImageIcon iconWorkerReport = new ImageIcon("src/gui/images/Report.png", "Worker Report");
		Boton bProductosReport = new Boton(iconProductsReport);
		Boton bOrderReport = new Boton(iconOrderReport);
		Boton bWorkerReport = new Boton(iconWorkerReport);
		add(bProductosReport);
		add(bOrderReport);
		add(bWorkerReport);
		bProductosReport.setActionCommand("OpenProductsReport");
		bProductosReport.addActionListener(gestorBotones);
		bOrderReport.setActionCommand("OpenOrdersReport");
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
	 * Constructor for products report panel using a JTable
	 * @param productos List of objects Productos
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
	 * Constructor for order report panel using JTable. Collet data from a List
	 * @param pedidos Lista de pedidos 
	 * @param str no se utiliza
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
	 * Genera un panel que contiene una tabla con la lista de trabajadores
	 * @param workers Lista de trabajadores
	 * @param str1 No se utiliza
	 * @param str2 No se utiliza
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
	/**
	 * Constructor for report panel using JTable. Collet data from a database based on a sql. Used for products, workers and orders 
	 * @param str the title of the Panel
	 */
	public Panel(String sql) throws SQLException {
		setLayout(new BorderLayout());
		ResultSet rst = Test.conex.colletSQl(sql);
		DefaultTableModel tableModel = new DefaultTableModel();
		ResultSetMetaData metaData = rst.getMetaData();
		// get column names from metadata and put in table model
		int columnCount = metaData.getColumnCount();
		for(int columnIndex =1; columnIndex<=columnCount; columnIndex++) {
			tableModel.addColumn(metaData.getColumnLabel(columnIndex)); //add colum to table model
		}
		// extraction of data
		Object[] row = new Object[columnCount];
		while(rst.next()) {
			for (int i = 0; i< columnCount;i++) {
				row[i] = rst.getObject(i+1);
			}
			tableModel.addRow(row); // add row data to table model
		}
		JTable tableData = new JTable(tableModel);
		
		tableData.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane panelScrollPedidosReport = new JScrollPane(tableData,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(panelScrollPedidosReport, BorderLayout.NORTH);
		Boton buttonBackPedidosReport = new Boton(iconBack);
		buttonBackPedidosReport.setActionCommand("BackReportsFromReportSql");
		buttonBackPedidosReport.addActionListener(gestorBotones);
		add(buttonBackPedidosReport, BorderLayout.SOUTH);	
	}
	
	/**
	 * Este panel muestra el total del tique y proporciona al usuario el dinero a devolver
	 * @param totalTique el total del tique a cobrar
	 * Constructor panel for change and collect money ticket)
	 */
	public Panel(double totalTique) {
		JTextField txfEntregaDato;
		Label lblCambioDato = new Label("",fuenteDatos,"LEFT");
		FocusListener focusListener = new FocusListener() {	
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(((JTextField)e.getSource()).getName().equals("txfEntrega")) {
					System.out.println("Cantidad entregada pierde foco");
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
						Frame.log.Escritura(e2.toString());
					};
					e.consume();
				}else {
						if (e.getKeyCode() == 10 || e.getKeyChar() == KeyEvent.VK_TAB) {  //si pulsa enter o tabulador (tabulador no funciona)
							calculaCambio(totalTique,((JTextField)e.getSource()).getText(),lblCambioDato);
						}
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				//System.out.println(e.getKeyCode());
				//System.out.println(e.getKeyChar());
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
	 * Crea o visualiza el panel de Productos asoci?ndolo al bot?n de la mesa, zona de barra o reparto seleccionado
	 * Si hay un pedido pendiente en el bot?n asociado carga los datos para visualizarlos  
	 */
	public void abrirPanelProductos() {
		if (Frame.InstanceFRestaurant.panelProductos == null) {
			System.out.println("nuevo panel productos");
			Frame.log.Escritura("Cantidad entregada pierde foco");
			Frame.InstanceFRestaurant.panelProductos = new Panel(Frame.InstanceFRestaurant.myRestaurant.foods,Panel.getBotonMesa());
		} else { // ya existe el panel de productos
			if (Frame.InstanceFRestaurant.panelMesas.checkForOpenOrder(Panel.getBotonMesa().getPedidoBoton())) { 
				// hay un pedido pendiente de la mesa, zona de barra o reparto asociado al bot?n
				// carga total pedido en curso en variable est?tica
				Panel.setSubtotal(Panel.getBotonMesa().getPedidoBoton().getOrderPriceWithoutTaxes());
				// Actualiza los valores de total pedido en curso y muestra el boton tique
				Frame.InstanceFRestaurant.getPanelProductos().getLsubtotalOrder().setText(
						"Subtotal Order: " + Panel.getBotonMesa().getPedidoBoton().getOrderPriceWithoutTaxes() + " ?");
				Frame.InstanceFRestaurant.getPanelProductos().getButtonTique().setVisible(true);
			}
		}
		// actualiza el texto del label que indica la mesa, zona de barra o reparto que se est? tratando
		Frame.InstanceFRestaurant.getPanelProductos().getlDestinoPedidoEnCurso().setText(Panel.getBotonMesa().getDestinoBoton().getDestinationDenomination());
		Frame.InstanceFRestaurant.getPanelProductos().getlDestinoPedidoEnCurso().repaint();
		// Visualiza panel productos y oculta panel mesas
		Frame.InstanceFRestaurant.cambiaPanel(Frame.InstanceFRestaurant.panelProductos,	Frame.InstanceFRestaurant.panelMesas);
	}

	/**
	 * Si ha quedado pedido sin cerrar cambia el aspecto del bot?n asociado a la mesa, zona de barra o reparto 
	 * Resetea variables que contienen datos del pedido en curso 
	 * @param e evento que genera el boton Back de panel productos. Contiene el contenido del pedido
	 * en curso si el usuario no lo ha cerrado
	 */
	public void cerrarPanelProductos(ActionEvent e) {
		if (Panel.getBotonMesa().getPedidoBoton() != null) { // Se ha insertado producto/s al pedido
			// rellena datos del pedido (el operador a?ade productos sin ver el tique)
			Frame.InstanceFRestaurant.panelProductos.upgradeOrderData(Panel.getBotonMesa().getPedidoBoton());
			Panel.getBotonMesa().setBackground(Color.MAGENTA);
			// guarda los datos en el bot?n del panel de mesas
			((BotonRestauranteMesas) e.getSource()).setPedidoBoton(Panel.getBotonMesa().getPedidoBoton());
		}
		reseteaVariablesEstaticas(); 
		// back to bar and tables panel
		Frame.InstanceFRestaurant.cambiaPanel(Frame.InstanceFRestaurant.panelMesas,	Frame.InstanceFRestaurant.panelProductos);
	}

	/**
	 * A?ade el pedido a la lista y lo marca como pagado Muestra panelcobro para
	 * insertar el dinero entregado y ofrecer el cambio muestra bot?n para imprimir
	 * el tique
	 */
	public void pagarPedido() {
		// store to pizzeria order list and mark as paid
		Frame.InstanceFRestaurant.getPanelProductos().paidOut(Panel.getBotonMesa().getPedidoBoton());
		//Al guardar en la base de datos ?no ser?a necesaria la lista de pedidos?
		//Se podr?a guardar en la lista si no hay conexi?n con la base de datos y guardar los datos de esta cada cierto tiempo
		Frame.InstanceFRestaurant.myRestaurant.getOrders().add(Panel.getBotonMesa().getPedidoBoton());
		Panel.getBotonMesa().setBackground(Color.LIGHT_GRAY);
		if(Test.conex.isConnected()) {
			//Insertar pedido en la base de datos
			try {
				ResultSetMetaData rsmdOrder = Test.conex.metadataTable("orders");  //obtain metadata from table
				Object[] valuesOrder = new Object[rsmdOrder.getColumnCount()];
				valuesOrder = Test.conex.valuesToArray(Panel.getBotonMesa().getPedidoBoton(), rsmdOrder.getColumnCount());	// get values of a object ant put on a array
				Test.conex.insertDataOnTableBd(valuesOrder,"orders",rsmdOrder); //insert data into database table
			}catch (Exception e){
				System.out.println("Order insertion" + e.getMessage() + e.getStackTrace().toString());
				Frame.log.Escritura("Order insertion" + e.getMessage() + e.getStackTrace());
			};			
			Test.conex.insertTableordersProductsBD(Panel.getBotonMesa().getPedidoBoton());	
		}
		// crea panel cobro y lo visualiza
		Frame.InstanceFRestaurant.panelCobro = new Panel(Panel.getBotonMesa().getPedidoBoton().getOrderPrice());
		Frame.InstanceFRestaurant.panelticket.setVisible(false);
		Frame.InstanceFRestaurant.add(Frame.InstanceFRestaurant.panelCobro, BorderLayout.CENTER);
		Frame.InstanceFRestaurant.panelCobro.setVisible(true);
		// reset de variables est?ticas
		Panel.getBotonMesa().setPedidoBoton(null);// resetea el pedido guardado
		reseteaVariablesEstaticas();
	}
	/**
	 * Resetea varibles est?tica botonMesa para cambiar de mesa...
	 * Resetea valor de subtotal en panel productos
	 */
	public void reseteaVariablesEstaticas() {
		Panel.setBotonMesa(null);
		Frame.InstanceFRestaurant.getPanelProductos().IncrementaSutotal(0.0);
		Frame.InstanceFRestaurant.panelProductos.getButtonTique().setVisible(false);
	}

	/**
	 * Este procedimiento a?ade el producto a la lista de productos del pedido en curso, 
	 * incrementa el valor del total pedido. Tambi?n habilita el bot?n Tique.
	 * @param e evento que genera el click del bot?n del producto que el usuario selecciona
	 * en el panel de productos
	 * 
	 */
	public void anyadeProducto(ActionEvent e) {
		if (Panel.getBotonMesa().getPedidoBoton() == null) {// crear pedido y a?adir el producto
			Panel.getBotonMesa().setPedidoBoton(new Pedido(Panel.getBotonMesa().getDestinoBoton()));
		}
		// Add product to the order object list
		Panel.getBotonMesa().getPedidoBoton().getOrderFoods().add(((Boton) e.getSource()).getProducto());
		// incrementa el precio del pedido, lo guarda en el bot?n y actualiza el label
		Panel.getBotonMesa().getPedidoBoton().setOrderPriceWithoutTaxes(Frame.InstanceFRestaurant.panelProductos
				.IncrementaSutotal(((Boton) e.getSource()).getProducto().getPrice()));
		// make visible Tique button
		Frame.InstanceFRestaurant.getPanelProductos().getButtonTique().setVisible(true);
		System.out.println("A?ade producto al pedido.");
		Frame.log.Escritura("A?ade producto al pedido.");
	}

	/**
	 * @param precio del producto a?adido al pedido
	 * @return la suma de productos a?adidos al pedido
	 * Actualiza el valor de variable subtotal o la resetea 0.0, lo visualiza en el Jlabel y devuelve el valor 
	 */
	public double IncrementaSutotal(Double precio) {
		if (precio != 0.0) {
			subtotal += precio;
		} else
			subtotal = precio;
		lsubtotalOrder.setText("Subtotal Order:" + formatoDecimales.format(subtotal) + " ?");
		lsubtotalOrder.repaint();
		return subtotal;
	}
	
	//rellena los datos del pedido con el precio con impuestos y descuentos, el camarero o repartidor
	public void upgradeOrderData(Pedido pedidoEnCurso) {
		pedidoEnCurso.setOrderPriceWithoutTaxes(subtotal); // add total price whitout taxes
		pedidoEnCurso.setTrabajador(Frame.InstanceFRestaurant.getTrabajadorValidado());
		// calc total price and discounts
		Frame.InstanceFRestaurant.myRestaurant.calculoPrecioPedido(pedidoEnCurso);
		System.out.println("Rellenado pedido " + pedidoEnCurso);
		Frame.log.Escritura("Rellenado pedido " + pedidoEnCurso);
	}
	
	/**
	 * @param boton Boton correspondiente a la mesa, zona de barra o reparto
	 * @param pedido Pedido en curso correspondiente a la mesa, zona de barra o reparto elegido por el usuario
	 * se asina al bot?n de la mesa, zona de barra o reparto el pedido modificado
	 */
	public void upgradePedidoEnCurso(BotonRestauranteMesas boton, Pedido pedido) {
		boton.setPedidoBoton(pedido);
	}
	
	/**
	 * @param pedidoEnCurso Pedido asociado al bot?n de la mesa, zona de barra o reparto elegido por el usuario
	 * @return Devuelve false si no hay un pedido en curso para el bot?n de la mesa, zona de barra o reparo elegido por el usuario
	 * Se comprueba si hay un pedido asociado al bot?n de mesa, zona de barra o reparto
	 */
	public Boolean checkForOpenOrder(Pedido pedidoEnCurso) {
		if (pedidoEnCurso != null  && pedidoEnCurso.getOrderPrice() != 0.0f && pedidoEnCurso.isPedidoCobrado() != true) {
			System.out.println("Boton mesa pulsado. Ya tiene datos");
			return true;
		}else {
			System.out.println("Boton mesa pulsado. No tiene datos");
			Frame.log.Escritura("Boton mesa pulsado. No tiene datos");
			return false;
		}
	}
	

	/**
	 * @param pedido
	 * Calcula el precio total de los productos del pedido sin impuestos
	 * si alg?n producto est? promocionado descuenta antes de aplicar impuestos
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

	public static BotonRestauranteMesas getBotonMesa() {
		return botonMesa;
	}

	public static void setBotonMesa(BotonRestauranteMesas botonMesa) {
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