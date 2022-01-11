package Gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.text.DateFormat;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.ImageIcon;


/**
 * @author Juan José Cárdenas
 * The ticket panel is generated with two sub-panels: one with the data of the ticket to be printed
 * and another with the action buttons.
 * The Caja button accesses the panel for calculation of change defined in the class "Panel"
 * Se genera el panel del ticket con dos subpaneles: uno con los datos del tiquet a imprimir
 * y otro con los botones de las acciones.
 * El botón Caja accede al panel para calculo de cambio y cobro definido en la clase Panel
 */
public class panelticket extends JPanel implements Printable{

	private GestorBotones gestorBotones = new GestorBotones();
	ImageIcon iconCaja = new ImageIcon("src/gui/images/caja.png", "Caja");
	ImageIcon iconImprimir = new ImageIcon("src/gui/images/imprimir.png", "Imprimir");
	ImageIcon iconBack = new ImageIcon("src/gui/images/Back.png", "Back");

	DateFormat formatoFechaHora = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
	DecimalFormat formatoDecimales = new DecimalFormat("###,###.##"); // formatea un double a String truncando según en
	Font fuenteDatos = new Font("arial", Font.PLAIN, 12);
	Font fuenteTitulo = new Font("arial", Font.BOLD, 15);
	JPanel panelDatos;
	JPanel panelBotones;
	
	public panelticket(Pedido order) {
		setLayout(new BorderLayout());
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		panelDatos = new JPanel(gridBagLayout);
		GridBagConstraints gbc_Miconstraint = new GridBagConstraints();
		Label lTitulopizzeria = new Label("<html>" + Frame.InstanceFRestaurant.myRestaurant.getName() +"<br/>" +
				 Frame.InstanceFRestaurant.myRestaurant.getAddress() +"</html>", fuenteTitulo, "CENTER");
		
		gbc_Miconstraint.gridx = 0;
		gbc_Miconstraint.gridy = 0;
		gbc_Miconstraint.gridwidth = 3;
		gbc_Miconstraint.gridheight = 1;
		gbc_Miconstraint.fill = GridBagConstraints.VERTICAL;
		gbc_Miconstraint.insets = new Insets(0, 0, 5, 0);
		panelDatos.add(lTitulopizzeria, gbc_Miconstraint);
		
		Label lTituloFecha = new Label("Fecha: ", fuenteTitulo, "RIGHT");
		gbc_Miconstraint.gridwidth = 1;
		gbc_Miconstraint.anchor = GridBagConstraints.EAST;
		gbc_Miconstraint.insets = new Insets(0, 0, 5, 5);
		gbc_Miconstraint.gridx = 1;
		gbc_Miconstraint.gridy = 1;
		panelDatos.add(lTituloFecha, gbc_Miconstraint);
		
		Label lFecha = new Label(formatoFechaHora.format(order.getDate()), fuenteDatos);
		gbc_Miconstraint.anchor = GridBagConstraints.WEST;
		gbc_Miconstraint.insets = new Insets(0, 0, 5, 5);
		gbc_Miconstraint.gridx = 2;
		gbc_Miconstraint.gridy = 1;
		panelDatos.add(lFecha, gbc_Miconstraint);
		
		Label lTituloProductos = new Label("Producto:", fuenteTitulo, "RIGHT");
		gbc_Miconstraint.anchor = GridBagConstraints.EAST;
		gbc_Miconstraint.insets = new Insets(0, 0, 5, 5);
		gbc_Miconstraint.gridx = 0;
		gbc_Miconstraint.gridy = 2;
		panelDatos.add(lTituloProductos, gbc_Miconstraint);
		
		Label lTituloImporte = new Label("Importe", fuenteTitulo);
		gbc_Miconstraint.anchor = GridBagConstraints.WEST;
		gbc_Miconstraint.insets = new Insets(0, 0, 5, 0);
		gbc_Miconstraint.gridx = 1;
		gbc_Miconstraint.gridy = 2;
		panelDatos.add(lTituloImporte, gbc_Miconstraint);
		int gridy = 2;
		for (Productos producto : order.getOrderFoods()) {
			Label prod = new Label(producto.getDenomination(), fuenteDatos, "RIGHT");
			gbc_Miconstraint.anchor = GridBagConstraints.EAST;
			gbc_Miconstraint.insets = new Insets(0, 0, 5, 5);
			gridy +=1;
			gbc_Miconstraint.gridx = 0;
			gbc_Miconstraint.gridy = gridy;
			panelDatos.add(prod, gbc_Miconstraint);
			Label importe = new Label(" " + producto.getPrice() + " €", fuenteDatos);
			gbc_Miconstraint.anchor = GridBagConstraints.WEST;
			gbc_Miconstraint.insets = new Insets(0, 0, 5, 5);
			gbc_Miconstraint.gridx = 1;
			gbc_Miconstraint.gridy = gridy;
			panelDatos.add(importe, gbc_Miconstraint);
		}
		gridy +=1;
		Label lTituloTotal = new Label("Total: ", fuenteTitulo, "RIGHT");
		gbc_Miconstraint.anchor = GridBagConstraints.EAST;
		gbc_Miconstraint.insets = new Insets(0, 0, 5, 5);
		gbc_Miconstraint.gridx = 0;
		gbc_Miconstraint.gridy = gridy;
		panelDatos.add(lTituloTotal, gbc_Miconstraint);
		
		Label lTotal = new Label(formatoDecimales.format(order.getOrderPrice()) + " €", fuenteTitulo);
		gbc_Miconstraint.anchor = GridBagConstraints.WEST;
		gbc_Miconstraint.insets = new Insets(0, 0, 5, 0);
		gbc_Miconstraint.gridx = 1;
		gbc_Miconstraint.gridy = gridy;
		panelDatos.add(lTotal, gbc_Miconstraint);
		
		gridy +=1;
		Label lTituloPrecioSinIva = new Label("Precio sin IVA: ", fuenteTitulo, "RIGHT");		
		gbc_Miconstraint.anchor = GridBagConstraints.EAST;
		gbc_Miconstraint.insets = new Insets(0, 0, 5, 5);
		gbc_Miconstraint.gridx = 1;
		gbc_Miconstraint.gridy = gridy;
		panelDatos.add(lTituloPrecioSinIva, gbc_Miconstraint);
		
		Frame.InstanceFRestaurant.getPanelProductos().calculoPrecioPedido(order);
		Label lPrecioSinIva = new Label(formatoDecimales.format(order.getOrderPriceWithoutTaxes()) + " €",	fuenteDatos);
		gbc_Miconstraint.anchor = GridBagConstraints.WEST;
		gbc_Miconstraint.insets = new Insets(0, 0, 5, 5);
		gbc_Miconstraint.gridx = 2;
		gbc_Miconstraint.gridy = gridy;
		panelDatos.add(lPrecioSinIva, gbc_Miconstraint);
		
		gridy +=1;
		Label lTituloDescuento = new Label("Descuento: ", fuenteTitulo, "RIGHT");		
		gbc_Miconstraint.anchor = GridBagConstraints.EAST;
		gbc_Miconstraint.insets = new Insets(0, 0, 5, 5);
		gbc_Miconstraint.gridx = 1;
		gbc_Miconstraint.gridy = gridy;
		panelDatos.add(lTituloDescuento, gbc_Miconstraint);
		
		Label lDescuento = new Label(formatoDecimales.format(order.getvalorDescuento()) + " €",	fuenteDatos);
		gbc_Miconstraint.anchor = GridBagConstraints.WEST;
		gbc_Miconstraint.insets = new Insets(0, 0, 5, 5);
		gbc_Miconstraint.gridx = 2;
		gbc_Miconstraint.gridy = gridy;
		panelDatos.add(lDescuento, gbc_Miconstraint);
		
		gridy +=1;
		Label lTituloAtendido = new Label("Le atendió: ", fuenteTitulo, "RIGHT");
		gbc_Miconstraint.anchor = GridBagConstraints.EAST;
		gbc_Miconstraint.insets = new Insets(0, 0, 5, 5);
		gbc_Miconstraint.gridx = 1;
		gbc_Miconstraint.gridy = gridy;
		panelDatos.add(lTituloAtendido, gbc_Miconstraint);
		Label lAtendido = new Label(order.getTrabajador().getName(), fuenteTitulo);
		gbc_Miconstraint.anchor = GridBagConstraints.WEST;
		gbc_Miconstraint.insets = new Insets(0, 5, 5, 0);
		gbc_Miconstraint.gridx = 2;
		gbc_Miconstraint.gridy = gridy;
		panelDatos.add(lAtendido, gbc_Miconstraint);
		
		panelBotones = new JPanel(gridBagLayout);
		Boton buttonPaidOut = new Boton(iconCaja);
		buttonPaidOut.setActionCommand("PaidOut");
		buttonPaidOut.addActionListener(gestorBotones);
		gbc_Miconstraint.insets = new Insets(0, 0, 0, 5);
		gbc_Miconstraint.gridx = 0;
		gbc_Miconstraint.gridy = 0;
		gbc_Miconstraint.gridwidth = 1;
		gbc_Miconstraint.gridheight = 1;
		panelBotones.add(buttonPaidOut, gbc_Miconstraint);
		
		Boton buttonImprimir = new Boton(iconImprimir);
		buttonImprimir.setActionCommand("Imprimir");
		buttonImprimir.addActionListener(gestorBotones);
		gbc_Miconstraint.insets = new Insets(0, 0, 0, 5);
		gbc_Miconstraint.gridx = 1;
		panelBotones.add(buttonImprimir, gbc_Miconstraint);
		
		Boton buttonSalirTique = new Boton(iconBack);
		buttonSalirTique.setActionCommand("SalirPanelTique");
		buttonSalirTique.addActionListener(gestorBotones);
		gbc_Miconstraint.gridx = 2;
		panelBotones.add(buttonSalirTique, gbc_Miconstraint);
		
		add(panelDatos,BorderLayout.NORTH);
		add(panelBotones, BorderLayout.CENTER);
	}

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		// TODO Auto-generated method stub
		if(pageIndex >0) {  //sólo queremos que se imprima una página
			return NO_SUCH_PAGE;
		}
		Graphics2D g2d = (Graphics2D)graphics;
		g2d.translate(pageFormat.getImageableX() + 5, pageFormat.getImageableY() + 5); //tabula 5 pixeles
		g2d.scale(1.0, 1.0);
		panelDatos.printAll(graphics);   //definimos que se va a imprimir
		return PAGE_EXISTS;
	}
	
	public void imprimirTicket() {
		try {
			PrinterJob pj = PrinterJob.getPrinterJob();
			pj.setPrintable(this);
			boolean top = pj.printDialog();
			if(top) {
				pj.print();
			}
		}catch(PrinterException ex){
			JOptionPane.showMessageDialog(getComponentPopupMenu(), ex, "Error",JOptionPane.INFORMATION_MESSAGE);
			Frame.log.Escritura("Print" + ex.toString());
		}
	}
}
