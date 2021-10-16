package Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class FramePizzeria extends JFrame {
	public static FramePizzeria InstanceFPizzerie = null;
	Pizzeria myPizzerie;
	PanelPizzeria panelPrincipal;
	PanelPizzeria panelMesas;
	PanelPizzeria panelProductos;
	PanelPizzeria panelTique;
	PanelPizzeria panelReport;
	PanelPizzeria panelProductsReport;
	//JScrollPane panelScrollProductosReport;
	PanelPizzeria panelOrderReport;
	Font fuenteTitulo = new Font("arial",Font.BOLD,20);
	
	public FramePizzeria(Pizzeria myPizzerie) {
		InstanceFPizzerie = this; // instancia del JFrame Singleton
		this.myPizzerie = myPizzerie;
		setTitle("Mi pizzeria");
		Toolkit mipantalla = Toolkit.getDefaultToolkit(); // almacena nuestro sistema nativo de ventanas
		Dimension tamanoPantalla = mipantalla.getScreenSize();
		int width = tamanoPantalla.width;
		int height = tamanoPantalla.height;
		setBounds(width / 6, height / 6, width * 2 / 3, height * 2 / 3);// le doy al frame la posicón centrada y el																		// tamaño de 2/3 de la pantalla
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image miIcono = mipantalla.getImage("src/images/icono.png");
		setIconImage(miIcono); // icono del JFrame
		setLayout(new BorderLayout());
		LabelPizzeria lTitle = new LabelPizzeria(FramePizzeria.InstanceFPizzerie.myPizzerie.getName(), fuenteTitulo);
		add(lTitle, BorderLayout.NORTH);
		//LabelPizzeria lOfertas = new LabelPizzeria("Nuestras ofertas", fuenteTitulo);
		panelPrincipal = new PanelPizzeria();
		add(panelPrincipal, BorderLayout.CENTER);
		setVisible(true);	
	}

	public void ActivaPanel(PanelPizzeria panel) {
		add(panel, BorderLayout.CENTER);
		setVisible(true);
	}

	public PanelPizzeria getPanelPrincipal() {
		return panelPrincipal;
	}

	public PanelPizzeria getPanelMesas() {
		return panelMesas;
	}

	public void setPanelPrincipal(PanelPizzeria panelPrincipal) {
		this.panelPrincipal = panelPrincipal;
	}

	public void setPanelMesas(PanelPizzeria panelOperation) {
		this.panelMesas = panelOperation;
	}

	public PanelPizzeria getPanelProductos() {
		return panelProductos;
	}

	public void setPanelProductos(PanelPizzeria panelProductos) {
		this.panelProductos = panelProductos;
	}

	public PanelPizzeria getPanelTique() {
		return panelTique;
	}

	public void setPanelTique(PanelPizzeria panelTique) {
		this.panelTique = panelTique;
	}

	public PanelPizzeria getPanelReport() {
		return panelReport;
	}

	public void setPanelReport(PanelPizzeria panelReport) {
		this.panelReport = panelReport;
	}

	public PanelPizzeria getPanelProductsReport() {
		return panelProductsReport;
	}

	public void setPanelProductsReport(PanelPizzeria panelProductsReport) {
		this.panelProductsReport = panelProductsReport;
	}

}
