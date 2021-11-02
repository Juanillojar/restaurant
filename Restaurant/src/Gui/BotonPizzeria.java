package Gui;

import java.awt.Color;
import java.awt.Image;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class BotonPizzeria extends JButton{
	private ComidaPizzeria producto;  // se usa para los botones de JPanel productos

	//constructor para botones del JPanel principal
	public BotonPizzeria(ImageIcon img) {
		setIcon(img);
		setVerticalTextPosition(SwingConstants.TOP);
		setHorizontalTextPosition(SwingConstants.CENTER);
		//setIconTextGap(orden);
		setSize(50, 100);
		setText(img.getDescription());
	}
	
	//constructor para botones del JPanel Productos
	public BotonPizzeria(ComidaPizzeria Cproducto) {
		producto = Cproducto;
	//	setIconTextGap(orden);
		setText(producto.getDenomination() + " " + producto.getPrice() + "€");
		if(producto.isLowPrice()) {
			setBackground(Color.CYAN);
		}
		setActionCommand("AddToOrder");
		System.out.print("Nuevo boton producto");
	}
	
	//getters and setters
	public ComidaPizzeria getProducto() {
		return producto;
	}
	public void setProducto(ComidaPizzeria producto) {
		this.producto = producto;
	}
}


class BotonPizzeriaMesas extends BotonPizzeria{
	private Pedido pedidoBoton;  //guarda un pedido antes de ser cobrado y añadido a la lista de pedidos
	private DestinoPedido destinoBoton; // guarda el destino del botón aún sin crear el pedido
	   
	//usado para el constructor botones zonaS barra, repartoS y mesas
	public BotonPizzeriaMesas(ImageIcon img, int orden,  Zone zone) {
		super(img);
		setBackground(Color.lightGray);
		setVerticalTextPosition(SwingConstants.TOP);
		setHorizontalTextPosition(SwingConstants.CENTER);
		setText(zone.name() + " " + ((Integer)orden).toString());
		setActionCommand("OpenProductsPanel");
		destinoBoton = new DestinoPedido(zone.name() + " " + ((Integer)orden).toString(), zone);
		System.out.print("Nuevo boton mesa");
	}
	
	//constructor el botón back que devuelve el pedido completo 
	public BotonPizzeriaMesas(ImageIcon img, int orden,  Pedido pedido) {
		super(img);
		setBackground(Color.lightGray);
		setVerticalTextPosition(SwingConstants.TOP);
		setHorizontalTextPosition(SwingConstants.CENTER);
		setText("Back");
		setActionCommand("OpenProductsPanel");
		//asigna el pedido al botón back 
		pedidoBoton = pedido;
	}
	
	//getters and setters
	public Pedido getPedidoBoton() {
		return pedidoBoton;
	}

	public void setPedidoBoton(Pedido pedidoEnCurso) {
		this.pedidoBoton = pedidoEnCurso;
	}

	public DestinoPedido getDestinoBoton() {
		return destinoBoton;
	}

	public void setDestinoBoton(DestinoPedido destinoEnCurso) {
		this.destinoBoton = destinoEnCurso;
	}
	
}