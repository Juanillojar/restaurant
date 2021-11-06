package Gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.text.DateFormat;
import java.text.DecimalFormat;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class panelticket extends JPanel {

	/**
	 * Create the panel.
	 */
	private GestorBotonesPizzeria gestorBotones = new GestorBotonesPizzeria();
	
	ImageIcon iconBack = new ImageIcon("src/gui/images/Back.png", "Back");
	ImageIcon iconCaja = new ImageIcon("src/gui/images/caja.png", "Caja");
	Font fuenteTitulo = new Font("arial", Font.BOLD, 20);
	Font fuenteDatos = new Font("arial", Font.PLAIN, 12);
	DateFormat formatoFechaHora = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
	DecimalFormat formatoDecimales = new DecimalFormat("###,###.##"); // formatea un double a String truncando según en
	
	public panelticket(Pedido order) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		//gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		//gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		//gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		GridBagConstraints gbc_Miconstraint = new GridBagConstraints();
		LabelPizzeria lTitulopizzeria = new LabelPizzeria(FramePizzeria.InstanceFPizzerie.myPizzerie.getName() + "  "
				+ FramePizzeria.InstanceFPizzerie.myPizzerie.getAddress(), fuenteTitulo, "CENTER");
		gbc_Miconstraint.gridwidth = 3;
		gbc_Miconstraint.insets = new Insets(0, 0, 5, 0);
		gbc_Miconstraint.gridx = 0;
		gbc_Miconstraint.gridy = 0;
		add(lTitulopizzeria, gbc_Miconstraint);
		
		LabelPizzeria lTituloFecha = new LabelPizzeria("Fecha: ", fuenteTitulo, "RIGHT");
		gbc_Miconstraint.gridwidth = 1;
		gbc_Miconstraint.anchor = GridBagConstraints.EAST;
		gbc_Miconstraint.insets = new Insets(0, 0, 5, 5);
		gbc_Miconstraint.gridx = 1;
		gbc_Miconstraint.gridy = 1;
		add(lTituloFecha, gbc_Miconstraint);
		
		LabelPizzeria lFecha = new LabelPizzeria(formatoFechaHora.format(order.getDate()), fuenteDatos);
		gbc_Miconstraint.anchor = GridBagConstraints.WEST;
		gbc_Miconstraint.insets = new Insets(0, 0, 5, 5);
		gbc_Miconstraint.gridx = 2;
		gbc_Miconstraint.gridy = 1;
		add(lFecha, gbc_Miconstraint);
		
		LabelPizzeria lTituloProductos = new LabelPizzeria("Producto:", fuenteTitulo, "RIGHT");
		gbc_Miconstraint.anchor = GridBagConstraints.EAST;
		gbc_Miconstraint.insets = new Insets(0, 0, 5, 5);
		gbc_Miconstraint.gridx = 0;
		gbc_Miconstraint.gridy = 2;
		add(lTituloProductos, gbc_Miconstraint);
		
		LabelPizzeria lTituloImporte = new LabelPizzeria("Importe", fuenteTitulo);
		gbc_Miconstraint.anchor = GridBagConstraints.WEST;
		gbc_Miconstraint.insets = new Insets(0, 0, 5, 0);
		gbc_Miconstraint.gridx = 1;
		gbc_Miconstraint.gridy = 2;
		add(lTituloImporte, gbc_Miconstraint);
		int gridy = 2;
		for (ComidaPizzeria producto : order.getOrderFoods()) {
			LabelPizzeria prod = new LabelPizzeria(producto.getDenomination(), fuenteDatos, "RIGHT");
			gbc_Miconstraint.anchor = GridBagConstraints.EAST;
			gbc_Miconstraint.insets = new Insets(0, 0, 5, 5);
			gridy +=1;
			gbc_Miconstraint.gridx = 0;
			gbc_Miconstraint.gridy = gridy;
			add(prod, gbc_Miconstraint);
			LabelPizzeria importe = new LabelPizzeria(" " + producto.getPrice(), fuenteDatos);
			gbc_Miconstraint.anchor = GridBagConstraints.WEST;
			gbc_Miconstraint.insets = new Insets(0, 0, 5, 5);
			gbc_Miconstraint.gridx = 1;
			gbc_Miconstraint.gridy = gridy;
			add(importe, gbc_Miconstraint);
		}
		gridy +=1;
		LabelPizzeria lTituloTotal = new LabelPizzeria("Total: ", fuenteTitulo, "RIGHT");
		gbc_Miconstraint.anchor = GridBagConstraints.EAST;
		gbc_Miconstraint.insets = new Insets(0, 0, 5, 5);
		gbc_Miconstraint.gridx = 0;
		gbc_Miconstraint.gridy = gridy;
		add(lTituloTotal, gbc_Miconstraint);
		
		LabelPizzeria lTotal = new LabelPizzeria(formatoDecimales.format(order.getOrderPrice()) + " €", fuenteTitulo);
		gbc_Miconstraint.anchor = GridBagConstraints.WEST;
		gbc_Miconstraint.insets = new Insets(0, 0, 5, 0);
		gbc_Miconstraint.gridx = 1;
		gbc_Miconstraint.gridy = gridy;
		add(lTotal, gbc_Miconstraint);
		
		gridy +=1;
		LabelPizzeria lTituloPrecioSinIva = new LabelPizzeria("Precio sin IVA: ", fuenteTitulo, "RIGHT");		
		gbc_Miconstraint.anchor = GridBagConstraints.EAST;
		gbc_Miconstraint.insets = new Insets(0, 0, 5, 5);
		gbc_Miconstraint.gridx = 1;
		gbc_Miconstraint.gridy = gridy;
		add(lTituloPrecioSinIva, gbc_Miconstraint);
		
		FramePizzeria.InstanceFPizzerie.getPanelProductos().calculoPrecioPedido(order);
		LabelPizzeria lPrecioSinIva = new LabelPizzeria(formatoDecimales.format(order.getOrderPriceWithoutTaxes()) + " €",	fuenteDatos);
		gbc_Miconstraint.anchor = GridBagConstraints.WEST;
		gbc_Miconstraint.insets = new Insets(0, 0, 5, 5);
		gbc_Miconstraint.gridx = 2;
		gbc_Miconstraint.gridy = gridy;
		add(lPrecioSinIva, gbc_Miconstraint);
		
		gridy +=1;
		LabelPizzeria lTituloDescuento = new LabelPizzeria("Descuento: ", fuenteTitulo, "RIGHT");		
		gbc_Miconstraint.anchor = GridBagConstraints.EAST;
		gbc_Miconstraint.insets = new Insets(0, 0, 5, 5);
		gbc_Miconstraint.gridx = 1;
		gbc_Miconstraint.gridy = gridy;
		add(lTituloDescuento, gbc_Miconstraint);
		
		LabelPizzeria lDescuento = new LabelPizzeria(formatoDecimales.format(order.getvalorDescuento()) + " €",	fuenteDatos);
		gbc_Miconstraint.anchor = GridBagConstraints.WEST;
		gbc_Miconstraint.insets = new Insets(0, 0, 5, 5);
		gbc_Miconstraint.gridx = 2;
		gbc_Miconstraint.gridy = gridy;
		add(lDescuento, gbc_Miconstraint);
		
		gridy +=1;
		LabelPizzeria lTituloAtendido = new LabelPizzeria("Le atendió: ", fuenteTitulo, "RIGHT");
		gbc_Miconstraint.anchor = GridBagConstraints.EAST;
		gbc_Miconstraint.insets = new Insets(0, 0, 5, 5);
		gbc_Miconstraint.gridx = 1;
		gbc_Miconstraint.gridy = gridy;
		add(lTituloAtendido, gbc_Miconstraint);
		
		String atiende = "";
		if (order.getDestination().getDestinationZone().equals(Zone.Delivery)) { // add delivery man
			atiende = order.getDeliveryMan().getName();
		} else { // add waiter
			atiende = order.getWaiter().getName();
		};
		LabelPizzeria lAtendido = new LabelPizzeria(atiende, fuenteTitulo);
		gbc_Miconstraint.anchor = GridBagConstraints.WEST;
		gbc_Miconstraint.insets = new Insets(0, 5, 5, 0);
		gbc_Miconstraint.gridx = 2;
		gbc_Miconstraint.gridy = gridy;
		add(lAtendido, gbc_Miconstraint);
		
		gridy +=1;
		BotonPizzeria buttonPaidOut = new BotonPizzeria(iconCaja);
		buttonPaidOut.setActionCommand("PaidOut");
		buttonPaidOut.addActionListener(gestorBotones);
		gbc_Miconstraint.insets = new Insets(0, 0, 0, 5);
		gbc_Miconstraint.gridx = 1;
		gbc_Miconstraint.gridy = gridy;
		add(buttonPaidOut, gbc_Miconstraint);
		
		BotonPizzeria buttonSalirTique = new BotonPizzeria(iconBack);
		buttonSalirTique.setActionCommand("SalirPanelTique");
		buttonSalirTique.addActionListener(gestorBotones);
		gbc_Miconstraint.gridx = 2;
		gbc_Miconstraint.gridy = gridy;
		add(buttonSalirTique, gbc_Miconstraint);
	}

}
