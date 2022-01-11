package Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Frame extends JFrame {
	public static Frame InstanceFRestaurant = null;
	Restaurant myRestaurant;
	Panel panelPrincipal;
	PanelValida panelValida;
	Panel panelMesas;
	Panel panelProductos;
	panelticket panelticket;
	Panel panelCobro;
	Panel panelReport;
	Panel panelReportsData;		//used to show products, orders or workers report if there are connection to database
	Panel panelProductsReport;	//used to show products report is there is no connection to database  
	Panel panelOrderReport;		//used to show orders report is there is no connection to database
	Panel panelWorkersReport;	//used to show workers report is there is no connection to database
	PanelConfig panelConfig;	//used to show and modify config.xml file data
	PanelInsert panelInsert;    //used to insert new data
	
	
	Trabajador trabajadorValidado;//  = new Trabajador(); //especifica el trabajador que está validado con su clave
	public static String path="src/Gui/restaurant.log";
	public static FicheroLog log = new FicheroLog(path);
	Font fuenteTitulo = new Font("arial",Font.BOLD,20);
	
	public Frame(Restaurant myPizzerie) {
		InstanceFRestaurant = this; // instancia del JFrame Singleton
		this.myRestaurant = myPizzerie;
		setTitle("Restaurant");
		Toolkit mipantalla = Toolkit.getDefaultToolkit(); // almacena nuestro sistema nativo de ventanas
		Dimension tamanoPantalla = mipantalla.getScreenSize();
		int width = tamanoPantalla.width;
		int height = tamanoPantalla.height;
		setBounds(width / 6, height / 6, width * 1 / 3, height * 2 / 3);// le doy al frame la posicón centrada y el tamaño																// tamaño de 2/3 de la pantalla
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image miIcono = mipantalla.getImage("src/images/icono.png");
		setIconImage(miIcono); // icono del JFrame
		setLayout(new BorderLayout());
		Label lTitle = new Label(Frame.InstanceFRestaurant.myRestaurant.getName(), fuenteTitulo);
		add(lTitle, BorderLayout.NORTH);
		panelPrincipal = new Panel();
		add(panelPrincipal, BorderLayout.CENTER);
		setVisible(true);	
	}
	
	public void cambiaPanel(JPanel panelMostrar, JPanel panelOcultar) {
		add(panelMostrar, BorderLayout.CENTER);
		panelMostrar.setVisible(true);
		panelOcultar.setVisible(false);
	}
	
	public Panel getPanelPrincipal() {
		return panelPrincipal;
	}

	public Panel getPanelMesas() {
		return panelMesas;
	}

	public void setPanelPrincipal(Panel panelPrincipal) {
		this.panelPrincipal = panelPrincipal;
	}

	public void setPanelMesas(Panel panelOperation) {
		this.panelMesas = panelOperation;
	}

	public Panel getPanelProductos() {
		return panelProductos;
	}

	public void setPanelProductos(Panel panelProductos) {
		this.panelProductos = panelProductos;
	}

	public Panel getPanelReport() {
		return panelReport;
	}

	public void setPanelReport(Panel panelReport) {
		this.panelReport = panelReport;
	}

	public Panel getPanelProductsReport() {
		return panelProductsReport;
	}

	public void setPanelProductsReport(Panel panelProductsReport) {
		this.panelProductsReport = panelProductsReport;
	}

	public Panel getPanelOrderReport() {
		return panelOrderReport;
	}

	public void setPanelOrderReport(Panel panelOrderReport) {
		this.panelOrderReport = panelOrderReport;
	}

	public panelticket getPanelticket() {
		return panelticket;
	}

	public void setPanelticket(panelticket panelticket) {
		this.panelticket = panelticket;
	}

	public PanelValida getPanelValida() {
		return panelValida;
	}

	public void setPanelValida(PanelValida panelValida) {
		this.panelValida = panelValida;
	}

	public Trabajador getTrabajadorValidado() {
		return trabajadorValidado;
	}

	public void setTrabajadorValidado(Trabajador trabajadorValidado) {
		this.trabajadorValidado = trabajadorValidado;
	}

	public Panel getPanelCobro() {
		return panelCobro;
	}

	public void setPanelCobro(Panel panelCobro) {
		this.panelCobro = panelCobro;
	}

	public Panel getPanelWorkersReport() {
		return panelWorkersReport;
	}

	public void setPanelWorkersReport(Panel panelWorkersReport) {
		this.panelWorkersReport = panelWorkersReport;
	}

	public Panel getPanelReportsData() {
		return panelReportsData;
	}

	public void setPanelReportsData(Panel panelReportsData) {
		this.panelReportsData = panelReportsData;
	}

	public PanelConfig getPanelConfig() {
		return panelConfig;
	}

	public void setPanelConfig(PanelConfig panelConfig) {
		this.panelConfig = panelConfig;
	}

	public PanelInsert getPanelInsert() {
		return panelInsert;
	}

	public void setPanelInsert(PanelInsert panelInsert) {
		this.panelInsert = panelInsert;
	}

}