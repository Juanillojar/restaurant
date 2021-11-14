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
	public static Frame InstanceFPizzerie = null;
	Restaurant myPizzerie;
	Panel panelPrincipal;
	PanelValida panelValida;
	Panel panelMesas;
	Panel panelProductos;
	//Panel panelTique;
	panelticket panelticket;
	Panel panelCobro;
	Panel panelReport;
	Panel panelProductsReport;
	Panel panelOrderReport;
	
	Trabajador trabajadorValidado  = new Trabajador(); //especifica el trabajador que est� validado con su clave
	
	Font fuenteTitulo = new Font("arial",Font.BOLD,20);
	
	public Frame(Restaurant myPizzerie) {
		InstanceFPizzerie = this; // instancia del JFrame Singleton
		this.myPizzerie = myPizzerie;
		setTitle("Mi pizzeria");
		Toolkit mipantalla = Toolkit.getDefaultToolkit(); // almacena nuestro sistema nativo de ventanas
		Dimension tamanoPantalla = mipantalla.getScreenSize();
		int width = tamanoPantalla.width;
		int height = tamanoPantalla.height;
		setBounds(width / 6, height / 6, width * 2 / 3, height * 2 / 3);// le doy al frame la posic�n centrada y el																		// tama�o de 2/3 de la pantalla
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image miIcono = mipantalla.getImage("src/images/icono.png");
		setIconImage(miIcono); // icono del JFrame
		setLayout(new BorderLayout());
		Label lTitle = new Label(Frame.InstanceFPizzerie.myPizzerie.getName(), fuenteTitulo);
		add(lTitle, BorderLayout.NORTH);
		//LabelPizzeria lOfertas = new LabelPizzeria("Nuestras ofertas", fuenteTitulo);
		panelPrincipal = new Panel();
		add(panelPrincipal, BorderLayout.CENTER);
		setVisible(true);	
	}

	public void ActivaPanel(Panel panel) {
		add(panel, BorderLayout.CENTER);
		setVisible(true);
	}
	public void ActivaPanel(ScrollPane panel) {
		add(panel, BorderLayout.CENTER);
		setVisible(true);
	}
	public void ActivaPanel(panelticket panel) {
		add(panel, BorderLayout.CENTER);
		setVisible(true);
	}
	public void ActivaPanel(JPanel panel) {
		add(panel, BorderLayout.CENTER);
		setVisible(true);
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
/*
	public Panel getPanelTique() {
		return panelTique;
	}

	public void setPanelTique(Panel panelTique) {
		this.panelTique = panelTique;
	}
*/
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
	
}