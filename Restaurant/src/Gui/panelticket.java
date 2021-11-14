package Gui;

import javax.swing.JPanel;
import javax.swing.plaf.multi.MultiLabelUI;

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

	private GestorBotones gestorBotones = new GestorBotones();
	
	ImageIcon iconBack = new ImageIcon("src/gui/images/Back.png", "Back");
	ImageIcon iconCaja = new ImageIcon("src/gui/images/caja.png", "Caja");
	Font fuenteTitulo = new Font("arial", Font.BOLD, 15);
	Font fuenteDatos = new Font("arial", Font.PLAIN, 12);
	DateFormat formatoFechaHora = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
	DecimalFormat formatoDecimales = new DecimalFormat("###,###.##"); // formatea un double a String truncando según en
	
	public panelticket(Pedido order) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);
		GridBagConstraints gbc_Miconstraint = new GridBagConstraints();
		Label lTitulopizzeria = new Label("<html>" + Frame.InstanceFPizzerie.myPizzerie.getName() +"<br/>" +
				 Frame.InstanceFPizzerie.myPizzerie.getAddress() +"</html>", fuenteTitulo, "CENTER");
		
		gbc_Miconstraint.gridx = 0;
		gbc_Miconstraint.gridy = 0;
		gbc_Miconstraint.gridwidth = 3;
		gbc_Miconstraint.gridheight = 1;
		gbc_Miconstraint.fill = GridBagConstraints.VERTICAL;
		gbc_Miconstraint.insets = new Insets(0, 0, 5, 0);
		add(lTitulopizzeria, gbc_Miconstraint);
		
		Label lTituloFecha = new Label("Fecha: ", fuenteTitulo, "RIGHT");
		gbc_Miconstraint.gridwidth = 1;
		gbc_Miconstraint.anchor = GridBagConstraints.EAST;
		gbc_Miconstraint.insets = new Insets(0, 0, 5, 5);
		gbc_Miconstraint.gridx = 1;
		gbc_Miconstraint.gridy = 1;
		add(lTituloFecha, gbc_Miconstraint);
		
		Label lFecha = new Label(formatoFechaHora.format(order.getDate()), fuenteDatos);
		gbc_Miconstraint.anchor = GridBagConstraints.WEST;
		gbc_Miconstraint.insets = new Insets(0, 0, 5, 5);
		gbc_Miconstraint.gridx = 2;
		gbc_Miconstraint.gridy = 1;
		add(lFecha, gbc_Miconstraint);
		
		Label lTituloProductos = new Label("Producto:", fuenteTitulo, "RIGHT");
		gbc_Miconstraint.anchor = GridBagConstraints.EAST;
		gbc_Miconstraint.insets = new Insets(0, 0, 5, 5);
		gbc_Miconstraint.gridx = 0;
		gbc_Miconstraint.gridy = 2;
		add(lTituloProductos, gbc_Miconstraint);
		
		Label lTituloImporte = new Label("Importe", fuenteTitulo);
		gbc_Miconstraint.anchor = GridBagConstraints.WEST;
		gbc_Miconstraint.insets = new Insets(0, 0, 5, 0);
		gbc_Miconstraint.gridx = 1;
		gbc_Miconstraint.gridy = 2;
		add(lTituloImporte, gbc_Miconstraint);
		int gridy = 2;
		for (Productos producto : order.getOrderFoods()) {
			Label prod = new Label(producto.getDenomination(), fuenteDatos, "RIGHT");
			gbc_Miconstraint.anchor = GridBagConstraints.EAST;
			gbc_Miconstraint.insets = new Insets(0, 0, 5, 5);
			gridy +=1;
			gbc_Miconstraint.gridx = 0;
			gbc_Miconstraint.gridy = gridy;
			add(prod, gbc_Miconstraint);
			Label importe = new Label(" " + producto.getPrice(), fuenteDatos);
			gbc_Miconstraint.anchor = GridBagConstraints.WEST;
			gbc_Miconstraint.insets = new Insets(0, 0, 5, 5);
			gbc_Miconstraint.gridx = 1;
			gbc_Miconstraint.gridy = gridy;
			add(importe, gbc_Miconstraint);
		}
		gridy +=1;
		Label lTituloTotal = new Label("Total: ", fuenteTitulo, "RIGHT");
		gbc_Miconstraint.anchor = GridBagConstraints.EAST;
		gbc_Miconstraint.insets = new Insets(0, 0, 5, 5);
		gbc_Miconstraint.gridx = 0;
		gbc_Miconstraint.gridy = gridy;
		add(lTituloTotal, gbc_Miconstraint);
		
		Label lTotal = new Label(formatoDecimales.format(order.getOrderPrice()) + " €", fuenteTitulo);
		gbc_Miconstraint.anchor = GridBagConstraints.WEST;
		gbc_Miconstraint.insets = new Insets(0, 0, 5, 0);
		gbc_Miconstraint.gridx = 1;
		gbc_Miconstraint.gridy = gridy;
		add(lTotal, gbc_Miconstraint);
		
		gridy +=1;
		Label lTituloPrecioSinIva = new Label("Precio sin IVA: ", fuenteTitulo, "RIGHT");		
		gbc_Miconstraint.anchor = GridBagConstraints.EAST;
		gbc_Miconstraint.insets = new Insets(0, 0, 5, 5);
		gbc_Miconstraint.gridx = 1;
		gbc_Miconstraint.gridy = gridy;
		add(lTituloPrecioSinIva, gbc_Miconstraint);
		
		Frame.InstanceFPizzerie.getPanelProductos().calculoPrecioPedido(order);
		Label lPrecioSinIva = new Label(formatoDecimales.format(order.getOrderPriceWithoutTaxes()) + " €",	fuenteDatos);
		gbc_Miconstraint.anchor = GridBagConstraints.WEST;
		gbc_Miconstraint.insets = new Insets(0, 0, 5, 5);
		gbc_Miconstraint.gridx = 2;
		gbc_Miconstraint.gridy = gridy;
		add(lPrecioSinIva, gbc_Miconstraint);
		
		gridy +=1;
		Label lTituloDescuento = new Label("Descuento: ", fuenteTitulo, "RIGHT");		
		gbc_Miconstraint.anchor = GridBagConstraints.EAST;
		gbc_Miconstraint.insets = new Insets(0, 0, 5, 5);
		gbc_Miconstraint.gridx = 1;
		gbc_Miconstraint.gridy = gridy;
		add(lTituloDescuento, gbc_Miconstraint);
		
		Label lDescuento = new Label(formatoDecimales.format(order.getvalorDescuento()) + " €",	fuenteDatos);
		gbc_Miconstraint.anchor = GridBagConstraints.WEST;
		gbc_Miconstraint.insets = new Insets(0, 0, 5, 5);
		gbc_Miconstraint.gridx = 2;
		gbc_Miconstraint.gridy = gridy;
		add(lDescuento, gbc_Miconstraint);
		
		gridy +=1;
		Label lTituloAtendido = new Label("Le atendió: ", fuenteTitulo, "RIGHT");
		gbc_Miconstraint.anchor = GridBagConstraints.EAST;
		gbc_Miconstraint.insets = new Insets(0, 0, 5, 5);
		gbc_Miconstraint.gridx = 1;
		gbc_Miconstraint.gridy = gridy;
		add(lTituloAtendido, gbc_Miconstraint);
		Label lAtendido = new Label(order.getTrabajador().getName(), fuenteTitulo);
		gbc_Miconstraint.anchor = GridBagConstraints.WEST;
		gbc_Miconstraint.insets = new Insets(0, 5, 5, 0);
		gbc_Miconstraint.gridx = 2;
		gbc_Miconstraint.gridy = gridy;
		add(lAtendido, gbc_Miconstraint);
		
		gridy +=1;
		Boton buttonPaidOut = new Boton(iconCaja);
		buttonPaidOut.setActionCommand("PaidOut");
		buttonPaidOut.addActionListener(gestorBotones);
		gbc_Miconstraint.insets = new Insets(0, 0, 0, 5);
		gbc_Miconstraint.gridx = 1;
		gbc_Miconstraint.gridy = gridy;
		add(buttonPaidOut, gbc_Miconstraint);
		
		Boton buttonSalirTique = new Boton(iconBack);
		buttonSalirTique.setActionCommand("SalirPanelTique");
		buttonSalirTique.addActionListener(gestorBotones);
		gbc_Miconstraint.gridx = 2;
		gbc_Miconstraint.gridy = gridy;
		add(buttonSalirTique, gbc_Miconstraint);
	}

}
