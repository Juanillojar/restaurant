package Gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class PanelConfig extends JPanel {
	JTextField tfDatabaseName, tfDatabasePort, tfDatabaseUser,tfDdatabaseHost;
	JTextField tfDatabaseMotor, tfRuta, tfDatabasePass;
	JTextField tfZonesBar, tfZonesIntTables, tfZonesExtTables, tfZonesDelivery;
	JTextField tfCompanyName, tfCompanyAddress, tfCompanyTaxes, tfCompanyDiscount;
	XmlDoc myXmlDoc;
	private GestorBotones gestorBotones = new GestorBotones();
	ImageIcon iconAceptar = new ImageIcon("src/gui/images/aceptar2.png", "Aceptar");
	ImageIcon iconBack = new ImageIcon("src/gui/images/Back.png", "Back");
	Font fuenteTitulo = new Font("arial", Font.BOLD, 20);
	Font fuenteDatos = new Font("arial", Font.PLAIN, 12);
	
	/**
	 * Constructor for Configuration panel
	 */
	public PanelConfig(String[] arrayConfig){

		myXmlDoc = new XmlDoc("config.xml", "src/Gui/");
		setLayout(new BorderLayout());
		JLabel lblTitulo = new Label("Datos de configuración", fuenteTitulo,"CENTER");
		add(lblTitulo,BorderLayout.NORTH);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		//Create panel for tab Database
		JPanel panelDatabase = new JPanel();
		tabbedPane.addTab("Database", null, panelDatabase, "Database data");
		GridBagLayout gbl_panelDatabase = new GridBagLayout();
		panelDatabase.setLayout(gbl_panelDatabase);
		
		GridBagConstraints gbc_MiConstraint= new GridBagConstraints();
		gbc_MiConstraint.gridx = 0;		 //columna inicial que ocupa
		gbc_MiConstraint.gridy = 0;		 //fila inicial que ocupa
		gbc_MiConstraint.gridwidth = 1;  //Columnas que ocupa
		gbc_MiConstraint.gridheight = 1; //Filas que ocupa
		gbc_MiConstraint.fill = GridBagConstraints.HORIZONTAL; //ajusta el componente a la celda
		gbc_MiConstraint.insets = new Insets(0, 50, 10, 0); //Espacio hasta los bordes del componente en la celda														  //top, left, botton, right
		JLabel lblDbMotor = new JLabel("Database motor:");		
		panelDatabase.add(lblDbMotor, gbc_MiConstraint);

		JLabel lblBbName = new JLabel("Database name:");
		gbc_MiConstraint.gridy = 1;		 //fila inicial que ocupa
		panelDatabase.add(lblBbName, gbc_MiConstraint);
		
		JLabel lblDbHost = new JLabel("Database host:");
		gbc_MiConstraint.gridy = 2;		 //fila inicial que ocupa
		panelDatabase.add(lblDbHost, gbc_MiConstraint);
		
		JLabel lblDataBasePort = new JLabel("Port:");
		gbc_MiConstraint.gridy = 3;		 //fila inicial que ocupa
		panelDatabase.add(lblDataBasePort, gbc_MiConstraint);

		JLabel lblDatabaseUser = new JLabel("User:");
		gbc_MiConstraint.gridy = 4;		 //fila inicial que ocupa
		panelDatabase.add(lblDatabaseUser, gbc_MiConstraint);

		JLabel lblDatabasePass= new JLabel("Pass:");
		gbc_MiConstraint.gridy = 5;		 //fila inicial que ocupa
		panelDatabase.add(lblDatabasePass, gbc_MiConstraint);
		
		tfDatabaseMotor = new JTextField(arrayConfig[0]);
		gbc_MiConstraint.gridx = 1;		 //columna inicial que ocupa
		gbc_MiConstraint.gridy = 0;		 //fila inicial que ocupa
		tfDatabaseMotor.setColumns(10);
		tfDatabaseMotor.setEnabled(false);
		panelDatabase.add(tfDatabaseMotor, gbc_MiConstraint);
		
		tfDatabaseName = new JTextField(arrayConfig[1]);
		gbc_MiConstraint.gridy = 1;		 //fila inicial que ocupa
		tfDatabaseName.setColumns(10);
		panelDatabase.add(tfDatabaseName, gbc_MiConstraint);
		
		tfDdatabaseHost = new JTextField(arrayConfig[2]);
		gbc_MiConstraint.gridy = 2;		 //fila inicial que ocupa
		panelDatabase.add(tfDdatabaseHost, gbc_MiConstraint);
		tfDdatabaseHost.setColumns(10);
		
		tfDatabasePort = new JTextField(arrayConfig[3]);
		gbc_MiConstraint.gridy = 3;		 //fila inicial que ocupa
		panelDatabase.add(tfDatabasePort, gbc_MiConstraint);
		tfDatabasePort.setColumns(10);
		
		tfDatabaseUser = new JTextField(arrayConfig[4]);
		gbc_MiConstraint.gridy = 4;		 //fila inicial que ocupa
		panelDatabase.add(tfDatabaseUser, gbc_MiConstraint);
		tfDatabaseUser.setColumns(10);
		
		tfDatabasePass = new JTextField(arrayConfig[5]);
		gbc_MiConstraint.gridy = 5;		 //fila inicial que ocupa
		panelDatabase.add(tfDatabasePass, gbc_MiConstraint);
		tfDatabasePass.setColumns(10);
		
		//Create panel for tab company
		JPanel panelCompany = new JPanel();
		tabbedPane.addTab("Company", null, panelCompany, "Company data");
		GridBagLayout gbl_panelCompany = new GridBagLayout();
		panelCompany.setLayout(gbl_panelCompany);
		
		gbc_MiConstraint.gridx = 0;		 //columna inicial que ocupa
		gbc_MiConstraint.gridy = 0;		 //fila inicial que ocupa
		gbc_MiConstraint.gridwidth = 1;  //Columnas que ocupa
		gbc_MiConstraint.gridheight = 1; //Filas que ocupa
		gbc_MiConstraint.fill = GridBagConstraints.HORIZONTAL; //ajusta el componente a la celda
		gbc_MiConstraint.insets = new Insets(0, 50, 10, 0); //Espacio hasta los bordes del componente en la celda														  //top, left, botton, right
		JLabel lblCompanyName = new JLabel("Company name:");		
		panelCompany.add(lblCompanyName, gbc_MiConstraint);

		JLabel lblCompanyAddress = new JLabel("Companu address:");
		gbc_MiConstraint.gridy = 1;		 //fila inicial que ocupa
		panelCompany.add(lblCompanyAddress, gbc_MiConstraint);
		
		JLabel lblCompanyTaxes = new JLabel("Company Taxes:");
		gbc_MiConstraint.gridy = 2;		 //fila inicial que ocupa
		panelCompany.add(lblCompanyTaxes, gbc_MiConstraint);
		
		JLabel lblCompanyDiscount = new JLabel("Company Discount:");
		gbc_MiConstraint.gridy = 3;		 //fila inicial que ocupa
		panelCompany.add(lblCompanyDiscount, gbc_MiConstraint);

		tfCompanyName = new JTextField(arrayConfig[10]);
		gbc_MiConstraint.gridx = 1;		 //columna inicial que ocupa
		gbc_MiConstraint.gridy = 0;		 //fila inicial que ocupa
		tfCompanyName.setColumns(20);
		panelCompany.add(tfCompanyName, gbc_MiConstraint);
		
		tfCompanyAddress = new JTextField(arrayConfig[11]);
		gbc_MiConstraint.gridy = 1;		 //fila inicial que ocupa
		tfCompanyAddress.setColumns(20);
		panelCompany.add(tfCompanyAddress, gbc_MiConstraint);
		
		tfCompanyTaxes = new JTextField(arrayConfig[12]);
		gbc_MiConstraint.gridy = 2;		 //fila inicial que ocupa
		panelCompany.add(tfCompanyTaxes, gbc_MiConstraint);
		tfCompanyTaxes.setColumns(5);
		
		tfCompanyDiscount = new JTextField(arrayConfig[13]);
		gbc_MiConstraint.gridy = 3;		 //fila inicial que ocupa
		panelCompany.add(tfCompanyDiscount, gbc_MiConstraint);
		tfCompanyDiscount.setColumns(5);
				
		//Create panel for tab zones
		JPanel panelZones = new JPanel();
		tabbedPane.addTab("Zones", null, panelZones, "Zones data");
		GridBagLayout gbl_panelZones = new GridBagLayout();
		panelZones.setLayout(gbl_panelZones);
		
		gbc_MiConstraint.gridx = 0;		 //columna inicial que ocupa
		gbc_MiConstraint.gridy = 0;		 //fila inicial que ocupa
		gbc_MiConstraint.gridwidth = 1;  //Columnas que ocupa
		gbc_MiConstraint.gridheight = 1; //Filas que ocupa
		gbc_MiConstraint.fill = GridBagConstraints.HORIZONTAL; //ajusta el componente a la celda
		gbc_MiConstraint.insets = new Insets(0, 50, 10, 0); //Espacio hasta los bordes del componente en la celda														  //top, left, botton, right
		JLabel lblZonesBar = new JLabel("Number of zones 'Bar':");		
		panelZones.add(lblZonesBar, gbc_MiConstraint);

		JLabel lblZonesIntTables = new JLabel("Number of zones 'Indoor tables':");
		gbc_MiConstraint.gridy = 1;		 //fila inicial que ocupa
		panelZones.add(lblZonesIntTables, gbc_MiConstraint);
		
		JLabel lblZonesExtTables = new JLabel("Number of zones 'Outdoor tables:");
		gbc_MiConstraint.gridy = 2;		 //fila inicial que ocupa
		panelZones.add(lblZonesExtTables, gbc_MiConstraint);
		
		JLabel lblZonesDelivery = new JLabel("Number of zones 'Delivery':");
		gbc_MiConstraint.gridy = 3;		 //fila inicial que ocupa
		panelZones.add(lblZonesDelivery, gbc_MiConstraint);

		tfZonesBar = new JTextField(arrayConfig[6]);
		gbc_MiConstraint.gridx = 1;		 //columna inicial que ocupa
		gbc_MiConstraint.gridy = 0;		 //fila inicial que ocupa
		tfZonesBar.setColumns(5);
		panelZones.add(tfZonesBar, gbc_MiConstraint);
		
		tfZonesIntTables = new JTextField(arrayConfig[7]);
		gbc_MiConstraint.gridy = 1;		 //fila inicial que ocupa
		tfZonesIntTables.setColumns(5);
		panelZones.add(tfZonesIntTables, gbc_MiConstraint);
		
		tfZonesExtTables = new JTextField(arrayConfig[8]);
		gbc_MiConstraint.gridy = 2;		 //fila inicial que ocupa
		panelZones.add(tfZonesExtTables, gbc_MiConstraint);
		tfZonesExtTables.setColumns(5);
		
		tfZonesDelivery = new JTextField(arrayConfig[9]);
		gbc_MiConstraint.gridy = 3;		 //fila inicial que ocupa
		panelZones.add(tfZonesDelivery, gbc_MiConstraint);
		tfZonesDelivery.setColumns(5);
				
		
		JPanel panelLog = new JPanel();
		tabbedPane.addTab("Log", null, panelLog, "Log");
		JLabel lblRuta = new JLabel("Ruta");
		panelLog.add(lblRuta);
		
		tfRuta = new JTextField();
		panelLog.add(tfRuta);
		tfRuta.setColumns(10);
		add(tabbedPane,BorderLayout.CENTER);
		

		JPanel botones = new JPanel();
		Boton buttonSalirConfig = new Boton(iconBack);
		buttonSalirConfig.addActionListener(gestorBotones);
		buttonSalirConfig.setActionCommand("SalirPanelConfig");
		botones.add(buttonSalirConfig);
		
		Boton buttonApplyConfig = new Boton(iconAceptar);
		buttonApplyConfig.addActionListener(gestorBotones);
		buttonApplyConfig.setActionCommand("ApplyPanelConfig");
		botones.add(buttonApplyConfig);
		
		add(botones, BorderLayout.SOUTH);
		setVisible(false);
	}

	public JTextField getTfDatabaseName() {
		return tfDatabaseName;
	}

	public JTextField getTfDatabasePort() {
		return tfDatabasePort;
	}

	public JTextField getTfDatabaseUser() {
		return tfDatabaseUser;
	}

	public JTextField getTfDdatabaseHost() {
		return tfDdatabaseHost;
	}

	public JTextField getTfDatabaseMotor() {
		return tfDatabaseMotor;
	}

	public JTextField getTfRuta() {
		return tfRuta;
	}

	public JTextField getTfDatabasePass() {
		return tfDatabasePass;
	}

	public JTextField getTfZonesBar() {
		return tfZonesBar;
	}

	public JTextField getTfZonesIntTables() {
		return tfZonesIntTables;
	}

	public JTextField getTfZonesExtTables() {
		return tfZonesExtTables;
	}

	public JTextField getTfZonesDelivery() {
		return tfZonesDelivery;
	}

	public JTextField getTfCompanyName() {
		return tfCompanyName;
	}

	public JTextField getTfCompanyAddress() {
		return tfCompanyAddress;
	}

	public JTextField getTfCompanyTaxes() {
		return tfCompanyTaxes;
	}

	public JTextField getTfCompanyDiscount() {
		return tfCompanyDiscount;
	}

	public XmlDoc getMyXmlDoc() {
		return myXmlDoc;
	}

	public void setTfDatabaseName(JTextField tfDatabaseName) {
		this.tfDatabaseName = tfDatabaseName;
	}

	public void setTfDatabasePort(JTextField tfDatabasePort) {
		this.tfDatabasePort = tfDatabasePort;
	}

	public void setTfDatabaseUser(JTextField tfDatabaseUser) {
		this.tfDatabaseUser = tfDatabaseUser;
	}

	public void setTfDdatabaseHost(JTextField tfDdatabaseHost) {
		this.tfDdatabaseHost = tfDdatabaseHost;
	}

	public void setTfDatabaseMotor(JTextField tfDatabaseMotor) {
		this.tfDatabaseMotor = tfDatabaseMotor;
	}

	public void setTfRuta(JTextField tfRuta) {
		this.tfRuta = tfRuta;
	}

	public void setTfDatabasePass(JTextField tfDatabasePass) {
		this.tfDatabasePass = tfDatabasePass;
	}

	public void setTfZonesBar(JTextField tfZonesBar) {
		this.tfZonesBar = tfZonesBar;
	}

	public void setTfZonesIntTables(JTextField tfZonesIntTables) {
		this.tfZonesIntTables = tfZonesIntTables;
	}

	public void setTfZonesExtTables(JTextField tfZonesExtTables) {
		this.tfZonesExtTables = tfZonesExtTables;
	}

	public void setTfZonesDelivery(JTextField tfZonesDelivery) {
		this.tfZonesDelivery = tfZonesDelivery;
	}

	public void setTfCompanyName(JTextField tfCompanyName) {
		this.tfCompanyName = tfCompanyName;
	}

	public void setTfCompanyAddress(JTextField tfCompanyAddress) {
		this.tfCompanyAddress = tfCompanyAddress;
	}

	public void setTfCompanyTaxes(JTextField tfCompanyTaxes) {
		this.tfCompanyTaxes = tfCompanyTaxes;
	}

	public void setTfCompanyDiscount(JTextField tfCompanyDiscount) {
		this.tfCompanyDiscount = tfCompanyDiscount;
	}

	public void setMyXmlDoc(XmlDoc myXmlDoc) {
		this.myXmlDoc = myXmlDoc;
	}

}
