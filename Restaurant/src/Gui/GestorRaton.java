package Gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GestorRaton implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		//si el botón es del panel productos y el producto asociado al botón tiene descuento crea tooltip
		if(e.getComponent() != null && e.getComponent().getClass() == Boton.class) {
			if(((Boton)e.getSource()).getProducto().isLowPrice()) {
				((Boton)e.getSource()).setToolTipText("Producto con descuento.");
			}
		}
		//Si el botón es del panel de zonas de barra, mesas o repartos y tiene un pedido en curso crea tooltip
		if(e.getComponent() != null && e.getComponent().getClass() == BotonRestauranteMesas.class) {
			if(((BotonRestauranteMesas)e.getSource()).getPedidoBoton() != null) {
				((BotonRestauranteMesas)e.getSource()).setToolTipText("Existe un pedido en curso");
			}else
				((BotonRestauranteMesas)e.getSource()).setToolTipText(null);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
